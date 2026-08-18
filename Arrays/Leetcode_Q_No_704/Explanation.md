# 704. Binary Search

## Problem Statement

Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`.

- If `target` exists, return its index.
- Otherwise, return `-1`.
- The algorithm must have **O(log n)** runtime complexity.

### Example 1

```text
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

### Example 2

```text
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

---

## Intuition (Explained Simply)

Think of binary search as repeatedly cutting the search area into **two halves**.

Because the array is already sorted:

1. Look at the **middle element**.
2. If the middle element is the target, we are done.
3. If the middle element is greater than the target, the target can only be in the **left half**.
4. If the middle element is smaller than the target, the target can only be in the **right half**.
5. Repeat until the target is found or there is no search area left.

Instead of checking every element one by one (`O(n)`), we eliminate approximately half of the remaining elements after every comparison. That gives us **O(log n)** time.

---

## Approach — Step by Step

We maintain two pointers:

- `lo` → left boundary of the current search range.
- `hi` → right boundary of the current search range.

### 1. Initialize the search range

```java
int lo = 0;
int hi = nums.length - 1;
```

Initially, the complete array is our search range.

### 2. Continue while the range is valid

```java
while (lo <= hi)
```

We use `lo <= hi` because when `lo == hi`, there is still **one element left to check**.

### 3. Calculate the middle index

```java
int mid = lo + (hi - lo) / 2;
```

This finds the middle of the current search range.

Using:

```java
lo + (hi - lo) / 2
```

is a safer standard way to calculate the middle index because it avoids potential integer overflow that can occur with:

```java
(lo + hi) / 2
```

### 4. Check whether the middle element is the target

```java
if (nums[mid] == target) {
    return mid;
}
```

If they are equal, return the index immediately.

### 5. If the middle value is greater than the target

```java
if (nums[mid] > target) {
    hi = mid - 1;
}
```

Since the array is sorted in ascending order, everything to the **right of `mid`** is also greater.

Therefore, we discard the right half and search only from `lo` to `mid - 1`.

### 6. Otherwise, search the right half

```java
else {
    lo = mid + 1;
}
```

If `nums[mid] < target`, the target must be somewhere to the right.

Therefore, we discard the left half and move `lo` to `mid + 1`.

### 7. Target not found

If the loop ends, there are no elements left to search.

```java
return -1;
```

---

## 🔍 Dry Run Example 1

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

### Initial state

```text
lo = 0
hi = 5
```

Array:

```text
Index:  0   1   2   3   4   5
Value: -1   0   3   5   9  12
        ↑           ↑       ↑
       lo          mid      hi
```

### Step 1

Calculate:

```text
mid = 0 + (5 - 0) / 2
    = 2
```

So:

```text
nums[mid] = nums[2] = 3
```

Compare:

```text
3 < 9
```

The target must be on the right.

Update:

```text
lo = mid + 1
lo = 3
```

---

### Step 2

Now:

```text
lo = 3
hi = 5
```

Calculate:

```text
mid = 3 + (5 - 3) / 2
    = 4
```

So:

```text
nums[mid] = nums[4] = 9
```

Compare:

```text
9 == 9
```

Target found.

```text
return 4;
```

### Final Output

```text
4
```

---

## 🔍 Dry Run Example 2

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 2
```

### Step 1

```text
lo = 0
hi = 5
mid = 2
nums[mid] = 3
```

Since:

```text
3 > 2
```

Search the left half:

```text
hi = mid - 1
hi = 1
```

---

### Step 2

```text
lo = 0
hi = 1
mid = 0
nums[mid] = -1
```

Since:

```text
-1 < 2
```

Search the right half:

```text
lo = mid + 1
lo = 1
```

---

### Step 3

```text
lo = 1
hi = 1
mid = 1
nums[mid] = 0
```

Since:

```text
0 < 2
```

Search the right half:

```text
lo = mid + 1
lo = 2
```

Now:

```text
lo = 2
hi = 1
```

The condition:

```text
lo <= hi
```

is false, so the loop stops.

The target was not found.

```text
return -1;
```

### Final Output

```text
-1
```

---

## Why `while (lo <= hi)`?

This is an important condition in binary search.

Suppose only one element remains:

```text
lo = 3
hi = 3
```

There is still one element at index `3` that needs to be checked.

Therefore:

```java
while (lo <= hi)
```

is correct.

If we used:

```java
while (lo < hi)
```

the loop would stop when `lo == hi`, potentially skipping the last remaining element.

