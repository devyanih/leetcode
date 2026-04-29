import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Step 1: Flatten grid
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        
        // Step 2: Check possibility
        int rem = list.get(0) % x;
        for (int num : list) {
            if (num % x != rem) {
                return -1;
            }
        }
        
        // Step 3: Sort
        Collections.sort(list);
        
        // Step 4: Get median
        int median = list.get(list.size() / 2);
        
        // Step 5: Count operations
        int operations = 0;
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }
        
        return operations;
    }
}