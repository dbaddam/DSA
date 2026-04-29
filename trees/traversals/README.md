# Tree Traversals

This folder contains implementations of common tree traversal algorithms, both recursive (DFS-based) and iterative (stack/queue-based) approaches.

**Note**: All traversal classes use the common `TreeNode` class defined in `../TreeNode.java`.

## Traversals

### Preorder (Root, Left, Right)
- **DFS (Recursive)**: `preorder/DFS.java`
- **Stack (Iterative)**: `preorder/Stack.java`

### Inorder (Left, Root, Right)
- **DFS (Recursive)**: `inorder/DFS.java`
- **Stack (Iterative)**: `inorder/Stack.java`

### Postorder (Left, Right, Root)
- **DFS (Recursive)**: `postorder/DFS.java`
- **Stack (Iterative)**: `postorder/Stack.java`

### Levelorder (BFS by levels)
- **DFS (Recursive with depth)**: `levelorder/DFS.java`
- **Queue (Iterative BFS)**: `levelorder/Queue.java`

## Key Differences

- **Recursive (DFS)**: Simple, uses call stack, may cause stack overflow for deep trees.
- **Iterative**: Uses explicit stack/queue, avoids recursion limits, often more complex.

## File Structure

```
trees/
├── TreeNode.java          (Common TreeNode class)
└── traversals/
    ├── preorder/
    │   ├── DFS.java
    │   └── Stack.java
    ├── inorder/
    │   ├── DFS.java
    │   └── Stack.java
    ├── postorder/
    │   ├── DFS.java
    │   └── Stack.java
    ├── levelorder/
    │   ├── DFS.java
    │   └── Queue.java
    └── README.md
```