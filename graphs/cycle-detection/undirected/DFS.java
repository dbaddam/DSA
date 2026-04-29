// Cycle Detection in Undirected Graph using DFS
import java.util.*;

public class CycleDetectionDFS {

    private boolean hasCycle(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) { // looping because graph might be disconnected
            if (!visited[i] && dfs(map, i, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int u, int parent, boolean[] visited) {
        visited[u] = true;

        for (int v : map.getOrDefault(u, new ArrayList<Integer>())) {
            if (v == parent) {
                continue;
            }
            if (visited[v]) {
                // cycle found
                return true;
            }
            if (dfs(map, v, u, visited)) {
                return true;
            }
        }
        return false;
    }
}
