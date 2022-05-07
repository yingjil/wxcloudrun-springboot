package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.FaceInfoMapper;
import com.tencent.wxcloudrun.model.FaceInfo;
import com.tencent.wxcloudrun.service.FaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Squall
 */
@Service
public class FaceInfoServiceImpl implements FaceInfoService {

    @Resource
    private FaceInfoMapper faceInfoMapper;

    @Override
    public int insert(FaceInfo faceInfo) {
        return faceInfoMapper.insert(faceInfo);
    }
}
