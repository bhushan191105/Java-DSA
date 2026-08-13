## 1. Two Sum

**Difficulty:** Easy  
**Approach:** HashMap (Single Pass)

### Problem
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

### Approach
Iterate through the array once. For each element, check if its complement (`target - nums[i]`) is already present in a HashMap. If it is, return the stored index and the current index. Otherwise, store the current number and its index in the map for future lookups.

### Complexity
| Metric | Complexity |
|--------|------------|
| Time   | O(n)       |
| Space  | O(n)       |

### Performance
- **Runtime:** 2 ms (Beats 99.34%)
- **Memory:** 47.42 MB (Beats 11.19%)
- **Test Cases:** 65/65 passed ✅

### Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
```