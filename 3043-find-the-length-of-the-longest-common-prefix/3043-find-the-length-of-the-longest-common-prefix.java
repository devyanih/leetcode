class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();

        // Store all prefixes of arr1 numbers
        for (int num : arr1) {
            while (num > 0) {
                set.add(num);
                num /= 10;
            }
        }

        int ans = 0;

        // Check prefixes for arr2 numbers
        for (int num : arr2) {
            int temp = num;

            while (temp > 0) {
                if (set.contains(temp)) {
                    ans = Math.max(ans, getLength(temp));
                    break; // longest prefix for this number found
                }
                temp /= 10;
            }
        }

        return ans;
    }

    private int getLength(int num) {
        int len = 0;

        while (num > 0) {
            len++;
            num /= 10;
        }

        return len;
    }
}