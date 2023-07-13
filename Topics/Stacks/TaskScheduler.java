package Topics.Stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Test;

public class TaskScheduler {
    @Test
    public void basicTest() {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        Scheduler sol = new Scheduler();
        System.out.println(sol.leastInterval(tasks, 2));
    }

    @Test
    public void basicTest2() {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B' };
        Scheduler sol = new Scheduler();
        System.out.println(sol.leastInterval(tasks, 0));
    }

    @Test
    public void basicTest3() {
        char[] tasks = { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        Scheduler sol = new Scheduler();
        System.out.println(sol.leastInterval(tasks, 2));
    }

    @Test
    public void basicTest4() {
        char[] tasks = { 'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E' };
        Scheduler sol = new Scheduler();
        System.out.println(sol.leastInterval(tasks, 2));
    }
}


class Scheduler {
    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<int[]> pq = this.getFrequencies(tasks);
        Queue<Character> q = new ArrayDeque<>(n);
        List<String> strbuilder = new ArrayList<>();
        while (!pq.isEmpty()) {
            char nextTask = this.getNotIncludedChar(pq, q);
            this.addTaskToQueue(nextTask, q, n, strbuilder);
        }
        System.out.println(strbuilder.toString());
        return strbuilder.size();
    }

    private char getNotIncludedChar(PriorityQueue<int[]> pq, Queue<Character> q) {
        PriorityQueue<int[]> newpq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
        while (!pq.isEmpty()) {
            int[] entry = pq.poll();
            char c = (char) (entry[0]);
            if (!q.contains(c)) {
                if (entry[1] - 1 != 0)
                    newpq.add(new int[] { c, entry[1] - 1 });
                while (!newpq.isEmpty()) {
                    pq.add(newpq.poll());
                }
                return c;
            } else {
                newpq.add(entry);
            }
        }

        while (!newpq.isEmpty()) {
            pq.add(newpq.poll());
        }
        return '0';
    }


    private void addTaskToQueue(char nextTask, Queue<Character> q, int n,
            List<String> strBuilder) {
        if (q.size() >= n) {
            q.poll();
        }
        if (nextTask == '0') {
            strBuilder.add("idle");
        } else {
            strBuilder.add(nextTask + "");
        }
        q.add(nextTask);
    }


    private PriorityQueue<int[]> getFrequencies(char[] tasks) {
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
        for (int i = 0; i < 26; i++) {
            if (frequencies[i] != 0) {
                pq.add(new int[] { i + 'A', frequencies[i] });
            }
        }
        return pq;
    }
}
