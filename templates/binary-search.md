# Binary Search: Universal Template & Blind Rules

## The Template (Never Changes)

```java
int lo = start, hi = end;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (/* mid is DEFINITELY not the answer */) {
        lo = mid + 1;   // or hi = mid - 1
    } else {
        hi = mid - 1;   // or lo = mid + 1
    }
}
return lo; // or hi
```

**Fixed parts (always identical, every problem):**
- `while (lo <= hi)` — loop runs while search space is non-empty
- `int mid = lo + (hi - lo) / 2` — midpoint, overflow-safe
- Always `mid + 1` or `mid - 1` — NEVER `mid` alone (causes infinite loops)

**Variable parts (change per problem):**
1. The `if` condition
2. Which pointer goes in `if` vs `else`
3. Which pointer you return

All three are decided by answering **two questions**.

---

## The Two Questions

### Q1: What can I DEFINITELY eliminate?

Look at `nums[mid]` and ask: "Can I be 100% sure this is NOT my answer?"

- If it's bad because it's **too small** → eliminate the left side → `lo = mid + 1`
- If it's bad because it's **too big** → eliminate the right side → `hi = mid - 1`

This goes in the `if` block.

### Q2: Do I want the FIRST or LAST valid element?

This decides the `else` block:

- Want the **first** valid → search left for an earlier one → `hi = mid - 1`
- Want the **last** valid → search right for a later one → `lo = mid + 1`

### Which pointer is the answer?

> **The pointer that does the eliminating (in the `if` block) holds the answer.**

- Eliminated with `lo = mid + 1` → return **`lo`**
- Eliminated with `hi = mid - 1` → return **`hi`**

---

## The Four Common Variants

All use the same `while` and `mid`. Only the `if` condition and return value change.

### 1. First element >= target

```java
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] < target) {    // too small → eliminate left
        lo = mid + 1;
    } else {
        hi = mid - 1;            // might be answer, search left
    }
}
return lo;
```

### 2. First element > target (strict)

```java
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] <= target) {   // too small or equal → eliminate left
        lo = mid + 1;
    } else {
        hi = mid - 1;
    }
}
return lo;
```

### 3. Last element <= target

```java
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] > target) {    // too big → eliminate right
        hi = mid - 1;
    } else {
        lo = mid + 1;            // might be answer, search right
    }
}
return hi;
```

### 4. Last element < target (strict)

```java
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] >= target) {   // too big or equal → eliminate right
        hi = mid - 1;
    } else {
        lo = mid + 1;
    }
}
return hi;
```

### Strict vs Non-Strict: The Only Difference

| Want | Is `== target` bad? | `if` condition |
|------|---------------------|----------------|
| first **>=** target | No | `nums[mid] < target` |
| first **>** target | Yes | `nums[mid] <= target` |
| last **<=** target | No | `nums[mid] > target` |
| last **<** target | Yes | `nums[mid] >= target` |

To go from non-strict to strict, just add `=` to the `if` condition. Everything else stays the same.

---

## What Happens When No Valid Element Exists?

When the loop ends, `lo = hi + 1` (they've crossed). The pointers land at natural "out of bounds" positions:

- `lo` lands at `end + 1` → "nothing was big enough" (for first >= / first >)
- `hi` lands at `start - 1` → "nothing was small enough" (for last <= / last <)

You can check for this if needed:

```java
int idx = lo; // or hi
if (idx < start || idx > end) {
    // no valid element found
}
```

---

## Worked Example: Count Fair Pairs

**Problem:** Given sorted `nums`, for each `i`, count `j > i` where `lower <= nums[i] + nums[j] <= upper`.

**Approach:** For each `i`, find the range `[lo, hi]` in the subarray `[i+1, n-1]`:
- `lo` = first index where `nums[j] >= lower - nums[i]` → use variant 1
- `hi` = last index where `nums[j] <= upper - nums[i]` → use variant 3

```java
public long countFairPairs(int[] nums, int lower, int upper) {
    long c = 0;
    int n = nums.length;
    Arrays.sort(nums);
    for (int i = 0; i < n - 1; i++) {
        int lo = firstAtLeast(nums, i + 1, n - 1, lower - nums[i]);
        int hi = lastAtMost(nums, i + 1, n - 1, upper - nums[i]);
        c += Math.max(0, hi - lo + 1);
    }
    return c;
}

int firstAtLeast(int[] nums, int lo, int hi, int target) {
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return lo;
}

int lastAtMost(int[] nums, int lo, int hi, int target) {
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] > target) hi = mid - 1;
        else lo = mid + 1;
    }
    return hi;
}
```

---

## Applying to Any DSA Problem

When you see a binary search problem:

1. **Identify the sorted search space** — could be an array, a range of values, or an answer space (e.g., "minimum possible maximum").

2. **Define what "valid" means** — what condition must `mid` satisfy?

3. **Answer Q1:** "If `mid` is bad, is it too small or too big?"
   - Too small → `lo = mid + 1`
   - Too big → `hi = mid - 1`

4. **Answer Q2:** "Do I want the first or last valid?"
   - First → `hi = mid - 1` in else, return `lo`
   - Last → `lo = mid + 1` in else, return `hi`

5. **Write the template.** Done.

---

## Binary Search on Answer Space

Many problems don't search in an array but search for the optimal answer in a range `[minPossible, maxPossible]`.

**Example:** "Find the minimum capacity such that all packages can be shipped within D days."

```java
int lo = maxWeight;        // minimum possible answer
int hi = totalWeight;      // maximum possible answer
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (canShipInDDays(mid)) {
        hi = mid - 1;      // works, but maybe smaller works too → search left
    } else {
        lo = mid + 1;      // doesn't work → need more capacity → search right
    }
}
return lo;  // lo eliminated the bad (too small) ones
```

Same two questions apply:
- Q1: "Too small capacity, can't ship" → `lo = mid + 1`
- Q2: Want the **first** (minimum) valid capacity → `hi = mid - 1`, return `lo`

---

## Quick Reference Card

```
┌─────────────────────────────────────────────────┐
│           BINARY SEARCH CHEAT SHEET             │
├─────────────────────────────────────────────────┤
│                                                 │
│  while (lo <= hi)           ← ALWAYS            │
│      mid = lo + (hi-lo)/2   ← ALWAYS            │
│      ALWAYS mid±1           ← NEVER just mid    │
│                                                 │
│  Q1: What's definitely bad?                     │
│      Too small → lo = mid+1 → answer is lo      │
│      Too big   → hi = mid-1 → answer is hi      │
│                                                 │
│  Q2: First or last valid?                       │
│      First → hi = mid-1 in else                 │
│      Last  → lo = mid+1 in else                 │
│                                                 │
│  Strict vs non-strict?                          │
│      Just toggle = in the if condition           │
│                                                 │
│  No valid element?                              │
│      lo > end  or  hi < start                   │
│                                                 │
└─────────────────────────────────────────────────┘
```
