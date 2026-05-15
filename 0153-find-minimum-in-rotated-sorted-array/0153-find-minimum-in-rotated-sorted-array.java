class Solution {

    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // Minimum in right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }

            // Minimum at mid or left half
            else {
                right = mid;
            }
        }

        return nums[left];
    }
}