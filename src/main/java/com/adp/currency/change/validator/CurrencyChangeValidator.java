package com.adp.currency.change.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.adp.currency.change.util.CurrencyChangeConstant;

@Component
public class CurrencyChangeValidator {

	public void validateCurrencyChangeRequest(Integer bill) {

		if (!CurrencyChangeConstant.DOLLAR_CENTS_MAP.containsKey(bill)) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bill " + bill + " is not available");
		}
	}
}
