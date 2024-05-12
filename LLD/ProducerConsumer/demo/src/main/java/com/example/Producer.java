package com.example;

import org.json.JSONObject;

public class Producer implements Runnable {
    private QueueService qService;

    Producer(QueueService qService) {
        this.qService = qService;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                this.qService.pushMessage(new JSONObject("{name: 'priyesh'}"));
            }
        } catch (Exception e) {

        }
    }

}
