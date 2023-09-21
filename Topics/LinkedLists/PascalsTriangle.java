package Topics.LinkedLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PascalsTriangle {
    @Test
    public void test1() {
        System.out.println(new PascalsGenerator().generate(5));
    }
}

class PascalsGenerator {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = Arrays.asList(new Integer[] { 1 });
        result.add(list);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for (int l = 0; l < list.size() - 1; l++) {
                newList.add(list.get(l) + list.get(l + 1));
            }
            newList.add(1);
            result.add(newList);
            list = newList;
        }
        return result;
    }
}