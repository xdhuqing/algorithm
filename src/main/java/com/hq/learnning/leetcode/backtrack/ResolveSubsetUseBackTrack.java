package com.hq.learnning.leetcode.backtrack;

import java.util.*;

/**
 * 回溯算法实现子集、组合、排列
 * 回溯：DFS
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 *
 * 解法一：子集问题可以利用数学归纳思想，假设已知一个规模较小的问题的结果，思考如何推导出原问题的结果。
 * 解法二：也可以用回溯算法，要用 start 参数排除已选择的数字。
 *
 * 组合问题利用的是回溯思想，结果可以表示成树结构，我们只要套用回溯算法模板即可，关键点在于要用一个 start 排除已经选择过的数字。
 *
 * 排列问题是回溯思想，也可以表示成树结构套用算法模板，不同之处在于使用 contains 方法排除已经选择的数字，前文有详细分析，这里主要是和组合问题作对比。
 *
 * 对于这三个问题，关键区别在于回溯树的结构，不妨多观察递归树的结构，很自然就可以理解代码的含义了。
 *
 */
public class ResolveSubsetUseBackTrack {

    public static void main(String[] args) {
        int [] nums = new int[]{1,2,3};
        //子集
        List<List<Integer>> result = getSubsetUseBackTrack(nums);
        print2DegreeList(result);

        System.out.println("组合 C(n,k)");
        //组合 C(n,k)
        int n = 4;
        int k = 2;
        result = getCombineUseBackTrack(n, k);
        print2DegreeList(result);


        System.out.println("排列 A(n,n)");
        //排列 A(n,n)
        result = getArrangeUseBackTrack(nums);
        print2DegreeList(result);


    }


    /**
     * 获取排列arrange
     * @param nums
     * @return
     */
    private static List<List<Integer>> getArrangeUseBackTrack(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length<2){
            return res;
        }
        // 记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();

        backtrackForArrange(res, nums, track);

        return res;
    }

    private static void backtrackForArrange(List<List<Integer>> res, int[] nums, LinkedList<Integer> track) {

        if (track.size() == nums.length){
            LinkedList<Integer> subset = new LinkedList<>();
            subset.addAll(track);
            res.add(subset);
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if (track.contains(nums[i])){
                continue;
            }
            track.offerLast(nums[i]);
            backtrackForArrange(res, nums, track);
            track.pollLast();
        }
    }


    /**
     * 获取组合
     * @param n
     * @param k
     * @return
     */
    private static List<List<Integer>> getCombineUseBackTrack(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k<=0 || n<=0){
            return res;
        }
        // 记录走过的路径
        Set<Integer> track = new HashSet<>();

        backtrackForCombine(res, n, k, 1, track);

        return res;
    }

    /**
     * 获取组合
     * @param res
     * @param n
     * @param k
     * @param start
     * @param track
     */
    private static void backtrackForCombine(List<List<Integer>> res, int n,int k, int start, Set<Integer> track) {

        if (track.size() == k){
            ArrayList<Integer> subset = new ArrayList<>();
            subset.addAll(track);
            res.add(subset);
            return;
        }

        for(int i = start; i <= n; i++){
            track.add(i);
            backtrackForCombine(res, n, k,i+1, track);
            track.remove(i);
        }
    }

    /**
     * 获取子集
     * @param nums
     * @return
     */
    private static List<List<Integer>> getSubsetUseBackTrack(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 记录走过的路径
        Set<Integer> track = new HashSet<>();

        backtrack(res, nums, 0, track);

        return res;
    }

    /**
     *
     * @param res
     * @param nums
     * @param start
     * @param track
     */
    private static void backtrack(List<List<Integer>> res, int[] nums, int start, Set<Integer> track) {

        ArrayList<Integer> subset = new ArrayList<>();
        subset.addAll(track);
        res.add(subset);
// *     for 选择 in 选择列表:
// *         做选择
//                *         backtrack(路径, 选择列表)
//                *         撤销选择
        for(int i=start; i<nums.length; i++){
            track.add(nums[i]);
            backtrack(res, nums, i+1, track);
            track.remove(nums[i]);
        }
    }


    public static void print2DegreeList(List<List<Integer>> result){
        for (List<Integer> list : result){
            System.out.print("[");
            for (Integer i :list){
                System.out.print(i+",");
            }
            System.out.println("]");
        }
    }

}
