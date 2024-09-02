package com.moveon.boot.learn.kafka.test.admin;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminTopicTest
 * @Description TODO
 * @Author Moveon
 * @Date 2024/9/2 09:41
 * @Version 1.0
 **/
public class AdminTopicTest {
    public static void main(String[] args) {
        Map<String, Object> confMap = new HashMap<>();
        confMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Admin admin = Admin.create(confMap);

        String topicName1 = "test1";
        int partitionCount1 = 1;
        short replicationCount1 = 1;
        NewTopic newTopic1 = new NewTopic(topicName1, partitionCount1, replicationCount1);
        String topicName2 = "test2";
        int partitionCount2 = 2;
        short replicationCount2 = 2;
        NewTopic newTopic2 = new NewTopic(topicName2, partitionCount2, replicationCount2);

        CreateTopicsResult topics = admin.createTopics(
                Arrays.asList(newTopic1, newTopic2)
        );
    }
}
