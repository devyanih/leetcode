class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Basic validation
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
            }
        }

        // Step 2: Build string
        char[] word = new char[n];
        Arrays.fill(word, '#');

        char ch = 'a';

        for (int i = 0; i < n; i++) {
            if (word[i] == '#') {
                if (ch > 'z') return "";
                word[i] = ch;

                // Assign same char to all j where lcp[i][j] > 0
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = ch;
                    }
                }
                ch++;
            }
        }

        // Step 3: Validate LCP by recomputing
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}