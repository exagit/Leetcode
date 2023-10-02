package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MonotonicArray {

    @Test
    public void test1() {
        int[] nums = { 1, 3, 2 };
        assertEquals(false, this.isMonotonic(nums));
    }

    public boolean isMonotonic(int[] nums) {
        if (nums.length == 1)
            return true;
        int direction = 0;
        int newNo = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currDirection = nums[i] - newNo;
            newNo = nums[i];
            if (direction < 0 && currDirection > 0) {
                return false;
            }
            if (direction > 0 && currDirection < 0) {
                return false;
            }
            if (currDirection != 0)
                direction = currDirection;
        }
        return true;
    }
}
