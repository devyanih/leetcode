import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // Step 2: Process each query
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);
            
            // If only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }
            
            int pos = Collections.binarySearch(list, q);
            
            int left = (pos - 1 + list.size()) % list.size();
            int right = (pos + 1) % list.size();
            
            int leftIndex = list.get(left);
            int rightIndex = list.get(right);
            
            int distLeft = Math.min(Math.abs(q - leftIndex), n - Math.abs(q - leftIndex));
            int distRight = Math.min(Math.abs(q - rightIndex), n - Math.abs(q - rightIndex));
            
            result.add(Math.min(distLeft, distRight));
        }
        
        return result;
    }
}