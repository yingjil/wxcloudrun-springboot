package com.tencent.wxcloudrun.aspect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.annotation.HttpLogger;
import com.tencent.wxcloudrun.dao.HttpLoggerMapper;
import com.tencent.wxcloudrun.model.HttpLog;
import com.tencent.wxcloudrun.utils.baidu.IPHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xuewq
 * @description http request aspect
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Autowired
    private HttpLoggerMapper httpLoggerMapper;

    @Pointcut("@annotation(com.tencent.wxcloudrun.annotation.HttpLogger)")
    public void controllerAspect() {
    }

    @Before(value = ("controllerAspect()"))
    public void beforeAction(JoinPoint joinPoint) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.setAttribute("beforePointTime", LocalDateTime.now());
            HttpLogger loggerParams = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(HttpLogger.class);
            if (loggerParams.hookRequestBody()) {
                request.setAttribute("requestBodyContent", argsArrayToString(joinPoint.getArgs()));
            }
        } catch (Exception e) {
            logger.error("aop日志注解异常:", e);
        }
    }


    @AfterReturning(pointcut = ("controllerAspect()"), returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        try {
            HttpLog message = initMessage(joinPoint, result);
            /**
             * should be refactored by async method
             */
            httpLoggerMapper.addHttpRequestLog(message);
            logger.info(JSONObject.toJSONString(message));

        } catch (Exception e) {
            logger.error("aop日志注解异常:", e);
        }
    }

    private HttpLog initMessage(JoinPoint joinPoint, Object result) {
        HttpLog message = new HttpLog();
        try {
            HttpLogger loggerParams = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(HttpLogger.class);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            message.setRequestBusinessCategory(loggerParams.businessCategory());
            message.setRequestTransactionId(String.valueOf(Thread.currentThread().getId()));
            message.setLogId(UUID.randomUUID().toString());
            message.setRequestUrl(request.getRequestURL().toString());
            message.setRequestIp(IPHelper.getIpAddress());
            message.setHttpMethod(request.getMethod());
            message.setRequestUserAgent(request.getHeader("user-agent"));
            message.setEncoding(request.getHeader("accept-encoding"));
            message.setAuthorization(request.getHeader("authorization"));
            message.setContentType(request.getHeader("content-type"));
            message.setProxyAuthorization(request.getHeader("proxy-authorization"));
            message.setReferer(request.getHeader("referer"));
            message.setRequestParams(request.getQueryString());
            message.setResponseStatus(String.valueOf(response.getStatus()));
            message.setProcessTime(ChronoUnit.MILLIS.between((LocalDateTime) request.getAttribute("beforePointTime"), LocalDateTime.now()));
            if (loggerParams.hookRequestBody()) {
                message.setRequestBody(String.valueOf(request.getAttribute("requestBodyContent")));
            }
            if (loggerParams.hookResponseBody()) {
                message.setResponseBody(JSONObject.toJSONString(result));
            }

        } catch (Exception ex) {
            logger.error("aop日志初始化模型异常:", ex);
            return null;
        }
        return message;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!(paramsArray[i] instanceof MultipartFile || paramsArray[i] instanceof HttpServletRequest || paramsArray[i] instanceof HttpServletResponse)) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params.append(jsonObj).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

}
