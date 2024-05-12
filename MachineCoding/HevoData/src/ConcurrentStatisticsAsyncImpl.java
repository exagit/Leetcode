package MachineCoding.HevoData.src;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ConcurrentStatisticsAsyncImpl implements Statistics {
    private CompletableFuture<Integer> minFuture;
    private CompletableFuture<Integer> maxFuture;
    private CompletableFuture<Integer> meanVarFuture;
    private int min;
    private int max;
    private int count;
    private float mean;
    private float variance;
    private double sum;
    private double squaredSum;

    public ConcurrentStatisticsAsyncImpl() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        mean = 0;
        sum = 0;
        squaredSum = 0;
        minFuture = CompletableFuture.supplyAsync(() -> min);
        maxFuture = CompletableFuture.supplyAsync(() -> max);
        meanVarFuture = CompletableFuture.supplyAsync(() -> null);
    }

    @Override
    public void event(int n) {
        long callStart = System.currentTimeMillis();
        try {
            Thread.sleep(10);
            synchronized (minFuture) {
                minFuture = minFuture.thenApplyAsync(min -> {
                    System.out.println("Processing min for event: " + n);
                    this.min = Math.min(this.min, n);
                    return -1;
                });
            }

            synchronized (maxFuture) {
                maxFuture = maxFuture.thenApplyAsync(max -> {
                    System.out.println("Processing max for event: " + n);
                    this.max = max >= n ? max : n;
                    return this.max;
                });
            }

            synchronized (meanVarFuture) {
                meanVarFuture = meanVarFuture.thenApplyAsync((v) -> {
                    System.out.println("Processing mean variance for event: " + n);
                    this.count++;

                    this.sum += n;
                    this.mean = (float) (this.sum / this.count);

                    this.squaredSum += Math.pow(n, 2);
                    this.variance = (float) ((this.squaredSum / this.count) - Math.pow(this.mean, 2));
                    return 1;
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long duration = System.currentTimeMillis() - callStart;
        System.out.println("duration:" + duration);
    }

    public int eventcount() {
        return this.count;
    }

    @Override
    public int min() {
        return this.min;
    }

    @Override
    public int max() {
        return this.max;
    }

    @Override
    public float mean() {
        return this.mean;
    }

    @Override
    public float variance() {
        return this.variance;
    }

    public void printAll() {
        System.out.printf("eventCount: %d%n", this.eventcount());
        System.out.printf("min: %d%n", this.min());
        System.out.printf("max: %d%n", this.max());
        System.out.printf("mean: %f%n", this.mean());
        System.out.printf("variance: %f%n", this.variance());
    }

    public CompletableFuture<Void> printAllStatsWhenCompleted() {
        return CompletableFuture.allOf(minFuture, maxFuture, meanVarFuture).whenComplete((t, ex) -> {
            this.printAll();
        });
    }

    @Test
    public void test1() throws Exception {
        ConcurrentStatisticsAsyncImpl stats = new ConcurrentStatisticsAsyncImpl();
        int threadCount = 10;
        int updationsPerThread = 10;

        // Simulate concurrent event submission
        int totalSubmitionParallelism = threadCount * updationsPerThread;
        ExecutorService executor = Executors.newFixedThreadPool(totalSubmitionParallelism);
        for (int i = 0; i < threadCount; i++) {
            final int threadid = i;
            executor.submit(() -> {
                for (int j = 1; j <= updationsPerThread; j++) {
                    int event = threadid * 10 + j;
                    stats.event(event);
                    System.out.printf("Submitting event:%d from thread: %d\n", event, threadid);
                }
            });
        }

        executor.awaitTermination(2, TimeUnit.SECONDS);
        stats.printAll();

        assertEquals(100, stats.eventcount());
        assertEquals(1, stats.min());
        assertEquals(100, stats.max());
        assertEquals(50.5, stats.mean(), 0.00001);
        assertEquals(833.25, stats.variance(), 0.00001);
    }
}
