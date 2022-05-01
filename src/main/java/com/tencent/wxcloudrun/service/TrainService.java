package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.TrainInfo;

import java.time.LocalDate;
import java.util.List;

public interface TrainService {
    /**
     * 查询火车数据
     *
     * @param from 出发城市
     * @param to   到达城市
     * @param date 日期
     * @return
     */
    List<TrainInfo> search(String from, String to, LocalDate date);
}
