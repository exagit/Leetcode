package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstMissingPositive {
    @Test
    public void test1() {
        int[] nums = { 0, 1, 2 };
        int result = new FMP().firstMissingPositive(nums);
        assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] nums = { 3, 4, -1, 2 };
        int result = new FMP().firstMissingPositive(nums);
        assertEquals(1, result);
    }

    @Test
    public void test3() {
        int[] nums = { 7, 8, 9, 11, 12 };
        int result = new FMP().firstMissingPositive(nums);
        assertEquals(1, result);
    }

    @Test
    public void test4() {
        int[] nums = { -1, 4, 2, 1, 9, 10 };
        int result = new FMP().firstMissingPositive(nums);
        assertEquals(3, result);
    }

    @Test
    public void test5() {
        int[] nums = { 1, 1 };
        int result = new FMP().firstMissingPositive(nums);
        assertEquals(2, result);
    }
}

class FMP {

    public int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                min = Math.min(min, nums[i]);
        }
        this.cycleSortWithAdjustment(nums, min);

        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                break;
            }
        }
        return i + 1;
    }

    private void cycleSortWithAdjustment(int[] nums, int adjustment) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] -= adjustment;
        }
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] && nums[i] >= 0 && nums[i] < nums.length && nums[nums[i]] != nums[i]) {
                int valueAt = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = valueAt;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] += adjustment;
        }
    }
}