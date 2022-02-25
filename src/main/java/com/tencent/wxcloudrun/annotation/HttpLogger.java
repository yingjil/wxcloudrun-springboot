package com.tencent.wxcloudrun.annotation;

import java.lang.annotation.*;

/**
 * @author xuewq
 * @date: 2022/02/24
 * @description: save http request information annotation
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpLogger {
    /**
     * 采集请求参数
     */
    boolean hookRequestBody() default false;

    /**
     * 采集请求结果
     */
    boolean hookResponseBody() default false;

    /**
     * 请求的业务类型
     */
    String businessCategory() default "HttpLogging";

    String transactionId() default "";
}
