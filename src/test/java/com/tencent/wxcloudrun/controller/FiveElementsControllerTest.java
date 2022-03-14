package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.FiveElementsRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiveElementsControllerTest {

    @Test
    public void testFiveElements() {
        FiveElementsRequest req1 = new FiveElementsRequest();
        req1.setBirthWords("");
        ApiResponse res1 = new FiveElementsController().fiveElements(req1);

        FiveElementsRequest req2 = new FiveElementsRequest();
        req1.setBirthWords("foobar");
        ApiResponse res2 = new FiveElementsController().fiveElements(req1);

        FiveElementsRequest req3 = new FiveElementsRequest();
        req1.setBirthWords("甲子乙丑丙寅丁卯");
        ApiResponse res3 = new FiveElementsController().fiveElements(req1);

        FiveElementsRequest req4 = new FiveElementsRequest();
        req1.setBirthWords("甲子乙丑丙寅丁卯戊");
        ApiResponse res4 = new FiveElementsController().fiveElements(req1);

        FiveElementsRequest req5 = new FiveElementsRequest();
        req1.setBirthWords("庚子乙丑丙寅丁卯");
        ApiResponse res5 = new FiveElementsController().fiveElements(req1);

        assertEquals("请输入正确的生辰八字", res1.getData());
        assertEquals("请输入正确的生辰八字", res2.getData());
        assertEquals("五行缺少：[金]", res3.getData());
        assertEquals("请输入正确的生辰八字", res4.getData());
        assertEquals("恭喜！阁下五行齐全", res5.getData());
    }
}