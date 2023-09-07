package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinReplaceOperationsToSort {
    @Test
    public void test1() {
        int[] nums = { 3, 9, 3 };
        long replacements = new MROS().minimumReplacement(nums);
        assertEquals(2, replacements);
    }

    @Test
    public void test2() {
        int[] nums = { 1, 2, 3, 4, 5 };
        long replacements = new MROS().minimumReplacement(nums);
        assertEquals(0, replacements);
    }

    @Test
    public void test3() {
        int[] nums = { 12, 9, 7, 6, 17, 19, 21 };
        long replacements = new MROS().minimumReplacement(nums);
        assertEquals(6, replacements);
    }
}

/**
 * 
 * 1. start from the last number , this should be the largest number in the
 * array and hence is the bounding value for all other elements, set boundingVal
 * = lastNum arr[n-1]
 * 2. while we reach to the front of array, move one step back
 * a. if this number is lesser than boundingVal, update the bounding Val to this
 * number.
 * b. divide this number num by boundingVal B, div=num/B & rem=num%B. Increment
 * the result with div and update the bounding val with rem only if rem>0. This
 * will further reduce the value of B.
 * 
 * 3,9,6 -> 3,4,5,6
 * 3,9,6 -> 3,3,6,6
 * 
 * 8,3 ->(8/3~3 (2,3,3)),3
 * 4,4,3
 * 12 - 4,4,4
 * 12 3 4,5
 * 
 * 3,5,4 - 1 2, 2,3, 4 => 4
 * 1,2 1,2,2 4 =>4
 * 8,8,6
 * 8,7,7
 * 
 * 19
 * 8,8,3
 * 8,6,5
 * 
 * 6,6,7
 * 6
 * given a value of B, we can always get minimum replacements with B and
 * remainder not less than B/2
 * Can we get a remainder greater than B/2 with B-1
 * 
 * 8+3/2 = 5
 * 3/3=1 3 0 operation
 * 4/3=1.33 1,3 - 1 operation
 * 6/3=2 3,3 1 operation
 * 8/3=2.6 , 2,3,3 , 2 operations
 * 9/3=3, 3,3,3 2 operations
 * 
 * 
 * 3,11,5 - 1,1,1, 1,5,5 ,5 4
 * 3 3,4,4 5
 * 
 * 3,19,8 -
 */
class MROS {
    public long minimumReplacement(int[] nums) {
        long replacements = 0;
        int N = nums.length;
        int B = nums[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] < B) {
                B = nums[i];
            } else if (nums[i] > B) {
                int div = (int) Math.ceil(nums[i] * 1.0 / B);
                replacements += div - 1;
                B = nums[i] / div;
            }
        }
        return replacements;
    }
}