class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0;
        
        long[][] prefix = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
            }
        }
        
        long[][][] dp = new long[n][n + 1][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= n; j++)
                java.util.Arrays.fill(dp[i][j], -1);
        
        // Initialize j=1
        for (int h0 = 0; h0 <= n; h0++) {
            for (int h1 = 0; h1 <= n; h1++) {
                if (h1 > h0) {
                    dp[1][h0][h1] = prefix[0][h1] - prefix[0][h0];
                } else {
                    dp[1][h0][h1] = 0;
                }
            }
        }
        
        for (int j = 2; j < n; j++) {
            for (int hPrev2 = 0; hPrev2 <= n; hPrev2++) {
                for (int hPrev = 0; hPrev <= n; hPrev++) {
                    long prevVal = dp[j - 1][hPrev2][hPrev];
                    if (prevVal < 0) continue;
                    
                    for (int hCurr = 0; hCurr <= n; hCurr++) {
                        long contribution = 0;
                        int maxNeighbor = Math.max(hPrev2, hCurr);
                        if (maxNeighbor > hPrev) {
                            contribution = prefix[j - 1][maxNeighbor] - prefix[j - 1][hPrev];
                        }
                        long newVal = prevVal + contribution;
                        if (newVal > dp[j][hPrev][hCurr]) {
                            dp[j][hPrev][hCurr] = newVal;
                        }
                    }
                }
            }
        }
        
        long ans = 0;
        for (int hPrev = 0; hPrev <= n; hPrev++) {
            for (int hLast = 0; hLast <= n; hLast++) {
                long val = dp[n - 1][hPrev][hLast];
                if (val < 0) continue;
                if (hPrev > hLast) {
                    val += prefix[n - 1][hPrev] - prefix[n - 1][hLast];
                }
                ans = Math.max(ans, val);
            }
        }
        return ans;
    }
}