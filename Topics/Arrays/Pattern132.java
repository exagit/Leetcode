package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class Pattern132 {

    @Test
    public void test1() {
        int nums[] = { 3, 1, 4, 2 };
        assertEquals(true, this.find132pattern(nums));
    }

    @Test
    public void test2() {
        int nums[] = { -1, 3, 2, 0 };
        assertEquals(true, this.find132pattern(nums));
    }

    @Test
    public void test3() {
        int nums[] = { 1, 0, 1, -4, -3 };
        assertEquals(false, this.find132pattern(nums));
    }

    @Test
    public void test4() {
        int nums[] = { -2, 1, 1 };
        assertEquals(false, this.find132pattern(nums));
    }

    @Test
    public void test5() {
        int nums[] = { 1, 4, 0, -1, -2, -3, -1, -2 };
        assertEquals(true, this.find132pattern(nums));
    }

    @Test
    public void test6() {
        int nums[] = { -2, 1, -2 };
        assertEquals(false, this.find132pattern(nums));
    }

    boolean find132pattern(int[] nums) {
        // arrange by num values first then arrange by indices
        TreeSet<int[]> set = new TreeSet<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            Set<int[]> lowerSet = set.headSet(new int[] { curr, i });
            Set<int[]> higherSet = set.tailSet(new int[] { curr, i });

            // arrange by indexes
            // equal num values are also possible due to the comparator by num
            // values also checking the indices, hence the filter is required
            int lowerMinPos = lowerSet.stream()
                    .filter(a -> a[0] != curr)
                    .min((a, b) -> a[1] - b[1])
                    .orElse(new int[] { -1, -1 })[1];
            int higherMaxPos = higherSet.stream()
                    .filter(a -> a[0] != curr)
                    .max((a, b) -> a[1] - b[1])
                    .orElse(new int[] { -1, -1 })[1];
            // If any one of the lower elements occur before the higher elements
            if (lowerMinPos != -1 && higherMaxPos != -1) {
                if (i > lowerMinPos && i > higherMaxPos
                        && lowerMinPos < higherMaxPos) {
                    return true;
                }
            }
            set.add(new int[] { curr, i });
        }
        return false;
    }

    public int find132patterncount(int[] nums) {
        int totalPairs = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            List<Integer> descList = new ArrayList<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > curr) {
                    int posInserted = this.insertIntoSortedList(descList, nums[j]);
                    totalPairs += posInserted;
                }
            }
        }
        return totalPairs;
    }

    private int insertIntoSortedList(List<Integer> sortedDescList, int num) {
        int start = 0, end = sortedDescList.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (num >= sortedDescList.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        sortedDescList.add(start, num);
        return start;
    }
}
