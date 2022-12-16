package com.adp.currency.change.to;

import java.util.Objects;

public class ExceptionResponse {

	private String errorMessage;
	private String requestedURI;

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the requestedURI
	 */
	public String getRequestedURI() {
		return requestedURI;
	}

	/**
	 * @param requestedURI the requestedURI to set
	 */
	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorMessage, requestedURI);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionResponse other = (ExceptionResponse) obj;
		return Objects.equals(errorMessage, other.errorMessage) && Objects.equals(requestedURI, other.requestedURI);
	}

	@Override
	public String toString() {
		return "ExceptionResponse [errorMessage=" + errorMessage + ", requestedURI=" + requestedURI + "]";
	}
}
