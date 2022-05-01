package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.manager.TuniuManager;
import com.tencent.wxcloudrun.model.TrainInfo;
import com.tencent.wxcloudrun.model.TuniuTrain;
import com.tencent.wxcloudrun.service.TrainService;
import com.tencent.wxcloudrun.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TuniuManager tuniuManager;

    /**
     * 查询火车数据
     *
     * @param from
     * @param to
     * @param date
     * @return
     */
    @Override
    public List<TrainInfo> search(String from, String to, LocalDate date) {
        List<TuniuTrain> trainList = tuniuManager.getTrainInfoList(from, to, DateUtils.format(date, "yyyy-MM-dd"));
        if (CollectionUtils.isEmpty(trainList)) {
            return Collections.emptyList();
        }
        List<TrainInfo> resultList = new ArrayList<>(trainList.size());
        for (TuniuTrain train : trainList) {
            resultList.add(convert(train));
        }
        return resultList;
    }

    /**
     * 将途牛火车数据转化形式
     *
     * @param train
     * @return
     */
    private TrainInfo convert(TuniuTrain train) {
        if (train == null) {
            return null;
        }
        TrainInfo trainInfo = new TrainInfo();
        BeanUtils.copyProperties(train, trainInfo);
        for (TuniuTrain.SeatInfo seatInfo : train.getPrices()) {
            switch (seatInfo.getSeatName()) {
                case "商务座":
                    trainInfo.setBusinessSeatLeft(seatInfo.getLeftNumber());
                    trainInfo.setBusinessSeatPrice(seatInfo.getPrice());
                    break;
                case "一等座":
                    trainInfo.setFirstSeatLeft(seatInfo.getLeftNumber());
                    trainInfo.setFirstSeatPrice(seatInfo.getPrice());
                    break;
                case "二等座":
                    trainInfo.setSecondSeatLeft(seatInfo.getLeftNumber());
                    trainInfo.setSecondSeatPrice(seatInfo.getPrice());
                    break;
                default:
                    break;
            }
        }
        return trainInfo;
    }


}
