package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.HttpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuewq
 * @Description save http request information
 */
@Mapper
public interface HttpLoggerMapper {

    /**
     * add http request information
     *
     * @param requestLog
     * @return
     */
    @Insert({
            "INSERT INTO HttpLogger(logId,requestBusinessCategory,requestTransactionId,requestIp,requestUrl,requestUserAgent,authorization,proxyAuthorization," +
                    "httpMethod,contentType,encoding,referer,createTime,updateTime,requestParams,requestBody,responseStatus,responseBody,processTime)",
            "VALUES(#{requestLog.logId},#{requestLog.requestBusinessCategory},#{requestLog.requestTransactionId},#{requestLog.requestIp},#{requestLog.requestUrl},#{requestLog.requestUserAgent},#{requestLog.authorization},#{requestLog.proxyAuthorization}," +
                    "#{requestLog.httpMethod},#{requestLog.contentType},#{requestLog.encoding},#{requestLog.referer},now(),#{requestLog.updateTime},#{requestLog.requestParams},#{requestLog.requestBody},#{requestLog.responseStatus},#{requestLog.responseBody},#{requestLog.processTime})"
    })
    Integer addHttpRequestLog(@Param("requestLog") HttpLog requestLog);

    /**
     * get latest http request log
     *
     * @return
     */
    @Select("select * from HttpLogger order by createTime desc limit 1 ")
    HttpLog getLatestHttpLog();

}
