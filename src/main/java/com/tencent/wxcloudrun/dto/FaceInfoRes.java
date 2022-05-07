package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hrz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceInfoRes implements Serializable {

    @JsonProperty("face_token")
    private String faceToken;

    private Emotion emotion;



    public boolean checkEmotion() {
        if (this.getEmotion() != null) {
            String type = this.getEmotion().getType();
            return type != null && !"".equals(type);
        }
        return false;
    }
}
