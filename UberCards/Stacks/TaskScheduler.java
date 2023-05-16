package UberCards.Stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
        int[] frequencySet = this.getFrequencies(tasks);
        Queue<Character> q = new ArrayDeque<>(n);
        List<String> strbuilder = new ArrayList<>();
        while (!this.isEmpty(frequencySet)) {
            char nextTask = this.getNotIncludedChar(frequencySet, q);
            if (nextTask != '0') {
                frequencySet[nextTask - 'A']--;
            }
            this.addTaskToQueue(nextTask, q, n, strbuilder);
        }
        return strbuilder.size();
    }


    private boolean isEmpty(int[] frequencySet) {
        for (int i = 0; i < 26; i++) {
            if (frequencySet[i] != 0) {
                return false;
            }
        }
        return true;
    }


    private char getNotIncludedChar(int[] frequencySet, Queue<Character> q) {
        for (int i = 0; i < 26; i++) {
            char currChar = (char) ('A' + i);
            if (!q.contains(currChar) && frequencySet[i] != 0) {
                return currChar;
            }
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


    private int[] getFrequencies(char[] tasks) {
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        return frequencies;
    }
}
