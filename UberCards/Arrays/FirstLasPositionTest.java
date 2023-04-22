package UberCards.Arrays;

import java.util.Arrays;
import org.junit.Test;

public class FirstLasPositionTest {
    @Test
    public void testFirstLast() {
        int[] nums = { 5, 6, 8, 8, 8, 10 };
        FirstLastPosition sol = new FirstLastPosition();
        int[] range = sol.searchRange(nums, 4);
        System.out.println(Arrays.toString(range));
    }

}
