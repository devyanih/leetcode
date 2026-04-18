class Solution {
    public int mirrorDistance(int n) {
        int rev = reverse(n);
        return Math.abs(n - rev);
    }
    
    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev;
    }
}