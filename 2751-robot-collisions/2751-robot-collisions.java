import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Sort indices based on positions
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));

        Deque<Integer> stack = new ArrayDeque<>(); // Stores indices of robots moving 'R'

        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } else {
                // Robot moving 'L' - check for collisions with robots moving 'R'
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.peek();

                    if (healths[currentIndex] > healths[topIndex]) {
                        // Left robot wins
                        healths[topIndex] = 0;
                        healths[currentIndex] -= 1;
                        stack.pop();
                    } else if (healths[currentIndex] < healths[topIndex]) {
                        // Right robot wins
                        healths[currentIndex] = 0;
                        healths[topIndex] -= 1;
                        if (healths[topIndex] == 0) stack.pop(); // Should not happen per rules but safe
                    } else {
                        // Both destroyed
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                        stack.pop();
                    }
                }
            }
        }

        // Collect survivors in original input order
        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                result.add(h);
            }
        }
        return result;
    }
}