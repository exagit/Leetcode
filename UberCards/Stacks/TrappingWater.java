package UberCards.Stacks;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.Test;

public class TrappingWater {
    @Test
    public void basicTest() {
        int arr[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        TrappingWaterSol sol = new TrappingWaterSol();
        System.out.println(sol.trap(arr));
    }

    @Test
    public void basicTest2() {
        int arr[] = { 4, 2, 0, 3, 2, 5 };
        TrappingWaterSol sol = new TrappingWaterSol();
        System.out.println(sol.trap(arr));
    }
}


class TrappingWaterSol {
    public int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int[] leftLargest = new int[height.length];
        int[] rightLargest = new int[height.length];
        leftLargest[0] = 0;
        leftLargest[1] = height[0];
        for (int i = 2; i < height.length; i++) {
            leftLargest[i] = Math.max(height[i - 1], leftLargest[i - 1]);
        }
        rightLargest[height.length - 1] = 0;
        rightLargest[height.length - 2] = height[height.length - 1];
        for (int i = height.length - 3; i >= 0; i--) {
            rightLargest[i] = Math.max(height[i + 1], rightLargest[i + 1]);
        }

        int totalVolume = 0;
        for (int i = 0; i < height.length; i++) {
            int leftHeight = leftLargest[i] - height[i];
            leftHeight = leftHeight >= 0 ? leftHeight : 0;
            int rightHeight = rightLargest[i] - height[i];
            rightHeight = rightHeight >= 0 ? rightHeight : 0;
            int volume = Math.min(leftHeight, rightHeight) * 1;
            totalVolume += volume;
        }
        return totalVolume;
    }
}
