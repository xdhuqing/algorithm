package com.hq.learnning.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChangeUseRecursiveOrSM {

	//增加缓存
private static Map<Integer, Integer> cache = new HashMap<>();

	public static void main(String[] args) {


		List<Integer> coins = new ArrayList<>();
		coins.add(1);
		coins.add(2);
		coins.add(5);
		int amount = 12;
		long startTime = System.nanoTime();
		int result = coinChangeRecursive(coins, amount);
		long endTime = System.nanoTime();
		System.out.println(String.format("use recursive, result={%d}, time={%d}nanoTime",
				result, endTime-startTime));

		startTime = System.nanoTime();
		result = coinChangeArray(coins, amount);
		endTime = System.nanoTime();
		System.out.println(String.format("user array, result={%d}, time={%d}nanoTime",
				result, endTime-startTime));

		/**
		 * use recursive, result={3}, time={274437}nanoTime
		 * user array, result={3}, time={42393}nanoTime
		 */

	}

	/**
	 * 递归解决方案
	 * 缓存解决重叠子问题
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChangeRecursive(List<Integer> coins, int amount){
		//优先使用缓存
		if (cache.containsKey(amount)){
			return cache.get(amount);
		}

		//终止条件
		if (amount <= 0){
			return amount == 0 ? 0 : -1;
		}
		//初始化结果
		int result = Integer.MAX_VALUE;

		//递归处理
		for (int coin : coins){
			int temp = coinChangeRecursive(coins, amount - coin);
			//跳过无解选项
			if (temp < 0){
				continue;
			}
			result = Math.min(result, 1 + temp);
			cache.put(amount, result);
		}
		//返回结果
		return result == Integer.MAX_VALUE ? -1 : result;
	}


	/**
	 * 这里有个坑：
	 * ArrayList dp.add(index, VALUE); 不等于  dp[index] = VALUE
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChangeArray(List<Integer> coins, int amount){

		int INIT_VALUE = amount + 1;
		int ARRAY_SIZE = amount + 1;
		int[] dp = new int[ARRAY_SIZE];

		//初始化
		dp[0] = 0;
		for (int index = 1; index < ARRAY_SIZE;index++){
			dp[index] = INIT_VALUE;
		}
		//循环处理，将结果记录在数组中
		for (int i = 0; i < ARRAY_SIZE; i++){
			for (int coin : coins){
				if (i - coin < 0){
					continue;
				}
				int value = Math.min(dp[i], dp[i-coin] + 1);
				dp[i] = value;
			}
		}

		return dp[amount] == INIT_VALUE ? -1 : dp[amount] ;
	}


}
