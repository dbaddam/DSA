// Cycle Detection in Undirected Graph using BFS
import java.util.*;

public class CycleDetectionBFS {

    // for a disconnected graph, we need to run BFS for each component
    private boolean isCyclicDisconnected(int n, Map<Integer, List<Integer>> map) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            if (isCyclicConnected(map, n, i, visited)) {
                return true;
            }
        }
        return false;
    }

    // for a connected graph, we can run BFS from any node
    private boolean isCyclicConnected(Map<Integer, List<Integer>> map, int n, int start, boolean[] visited) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : map.getOrDefault(u, new ArrayList<Integer>())) {
                if (parent[u] == v) {
                    continue;
                }
                if (visited[v]) {
                    // cycle found
                    return true;
                }
                visited[v] = true;
                parent[v] = u;
                q.add(v);
            }
        }
        return false;
    }
}