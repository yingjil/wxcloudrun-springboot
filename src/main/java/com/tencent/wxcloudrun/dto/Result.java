package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Squall
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @JsonProperty("face_list")
    private List<FaceInfoRes> faceList;

}
