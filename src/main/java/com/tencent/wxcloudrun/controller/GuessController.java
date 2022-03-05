package com.tencent.wxcloudrun.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.dto.GuessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Random;

/**
 * guess控制器
 */
@RestController
public class GuessController {
    final CounterService cntService;
    final Logger logger;

    public GuessController(@Autowired CounterService counterService) {
        this.cntService = counterService;
        this.logger = LoggerFactory.getLogger(GuessController.class);
    }

    /**
     * 生成新数字（0~100），更新到存储
     *
     * @return N/A
     */
    protected void generateNewValue(){
        Random r = new Random();
        Counter counter = new Counter();
        counter.setId(2);
        counter.setCount(r.nextInt(101));
        cntService.upsertCount(counter);
    }

    /**
     * 判断输入参数值与存储值之间的关系，并返回(1)大了,(2)小了,(3)猜对
     *
     * @return API response json
     */
    protected ApiResponse doJudge(Integer v){
        Optional<Counter> curCounter = cntService.getCounter(2);
        if (curCounter.isPresent() == false) {
            generateNewValue();
            curCounter = cntService.getCounter(2);
        }
        if (v == curCounter.get().getCount()) {
            generateNewValue();
            return ApiResponse.ok("correct");
        } else if (v > curCounter.get().getCount()) {
            return ApiResponse.ok("upper");
        } else {
            return ApiResponse.ok("lower");
        }
    }


    /**
     * 猜测数字，返回(1)大了,(2)小了,(3)猜对
     *
     * @return API response json
     */
    @GetMapping(value = "/api/guess")
    ApiResponse get(@RequestParam("val") Integer val) {
        logger.info("/api/guess get request, val: {}", val);

        return doJudge(val);
    }

    /**
     * 猜测数字，返回(1)大了,(2)小了,(3)猜对
     *
     * @param request {@link GuessRequest}
     * @return API response json
     */
    //@HttpLogger(hookRequestBody = true, hookResponseBody = true)
    @PostMapping(value = "/api/guess")
    ApiResponse create(@RequestBody GuessRequest request) {
        logger.info("/api/guess post request, val: {}", request.getVal());

        return doJudge(request.getVal());
    }

    /**
     * 设置年龄值，用于后面的猜数字
     *
     * @param request {@link GuessRequest}
     * @return API response json
     */
    //@HttpLogger(hookRequestBody = true, hookResponseBody = true)
    @PostMapping(value = "/api/set-age")
    ApiResponse setAge(@RequestBody GuessRequest request) {
        logger.info("/api/set-age post request, val: {}", request.getVal());

        Counter counter = new Counter();
        counter.setId(2);
        counter.setCount(request.getVal());
        cntService.upsertCount(counter);

        return ApiResponse.ok("success");
    }
}
