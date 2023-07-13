package Topics.TwoPointer;

import org.junit.Test;

public class DuplicateNumber {
    @Test
    public void test1() {
        int[] nums = { 1, 3, 4, 2, 2 };
        DuplicateNumberSol sol = new DuplicateNumberSol();
        System.out.println(sol.findDuplicate(nums));
    }

    @Test
    public void test2() {
        int[] nums = { 3, 1, 3, 4, 2 };
        DuplicateNumberSol sol = new DuplicateNumberSol();
        System.out.println(sol.findDuplicate(nums));
    }
}


class DuplicateNumberSol {
    public int findDuplicate(int[] nums) {
        int fastpt = nums[0] - 1, slowpt = nums[0] - 1;

        while (fastpt != slowpt) {
            fastpt = nums[nums[fastpt] - 1] - 1;
            slowpt = nums[slowpt - 1] - 1;
        }
        fastpt = nums[0] - 1;
        while (fastpt != slowpt) {
            fastpt = nums[fastpt] - 1;
            slowpt = nums[slowpt] - 1;
        }
        return slowpt + 1;
    }
}
