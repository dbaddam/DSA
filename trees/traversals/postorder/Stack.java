// Postorder Traversal using Stack (Iterative)
import java.util.*;

public class PostorderTraversalStack {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if (root == null) {
            return l;
        }
        Deque<TreeNode> st1 = new ArrayDeque<>();
        Deque<TreeNode> st2 = new ArrayDeque<>();
        st1.push(root);
        while (!st1.isEmpty()) {
            TreeNode node = st1.pop();
            st2.push(node);
            if (node.left != null) {
                st1.push(node.left);
            }
            if (node.right != null) {
                st1.push(node.right);
            }
        }
        while (!st2.isEmpty()) {
            l.add(st2.pop().val);
        }
        return l;
    }
}