package Topics.Backtracking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class AdditiveNumbers {
    @Test
    public void testBasic() {
        AdditiveNumbersSolution solution = new AdditiveNumbersSolution();
        String num = "112359";
        boolean result = solution.isAdditiveNumber(num);
        System.out.println(result);
    }

    @Test
    public void testBasic2() {
        AdditiveNumbersSolution solution = new AdditiveNumbersSolution();
        String num = "199100199";
        boolean result = solution.isAdditiveNumber(num);
        System.out.println(result);
    }

    @Test
    public void testBasic3() {
        AdditiveNumbersSolution solution = new AdditiveNumbersSolution();
        String num = "199";
        boolean result = solution.isAdditiveNumber(num);
        System.out.println(result);
    }

    @Test
    public void testBasic4() {
        AdditiveNumbersSolution solution = new AdditiveNumbersSolution();
        String num = "1012";
        boolean result = solution.isAdditiveNumber(num);
        System.out.println(result);
    }
}


class AdditiveNumbersSolution {
    public boolean isAdditiveNumber(String numStr) {
        List<BigInteger> fibArr = new ArrayList<>(numStr.length());
        return this.isAdditiveNumberInternal(numStr, 0, fibArr);
    }

    private boolean isAdditiveNumberInternal(String numStr, int start, List<BigInteger> fibArr) {
        if (start == numStr.length()) {
            System.out.println(fibArr);
            return fibArr.size() > 2 ? true : false;
        }
        int end = (numStr.charAt(start) == '0') ? start + 1 : numStr.length();
        for (int i = start + 1; i <= end; i++) {
            String str = numStr.substring(start, i);
            BigInteger number = new BigInteger(str);
            int compareFibArrResult = this.compareFibArr(fibArr, number);
            if (compareFibArrResult < 0) {
                continue;
            } else if (compareFibArrResult > 0) {
                break;
            }
            fibArr.add(number);
            boolean recursiveResult = this.isAdditiveNumberInternal(numStr, i, fibArr);
            if (recursiveResult) {
                return true;
            }
            fibArr.remove(fibArr.size() - 1);
        }
        return false;
    }

    private int compareFibArr(List<BigInteger> fibArr, BigInteger number) {
        if (fibArr.size() >= 2) {
            BigInteger previous = fibArr.get(fibArr.size() - 1);
            BigInteger prevprev = fibArr.get(fibArr.size() - 2);
            return number.compareTo(previous.add(prevprev));
        }
        return 0;
    }
}
