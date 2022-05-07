package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.FaceDetectResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Squall
 */
public interface FaceDetectService {

  /**
   * 上传图片并获取表情
   * @param file 图片文件
   * @return 表情字符串
   */
  FaceDetectResponse getEmotionByFile(MultipartFile file);

  /**
   * 通过URL获取表情
   * @param src URL
   * @return 表情字符串
   */
  String getEmotionBySrc(String src);

}
