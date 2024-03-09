package Topics.Graphs;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LongestConsecutiveSequence {
    @Test
    public void test1() {
        LCSSolution sol = new LCSSolution();
        int nums[] = { 103, 104, 100, 4, 101, 1, 3, 2, 102 };
        System.out.println(sol.longestConsecutive(nums));
    }

    @Test
    public void test2() {
        LCSSolution sol = new LCSSolution();
        int nums[] = { 1, 2, 0, 1 };
        System.out.println(sol.longestConsecutive(nums));
    }
}

class LCSSolution {

    public int longestConsecutive(int[] nums) {
        DisjointSet dSet = new DisjointSet();
        for (int n : nums) {
            dSet.makeSet(n);
            dSet.unionSet(n, n - 1);
            dSet.unionSet(n, n + 1);
        }
        return dSet.findMaxSubset();
    }
}

class DisjointSet {
    private Map<Integer, Node> map;

    public DisjointSet() {
        this.map = new HashMap<Integer, Node>();
    }

    int findMaxSubset() {
        int maxSubsetSize = 0;
        for (int key : this.map.keySet()) {
            maxSubsetSize = Math.max(maxSubsetSize, this.map.get(key).subsetSize);
        }
        return maxSubsetSize;
    }

    void makeSet(int number) {
        this.map.putIfAbsent(number, new Node(number));
    }

    void unionSet(int n1, int n2) {
        Node node1 = this.map.get(n1);
        Node node2 = this.map.get(n2);
        if (node1 == null || node2 == null) {
            return;
        }
        Node node1Parent = this.findParent(node1);
        Node node2Parent = this.findParent(node2);
        if (node1Parent == node2Parent) {
            return;
        }
        if (node2Parent.subsetSize <= node1Parent.subsetSize) {
            node2Parent.parent = node1Parent;
            node1Parent.subsetSize += node2Parent.subsetSize;
        } else {
            node1Parent.parent = node2Parent;
            node2Parent.subsetSize += node1Parent.subsetSize;
        }
    }

    Node findParent(Node n) {
        if (n.parent != n) {
            n.parent = this.findParent(n.parent);
        }
        return n.parent;
    }

    class Node {
        int rank;
        int subsetSize;
        Node parent;
        int number;

        public Node(int number) {
            this.number = number;
            this.rank = 0;
            this.subsetSize = 1;
            this.parent = this;
        }
    }
}
