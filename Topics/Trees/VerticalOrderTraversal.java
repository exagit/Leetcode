// Preorder traversal of the tree and push the element the list in map corresponding to the column
// Sort the keys in ascending order and return the list

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Supplier;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class VerticalOrderTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Integer[]>> map = new HashMap<>();
        Supplier<PriorityQueue<Integer[]>> createNewQueueLambda = () -> new PriorityQueue<>(
                Comparator.comparing((Integer[] arr) -> arr[1])
                        .thenComparing((Integer[] arr) -> arr[2])
                        .thenComparing((Integer[] arr) -> arr[0]));
        this.addNodeToVerticalList(root, 0, 0, map, createNewQueueLambda);

        List<Integer> allPositionEntries = new ArrayList<>();
        allPositionEntries.addAll(map.keySet());
        allPositionEntries.sort((c1, c2) -> c1 - c2);

        List<List<Integer>> result = new ArrayList<>();
        for (Integer column : allPositionEntries) {
            List<Integer> newSortedList = new ArrayList<>();
            PriorityQueue<Integer[]> sortedColumnQ = map.get(column);
            while (!sortedColumnQ.isEmpty()) {
                newSortedList.add(sortedColumnQ.poll()[0]);
            }
            result.add(newSortedList);
        }
        return result;
    }

    private void addNodeToVerticalList(TreeNode root, int row, int column, Map<Integer, PriorityQueue<Integer[]>> map,
            Supplier<PriorityQueue<Integer[]>> createNewQueueLambda) {
        if (root == null) {
            return;
        }
        map.putIfAbsent(column, createNewQueueLambda.get());
        PriorityQueue<Integer[]> columnNodeList = map.get(column);
        columnNodeList.add(new Integer[] { root.val, row, column });
        this.addNodeToVerticalList(root.left, row + 1, column - 1, map, createNewQueueLambda);
        this.addNodeToVerticalList(root.right, row + 1, column + 1, map, createNewQueueLambda);
    }
}