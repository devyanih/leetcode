import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // store last two indices for each number
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            
            map.putIfAbsent(val, new ArrayDeque<>());
            Deque<Integer> dq = map.get(val);
            
            dq.addLast(i);
            
            // keep only last 3 indices
            if (dq.size() > 3) {
                dq.removeFirst();
            }
            
            // if we have 3 indices
            if (dq.size() == 3) {
                int first = dq.peekFirst();
                int last = dq.peekLast();
                
                int dist = 2 * (last - first);
                minDist = Math.min(minDist, dist);
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}