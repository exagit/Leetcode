package Topics.Trees;

public class MaxPathSum {

}


class MPSSol {

    public int maxPathSum(TreeNode root) {
        int ans[] = this.findMax(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] findMax(TreeNode node) {
        if (node == null) {
            return new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
        }
        int leftAns[] = this.findMax(node.left);
        int rightAns[] = this.findMax(node.right);
        int leftPathFromNode = leftAns[1] == Integer.MIN_VALUE ? 0 : leftAns[1];
        int rightPathFromNode = rightAns[1] == Integer.MIN_VALUE ? 0 : rightAns[1];
        int pathIncludingNode = leftPathFromNode + rightPathFromNode + node.val;
        int maxPathStartFromNode = Math.max(leftAns[1], rightAns[1]) + node.val;
        int maxPathInSubTree = Math.max(leftAns[0], rightAns[0]);
        return new int[] { Math.max(pathIncludingNode, maxPathInSubTree), maxPathStartFromNode };
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

