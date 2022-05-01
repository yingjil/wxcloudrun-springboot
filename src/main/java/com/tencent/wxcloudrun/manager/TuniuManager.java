package com.tencent.wxcloudrun.manager;

import com.tencent.wxcloudrun.model.TuniuTrain;
import com.tencent.wxcloudrun.utils.RestTemplateUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TuniuManager {

    @Autowired
    private RestTemplateUtils restTemplateutils;

    private static final String BASE_URL = "http://huoche.tuniu.com/yii.php?r=train/trainTicket/getTickets&primary[departureDate]={date}&primary[departureCityName]={fromCity}&primary[arrivalCityName]={toCity}";

    /**
     * 查询途牛火车票服务
     *
     * @param fromCity
     * @param toCity
     * @param date
     * @return
     */
    public List<TuniuTrain> getTrainInfoList(String fromCity, String toCity, String date) {
        TuniuEntity tuniuEntity = restTemplateutils.getForObject(BASE_URL, TuniuEntity.class, buildRequest(fromCity, toCity, date));
        if (tuniuEntity == null || tuniuEntity.code == null || tuniuEntity.code != 200) {
            return Collections.emptyList();
        }
        if (tuniuEntity.data != null) {
            return tuniuEntity.data.list;
        }
        return Collections.emptyList();
    }

    /**
     * 构建请求参数
     *
     * @param from
     * @param to
     * @param date
     * @return
     */
    private Map<String, String> buildRequest(String from, String to, String date) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("fromCity", from);
        paramMap.put("toCity", to);
        paramMap.put("date", date);
        return paramMap;
    }

    /**
     * 途牛响应结果实体
     */
    @Data
    private static class TuniuEntity {
        Integer code;
        TuniuData data;
    }

    @Data
    private static class TuniuData {
        Integer count;
        List<TuniuTrain> list;
    }


}
