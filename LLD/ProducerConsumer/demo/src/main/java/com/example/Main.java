package com.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        QueueService qService = QueueService.getInstance();
        qService.subcribe(1,
                (message) -> {
                    System.out.printf("Consumer 1: message name is %s", message.getString("name"));
                },
                (message) -> {
                    return message.getString("name") != null;
                },
                null);

        qService.subcribe(2,
                (message) -> {
                    System.out.printf("Consumer 2: message product is %s", message.getString("product"));
                },
                (message) -> {
                    return message.getString("product") != null;
                },
                Arrays.asList(1));

        qService.subcribe(3,
                (message) -> {
                    System.out.printf("Consumer 3:message name and product is %s : %s",
                            message.getString("name"),
                            message.getString("product"));
                },
                (message) -> {
                    return message.getString("name") != null
                            && message.getString("product") != null;
                },
                Arrays.asList(2, 3));
        Producer p = new Producer(qService);
        qService.run();
        p.run();
    }
}