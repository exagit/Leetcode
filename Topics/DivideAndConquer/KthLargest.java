package Topics.DivideAndConquer;

import java.util.Arrays;
import org.junit.Test;

public class KthLargest {
    @Test
    public void test1() {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        System.out.println(new KthLargestSol().findKthLargest(nums, 2));
    }

    @Test
    public void test2() {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        System.out.println(new KthLargestSol().findKthLargest(nums, 4));
    }

    @Test
    public void test3() {
        int[] nums = { 5, 2, 4, 1, 3, 6, 0 };
        System.out.println(new KthLargestSol().findKthLargest(nums, 4));
    }

    @Test
    public void test4() {
        String pattern = "aab";
        System.out.println(Arrays.toString(generatePreprocessingTable(pattern)));
    }

    public static int[] generatePreprocessingTable(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0; // index for traversing the pattern

        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j;
            } else {
                if (j != 0) {
                    j = pi[j - 1];
                    i--; // keep the current character the same
                } else {
                    pi[i] = 0;
                }
            }
        }

        return pi;
    }
}


class KthLargestSol {
    public int findKthLargest(int[] nums, int k) {
        return this.kthLargestInternal(nums, 0, nums.length - 1, k - 1);
    }

    int kthLargestInternal(int[] nums, int start, int end, int k) {
        if (start > end || k > end - start + 1) {
            return -1;
        }
        int pivotIndex = this.pivot(nums, start, end);
        int pivotRank = end - pivotIndex;
        if (pivotRank == k) {
            return nums[pivotIndex];
        }
        if (pivotRank > k) {
            return this.kthLargestInternal(nums, pivotIndex + 1, end, k);
        } else {
            return this.kthLargestInternal(nums, start, pivotIndex - 1, k - pivotRank - 1);
        }
    }

    private int pivot(int[] nums, int start, int end) {
        int pivotIndex = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] < nums[pivotIndex]) {
                // nums[i] needs to be moved to left of pivot
                int temp = nums[i];
                nums[i] = nums[pivotIndex + 1];
                nums[pivotIndex + 1] = nums[pivotIndex];
                nums[pivotIndex] = temp;
                pivotIndex++;
            }
        }
        return pivotIndex;
    }
}
