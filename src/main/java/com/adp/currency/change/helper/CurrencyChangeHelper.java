package com.adp.currency.change.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adp.currency.change.to.CoinChangeResponse;
import com.adp.currency.change.util.CurrencyChangeConstant;

@Component
public class CurrencyChangeHelper {

	@Value("${1.cents.coin.count}")
	private int centsCount1;

	@Value("${5.cents.coin.count}")
	private int centsCount5;

	@Value("${10.cents.coin.count}")
	private int centsCount10;

	@Value("${25.cents.coin.count}")
	private int centsCount25;

	public CoinChangeResponse convertToCoins(Integer bill, Boolean maxCoins) throws Exception {

		int[] minDenominationCoins = { 25, 10, 5, 1 };
		int[] maxDenominationCoins = { 1, 5, 10, 25 };

		int[] counts = { centsCount25, centsCount10, centsCount5, centsCount1 };
		int targetAmount = CurrencyChangeConstant.DOLLAR_CENTS_MAP.get(bill);
		int[] nums = combine(maxCoins ? maxDenominationCoins : minDenominationCoins, counts);
		int coinsCount = minCount(nums, targetAmount, 0, 0, 0);

		if (coinsCount < 0) {
			throw new Exception("Coins not available to make a change");
		}

		CoinChangeResponse res = new CoinChangeResponse();
		res.setBill(bill);
		res.setConins(coinsCount);
		return res;
	}

	private int minCount(int[] nums, int target, int sum, int current, int count) {
		if (sum == target)
			return count;
		if (current >= nums.length)
			return -1;
		if (sum + nums[current] <= target) {
			return minCount(nums, target, sum + nums[current], current + 1, count + 1);
		} else {
			return minCount(nums, target, sum, current + 1, count);
		}
	}

	private int[] combine(int[] coins, int[] counts) {
		int sum = 0;
		for (int count : counts) {
			sum += count;
		}
		int[] returnArray = new int[sum];
		int returnArrayIndex = 0;
		for (int i = 0; i < coins.length; i++) {
			int count = counts[i];
			while (count != 0) {
				returnArray[returnArrayIndex] = coins[i];
				returnArrayIndex++;
				count--;
			}
		}
		return returnArray;
	}

}
