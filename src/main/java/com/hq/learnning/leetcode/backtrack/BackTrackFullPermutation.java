package com.hq.learnning.leetcode.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯
 * for 选择 in 选择列表:
 * 		# 做选择
 * 		将该选择从选择列表移除
 * 		路径.add(选择)
 * 		backtrack(路径, 选择列表)
 * 		# 撤销选择
 * 		路径.remove(选择)
 * 		将该选择再加⼊选择列表
 *
 *
 */
public class BackTrackFullPermutation {

	private static List<List<Integer>> res = new LinkedList<>();

	public static void main(String[] args) {

		int [] nums = new int[]{1,2,3};
		long startTime = System.nanoTime();
		fullPermutation(nums);
		long endTime = System.nanoTime();

		System.out.println(String.format("trace num={%d}, cost time = {%d} nano",
				res.size(), endTime-startTime));
		for (List<Integer> trace : res){
			for (int num : trace){
				System.out.print(num);
			}
			System.out.println();
		}


	}


	/**
	 * 全排列：非重复数组
	 */
	public static void fullPermutation(int [] nums){
		// 记录「路径」
		LinkedList<Integer> track = new LinkedList<>();
		backtrack(nums, track);
	}

	/**
	 * 回溯法实现全排列
	 * @param nums
	 * @param track
	 */
	public static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
		if (track.size() == nums.length) {
			res.add(new LinkedList<>(track));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
		    // 排除不合法的选择
			if (track.contains(nums[i]))
				continue;
			// 做选择
			track.add(nums[i]);
			// 进⼊下⼀层决策树
			backtrack(nums, track);
			// 取消选择
			track.removeLast();
		}
	}
}


