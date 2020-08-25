package com.sapient.FeeCalculator;

import java.util.Calendar;

public class Transaction {
	private String transactionId;
	private String clientId;
	private String securityId;
	private String type;
	private String priority;
	private Calendar date;
	private int fee;

	public Transaction(String transactionId, String clientId, String securityId, String type, String priority,
			Calendar date, int fee) {
		super();
		this.transactionId = transactionId;
		this.clientId = clientId;
		this.securityId = securityId;
		this.type = type;
		this.priority = priority;
		this.date = date;
		this.fee = fee;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getClientId() {
		return clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public String getType() {
		return type;
	}

	public Calendar getDate() {
		return date;
	}

	public String getPriority() {
		return priority;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "Transaction [clientId=" + clientId + ", securityId=" + securityId + ", type=" + type + ", priority="
				+ priority + ", date=" + date.getTime()+ ", fee=" + fee + "]";
	}

}
