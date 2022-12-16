package com.adp.currency.change.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adp.currency.change.helper.CurrencyChangeHelper;
import com.adp.currency.change.to.CoinChangeResponse;
import com.adp.currency.change.validator.CurrencyChangeValidator;

@RestController
public class CurrencyChangeController {

	@Autowired
	private CurrencyChangeValidator validator;

	@Autowired
	private CurrencyChangeHelper helper;

	@GetMapping("/coin/for/{bill}/dollar-bill")
	public CoinChangeResponse getCoinChange(@PathVariable("bill") Integer bill,
			@RequestParam(required = false, defaultValue = "false", value = "maxCoins") Boolean maxCoins)
			throws Exception {

		validator.validateCurrencyChangeRequest(bill);

		CoinChangeResponse res = helper.convertToCoins(bill, maxCoins);

		return res;
	}
}
