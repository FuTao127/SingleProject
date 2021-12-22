package com.szeastroc.middle.code.syn.mq.sender;

import com.alibaba.fastjson.JSONObject;
import com.szeastroc.middle.code.syn.config.mq.MqQueue;
import com.szeastroc.middle.code.syn.model.ProductionCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:生产码同步发送到商户
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Component
public class ProductionCodeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendData(ProductionCode productionCode) {
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(MqQueue.MIDDLE_CODE_SYNC_QUEUE, JSONObject.toJSONString(productionCode));

    }
}
