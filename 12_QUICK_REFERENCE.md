# Quick Reference - Cheat Sheets and Quick Lookups

## 📊 Overview

This file provides quick reference sheets for key concepts, algorithms, and data structures covered in this learning map.

## 🧮 Big O Complexity Cheatsheet

### Common Data Structures

| Data Structure | Access | Search | Insertion | Deletion | Space |
|--------------|--------|--------|-----------|----------|-------|
| Array | O(1) | O(n) | O(1) | O(1) | O(n) |
| Linked List | O(n) | O(n) | O(1) | O(1) | O(n) |
| Stack | O(n) | O(n) | O(1) | O(1) | O(n) |
| Queue | O(n) | O(n) | O(1) | O(1) | O(n) |
| Hash Table | N/A | O(1) | O(1) | O(1) | O(n) |
| BST | O(log n) | O(log n) | O(log n) | O(log n) | O(n) |
| Heap | O(1) | O(n) | O(log n) | O(log n) | O(n) |
| Graph (Adjacency List) | O(1) | O(V+E) | O(1) | O(1) | O(V+E) |

### Common Algorithms

| Algorithm | Time | Space | Notes |
|-----------|------|-------|-------|
| Linear Search | O(n) | O(1) | Unsorted data |
| Binary Search | O(log n) | O(1) | Sorted data |
| Bubble Sort | O(n²) | O(1) | Simple but slow |
| Selection Sort | O(n²) | O(1) | Minimizes swaps |
| Insertion Sort | O(n²) | O(1) | Good for small/nearly sorted |
| Merge Sort | O(n log n) | O(n) | Stable, good for large data |
| Quick Sort | O(n log n) avg | O(log n) | Fast in practice |
| Heap Sort | O(n log n) | O(1) | In-place, not stable |
| BFS | O(V+E) | O(V) | Shortest path in unweighted |
| DFS | O(V+E) | O(V) | Good for connectivity |
| Dijkstra | O(E log V) | O(V) | Shortest path weighted |

## 🗂️ Data Structure Cheatsheet

### Array (Java 17)
```java
// Basic operations
int[] arr = {1, 2, 3, 4, 5};
// Dynamic array using ArrayList
List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
list.add(6);                    // O(1) amortized
list.remove(list.size() - 1);   // O(1)
list.add(0, 0);                 // O(n)
list.remove(Integer.valueOf(3)); // O(n)
arr[2];                         // O(1)
```

### Linked List (Java 17)
```java
public class Node<T> {
    T data;
    Node<T> next;
    
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Basic operations
Node<Integer> head = new Node<>(1);
head.next = new Node<>(2);
```

### Stack (Java 17)
```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);           // push: O(1)
stack.pop();             // pop: O(1)
stack.peek();            // peek: O(1)
stack.size();            // size: O(1)
```

### Queue (Java 17)
```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> queue = new ArrayDeque<>();
queue.addLast(1);        // enqueue: O(1)
queue.removeFirst();     // dequeue: O(1)
queue.peekFirst();       // peek: O(1)
queue.size();            // size: O(1)
```

### Hash Table (Java 17)
```java
import java.util.HashMap;
import java.util.Map;

Map<String, String> hashMap = new HashMap<>();
hashMap.put("key", "value");           // O(1)
hashMap.get("key");                    // O(1)
hashMap.getOrDefault("key", "default"); // O(1)
hashMap.remove("key");                  // O(1)
```

### Binary Tree (Java 17)
```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

// Traversals
public void inorder(TreeNode root) {
    if (root != null) {
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
}
```

### Heap (Java 17)
```java
import java.util.PriorityQueue;

PriorityQueue<Integer> heap = new PriorityQueue<>();
heap.offer(1);    // O(log n)
heap.poll();       // O(log n)
heap.peek();       // O(1) peek
```

### Graph (Java 17)
```java
import java.util.*;

// Adjacency list
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(0, List.of(1, 2));
graph.put(1, List.of(0, 2));
graph.put(2, List.of(0, 1));

// BFS
public void bfs(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        if (!visited.contains(node)) {
            visited.add(node);
            queue.addAll(graph.getOrDefault(node, List.of()));
        }
    }
}
```

