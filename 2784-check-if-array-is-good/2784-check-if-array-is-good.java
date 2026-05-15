class Solution {

    public boolean isGood(int[] nums) {

        int n = 0;

        // Find maximum element
        for (int num : nums) {
            n = Math.max(n, num);
        }

        // Length must be n + 1
        if (nums.length != n + 1) {
            return false;
        }

        int[] freq = new int[n + 1];

        for (int num : nums) {

            if (num > n) {
                return false;
            }

            freq[num]++;
        }

        // 1 to n-1 should appear once
        for (int i = 1; i < n; i++) {

            if (freq[i] != 1) {
                return false;
            }
        }

        // n should appear twice
        return freq[n] == 2;
    }
}