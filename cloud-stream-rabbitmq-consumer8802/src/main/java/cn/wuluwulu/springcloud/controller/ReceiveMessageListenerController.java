package cn.wuluwulu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
@Slf4j
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    public String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("消费者1号:----->收到的消息:{} \t ;端口号:{}", message.getPayload(), port);

    }
}
