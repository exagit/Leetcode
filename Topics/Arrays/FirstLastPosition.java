package Topics.Arrays;

class FirstLastPosition {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[] { -1, -1 };
        }
        // search element equal to target where left is smaller
        int startInd = this.searchStart(nums, target);
        // search element equal to target where right is bigger
        int endInd = this.searchEnd(nums, target);
        return new int[] { startInd, endInd };
    }

    private int searchStart(int[] nums, int target) {
        int startInd = 0, endInd = nums.length - 1;
        while (startInd <= endInd) {
            int midInd = (startInd + endInd) / 2;
            if (nums[midInd] == target) {
                if (startInd == midInd || (target > nums[midInd - 1])) {
                    return midInd;
                }
            }
            boolean left = (target <= nums[midInd]);
            if (left) {
                endInd = midInd - 1;
            } else {
                startInd = midInd + 1;
            }
        }
        return -1;
    }

    private int searchEnd(int[] nums, int target) {
        int startInd = 0, endInd = nums.length - 1;
        while (startInd <= endInd) {
            int midInd = (startInd + endInd) / 2;
            if (nums[midInd] == target) {
                if (endInd == midInd || (target < nums[midInd + 1])) {
                    return midInd;
                }
            }
            boolean left = (target < nums[midInd]);
            if (left) {
                endInd = midInd - 1;
            } else {
                startInd = midInd + 1;
            }
        }
        return -1;
    }
}
