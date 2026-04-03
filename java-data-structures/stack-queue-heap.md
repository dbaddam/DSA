# Java Data Structures — Interview Cheatsheet

## The Blind Rule
```
Need a stack?     → Deque<T> st  = new ArrayDeque<>()
Need a queue?     → Deque<T> q   = new ArrayDeque<>()
Need a deque?     → Deque<T> dq  = new ArrayDeque<>()
Need a min heap?  → PriorityQueue<T> pq = new PriorityQueue<>()
Need a max heap?  → PriorityQueue<T> pq = new PriorityQueue<>(Collections.reverseOrder())
```
**Always `ArrayDeque`. Always `PriorityQueue`. Never think about it again.**

---

## Family Tree
```
Collection
├── Queue (interface)
│   ├── Deque (interface) ← double-ended, works as stack, queue, or deque
│   │   ├── ArrayDeque   ✓ USE THIS — fast, no overhead
│   │   └── LinkedList   ✗ avoid — extra memory per node, poor cache performance
│   └── PriorityQueue    ✓ USE THIS for heap
└── Stack (legacy class) ✗ avoid — extends Vector, synchronized, slow
```

---

## What NOT to use & why

| Class | Why avoid |
|---|---|
| `Stack` | Extends `Vector` (synchronized = slow). Legacy. Java docs say don't use it. |
| `LinkedList` | Extra prev/next pointers per node. Poor cache locality. No advantage over `ArrayDeque` unless you need O(1) mid-list insertion (rare in interviews). |

---

## ArrayDeque — 3 modes, same class

**Stack (LIFO) — touch front only**
```java
Deque<Integer> st = new ArrayDeque<>();
st.push(x);       // add to front
st.pop();         // remove from front  (throws if empty)
st.peek();        // look at front      (throws if empty)
st.isEmpty();
```

**Queue (FIFO) — add back, remove front**
```java
Deque<Integer> q = new ArrayDeque<>();
q.offer(x);       // add to back
q.poll();         // remove from front  (returns null if empty)
q.peek();         // look at front      (returns null if empty)
q.isEmpty();
```

**Deque (both ends) — sliding window, monotonic deque problems**
```java
Deque<Integer> dq = new ArrayDeque<>();
dq.offerFirst(x);  dq.offerLast(x);   // add to front / back
dq.pollFirst();    dq.pollLast();      // remove from front / back
dq.peekFirst();    dq.peekLast();      // look at front / back
```

> **Rule:** `poll/offer` return null on empty. `pop/push/peek` throw on empty. Use `poll` when in doubt — safer.

---

## PriorityQueue — Heap

**Min heap (default)**
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

**Max heap**
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

**Custom object — always use `Long.compare` or `Integer.compare`, never subtraction on long fields**
```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
```

> **Why not subtraction?** `a - b` on `long` returns `long`, but comparator needs `int`. Silent overflow bug.

**Methods**
```java
pq.offer(x);      // push
pq.poll();        // pop min (returns null if empty)
pq.peek();        // look at min (returns null if empty)
pq.isEmpty();
pq.size();
```

---

## When to use which

| Scenario | Use |
|---|---|
| Undo / balanced parens / monotonic | Stack → `ArrayDeque` |
| BFS / level order traversal | Queue → `ArrayDeque` |
| Sliding window maximum/minimum | Deque → `ArrayDeque` (both ends) |
| Always pick min or max efficiently | Heap → `PriorityQueue` |
| K largest / K smallest | `PriorityQueue` |
| Dijkstra / scheduling / repair cars | `PriorityQueue` |

---

## Quick Reference Card

```
ArrayDeque as stack:   push · pop · peek
ArrayDeque as queue:   offer · poll · peek
ArrayDeque as deque:   offerFirst · offerLast · pollFirst · pollLast · peekFirst · peekLast
PriorityQueue:         offer · poll · peek
```
