package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dto.FaceDetectRequest;
import com.tencent.wxcloudrun.dto.FaceDetectResponse;
import com.tencent.wxcloudrun.service.FaceDetectService;
import com.tencent.wxcloudrun.utils.baidu.Base64Util;
import com.tencent.wxcloudrun.utils.baidu.FaceDetect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ruizhi.huang
 */
@Service
@Slf4j
public class FaceDetectServiceImpl implements FaceDetectService {

    @Resource
    private FaceDetect faceDetect;

    @Override
    public FaceDetectResponse getEmotionByFile(MultipartFile file) {
        try {
            String encode = Base64Util.encode(file.getBytes());
            FaceDetectRequest request = FaceDetectRequest.builder()
                    .image(encode)
                    .maxFaceNum(120)
                    .faceField("emotion")
                    .imageType("BASE64")
                    .build();
            return faceDetect.faceDetectNew(request);
        } catch (Exception e) {
            log.error(this.getClass() + "_getEmotion_error,message:{}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String getEmotionBySrc(String src) {
        return faceDetect.faceDetect(src);
    }
}
