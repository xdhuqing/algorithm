package com.hq.learnning.leetcode;

/**
 * 用状态机解决股票最大盈利问题
 * 1 操作： 买 卖 空
 * 2 持有： 有 无
 * 3 操作次数： k（一买+一卖 对应 一次操作）
 * 4 目标： 盈利最大（最后一天，手里无股票，才可能盈利最大）
 *
 * 状态转移方程
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 *                 max( 选择 rest , 选择 sell )
 * 解释：今天我没有持有股票，有两种可能：
 * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
 * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *                 max( 选择 rest , 选择 buy )
 * 解释：今天我持有着股票，有两种可能：
 * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
 * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
 *
 * 初始状态
 * dp[-1][k][0] = 0
 * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0
 * dp[-1][k][1] = -infinity
 * 解释：还没开始的时候，是不可能持有股票的，⽤负⽆穷表⽰这种不可能。
 * dp[i][0][0] = 0
 * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0
 * dp[i][0][1] = -infinity
 * 解释：不允许交易的情况下，是不可能持有股票的，⽤负⽆穷表⽰这种不可能。
 *
 */
public class StateMachine4Stock {

	public static void main(String[] args) {

//		int[] prices = new int[]{3,2,3,6,5,1,3,1,3};
		int[] prices = new int[]{2,4,1};
		int maxProfit = 0;
		int k = 2;

		if (k == 1){
			maxProfit = maxProfitWithKequals1(prices);
//		}else if (k == 2){
//			maxProfit = maxProfit_k_2(prices);
		}else if (k == Integer.MAX_VALUE){
			maxProfit = maxProfit_k_inf(prices);
		}else {
			maxProfit = maxProfit_k_any(k, prices);
		}

		System.out.println(String.format("k = [%d] ,  maxProfit = [%d]", k , maxProfit));

	}


	/**
	 * k = 1
	 * 简化模板，由三维变二维
	 * dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
	 * dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
	 * @param prices
	 * @return
	 */
	public static int maxProfitWithKequals1(int[] prices){
		int n = prices.length;
		int[][] dp = new int[n][2];
		for (int i = 0; i < n; i++) {

			if (i - 1 < 0){
				dp[i][0] = 0;
				dp[i][1] = -prices[i];
				continue;
			}

			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
			dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
		}
		return dp[n - 1][0];
	}

	/**
	 * 注意⼀下状态转移
	 * ⽅程，新状态只和相邻的⼀个状态有关，其实不⽤整个 dp 数组，只需要⼀
	 * 个变量储存相邻的那个状态就⾜够了，这样可以把空间复杂度降到 O(1):
	 * @param prices
	 * @return
	 */
	public static int maxProfit_k_1_simple(int[] prices) {
		int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
		int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
			dp_i_1 = Math.max(dp_i_1, -prices[i]);
		}
		return dp_i_0;
	}


	/**
	 *
	 *      int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
	 * 		int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
	 * 		for (int price : prices) {
	 * 			dp_i20 = Math.max(dp_i20, dp_i21 + price);
	 * 			dp_i21 = Math.max(dp_i21, dp_i10 - price);
	 * 			dp_i10 = Math.max(dp_i10, dp_i11 + price);
	 * 			dp_i11 = Math.max(dp_i11, -price);
	 *                }
	 * 		return dp_i20;
	 * @param prices
	 * @return
	 */
	public static int maxProfit_k_2(int[] prices) {
		int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
		int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
		for (int price : prices) {
			dp_i20 = Math.max(dp_i20, dp_i21 + price);
			dp_i21 = Math.max(dp_i21, dp_i10 - price);
			dp_i10 = Math.max(dp_i10, dp_i11 + price);
			dp_i11 = Math.max(dp_i11, -price);
		}
		return dp_i20;
	}


	/**
	 * ⼀次交易由买⼊和卖出构成，⾄少需要两天。所以说有效的限制 k 应该不超
	 * 过 n/2，如果超过，就没有约束作⽤了，相当于 k = +infinity。
	 * @param max_k
	 * @param prices
	 * @return
	 */
	public static int maxProfit_k_any(int max_k, int[] prices) {
		int n = prices.length;
		if (max_k > n / 2)
			return maxProfit_k_inf(prices);
		int[][][] dp = new int[n][max_k + 1][2];
		for (int i = 0; i < n; i++)
			for (int k = max_k; k >= 1; k--) {
				if (i - 1 == -1) { /* 处理 base case */
					dp[i][k][0] = 0;
					dp[i][k][1] = Integer.MIN_VALUE;
					continue;
				}
				dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i
						]);
				dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
			}
		return dp[n - 1][max_k][0];
	}

	/**
	 * k 无限制
	 *
	 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
	 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
	 *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
	 * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
	 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
	 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
	 *
	 *
	 * @param prices
	 * @return
	 */
	private static int maxProfit_k_inf(int[] prices) {
		int n = prices.length;
		// base case: dp[-1][0] = 0, dp[-1][1] = -infinity
		int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int temp = dp_i_0;
			// dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
			dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
			// dp[i][1] = max(dp[i-1][1], -prices[i])
			dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
		}
		return dp_i_0;
	}


}
