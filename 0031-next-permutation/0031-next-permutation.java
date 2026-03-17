class Solution {
    // reverse function
    public static void reverse(int nums[], int s, int e){
        while(s <= e){
            int temp=nums[s];
            nums[s]=nums[e];
            nums[e]=temp;
            s++;
            e--;
        }
    }
    public void nextPermutation(int[] nums) {
        int pivot=-1;
        int n=nums.length;
        for(int i=n-2;i>=0;i--){
            if(nums[i]< nums[i+1]){
                pivot=i;
                break;
            }
        }
        if(pivot==-1){
            reverse(nums,0,n-1);
            return;
        }
        // step 2 finding right most element> pivot
        for(int i=n-1;i>pivot;i--){
            if(nums[i]>nums[pivot]){
                int temp=nums[i];
                nums[i]=nums[pivot];
                nums[pivot]=temp;
                break;
            }

        }
        // step 3 reverse element from pivot +1
            reverse(nums,pivot+1,n-1);
    }
}