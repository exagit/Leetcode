package Topics.Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

public class GroupNumbers {
    @Test
    public void test1() {
        int groupSizes[] = { 3, 3, 3, 3, 3, 1, 3 };
        List<List<Integer>> result = new GroupPeopleBySizeOfTheiGroups().groupThePeople(groupSizes);
        System.out.println(result);
    }
}

class GroupPeopleBySizeOfTheiGroups {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> ((a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]));
        for (int i = 0; i < groupSizes.length; i++) {
            q.add(new int[] { groupSizes[i], i });
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> group = new ArrayList<>();
        int groupSize = 0;
        while (!q.isEmpty()) {
            int elem[] = q.poll();
            if (groupSize == 0) {
                groupSize = elem[0] - 1;
                if (group.size() != 0) {
                    result.add(group);
                    group = new ArrayList<>();
                }
            } else {
                groupSize--;
            }
            group.add(elem[1]);
        }
        result.add(group);
        return result;
    }
}