package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Squall
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceDetectRequest implements Serializable {

    /**
     * 图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     */
    @JsonProperty("image_type")
    private String imageType;

    /**
     * 图片信息(总数据大小应小于10M)，图片上传方式根据image_type来判断
     */
    private String image;

    /**
     * 包括age,expression,face_shape,gender,glasses,landmark,landmark150,
     * quality,eye_status,emotion,face_type,mask,spoofing信息
     * 逗号分隔. 默认只返回face_token、人脸框、概率和旋转角度
     */
    @JsonProperty("face_field")
    private String faceField;

    /**
     * 最多处理人脸的数目，默认值为1，根据人脸检测排序类型检测图片中排序第一的人脸（默认为人脸面积最大的人脸），最大值120
     */
    @JsonProperty("max_face_num")
    private Integer maxFaceNum;
}
