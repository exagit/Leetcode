package Topics.Test;

import org.junit.Test;

public class Solution_2 {

    // next greatest element on right, initialize with index itself
    // next greatest element on left
    // contains the index of the next greatest on either side or index of itself
    // for each element we can calculate and add both and find the max

    @Test
    public void test1() {
        int[] A = { 2, 6, 8, 5 };
        System.out.println(this.solution(A));
    }

    @Test
    public void test2() {
        int[] A = { 1, 5, 5, 2, 6 };
        System.out.println(this.solution(A));
    }

    @Test
    public void test3() {
        int[] A = { 1, 1 };
        System.out.println(this.solution(A));
    }


    @Test
    public void test4() {
        int[] A = { 1 };
        System.out.println(this.solution(A));
    }

    public int solution(int[] blocks) {
        int n = blocks.length;
        int[] ngeright = new int[n];
        int[] ngeleft = new int[n];

        ngeleft[0] = 0;
        for (int i = 1; i < n; i++) {
            if (blocks[i - 1] >= blocks[i] && blocks[ngeleft[i - 1]] >= blocks[i]) {
                ngeleft[i] = ngeleft[i - 1];
            } else {
                ngeleft[i] = i;
            }
        }


        ngeright[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (blocks[i + 1] >= blocks[i] && blocks[ngeright[i + 1]] >= blocks[i]) {
                ngeright[i] = ngeright[i + 1];
            } else {
                ngeright[i] = i;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int maxJumpAtI = ngeright[i] - ngeleft[i] + 1;
            ans = Math.max(ans, maxJumpAtI);
        }
        return ans;
    }
}
