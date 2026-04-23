import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Step 1: group indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Step 2: process each group
        for (List<Integer> list : map.values()) {
            int size = list.size();
            
            // prefix sum
            long[] prefix = new long[size];
            prefix[0] = list.get(0);
            
            for (int i = 1; i < size; i++) {
                prefix[i] = prefix[i - 1] + list.get(i);
            }
            
            for (int k = 0; k < size; k++) {
                int idx = list.get(k);
                
                long left = 0;
                long right = 0;
                
                if (k > 0) {
                    left = (long) idx * k - prefix[k - 1];
                }
                
                if (k < size - 1) {
                    right = (prefix[size - 1] - prefix[k]) 
                            - (long) idx * (size - k - 1);
                }
                
                res[idx] = left + right;
            }
        }
        
        return res;
    }
}