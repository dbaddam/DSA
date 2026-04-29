# Cycle Detection in Graphs

This folder contains implementations of cycle detection algorithms for both **undirected** and **directed** graphs. Different graph types require different approaches due to their structural properties.

---

## Undirected Graphs

In undirected graphs, edges are bidirectional. A cycle exists if we can traverse from a node back to itself without using the same edge twice.

### Approaches for Undirected Graphs:

#### 1. **DFS (Depth-First Search)**
- **File**: `undirected/DFS.java`
- **How it works**: Traverse the graph using DFS while tracking the parent node. If we visit a node that is already visited and is not the parent, we've found a cycle.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Best for**: Learning and general-purpose cycle detection

#### 2. **BFS (Breadth-First Search)**
- **File**: `undirected/BFS.java`
- **How it works**: Similar to DFS but uses a queue instead of recursion. Maintains a parent array to track the path. A cycle is found if we encounter a visited node that isn't the parent.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Best for**: When you need iterative traversal (avoids recursion stack overflow)

#### 3. **Union-Find (Disjoint Set Union)**
- **File**: `undirected/UnionFind.java`
- **How it works**: For each edge, try to union two nodes. If they are already in the same set (same parent), a cycle exists.
- **Time Complexity**: O(E × α(V)) where α is the inverse Ackermann function (nearly constant)
- **Space Complexity**: O(V)
- **Best for**: Efficient cycle detection, especially for dense graphs and Minimum Spanning Tree (MST) problems

---

## Directed Graphs

In directed graphs, edges have direction. A cycle exists if we can follow directed edges to return to a previously visited node in the current path.

### Approaches for Directed Graphs:

#### 1. **DFS with Recursion Stack**
- **File**: `directed/DFS.java`
- **How it works**: Uses two boolean arrays:
  - `recVisited[]`: Tracks nodes in the current recursion stack (current DFS path)
  - `explored[]`: Tracks fully processed nodes
  - If we encounter a node that's in the recursion stack, we've found a back edge (cycle).
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Best for**: Direct detection of back edges and cycle paths

#### 2. **Kahn's Algorithm (Topological Sort)**
- **File**: `directed/Indegree.java`
- **How it works**: 
  - Calculate in-degree for each node
  - Add all nodes with in-degree 0 to a queue
  - Process each node and decrease in-degree of its neighbors
  - If all nodes are processed, no cycle exists. Otherwise, a cycle is present.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Best for**: When you need topological ordering or want to detect cycles while sorting

---

## Key Differences

| Feature | Undirected | Directed |
|---------|-----------|----------|
| **Edge Direction** | Bidirectional | One-way |
| **Back Edge Detection** | Compare with parent node | Track recursion stack |
| **Unique Approaches** | DFS, BFS, Union-Find | DFS with stack tracking, Kahn's Algorithm |
| **Complexity** | All ~O(V + E) | All ~O(V + E) |

---

## Usage Tips

- **Undirected + Dense Graph**: Use Union-Find for best performance
- **Undirected + Learning**: Use DFS or BFS to understand the concepts
- **Directed + Need Path Info**: Use DFS to trace the cycle
- **Directed + Need Topological Order**: Use Kahn's Algorithm
- **Directed + Recursion Issues**: Use Kahn's Algorithm (iterative, no stack overflow risk)

---

## File Structure

```
cycle-detection/
├── undirected/
│   ├── DFS.java
│   ├── BFS.java
│   └── UnionFind.java
├── directed/
│   ├── DFS.java
│   └── Indegree.java (Kahn's Algorithm)
└── README.md (this file)
```
