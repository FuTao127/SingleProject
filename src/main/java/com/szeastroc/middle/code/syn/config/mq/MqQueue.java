package com.szeastroc.middle.code.syn.config.mq;

/**
 * @Description:
 * @Author: futao
 * @Date: 2021/12/14
 **/
public class MqQueue {
    /**
     * 生产码同步消息队列
     */
    public static final String MIDDLE_CODE_SYNC_QUEUE = "middle_code_sync_queue";
    /**
     * 发货详情信息同步消息队列
     */
    public static final String MIDDLE_SEND_CODE_QUEUE = "middle_send_code_queue";


    /**
     * 生产码同步消息队列key
     */
    public static final String MIDDLE_CODE_SYNC_KEY = "middle_code_sync_key";
    /**
     * 发货详情信息同步消息队列key
     */
    public static final String MIDDLE_SEND_CODE_KEY = "middle_send_code_key";
    /**
     * 基地中间库交换机
     */
    public  static final String OTOC_MQ_MIDDLE_CODE_EXCHANGE = "otoc_mq_middle_code_exchange";
}
