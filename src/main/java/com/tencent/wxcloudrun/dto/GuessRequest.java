package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class GuessRequest {
    // `val`：`Integer` 类型，猜测值
    private Integer val;
}
