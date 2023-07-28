package Topics.Arrays;

import java.util.Arrays;
import org.junit.Test;

public class FirstLastPositionTest {
    @Test
    public void testFirstLast() {
        int[] nums = { 5, 6, 8, 8, 8, 10 };
        FirstLastPosition sol = new FirstLastPosition();
        int[] range = sol.searchRange(nums, 10);
        System.out.println(Arrays.toString(range));
    }

    @Test
    public void test() {
        int a = 5, b = 0;
        double a2 = Math.pow(1.1, a);
        double b2 = Math.pow(1.1, b);
        int sum = (int) (Math.log(a2 * b2) / Math.log(1.1));
        System.out.println(sum);
    }

}
