package Topics.Graphs;

import java.util.List;

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedListSum {

    public int depthSum(List<NestedInteger> nestedList) {
        return this.findDepthSum(nestedList, 1);
    }

    private int findDepthSum(List<NestedInteger> nestedList, int level) {
        int currDepthSum = 0;
        for (NestedInteger currentNestedInteger : nestedList) {
            if (currentNestedInteger.isInteger()) {
                currDepthSum += currentNestedInteger.getInteger().intValue() * level;
            } else {
                currDepthSum += this.findDepthSum(currentNestedInteger.getList(), level + 1);
            }
        }
        return currDepthSum;
    }
}
