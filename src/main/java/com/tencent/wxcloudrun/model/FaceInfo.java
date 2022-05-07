package com.tencent.wxcloudrun.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Squall
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceInfo implements Serializable {

  /**
   * 主键id
   */
  private Long id;
  /**
   * 人脸图片的唯一标识 （人脸检测face_token有效期为60min）
   */
  private String faceToken;

  /**
   * 情绪
   */
  private String emotion;

  /**
   * 创建时间
   */
  private LocalDateTime createdAt;

  /**
   * 更新时间
   */
  private LocalDateTime updatedAt;


}
