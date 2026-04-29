// Cycle Detection in Directed Graph using Kahn's Algorithm
import java.util.*;

public class CycleDetectionKahns {

    private boolean hasCycle(int n, int[][] edges) {
        int[] indegree = new int[n];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            map.putIfAbsent(u, new ArrayList<>());
            map.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int explored = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : map.getOrDefault(u, new ArrayList<Integer>())) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
            explored++;
        }

        if (explored != n) {
            return true; // cycle found
        }
        return false;
    }
}