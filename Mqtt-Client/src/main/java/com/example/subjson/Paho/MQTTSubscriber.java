package com.example.subjson.Paho;
import com.example.subjson.Service.TestService;
import com.example.subjson.po.TestDataWrapper;
import com.example.subjson.po.test;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class MQTTSubscriber {
    private MqttClient client;
    private TestService testService;
    private Queue<test> messageQueue;
    private static final int MAX_QUEUE_SIZE = 1000;
    public MQTTSubscriber(TestService testService ,@Value("${mqtt.broker}") String broker,
                          @Value("${mqtt.topic}") String topic,
                          @Value("${mqtt.UserName}") String username,
                          @Value("${mqtt.password}") String password,
                          @Value("${mqtt.clientId}") String clientID
                          ) {
        this.testService = testService;
        this.messageQueue = new LinkedList<>();
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            client = new MqttClient(broker, clientID);
            client.connect();
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                }
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    String json = new String(mqttMessage.getPayload());
                    //System.out.println(json);

                    Gson gson = new Gson();
                    TestDataWrapper wrapper = gson.fromJson(json, TestDataWrapper.class);
                    List<test> dataList = wrapper.getDataList();

                    if (dataList != null && !dataList.isEmpty()) {
                        for (test testData : dataList) {
                            System.out.println(testData.getOrdinal());
                            addToMessageQueue(testData);
                            //testService.saveDataFromJson(testData);
                        }
                    }
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });

            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void addToMessageQueue(test testData) {
        //System.out.println(testService.getOrdinalCount());
        if (testService.getOrdinalCount() >= 1000) {
            Date oldestTest = testService.getEarliestDate();
            if (oldestTest != null) {
                testService.deleteDataByDate(oldestTest);
            }
        }

        messageQueue.offer(testData);
        testService.saveDataFromJson(testData);

//        if (messageQueue.size() >= MAX_QUEUE_SIZE) {
//            test oldestTest = messageQueue.poll(); // 移除队列里最早的
//           // testService.createTempTable(); // 创建临时表
//            testService.deleteEarliestData(); // 从数据库中删除最早的消息
//        }
//        messageQueue.offer(testData); //将新数据添加到队尾
//        testService.saveDataFromJson(testData); // 将新消息保存到数据库
    }
    // 其他方法...
}
