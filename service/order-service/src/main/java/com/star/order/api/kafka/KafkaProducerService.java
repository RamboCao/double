package com.star.order.api.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Caolp
 */
@Service
public class KafkaProducerService implements IKafkaProducerService{
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageSync(String topic, String message) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, message).get(10, TimeUnit.SECONDS);
    }

    public void sendMessageAsync(String topic, String message){
        System.out.println("kafka topicName：" + topic);
        String mString = "msg：" + new Date();
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, mString);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable thr) {
                //发送失败的处理
                System.out.println(topic + " - 生产者 发送消息失败：" + thr.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringObjectSendResult) {
                //成功的处理
                System.out.println(topic + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}
