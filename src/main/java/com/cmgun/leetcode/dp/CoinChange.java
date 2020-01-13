package com.cmgun.leetcode.dp;

/**
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 *
 * Created by cmgun on 2020/01/13
 */
public class CoinChange {

    /**
     * Runtime: 8 ms, faster than 93.33% of Java online submissions for Coin Change.
     * Memory Usage: 36.3 MB, less than 94.08% of Java online submissions for Coin Change.
     * @param args
     */
    public static void main(String[] args) {
        // coins = [1, 2, 5], amount = 11
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        // coins = [1,2,7,10], amount = 14
        System.out.println(coinChange(new int[]{1,2,7,10}, 14));
    }

    /**
     * 完全背包问题，f(amount)=min(f(amount-coins[i])+1)
     * 如果f(amount)>amount，则无解；
     * 剪枝算法、回溯法。
     *
     * 例子：coins=[1,2,7,10],amounts=14
     * 使用贪心法动态规划（优先取最大的值），则输出结果为10+2+2=3个，实际应该输出7+7=2个
     * 思路：使用【金额数】进行动态规划
     * dp[i]表示使用最少的数量，如dp[i-1]和coins[0]，表示1的组合
     * dp[i]=min（dp[i-1],dp[i-2],dp[i-7],dp[i-10]）
     */
    public static int coinChange(int[] coins, int amount) {
        // 初始化dp
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        // 从金额0开始，i表示金额
        for (int i = 1; i < dp.length; i++) {
            // 默认硬币个数
            dp[i] = dp.length;
            // 硬币面额遍历
            for (int j = 0; j < coins.length; j++) {
                // 当前的硬币面额，如果比当前所需面值i大，则跳过
                if (i >= coins[j]) {
                    // 如果拿了这个面额，则硬币个数+1，否则维持原状
                    // i-coins[j]表示，拿了当前的硬币后，剩余金额所需要的最少硬币数量
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        return dp[amount] == dp.length ? -1 : dp[amount];
    }
}
