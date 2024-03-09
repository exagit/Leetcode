package Topics.Trees;

class Node {
    Node left;
    Node right;
    int val;

    public Node(int val) {
        this.val = val;
    }

}

public class IsBalancedTree {
    public static void main(String[] args){
        Node n1 = new 
    }

    Object[] isBalancedTree(Node root) {
        if (root == null) {
            return new Object[] { true, 0 };
        }
        Object[] left = this.isBalancedTree(root.left);
        Object[] right = this.isBalancedTree(root.right);
        boolean areBothBalanced = (boolean) left[0] && (boolean) right[0];
        int leftHeight = (int) left[1];
        int rightHeight = (int) right[1];
        int absDiff = Math.abs(leftHeight - rightHeight);
        boolean isNodeBalanced = (absDiff <= 1) && areBothBalanced;
        return new Object[] { isNodeBalanced, Math.max(leftHeight, rightHeight) + 1 };
    }

}
