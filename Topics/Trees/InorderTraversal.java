package Topics.Trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.junit.Test;

public class InorderTraversal {
    @Test
    public void test1() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;
        List<Integer> result = new InorderTraversalSol().inorderTraversal(n1);
        System.out.println(result.toString());
    }

    @Test
    public void test2() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n2.right = n3;
        List<Integer> result = new InorderTraversalSol().inorderTraversal(n1);
        System.out.println(result.toString());
    }
}


class InorderTraversalSol {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        InorderIterator t = new InorderIterator(root);
        while (t.hasNext()) {
            result.add(t.next().val);
        }
        return result;
    }
}


class InorderIterator implements Iterator<TreeNode> {
    Stack<TreeNode> stack;

    InorderIterator(TreeNode root) {
        this.stack = new Stack<>();
        this.populateNodeToStack(root);
    }

    public TreeNode next() {
        if (!this.stack.isEmpty()) {
            TreeNode ans = this.stack.pop();
            this.populateNodeToStack(ans.right);
            return ans;
        } else {
            return null;
        }
    }

    private void populateNodeToStack(TreeNode root) {
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !this.stack.isEmpty();
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
