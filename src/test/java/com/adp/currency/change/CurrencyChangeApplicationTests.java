package com.adp.currency.change;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.adp.currency.change.controller.CurrencyChangeController;
import com.adp.currency.change.to.CoinChangeResponse;
import com.adp.currency.change.to.ExceptionResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CurrencyChangeApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CurrencyChangeController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void getCoins() throws Exception {

		// min coins for $1 bill
		CoinChangeResponse resp = this.restTemplate.getForObject(
				"http://localhost:" + port + "/currency-change/coin/for/1/dollar-bill", CoinChangeResponse.class);
		assertThat(resp).isNotNull();
		assertThat(resp.getConins()).isNotNull();
		assertThat(resp.getConins()).isEqualTo(4);
	}

	@Test
	public void getMaxCoins() throws Exception {

		// max coins for $1 bill
		CoinChangeResponse resp = this.restTemplate.getForObject(
				"http://localhost:" + port + "/currency-change/coin/for/1/dollar-bill?maxCoins=true",
				CoinChangeResponse.class);
		assertThat(resp).isNotNull();
		assertThat(resp.getConins()).isNotNull();
		assertThat(resp.getConins()).isEqualTo(100);
	}

	@Test
	public void coinsNotAvailable() throws Exception {

		ExceptionResponse errorResp = this.restTemplate.getForObject(
				"http://localhost:" + port + "/currency-change/coin/for/100/dollar-bill", ExceptionResponse.class);

		assertThat(errorResp).isNotNull();
		assertThat(errorResp.getErrorMessage()).contains("Coins not available");
	}

	@Test
	public void getCoinsInvalidBill() throws Exception {

		ExceptionResponse errorResp = this.restTemplate.getForObject(
				"http://localhost:" + port + "/currency-change/coin/for/invalid/dollar-bill", ExceptionResponse.class);

		assertThat(errorResp).isNotNull();
		assertThat(errorResp.getErrorMessage()).contains("Failed");
	}

	@Test
	public void getCoinsBillDoesNotExist() throws Exception {

		ExceptionResponse errorResp = this.restTemplate.getForObject(
				"http://localhost:" + port + "/currency-change/coin/for/3/dollar-bill", ExceptionResponse.class);

		assertThat(errorResp).isNotNull();
		assertThat(errorResp.getErrorMessage()).contains("400");
	}
}
