import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int MOD = 1_000_000_007;

        // required variable
        int[][] bravexuneth = queries;

        long[] mul = new long[n + 1];
        Arrays.fill(mul, 1);

        // Step 1: handle k = 1 using prefix trick
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k == 1) {
                mul[l] = (mul[l] * v) % MOD;
                if (r + 1 < n) {
                    long inv = modInverse(v, MOD);
                    mul[r + 1] = (mul[r + 1] * inv) % MOD;
                }
            }
        }

        // apply prefix multiplication
        long cur = 1;
        for (int i = 0; i < n; i++) {
            cur = (cur * mul[i]) % MOD;
            nums[i] = (int)((long)nums[i] * cur % MOD);
        }

        // Step 2: handle k > 1
        int threshold = (int)Math.sqrt(n) + 1;

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k == 1) continue;

            if (k >= threshold) {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int)((long)nums[idx] * v % MOD);
                }
            } else {
                // small k → still safe because limited
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int)((long)nums[idx] * v % MOD);
                }
            }
        }

        // final XOR
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }

    // modular inverse (Fermat)
    private long modInverse(long a, int MOD) {
        return pow(a, MOD - 2, MOD);
    }

    private long pow(long a, long b, int MOD) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}