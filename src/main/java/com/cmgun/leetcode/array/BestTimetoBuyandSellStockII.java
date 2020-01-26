package com.cmgun.leetcode.array;

/**
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 *
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Created by cmgun on 2020/1/26
 */
public class BestTimetoBuyandSellStockII {

    /**
     * Runtime: 1 ms, faster than 87.39% of Java online submissions for Best Time to Buy and Sell Stock II.
     * Memory Usage: 41.6 MB, less than 5.71% of Java online submissions for Best Time to Buy and Sell Stock II.
     * @param args
     */
    public static void main(String[] args) {
        // 7
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        // 4
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
        // 0
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    /**
     * 只需要计算最大收益，因此只要满足：
     *      prices[i] < prices[i+1]
     * 则两者差值就可以算进最大收益中
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        // 处理边界
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        // 最大收益
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;

    }
}
