package Topics.Stacks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;

public class ExclusiveFunctionTime {
    @Test
    public void testBasic() {
        int n = 2;
        String[] logs = { "0:start:0", "1:start:2", "1:end:5", "0:end:6" };
        int[] result = new ExclusiveTime().exclusiveTime(n, Arrays.asList(logs));
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testBasic2() {
        int n = 1;
        String[] logs = { "0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7" };
        int[] result = new ExclusiveTime().exclusiveTime(n, Arrays.asList(logs));
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testBasic3() {
        int n = 2;
        String[] logs = { "0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7" };
        int[] result = new ExclusiveTime().exclusiveTime(n, Arrays.asList(logs));
        System.out.println(Arrays.toString(result));
    }
}


class ExclusiveTime {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> s = new Stack<>();
        Map<Integer, Integer> durationMap = new HashMap<>();
        for (String log : logs) {
            String[] tokens = log.split(":");
            int functionId = Integer.parseInt(tokens[0]);
            boolean start = tokens[1].equals("start");
            int timestamp = Integer.parseInt(tokens[2]);
            if (start) {
                this.markFunctionStart(functionId, timestamp, s, durationMap);
            } else {
                this.markFunctionEnd(functionId, timestamp, s, durationMap);
            }
        }
        int[] result = new int[n];
        int i = 0;
        for (int val : durationMap.values()) {
            result[i++] = val;
        }
        return result;
    }

    private void markFunctionStart(int functionId, int starttimestamp, Stack<int[]> s,
            Map<Integer, Integer> durationMap) {
        this.endLastExecutedFunction(starttimestamp, s, durationMap);
        s.add(new int[] { functionId, starttimestamp });
    }

    private void markFunctionEnd(int functionId, int endTimestamp, Stack<int[]> s,
            Map<Integer, Integer> durationMap) {
        this.endLastExecutedFunction(endTimestamp + 1, s, durationMap);
        s.pop();
        if (s.size() > 0) {
            int[] parentFunctionEntry = s.peek();
            parentFunctionEntry[1] = endTimestamp + 1;
        }
    }

    private void endLastExecutedFunction(int endTimestamp, Stack<int[]> s,
            Map<Integer, Integer> durationMap) {
        if (s.size() > 0) {
            int[] lastEntry = s.peek();
            int functionDuration = endTimestamp - lastEntry[1];
            int functionId = lastEntry[0];
            durationMap.putIfAbsent(functionId, 0);
            int totalFunctionExclusiveDuration = durationMap.get(functionId) + functionDuration;
            durationMap.put(functionId, totalFunctionExclusiveDuration);
        }
    }
}
