package com.tencent.wxcloudrun.model;

import java.util.Date;

/**
 * @author xuewq
 * @Description http请求信息类
 */
public class HttpLog {
    private String logId;
    private String requestBusinessCategory;
    private String requestTransactionId;
    private String requestIp;
    private String requestUrl;
    private String requestUserAgent;
    private String authorization;
    private String proxyAuthorization;
    private String httpMethod;
    private String contentType;
    private String encoding;
    private String referer;
    private Date createTime;
    private Date updateTime;
    private String requestParams;
    private String requestBody;
    private String responseStatus;
    private String responseBody;
    private long processTime;


    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getRequestBusinessCategory() {
        return requestBusinessCategory;
    }

    public void setRequestBusinessCategory(String requestBusinessCategory) {
        this.requestBusinessCategory = requestBusinessCategory;
    }

    public String getRequestTransactionId() {
        return requestTransactionId;
    }

    public void setRequestTransactionId(String requestTransactionId) {
        this.requestTransactionId = requestTransactionId;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestUserAgent() {
        return requestUserAgent;
    }

    public void setRequestUserAgent(String requestUserAgent) {
        this.requestUserAgent = requestUserAgent;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }


    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getProxyAuthorization() {
        return proxyAuthorization;
    }

    public void setProxyAuthorization(String proxyAuthorization) {
        this.proxyAuthorization = proxyAuthorization;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }
}

