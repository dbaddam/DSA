// Inorder Traversal using DFS (Recursive)
import java.util.*;

public class InorderTraversalDFS {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        dfs(root, l);
        return l;
    }

    private void dfs(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }

        dfs(root.left, l);
        l.add(root.val);
        dfs(root.right, l);
    }
}