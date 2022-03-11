package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.FiveElementsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 测测五行缺失Controller
 *
 * @author Kent HAN
 * @date 2022-03-08 20:28
 */
@RestController
public class FiveElementsController {
    private final Logger logger = LoggerFactory.getLogger(FiveElementsController.class);

    private static final String FIVE_ELEMENTS = "金水木火土";
    private static final String[] TIANGAN = "甲乙丙丁戊己庚辛壬癸".split("");
    private static final String[] TIANGAN_ELEMENT = "木木火火土土金金水水".split("");
    private static final String[] DIZHI = "子丑寅卯辰巳午未申酉戌亥".split("");
    private static final String[] DIZHI_MAP = "水土木木土火火土金金土水".split("");

    @PostMapping(value = "/api/fiveele")
    ApiResponse fiveElements(@RequestBody FiveElementsRequest request) {
        String birthWords = request.getBirthWords();
        // 验证非法输入
        String[] birthWordsArray = birthWords.split("");
        String eightWords = Arrays.toString(TIANGAN) + Arrays.toString(DIZHI) ;
        for (String birthWord : birthWordsArray) {
            if (birthWordsArray.length != 8 || !eightWords.contains(birthWord)) {
                return ApiResponse.ok("请输入正确的生辰八字");
            }
        }
        logger.info("/api/fiveele get request, birthwords: {}", birthWords);
        // 天干地支与五行的映射，如 {"甲":"木"}
        HashMap<String, String> tianganMap = new HashMap<>();
        HashMap<String, String> dizhiMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            tianganMap.put(TIANGAN[i], TIANGAN_ELEMENT[i]);
        }
        for (int i = 0; i < 12; i++) {
            dizhiMap.put(DIZHI[i], DIZHI_MAP[i]);
        }

        // 遍历八字奇数位得到天干五行
        HashSet<String> tianganElements = new HashSet<>();
        for (int i = 0; i < birthWordsArray.length; i += 2) {
            tianganElements.add(tianganMap.get(birthWordsArray[i]));
        }

        // 遍历八字偶数位得到地支五行
        HashSet<String> dizhiElements = new HashSet<>();
        for (int i = 1; i < birthWordsArray.length; i += 2) {
            dizhiElements.add(dizhiMap.get(birthWordsArray[i]));
        }

        logger.info("天干五行：{}", tianganElements);
        logger.info("地支五行：{}", dizhiElements);

        // 取天干五行和地支五行的并集
        tianganElements.addAll(dizhiElements);
        logger.info("八字五行：{}", tianganElements);

        // 计算五行缺失
        String[] fiveElements = FIVE_ELEMENTS.split("");
        HashSet<String> fiveElementsSet = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            fiveElementsSet.add(fiveElements[i]);
        }
        fiveElementsSet.removeAll(tianganElements);
        logger.info("五行缺少：{}", fiveElementsSet);

        // 五行不缺失
        if (fiveElementsSet.size() == 0) {
            return ApiResponse.ok("恭喜！阁下五行齐全");
        }
        return ApiResponse.ok("五行缺少：" + fiveElementsSet);
    }
}
