package com.szeastroc.middle.code.syn.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:rabbitmq配置
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Configuration
public class RabbitConfig {
    @Bean
    public Queue middleCodeSyncQueue() {
        return new Queue(MqQueue.MIDDLE_CODE_SYNC_QUEUE,true);
    }

    @Bean
    public Queue middleSendCodeQueue() {
        return new Queue(MqQueue.MIDDLE_SEND_CODE_QUEUE,true);
    }


    /**
     * 直连交换机
     * @return
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MqQueue.OTOC_MQ_MIDDLE_CODE_EXCHANGE);
    }


    /**
     * 声明绑定关系
     * @return
     */

    @Bean
    Binding binding1(@Autowired DirectExchange defaultExchange,
                     @Qualifier("middleCodeSyncQueue") Queue middleCodeSyncQueue) {
        return BindingBuilder.bind(middleCodeSyncQueue).to(defaultExchange).with(MqQueue.MIDDLE_CODE_SYNC_KEY);
    }

    @Bean
    Binding binding2(@Autowired DirectExchange defaultExchange,
                     @Qualifier("middleSendCodeQueue") Queue middleSendCodeQueue) {
        return BindingBuilder.bind(middleSendCodeQueue).to(defaultExchange).with(MqQueue.MIDDLE_SEND_CODE_KEY);
    }
}
