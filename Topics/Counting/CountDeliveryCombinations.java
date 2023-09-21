package Topics.Counting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountDeliveryCombinations {
    @Test
    public void test1() {
        int result = new DeliveryCombinations().countOrders(1);
        assertEquals(1, result);
    }

    @Test
    public void test2() {
        int result = new DeliveryCombinations().countOrders(2);
        assertEquals(6, result);
    }

    @Test
    public void test3() {
        int result = new DeliveryCombinations().countOrders(3);
        assertEquals(90, result);
    }

    @Test
    public void test4() {
        int result = new DeliveryCombinations().countOrders(8);
        assertEquals(729647433, result);
    }
}

class DeliveryCombinations {
    public static final int MOD = (int) (Math.pow(10, 9)) + 7;

    public int countOrders(int n) {
        long result = 1;
        if (n == 1) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            result = this.modulated(result * this.getApSum((i - 1) * 2 + 1));
        }
        return (int) result;
    }

    private long getApSum(int n) {
        long result = n * (n + 1) / 2;
        return this.modulated(result);
    }

    private long modulated(long result) {
        if (result > MOD) {
            return Math.floorMod(result, MOD);
        }
        return result;
    }
}