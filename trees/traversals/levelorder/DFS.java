// Levelorder Traversal using DFS (Recursive with depth tracking)
import java.util.*;

public class LevelorderTraversalDFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    void dfs(TreeNode root, int depth, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        // if first node of this level
        // create a new arraylist for this level
        if (depth == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(depth).add(root.val);
        dfs(root.left, depth + 1, ans);
        dfs(root.right, depth + 1, ans);
    }
}