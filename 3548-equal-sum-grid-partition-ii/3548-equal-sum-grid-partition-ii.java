import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        long[] rowSums = new long[m];
        long[] colSums = new long[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
                rowSums[i] += grid[i][j];
                colSums[j] += grid[i][j];
            }
        }

        // 1. Horizontal Cuts
        long topSum = 0;
        Map<Integer, Integer> topFreq = new HashMap<>();
        for (int i = 0; i < m - 1; i++) {
            topSum += rowSums[i];
            // Update frequency map with the current row being added to the top section
            for (int j = 0; j < n; j++) {
                topFreq.put(grid[i][j], topFreq.getOrDefault(grid[i][j], 0) + 1);
            }
            
            long bottomSum = totalSum - topSum;
            
            // Check top section (heavy)
            if (isPossible(topSum, bottomSum, topFreq, i + 1, n, grid, 0, i, 0, n - 1)) return true;
            // Check bottom section (heavy) - Note: we don't build bottomFreq to save time, 
            // we use the fact that target must exist in the grid but NOT in topFreq
        }

        // 2. Vertical Cuts
        long leftSum = 0;
        Map<Integer, Integer> leftFreq = new HashMap<>();
        for (int j = 0; j < n - 1; j++) {
            leftSum += colSums[j];
            for (int i = 0; i < m; i++) {
                leftFreq.put(grid[i][j], leftFreq.getOrDefault(grid[i][j], 0) + 1);
            }
            
            long rightSum = totalSum - leftSum;
            
            // Check left section (heavy)
            if (isPossible(leftSum, rightSum, leftFreq, m, j + 1, grid, 0, m - 1, 0, j)) return true;
        }
        
        // Final check: Since the logic above only checks the "current" section being built, 
        // we must also run the logic in reverse or check the remaining section.
        // To be most efficient, let's just re-run the scan from the other side.
        return checkReverse(grid, rowSums, colSums, totalSum);
    }

    private boolean isPossible(long heavySum, long lightSum, Map<Integer, Integer> freq, 
                               int h, int w, int[][] grid, int r1, int r2, int c1, int c2) {
        if (heavySum == lightSum) return true;
        long diff = heavySum - lightSum;
        if (diff <= 0 || diff > 1000000) return false; // Optimization: grid values max 10^5
        int target = (int) diff;

        if (!freq.containsKey(target)) return false;

        // Connectivity Rule:
        if (h > 1 && w > 1) return true; // Any cell in a 2D block
        if (h == 1) return grid[r1][c1] == target || grid[r1][c2] == target; // Ends of row
        if (w == 1) return grid[r1][c1] == target || grid[r2][c1] == target; // Ends of col
        return true; // 1x1 case
    }

    private boolean checkReverse(int[][] grid, long[] rowSums, long[] colSums, long totalSum) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Reverse Horizontal
        long botSum = 0;
        Map<Integer, Integer> botFreq = new HashMap<>();
        for (int i = m - 1; i > 0; i--) {
            botSum += rowSums[i];
            for (int j = 0; j < n; j++) botFreq.put(grid[i][j], botFreq.getOrDefault(grid[i][j], 0) + 1);
            if (isPossible(botSum, totalSum - botSum, botFreq, m - i, n, grid, i, m - 1, 0, n - 1)) return true;
        }

        // Reverse Vertical
        long rightSum = 0;
        Map<Integer, Integer> rightFreq = new HashMap<>();
        for (int j = n - 1; j > 0; j--) {
            rightSum += colSums[j];
            for (int i = 0; i < m; i++) rightFreq.put(grid[i][j], rightFreq.getOrDefault(grid[i][j], 0) + 1);
            if (isPossible(rightSum, totalSum - rightSum, rightFreq, m, n - j, grid, 0, m - 1, j, n - 1)) return true;
        }
        return false;
    }
}