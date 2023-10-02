package Topics.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import Topics.TestUtils;

public class SortItemsByGroups {
    @Test
    public void test1() throws Exception {
        int n = 8, m = 2, group[] = { -1, -1, 1, 0, 0, 1, 0, -1 },
                beforeItems[][] = { {}, { 6 }, { 5 }, { 6 }, { 3, 6 }, {}, {}, {} };
        List<List<Integer>> beforeItemsList = TestUtils.convert2dArrayToNestedList(beforeItems);
        int finalListing[] = new SIBG().sortItems(n, m, group, beforeItemsList);
        System.out.println(Arrays.toString(finalListing));
    }

    @Test
    public void test2() throws Exception {
        int n = 8, m = 2, group[] = { -1, -1, 1, 0, 0, 1, 0, -1 },
                beforeItems[][] = { {}, { 6 }, { 5 }, { 6 }, { 3 }, {}, { 4 }, {} };
        List<List<Integer>> beforeItemsList = TestUtils.convert2dArrayToNestedList(beforeItems);
        int finalListing[] = new SIBG().sortItems(n, m, group, beforeItemsList);
        System.out.println(Arrays.toString(finalListing));
    }

    @Test
    public void test3() throws Exception {
        int n = 5, m = 3, group[] = { 0, 0, 2, 1, 0 },
                beforeItems[][] = { { 3 }, {}, {}, {}, { 1, 3, 2 } };
        List<List<Integer>> beforeItemsList = TestUtils.convert2dArrayToNestedList(beforeItems);
        int finalListing[] = new SIBG().sortItems(n, m, group, beforeItemsList);
        System.out.println(Arrays.toString(finalListing));
    }

    @Test
    public void test4() throws Exception {
        int n = 8, m = 2, group[] = { -1, -1, 1, 0, 0, 1, 0, -1 },
                beforeItems[][] = { { 3 }, { 6, 0 }, { 5 }, { 6 }, { 3, 6, 7 }, {}, {}, {} };
        List<List<Integer>> beforeItemsList = TestUtils.convert2dArrayToNestedList(beforeItems);
        int finalListing[] = new SIBG().sortItems(n, m, group, beforeItemsList);
        System.out.println(Arrays.toString(finalListing));
    }
}

class SIBG {

    public int[] sortItems(int n, int m, int[] groups, List<List<Integer>> beforeItems) {
        Graph groupGraph = new Graph();
        Map<Integer, Graph> groupNodeGraphMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int g = this.getGroupForItem(groups, i, m);
            groupGraph.addNode(g);
            groupNodeGraphMap.putIfAbsent(g, new Graph());
            groupNodeGraphMap.get(g).addNode(i);
        }

        for (int i = 0; i < n; i++) {
            int itemGroup = this.getGroupForItem(groups, i, m);
            for (int j = 0; j < beforeItems.get(i).size(); j++) {
                int itemBeforeThis = beforeItems.get(i).get(j);
                int itemBeforeThisGroup = this.getGroupForItem(groups, itemBeforeThis, m);
                if (itemGroup != itemBeforeThisGroup) {
                    groupGraph.addRelation(itemBeforeThisGroup, itemGroup);
                } else {
                    groupNodeGraphMap.get(itemGroup).addRelation(itemBeforeThis, i);
                }
            }
        }

        List<Integer> finalListing = new ArrayList<>();
        try {
            for (int g : groupGraph.getOrderedListing()) {
                try {
                    finalListing.addAll(groupNodeGraphMap.get(g).getOrderedListing());
                } catch (Exception e) {
                    System.err.println(
                            "Error while processing group nodes ordering for group :" + g + ":" + e.toString());
                    finalListing = new ArrayList<>();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error while processing overall group ordering :" + e.toString());
            finalListing = new ArrayList<>();
        }
        return finalListing.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getGroupForItem(int[] group, int i, int m) {
        int g = group[i];
        if (g == -1) {
            g = m + i;
        }
        return g;
    }

    class Graph {
        private Map<Integer, Node> map;

        public Graph() {
            this.map = new HashMap<Integer, Node>();
        }

        Node addNode(int nodeNo) {
            if (!this.map.containsKey(nodeNo)) {
                Node n = new Node(nodeNo);
                this.map.put(nodeNo, n);
            }
            return this.map.get(nodeNo);
        }

        // in the final ordering parent comes before child
        void addRelation(int parentNode, int childNode) {
            if (!this.map.containsKey(parentNode)) {
                System.err.println(String.format("parent node name does not exist with name %d", parentNode));
                return;
            }

            if (!this.map.containsKey(childNode)) {
                System.err.println(String.format("child node name does not exist with name %d", childNode));
                return;
            }
            this.map.get(parentNode).children.add(this.map.get(childNode));
        }

        public List<Integer> getOrderedListing() throws Exception {
            Stack<Node> s = new Stack<>();
            Set<Node> visitedSet = new HashSet<>();
            for (Entry<Integer, Node> entry : this.map.entrySet()) {
                Node node = entry.getValue();
                this.addToStack(node, s, visitedSet, new HashSet<>());
            }
            List<Integer> finalOrdering = new ArrayList<>();
            while (!s.isEmpty()) {
                finalOrdering.add(s.pop().name);
            }
            return finalOrdering;
        }

        private void addToStack(Node node, Stack<Node> s, Set<Node> visitedSet, Set<Node> thisSet) throws Exception {
            if (!thisSet.contains(node)) {
                thisSet.add(node);
                if (!visitedSet.contains(node)) {
                    visitedSet.add(node);
                    for (Node child : node.children) {
                        this.addToStack(child, s, visitedSet, thisSet);
                    }
                    s.push(node);
                }
                thisSet.remove(node);
            } else {
                throw new Exception("Cycle detected at node " + node.name);
            }
        }

        class Node {
            int name;
            Set<Node> children;

            public Node(int name) {
                this.name = name;
                this.children = new HashSet<>();
            }
        }
    }
}