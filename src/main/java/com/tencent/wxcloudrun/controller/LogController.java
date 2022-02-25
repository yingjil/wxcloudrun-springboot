package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.HttpLog;
import com.tencent.wxcloudrun.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xuewq
 */
@RestController
@RequestMapping("/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @GetMapping(value = "/operation")
    ApiResponse getHttpRequestOperation() {
        logger.info("/log/operation getHttpRequestOperation");
        HttpLog httpLog = logService.getLatestHttpLog();
        return ApiResponse.ok(httpLog.getProcessTime());
    }
}
