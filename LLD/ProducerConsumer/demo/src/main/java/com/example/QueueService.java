package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.json.JSONObject;

public class QueueService implements Runnable {
    private static volatile QueueService queueService;

    static QueueService getInstance() {
        if (queueService == null) {
            synchronized (QueueService.class) {
                if (queueService == null) {
                    queueService = new QueueService();
                }
            }
        }
        return queueService;
    }

    private BlockingQueue<JSONObject> blockingQ;
    private Map<Integer, Consumer<JSONObject>> callbackMap;
    private Map<Integer, Predicate<JSONObject>> filterMap;
    private Graph consumerGraph;
    private ExecutorService callbackExecutorService;

    public QueueService() {
        this.blockingQ = new ArrayBlockingQueue<>(10);
        this.callbackExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.callbackMap = new HashMap<>();
        this.filterMap = new HashMap<>();
    }

    void pushMessage(JSONObject message) {
        this.blockingQ.add(message);
    }

    void subcribe(
            Integer consumerId,
            Consumer<JSONObject> callback,
            Predicate<JSONObject> filter,
            List<Integer> dependencies) {
        this.callbackMap.put(consumerId, callback);
        this.filterMap.put(consumerId, filter);
        if (dependencies != null)
            for (Integer dependencyConsumer : dependencies) {
                this.consumerGraph.addDependency(consumerId, dependencyConsumer);
            }
    }

    @Override
    public void run() {
        while (true) {
            JSONObject message;
            try {
                message = this.blockingQ.take();
                System.out.println("Received message from queue");
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            for (Integer consumerId : this.consumerGraph.getOrderedNodes()) {
                Predicate<JSONObject> consumerFilter = this.filterMap.get(consumerId);
                if (consumerFilter.test(message)) {
                    Consumer<JSONObject> consumerCallback = this.callbackMap.get(consumerId);
                    this.callbackExecutorService.submit(() -> {
                        try {
                            consumerCallback.accept(message);
                        } catch (Exception e) {
                            System.out.printf("error executing consumer callback for consumer id: %d", consumerId);
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }

}
