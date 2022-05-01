package com.tencent.wxcloudrun.model;


import lombok.Data;

/**
 * 火车票信息数据
 */
@Data
public class TrainInfo {
    private Integer trainId;
    private String trainNum;
    private String departStationName;
    private String destStationName;
    private String departDepartTime;
    private String destArriveTime;
    private String durationStr;
    private Double businessSeatPrice;
    private Integer businessSeatLeft;
    private Double firstSeatPrice;
    private Integer firstSeatLeft;
    private Double secondSeatPrice;
    private Integer secondSeatLeft;
}
