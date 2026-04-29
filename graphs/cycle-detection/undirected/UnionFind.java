// Cycle Detection in Undirected Graph using Union-Find
import java.util.*;

public class CycleDetectionUnionFind {

    static class DS {
        int n;
        int[] parent;
        int[] rank;
        int components;

        public DS(int n) {
            this.n = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            rank = new int[n];
            components = n;
        }

        public int find(int i) {
            // iterative approach with path compression
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return parent[i];
        }

        // find with path compression using recursion
        public int findR(int i) {
            // recursive approach with path compression
            if (parent[i] == i) {
                return i;
            }
            parent[i] = findR(parent[i]);
            return parent[i];
        }

        public boolean union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a == b) {
                return false; // cycle detected
            }
            components--;
            if (rank[a] > rank[b]) {
                parent[b] = a;
            } else if (rank[b] > rank[a]) {
                parent[a] = b;
            } else {
                parent[a] = b;
                rank[b]++;
            }
            return true;
        }
    }


    private boolean isCyclicDisconnected(int n, int[][] edges) {
        DS ds = new DS(n);

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            if (!ds.union(u, v)) {
                return true; // cycle found
            }
        }

        // we can use ds.components to find number of connected components in the graph
        // for example, if graph has to be a valid tree, then ds.components should be 1 at the end

        return false;
    }
}