class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer>hm=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int reqNum=target-nums[i];
            if(hm.containsKey(reqNum)){
                int arr[]={hm.get(reqNum),i};
                return arr;
            }
            else{
                hm.put(nums[i],i);
            }
        }
        return null;
    }
}