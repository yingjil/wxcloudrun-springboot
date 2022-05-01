package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.util.List;

/**
 * 途牛火车票信息
 */
@Data
public class TuniuTrain {
    private Integer trainId;
    private String trainNum;
    private String departStationName;
    private String destStationName;
    private String departDepartTime;
    private String destArriveTime;
    private String durationStr;
    private List<SeatInfo> prices;

    /**
     * 途牛火车票座位信息
     */
    @Data
    public static class SeatInfo {
        private String seatName;
        private Integer leftNumber;
        private Double price;
    }
}
