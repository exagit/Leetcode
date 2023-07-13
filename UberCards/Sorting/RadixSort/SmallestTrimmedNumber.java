package UberCards.Sorting.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

public class SmallestTrimmedNumber {
    @Test
    public void test1() {
        String nums[] = { "102", "473", "251", "814" };
        int queries[][] = { { 1, 1 }, { 2, 3 }, { 4, 2 }, { 1, 2 } };
        STNSol sol = new STNSol();
        int[] result = sol.smallestTrimmedNumbers(nums, queries);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void test2() {
        String nums[] = { "24", "37", "96", "04" };
        int queries[][] = { { 2, 1 }, { 2, 2 } };
        STNSol sol = new STNSol();
        int[] result = sol.smallestTrimmedNumbers(nums, queries);
        System.out.println(Arrays.toString(result));
    }
}


class STNSol {

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        RadixSorter sorter = new RadixSorter(nums);
        int c = 0;
        int[] output = new int[queries.length];
        for (int[] query : queries) {
            int K = query[0], digits = query[1];
            List<Integer> sorted = sorter.sort(digits - 1);
            output[c++] = sorted.get(K - 1);
        }
        return output;
    }
}


class RadixSorter {
    private String[] nums;
    private List<List<List<Integer>>> bucketList;
    private int maxLen;
    private boolean[] processed;


    public RadixSorter(String[] nums) {
        this.nums = nums;

        this.maxLen = 0;
        for (String num : nums) {
            this.maxLen = Math.max(this.maxLen, num.length());
        }
        this.processed = new boolean[this.maxLen];
        this.bucketList = new ArrayList<>(this.maxLen);
        for (int digit = 0; digit < this.maxLen; digit++) {
            List<List<Integer>> newBucket = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                newBucket.add(i, new ArrayList<>());
            }
            this.bucketList.add(newBucket);
        }
    }

    public List<Integer> sort(int uptoDigit) {
        if (uptoDigit == -1) {
            return IntStream.range(0, this.nums.length)
                    .boxed()
                    .collect(Collectors.toList());
        }
        List<List<Integer>> buckets = this.bucketList.get(uptoDigit);

        if (!this.processed[uptoDigit]) {
            this.processed[uptoDigit] = true;
            for (Integer stringIndex : this.sort(uptoDigit - 1)) {
                int index = this.nums[stringIndex].charAt(this.maxLen - 1 - uptoDigit) - '0';
                List<Integer> b = buckets.get(index);
                b.add(stringIndex);
            }
        }

        return this.traverseBucket(uptoDigit);
    }

    private List<Integer> traverseBucket(int digit) {
        List<List<Integer>> bucket = this.bucketList.get(digit);
        List<Integer> finalList =
                bucket.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        return finalList;
    }
}
