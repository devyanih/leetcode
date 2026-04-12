class Solution {

    private int dist(int a, int b) {
        if (a == -1) return 0;
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minimumDistance(String word) {
        Integer[][][] dp = new Integer[word.length()][27][27];
        return dfs(word, 0, -1, -1, dp);
    }

    private int dfs(String word, int i, int f1, int f2, Integer[][][] dp) {
        if (i == word.length()) return 0;

        if (dp[i][f1 + 1][f2 + 1] != null)
            return dp[i][f1 + 1][f2 + 1];

        int curr = word.charAt(i) - 'A';

        // Move finger1
        int useF1 = dist(f1, curr) + dfs(word, i + 1, curr, f2, dp);

        // Move finger2
        int useF2 = dist(f2, curr) + dfs(word, i + 1, f1, curr, dp);

        return dp[i][f1 + 1][f2 + 1] = Math.min(useF1, useF2);
    }
}