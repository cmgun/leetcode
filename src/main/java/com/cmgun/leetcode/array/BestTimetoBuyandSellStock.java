package com.cmgun.leetcode.array;

/**
 * 121. Best Time to Buy and Sell Stock
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Created by cmgun on 2022/4/1
 */
public class BestTimetoBuyandSellStock {

    public static void main(String[] args) {
        // 5
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        // 0
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int x = prices[i];
            if (x < min) {
                min = x;
            }
            int current = x - min;
            if (current > max) {
                max = current;
            }
        }
        return max;
    }
}
