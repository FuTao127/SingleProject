package com.szeastroc.middle.code.syn.mq.sender;

import com.alibaba.fastjson.JSONObject;
import com.szeastroc.middle.code.syn.config.mq.MqQueue;
import com.szeastroc.middle.code.syn.model.SendCodeDetail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:发货详情信息同步发送到商户
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Component
public class SendCodeDetailSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendData(SendCodeDetail sendCodeDetail) {
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(MqQueue.MIDDLE_SEND_CODE_QUEUE, JSONObject.toJSONString(sendCodeDetail));

    }
}
