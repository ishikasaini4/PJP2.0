package com.sapient.IncomeCalculator;

public class Income {
	private String city;
	private String country;
	private String gender;
	private String currency;
	private double amount;

	public Income(String city, String country, String gender, String currency, double amount) {
		super();
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.currency = currency;
		this.amount = amount;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Income [city=" + city + ", country=" + country + ", gender=" + gender + ", currency=" + currency
				+ ", amount=" + amount + "]";
	}

}
