class Solution {
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
        // find pivot
        int pivot = -1;
        int n=nums.length;
        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                pivot=i;
                break;
            }
        }
        if(pivot == -1){
            reverse(nums,0,n-1);
            return;
        }

        // right most num > pivot

        for(int i=n-1;i>pivot;i--){
            if(nums[i]>nums[pivot]){
                //swap
                int temp=nums[i];
                nums[i]=nums[pivot];
                nums[pivot]=temp;
                break;
            }
        }

        //reverse nums after pivot
        reverse(nums,pivot+1,n-1); 
    }
}