package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.common.StatusType;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.TrainInfo;
import com.tencent.wxcloudrun.service.TrainService;
import com.tencent.wxcloudrun.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TrainController {
    private final Logger logger = LoggerFactory.getLogger(TrainController.class);
    private final TrainService trainTicketService;

    TrainController(@Autowired TrainService trainTicketService) {
        this.trainTicketService = trainTicketService;
    }

    /**
     * 根据出发城市、目的城市以及日期查询火车票
     *
     * @param fromCity
     * @param toCity
     * @param date
     * @return
     */
    @GetMapping(value = "/api/train/ticket")
    ApiResponse getTrainTicket(@RequestParam("from") String fromCity,
                               @RequestParam("to") String toCity,
                               @RequestParam("date") String date) {
        logger.info("/api/train/ticket|from={}|to={}|date={}", fromCity, toCity, date);
        if (!isChineseChar(fromCity) || !isChineseChar(toCity)) {
            return ApiResponse.error(StatusType.ILLEGAL_CITY_NAME);
        }
        try {
            LocalDate localDate = DateUtils.toDate(date);
            List<TrainInfo> ticketInfoList = trainTicketService.search(fromCity, toCity, localDate);
            return ApiResponse.ok(ticketInfoList);
        } catch (Exception e) {
            logger.error("/api/train/ticket|from={}|to={}|date={}|error={}", fromCity, toCity, date, ExceptionUtils.getMessage(e));
            return ApiResponse.error(StatusType.SERVER_ERROR);
        }
    }

    /**
     * 判断是否为中文
     *
     * @param str
     * @return
     */
    private static boolean isChineseChar(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);
            if (!(19968 <= ch && ch < 40869)) {
                return false;
            }
        }
        return true;
    }


}
