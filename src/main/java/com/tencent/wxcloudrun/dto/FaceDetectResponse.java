package com.tencent.wxcloudrun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Squall
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceDetectResponse implements Serializable {

    private Result result;

    public boolean checkEmotion() {
        if (this.getResult() != null) {
            List<FaceInfoRes> faceList = this.getResult().getFaceList();
            if (faceList != null && !faceList.isEmpty()) {
                Optional<FaceInfoRes> first = faceList.stream().filter(Objects::nonNull)
                        .filter(FaceInfoRes::checkEmotion).findFirst();
                if (first.isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }
}
