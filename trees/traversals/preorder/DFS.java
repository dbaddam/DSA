// Preorder Traversal using DFS (Recursive)
import java.util.*;

public class PreorderTraversalDFS {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        dfs(root, l);
        return l;
    }

    private void dfs(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }

        l.add(root.val);
        dfs(root.left, l);
        dfs(root.right, l);
    }
}