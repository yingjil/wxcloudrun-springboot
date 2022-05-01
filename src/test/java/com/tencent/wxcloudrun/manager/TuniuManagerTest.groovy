package com.tencent.wxcloudrun.manager

import com.alibaba.fastjson.JSON
import com.tencent.wxcloudrun.model.TuniuTrain
import com.tencent.wxcloudrun.utils.RestTemplateUtils
import spock.lang.Specification
import spock.lang.Unroll

class TuniuManagerTest extends Specification {

    @Unroll
    def "getTrainInfoList"() {
        given: "构造上下文环境"
        def restTemplateutils = Mock(RestTemplateUtils)
        def tuniuManager = new TuniuManager(restTemplateutils: restTemplateutils)
        and: "mock行为"
        restTemplateutils.getForObject(*_) >> data
        when: "调用验证方法"
        def resList = tuniuManager.getTrainInfoList("北京", "济南", "2022-05-01")
        then: "验证结果"
        resList.size() == size
        where: "case列表"
        data                                                                                                          || size
        new TuniuManager.TuniuEntity(code: 200, data: new TuniuManager.TuniuData(count: 1, list: [new TuniuTrain()])) || 1
        new TuniuManager.TuniuEntity(code: 100)                                                                       || 0
        new TuniuManager.TuniuEntity(code: 200)                                                                       || 0
        null                                                                                                          || 0
    }

}