## 🔄 Algorithm Cheatsheet

### Binary Search (Java 17)
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;  // Prevents overflow
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

### Two Pointers (Java 17)
```java
public int[] twoPointers(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int current = arr[left] + arr[right];
        if (current == target) {
            return new int[]{left, right};
        } else if (current < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[]{};
}
```

### Sliding Window (Fixed) (Java 17)
```java
public int maxSumSubarray(int[] arr, int k) {
    int windowSum = 0;
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    int maxSum = windowSum;
    
    for (int i = k; i < arr.length; i++) {
        windowSum += arr[i] - arr[i - k];
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}
```

### Sliding Window (Variable) (Java 17)
```java
public int longestSubstring(String s) {
    Set<Character> charSet = new HashSet<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        while (charSet.contains(s.charAt(right))) {
            charSet.remove(s.charAt(left));
            left++;
        }
        charSet.add(s.charAt(right));
        maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
}
```

### DFS (Recursive) (Java 17)
```java
public void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
    if (visited.contains(node)) {
        return;
    }
    visited.add(node);
    for (int neighbor : graph.getOrDefault(node, List.of())) {
        dfs(neighbor, visited, graph);
    }
}
```

### BFS (Iterative) (Java 17)
```java
public void bfs(int start, Map<Integer, List<Integer>> graph) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        if (!visited.contains(node)) {
            visited.add(node);
            queue.addAll(graph.getOrDefault(node, List.of()));
        }
    }
}
```

### Merge Sort
```python
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)
```

### Quick Sort
```python
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)
```

## 💎 Dynamic Programming Patterns

### 1D DP (Java 17)
```java
// Fibonacci
public int fib(int n) {
    if (n <= 1) {
        return n;
    }
    int[] dp = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```

### 2D DP (Java 17)
```java
// Unique Paths
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
        dp[0][j] = 1;
    }
    
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}
```

### LCS (Java 17)
```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[m][n];
}
```

## 🎯 System Design Cheatsheet

### Load Balancing Algorithms
```python
# Round Robin
servers = ['s1', 's2', 's3']
current = 0
def get_server():
    global current
    server = servers[current % len(servers)]
    current += 1
    return server

# Least Connections
connections = {'s1': 5, 's2': 2, 's3': 8}
def get_least_connected():
    return min(connections, key=connections.get)
```

### Caching Strategies
```python
# Cache Aside
def get_user(user_id):
    user = cache.get(f"user:{user_id}")
    if user:
        return user
    user = db.query(user_id)
    cache.set(f"user:{user_id}", user, ttl=3600)
    return user

# Write Through
def update_user(user_id, data):
    cache.set(f"user:{user_id}", data, ttl=3600)
    db.update(user_id, data)
```

### CAP Theorem Quick Reference
```python
# CP: Consistency + Partition Tolerance
# Choose when: Data consistency is critical
# Example: Financial systems, inventory

# AP: Availability + Partition Tolerance
# Choose when: System availability is critical
# Example: Social media feeds, caching

# CA: Consistency + Availability
# Choose when: Network partitions are unlikely
# Example: Single-node databases
```

## 🔍 Problem-Solving Patterns

### Two Sum Pattern (Java 17)
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (seen.containsKey(complement)) {
            return new int[]{seen.get(complement), i};
        }
        seen.put(nums[i], i);
    }
    return new int[]{};
}
```

### Sliding Window Pattern (Java 17)
```java
public int slidingWindow(int[] arr, int k) {
    int windowSum = 0;
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    int maxSum = windowSum;
    
    for (int i = k; i < arr.length; i++) {
        windowSum += arr[i] - arr[i - k];
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}
```

### Fast-Slow Pointers (Java 17)
```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true;
        }
    }
    return false;
}
```

### Merge Intervals Pattern (Java 17)
```java
public int[][] mergeIntervals(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    List<int[]> merged = new ArrayList<>();
    
    for (int[] interval : intervals) {
        if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
            merged.add(interval);
        } else {
            merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
        }
    }
    return merged.toArray(new int[merged.size()][]);
}
```

## 📊 System Design Calculations

### Capacity Estimation
```python
# Requests per second
qps = daily_users * daily_requests_per_user / seconds_per_day

