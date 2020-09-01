package main.com.sapient.Calculator.Pojo;

import java.util.Calendar;

public class DateTimePojo {
	private Calendar date;

	public DateTimePojo() {
	}

	public DateTimePojo(String s) {
		setDate(s);
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(String s) {
		this.date = stringToDate(s);
	}

	private Calendar stringToDate(String s) {
		Calendar c = Calendar.getInstance();
		c.clear();
		String[] t = s.split("-");
		c.set(Integer.parseInt(t[2]), Integer.parseInt(t[1]) - 1, Integer.parseInt(t[0]));
		return c;
	}
}
