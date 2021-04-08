package com.hq.learnning.leetcode.doublepointer;


/**
 * 左右指针在数组中实际是指两个索引值，⼀般初始化为 left = 0, right = nums.length - 1
 *
 * 公式：
 * int binarySearch(int[] nums, int target) {
 *      int left = 0;
 *      int right = nums.length - 1;
 *      while(left <= right) {
 *          int mid = (right + left) / 2;  //这里是个坑，可能溢出。改为： left + (right - left)/2
 *          if(nums[mid] == target)
 *              return mid;
 *          else if (nums[mid] < target)
 *              left = mid + 1;
 *          else if (nums[mid] > target)
 *              right = mid - 1;
 *          }
 *      return -1;
 * }
 *
 */
public class BSuseLeftRightPointer {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        int target = 5;
        int result = binarySearchUseDoublePointer(nums, target);
        System.out.println("target index is " + result);


        //两数之和
        int [] indexes = getCombineTargetIndex(nums, target);
        System.out.println("getCombineTargetIndex "+ indexes[0] + "," + indexes[1]);


        //翻转数组
        reverse(nums);


        //滑动窗口
        /**
         * int left = 0, right = 0;
         *
         * while (right < s.size()) {
         *     // 增大窗口
         *     window.add(s[right]);
         *     right++;
         *
         *     while (window needs shrink) {
         *         // 缩小窗口
         *         window.remove(s[left]);
         *         left++;
         *     }
         * }
         */


    }

    /**
     * 查找两数之和等于目标值的下标（从1开始）
     * @param nums
     * @param target
     * @return
     */
    private static int[] getCombineTargetIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int sum = nums[left] + nums[right];

            if (sum == target){
                return new int[]{left + 1, right + 1};
            }else if (sum < target){
                left++;
            }else {
                right--;
            }
        }

        return new int[]{-1,-1};
    }

    /**
     * 在有序数组中查找目标值
     * 默认升序
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearchUseDoublePointer(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }

        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid;
            }else {
                left = mid;
            }
        }

        return -1;
    }

    /**
     * 翻转数组
     *
     */
    public static void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // swap(nums[left], nums[right])
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++; right--;
        }
    }

}
