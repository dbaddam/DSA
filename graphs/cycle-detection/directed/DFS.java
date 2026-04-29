// Cycle Detection in Directed Graph using DFS
import java.util.*;

public class CycleDetectionDFS {

    private boolean hasCycle(int n, Map<Integer, List<Integer>> map) {
        boolean[] explored = new boolean[n];
        boolean[] recVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (hasCycleUtil(map, i, recVisited, explored)) {
                // found cycle
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(Map<Integer, List<Integer>> map, int u, boolean[] recVisited, boolean[] explored) {
        if (explored[u]) {
            // this node was completely explored earlier / no cycle was found.
            // hence we marked it true. No need of again doing dfs from here.
            // eliminating the re-work, we just return false.
            return false;
        }

        if (recVisited[u]) {
            // we are visiting a node again, meaning there is a cycle.
            // we have reached an ancestor node, meaning this is back edge
            // cycle found.
            return true;
        }

        recVisited[u] = true;

        for (int v : map.getOrDefault(u, new ArrayList<Integer>())) {
            if (hasCycleUtil(map, v, recVisited, explored)) {
                return true;
            }
        }

        recVisited[u] = false; // backtrack
        explored[u] = true;
        return false;
    }
}