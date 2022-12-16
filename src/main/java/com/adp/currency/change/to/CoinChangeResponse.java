package com.adp.currency.change.to;

import java.util.Objects;

public class CoinChangeResponse {

	private Integer bill;
	private Integer conins;

	/**
	 * @return the bill
	 */
	public Integer getBill() {
		return bill;
	}

	/**
	 * @param bill the bill to set
	 */
	public void setBill(Integer bill) {
		this.bill = bill;
	}

	/**
	 * @return the conins
	 */
	public Integer getConins() {
		return conins;
	}

	/**
	 * @param conins the conins to set
	 */
	public void setConins(Integer conins) {
		this.conins = conins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bill, conins);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinChangeResponse other = (CoinChangeResponse) obj;
		return Objects.equals(bill, other.bill) && Objects.equals(conins, other.conins);
	}

	@Override
	public String toString() {
		return "CoinChangeResponse [bill=" + bill + ", conins=" + conins + "]";
	}

}
