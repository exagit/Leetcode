package Topics.Stacks;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class StackUsingQueues {
    @Test
    public void test1() {
        String[] commands = { "MyStack", "push", "push", "top", "pop", "empty" };
        int[][] cmdInputs = { {}, { 1 }, { 2 }, {}, {}, {} };
        Object[] output = this.process(commands, cmdInputs);
        Object[] expectedOutput = { null, null, null, 2, 2, false };
        assertArrayEquals(expectedOutput, output);
    }

    private Object[] process(String[] commands, int[][] cmdInputs) {
        MyStack mystack = null;
        Object output[] = new Object[commands.length];
        for (int i = 0; i < commands.length; i++) {
            switch (commands[i]) {
                case "MyStack":
                    if (mystack == null)
                        mystack = new MyStack();
                    break;
                case "push":
                    mystack.push(cmdInputs[i][0]);
                    break;
                case "top":
                    output[i] = mystack.top();
                    break;
                case "pop":
                    output[i] = mystack.pop();
                    break;
                case "empty":
                    output[i] = mystack.empty();
                    break;
            }
        }
        return output;
    }
}

class MyStack {
    List<Queue<Integer>> queues;
    private int activeQueue;

    public MyStack() {
        this.queues = new ArrayList<>();
        this.queues.add(new ArrayDeque<>());
        this.queues.add(new ArrayDeque<>());
        this.activeQueue = 0;
    }

    public void push(int x) {
        this.activeQ().offer(x);
    }

    private Queue<Integer> activeQ() {
        return this.queues.get(this.activeQueue);
    }

    private Queue<Integer> inactiveQ() {
        return this.queues.get(Math.abs(1 - this.activeQueue));
    }

    public int pop() {
        if (this.activeQ().isEmpty()) {
            return 0;
        }
        while (this.activeQ().size() > 1) {
            int popped = this.activeQ().poll();
            this.inactiveQ().offer(popped);
        }
        int popped = this.activeQ().poll();
        this.activeQueue = Math.abs(1 - this.activeQueue);
        return popped;
    }

    public int top() {
        if (this.activeQ().isEmpty()) {
            return 0;
        }
        int popped = 0;
        while (this.activeQ().size() > 0) {
            popped = this.activeQ().poll();
            this.inactiveQ().offer(popped);
        }
        this.activeQueue = Math.abs(1 - this.activeQueue);
        return popped;
    }

    public boolean empty() {
        return this.activeQ().isEmpty();
    }
}