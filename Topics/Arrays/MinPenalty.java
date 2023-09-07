package Topics.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinPenalty {
    @Test
    public void test1() {
        String customers = "YYNY";
        int bestClosingTime = new MPSol().bestClosingTime(customers);
        assertEquals(2, bestClosingTime);
    }

    @Test
    public void test2() {
        String customers = "YYYY";
        int bestClosingTime = new MPSol().bestClosingTime(customers);
        assertEquals(4, bestClosingTime);
    }

    @Test
    public void test3() {
        String customers = "NNNN";
        int bestClosingTime = new MPSol().bestClosingTime(customers);
        assertEquals(0, bestClosingTime);
    }
}

class MPSol {

    public int bestClosingTime(String customers) {
        int n = customers.length();
        int penalty = 0;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                penalty++;
            }
        }
        int minPenalty = penalty, ans = 0;
        for (int i = 1; i <= n; i++) {
            if (customers.charAt(i - 1) == 'Y') {
                penalty--;
            } else {
                penalty++;
            }
            if (penalty < minPenalty) {
                minPenalty = penalty;
                ans = i;
            }
        }
        return ans;
    }
}