package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.HttpLoggerMapper;
import com.tencent.wxcloudrun.model.HttpLog;
import com.tencent.wxcloudrun.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuewq
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private HttpLoggerMapper httpLoggerMapper;

    @Override
    public HttpLog getLatestHttpLog() {
        return httpLoggerMapper.getLatestHttpLog();
    }
}