# Storage requirements
storage = total_records * record_size * replication_factor

# Bandwidth requirements
bandwidth = qps * average_response_size
```

### Database Sharding
```python
# Hash-based sharding
def get_shard(key, num_shards):
    return hash(key) % num_shards

# Range-based sharding
def get_shard_range(key, ranges):
    for shard_id, (start, end) in enumerate(ranges):
        if start <= key < end:
            return shard_id
    return len(ranges) - 1
```

## 🚨 Common Pitfalls

### Array Pitfalls
```python
# ❌ Wrong: Modifying array while iterating
for i in range(len(arr)):
    if arr[i] == target:
        arr.pop(i)  # This will skip elements

# ✅ Correct: Create new array or iterate backwards
result = [x for x in arr if x != target]
```

### Recursion Pitfalls
```python
# ❌ Wrong: No base case
def factorial(n):
    return n * factorial(n - 1)  # Infinite recursion

# ✅ Correct: Include base case
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)
```

### String Pitfalls
```python
# ❌ Wrong: Strings are immutable
s = "hello"
s[0] = "H"  # This will fail

# ✅ Correct: Create new string
s = "H" + s[1:]
```

## 🎓 Interview Tips

### Problem-Solving Framework
1. **Understand**: Clarify requirements and constraints
2. **Plan**: Choose appropriate data structure/algorithm
3. **Implement**: Write clean, readable code
4. **Test**: Check edge cases and verify
5. **Optimize**: Analyze complexity and improve if needed

### Communication Tips
- Think aloud during problem-solving
- Ask clarifying questions
- Explain your approach before coding
- Discuss trade-offs of different solutions
- Handle feedback constructively

### Time Management
- Easy problems: 5-10 minutes
- Medium problems: 15-25 minutes
- Hard problems: 30-45 minutes
- System design: 45 minutes

## 📱 Quick Reference Resources

### LeetCode Tags
- Arrays: `array`
- Strings: `string`
- Linked Lists: `linked-list`
- Trees: `tree`
- Graphs: `graph`
- Dynamic Programming: `dynamic-programming`
- Backtracking: `backtracking`
- Greedy: `greedy`

### System Design Components
- **Load Balancer**: NGINX, HAProxy, AWS ELB
- **Caching**: Redis, Memcached, CDN
- **Database**: PostgreSQL, MySQL, MongoDB
- **Message Queue**: RabbitMQ, Kafka, AWS SQS
- **Monitoring**: Prometheus, Grafana, CloudWatch

## 🔧 Debugging Checklist

### Code Review Checklist
- [ ] Edge cases handled
- [ ] Input validation
- [ ] Time complexity optimal
- [ ] Space complexity reasonable
- [ ] Code is readable
- [ ] Variable names meaningful
- [ ] Comments added if needed
- [ ] No off-by-one errors

### Common Bugs
- **Off-by-one errors**: Check loop bounds
- **Null pointer exceptions**: Check for None
- **Integer overflow**: Use appropriate types
- **Memory leaks**: Clean up resources
- **Race conditions**: Handle concurrency

## 📈 Performance Optimization

### Optimization Strategies
1. **Algorithm choice**: Choose O(log n) over O(n)
2. **Data structure choice**: Use hash tables for lookups
3. **Caching**: Store computed results
4. **Early termination**: Break when possible
5. **Batch operations**: Process in groups

### Memory Optimization
1. **Reuse variables**: Avoid unnecessary allocations
2. **Use generators**: For large sequences
3. **Clean up explicitly**: Release resources
4. **Choose right types**: Use appropriate data types

## 🎯 Success Metrics

### Readiness Indicators
- ✅ Can solve medium problems in 20 minutes
- ✅ Can explain Big O complexity
- ✅ Can design basic systems
- ✅ Can communicate clearly
- ✅ Can handle pressure
- ✅ Can learn from mistakes

### Improvement Signs
- Solving problems faster
- Higher accuracy rate
- Better code quality
- Clearer communication
- More confident approach

---

**Keep this reference handy during practice sessions!** → [Back to Overview](./00_LEARNING_MAP_OVERVIEW.md)
