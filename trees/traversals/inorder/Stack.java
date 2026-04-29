// Inorder Traversal using Stack (Iterative)
import java.util.*;

public class InorderTraversalStack {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        while (root != null || !st.isEmpty()) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            l.add(root.val);
            root = root.right;
        }
        return l;
    }
}