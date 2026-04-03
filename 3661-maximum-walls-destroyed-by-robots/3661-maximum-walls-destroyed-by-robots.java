import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Arrays.sort(walls);

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] leftCount = new int[n];
        int[] rightCount = new int[n];

        int[] L = new int[n];
        int[] R = new int[n];

        // compute ranges
        for (int i = 0; i < n; i++) {
            int pos = arr[i][0];
            int dist = arr[i][1];

            L[i] = pos - dist;
            R[i] = pos + dist;

            if (i > 0) L[i] = Math.max(L[i], arr[i - 1][0] + 1);
            if (i < n - 1) R[i] = Math.min(R[i], arr[i + 1][0] - 1);

            leftCount[i] = count(walls, L[i], pos);
            rightCount[i] = count(walls, pos, R[i]);
        }

        int[][] dp = new int[n][2];

        dp[0][0] = leftCount[0];
        dp[0][1] = rightCount[0];

        for (int i = 1; i < n; i++) {

            // overlap between prev RIGHT and curr LEFT
            int overlap = overlap(walls, arr[i-1][0], R[i-1], L[i], arr[i][0]);

            // LEFT
            dp[i][0] = Math.max(
                dp[i-1][0] + leftCount[i],
                dp[i-1][1] + leftCount[i] - overlap
            );

            // RIGHT
            dp[i][1] = Math.max(
                dp[i-1][0] + rightCount[i],
                dp[i-1][1] + rightCount[i]
            );
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    private int count(int[] walls, int l, int r) {
        int left = lowerBound(walls, l);
        int right = upperBound(walls, r);
        return Math.max(0, right - left);
    }

    private int overlap(int[] walls, int l1, int r1, int l2, int r2) {
        int l = Math.max(l1, l2);
        int r = Math.min(r1, r2);
        if (l > r) return 0;
        return count(walls, l, r);
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}