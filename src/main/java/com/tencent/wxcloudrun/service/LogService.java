package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.HttpLog;

/**
 * @author xuewq
 */
public interface LogService {

    /**
     * get latest http request log
     *
     * @return
     */
    HttpLog getLatestHttpLog();
}