---

## Why `mid = lo + (hi - lo) / 2`?

The common formula is:

```java
mid = (lo + hi) / 2;
```

But the preferred implementation is:

```java
mid = lo + (hi - lo) / 2;
```

This avoids overflow when `lo + hi` becomes larger than the maximum value an integer can represent.

For normal LeetCode array sizes this may not cause a practical issue, but:

```java
lo + (hi - lo) / 2
```

is a good binary-search habit to develop.

---

## Why Binary Search is O(log n)

Every iteration removes approximately half of the remaining search space.

For example:

```text
n elements
→ n/2
→ n/4
→ n/8
→ n/16
→ ...
→ 1
```

The number of times we can divide `n` by 2 until reaching 1 is:

```text
log₂(n)
```

Therefore:

```text
Time Complexity = O(log n)
```

---

## Complexity Analysis

| Complexity | Value | Why |
|---|---|---|
| Time | **O(log n)** | The search range is divided approximately in half after every iteration. |
| Space | **O(1)** | Only `lo`, `hi`, and `mid` variables are used. |

This is an efficient solution because it satisfies the problem's required **O(log n)** runtime.

---

## Java Code

```java
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return -1;
    }
}
```

---

## Code Explanation

```java
int lo = 0, hi = nums.length - 1;
```

Creates the initial search range from the first index to the last index.

```java
while (lo <= hi)
```

Keeps searching while at least one element remains.

```java
int mid = lo + (hi - lo) / 2;
```

Finds the middle index of the current search range.

```java
if (nums[mid] == target) {
    return mid;
}
```

If the middle element is the target, return its index.

```java
if (nums[mid] > target) {
    hi = mid - 1;
}
```

The target is smaller, so search the left half.

```java
else {
    lo = mid + 1;
}
```

The target is larger, so search the right half.

```java
return -1;
```

If the loop finishes, the target does not exist in the array.

---

## Edge Cases

### 1. Target is the first element

```text
nums = [1, 3, 5, 7]
target = 1
```

Output:

```text
0
```

### 2. Target is the last element

```text
nums = [1, 3, 5, 7]
target = 7
```

Output:

```text
3
```

### 3. Array contains one element and target exists

```text
nums = [5]
target = 5
```

Output:

```text
0
```

### 4. Array contains one element and target does not exist

```text
nums = [5]
target = 2
```

Output:

```text
-1
```

### 5. Target is smaller than every element

```text
nums = [3, 5, 7, 9]
target = 1
```

Output:

```text
-1
```

### 6. Target is larger than every element

```text
nums = [3, 5, 7, 9]
target = 12
```

Output:

```text
-1
```

---

## Common Mistakes

### Mistake 1: Using linear search

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
        return i;
    }
}
```

This works, but its time complexity is:

```text
O(n)
```

The problem specifically requires:

```text
O(log n)
```

So binary search should be used.

### Mistake 2: Updating the boundaries incorrectly

When the middle value is greater than the target:

```java
hi = mid - 1;
```

When the middle value is smaller than the target:

```java
lo = mid + 1;
```

Do not use `hi = mid` or `lo = mid`, because that can cause the same middle element to be checked repeatedly.

### Mistake 3: Wrong loop condition

Use:

```java
while (lo <= hi)
```

so that the case where only one element remains is also checked.

---

## Performance Summary

| Metric | Result |
|---|---|
| Algorithm | Binary Search |
| Runtime | **O(log n)** |
| Extra Space | **O(1)** |
| Approach | Iterative |
| Test Cases | Accepted |

---

## Key Takeaway

This problem is the fundamental example of **Binary Search**.

The most important pattern to remember is:

```text
1. Set lo and hi.
2. Find mid.
3. If nums[mid] == target → return mid.
4. If nums[mid] > target → move hi left.
5. If nums[mid] < target → move lo right.
6. If the range becomes empty → return -1.
```

The key reason binary search works is that the array is **sorted**. Because of that ordering, every comparison lets us eliminate approximately half of the remaining elements.

The core template is:

```java
int lo = 0, hi = nums.length - 1;

while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;

    if (nums[mid] == target) {
        return mid;
    }

    if (nums[mid] > target) {
        hi = mid - 1;
    } else {
        lo = mid + 1;
    }
}

return -1;
```

Once this pattern is understood, it becomes the foundation for many other binary-search problems, including **Search Insert Position**, finding boundaries, and searching in modified sorted arrays.
