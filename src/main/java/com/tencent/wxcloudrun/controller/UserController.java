package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.FaceDetectResponse;
import com.tencent.wxcloudrun.dto.FaceInfoRes;
import com.tencent.wxcloudrun.dto.UserRequest;
import com.tencent.wxcloudrun.model.FaceInfo;
import com.tencent.wxcloudrun.model.Userinfo;
import com.tencent.wxcloudrun.service.FaceDetectService;
import com.tencent.wxcloudrun.service.FaceInfoService;
import com.tencent.wxcloudrun.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * counter控制器
 */
@RestController
public class UserController {

    @Resource
    private FaceDetectService faceDetectService;
    @Resource
    private FaceInfoService faceInfoService;

    final UserinfoService userinfoService;
    final Logger logger;


    public UserController(@Autowired UserinfoService userinfoService) {
        this.userinfoService = userinfoService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }


    /**
     * 更新、删除用户记录
     *
     * @param request {@link UserRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/user")
    ApiResponse create(@RequestBody UserRequest request) {
        logger.info("/api/user post request, action: {}", request.getAction());

        if (request.getAction().equals("inc")) {

            Userinfo userinfo = new Userinfo();
            userinfo.setId(request.getId());
            userinfo.setSex(request.getSex());
            userinfo.setLocation(request.getLocation());
            userinfoService.upsertUser(userinfo);
            return ApiResponse.ok(userinfo);
        } else if (request.getAction().equals("clear")) {
            userinfoService.clearUser(request.getId());
            return ApiResponse.ok(0);
        } else {
            return ApiResponse.error("参数action错误");
        }
    }


    @PostMapping(value = "/api/emotion")
    ApiResponse emotion(@RequestParam String id, @RequestParam String src) {
        String emotion = faceDetectService.getEmotionBySrc(src);
        if (null != emotion) {
            Userinfo userinfo = new Userinfo();
            userinfo.setId(id);
            userinfo.setEmotion(emotion);
            userinfoService.upsertUserEmotion(userinfo);
            return ApiResponse.ok(emotion);
        } else {
            return ApiResponse.error("您的头像可能没有表情");
        }
    }

    @PostMapping(value = "/api/user/getEmotion")
    ApiResponse getEmotion(MultipartFile file) {
        try {
            FaceDetectResponse faceDetectResponse = faceDetectService.getEmotionByFile(file);
            //写db
            if (null != faceDetectResponse) {
                LocalDateTime now = LocalDateTime.now();
                StringBuilder stringBuilder = new StringBuilder();
                List<FaceInfoRes> faceList = faceDetectResponse.getResult().getFaceList();
                faceList.stream().filter(FaceInfoRes::checkEmotion)
                        .forEach(faceInfoRes -> {
                            stringBuilder.append(",").append(faceInfoRes.getEmotion().getType());
                            FaceInfo faceInfo = FaceInfo.builder()
                                    .faceToken(faceInfoRes.getFaceToken())
                                    .emotion(faceInfoRes.getEmotion().getType())
                                    .createdAt(now)
                                    .updatedAt(now)
                                    .build();
                            faceInfoService.insert(faceInfo);
                        });
                return ApiResponse.ok(stringBuilder.toString().replaceFirst(",", ""));
            } else {
                return ApiResponse.error("您的头像可能没有表情");
            }
        } catch (Exception e) {
            return ApiResponse.error("获取表情失败：" + e.getMessage());
        }
    }


}