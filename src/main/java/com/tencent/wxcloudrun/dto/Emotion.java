package com.tencent.wxcloudrun.dto;

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
public class Emotion {

    private String type;

    private String probability;

}
