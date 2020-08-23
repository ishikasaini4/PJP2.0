package com.sapient.DateTimeCalculator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class DateOperations {
	public DateTimePojo d1;
	public DateTimePojo d2;

	public DateOperations() {
		d1 = new DateTimePojo();
		d2 = new DateTimePojo();
	}

	public Calendar add(String a, String b) {
		d1.setDate(a);
		d2.setDate(b);
		Calendar res = (Calendar) d1.getDate().clone();

		res.add(Calendar.YEAR, d2.getDate().get(Calendar.YEAR));
		res.add(Calendar.MONTH, d2.getDate().get(Calendar.MONTH));
		res.add(Calendar.DAY_OF_MONTH, d2.getDate().get(Calendar.DAY_OF_MONTH));
		return res;
	}

	public HashMap<String, Long> subtract(String a, String b) {

		if (a.length() < 10)
			a = format(a);
		if (b.length() < 10)
			b = format(b);

		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate da = LocalDate.parse(a, f);
		LocalDate db = LocalDate.parse(b, f);

		HashMap<String, Long> map = new HashMap<String, Long>();
		LocalDate l, s;

		if (da.isBefore(db)) {
			l = da;
			s = db;
		} else {
			l = db;
			s = da;
		}

		map.put("DAYS", ChronoUnit.DAYS.between(l, s));
		map.put("WEEKS", ChronoUnit.WEEKS.between(l, s));
		map.put("MONTHS", ChronoUnit.MONTHS.between(l, s));
		map.put("YEARS", ChronoUnit.YEARS.between(l, s));
		return map;
	}

	public Calendar minusNDays(String date, int n) {
		d1.setDate(date);
		Calendar res = d1.getDate();
		res.add(Calendar.DATE, -n);
		return res;
	}

	public Calendar minusNWeeks(String date, int n) {
		d1.setDate(date);
		Calendar res = d1.getDate();
		res.add(Calendar.WEEK_OF_YEAR, -n);
		return res;
	}

	public Calendar minusNMonths(String date, int n) {
		d1.setDate(date);
		Calendar res = d1.getDate();
		res.add(Calendar.MONTH, -n);
		return res;
	}

	public int getDayOfTheWeek(String date) {
		d1.setDate(date);
		return d1.getDate().get(Calendar.DAY_OF_WEEK);
	}

	public int getWeekNumber(String date) {
		d1.setDate(date);
		return d1.getDate().get(Calendar.WEEK_OF_YEAR);
	}

	public Calendar NLPToDate(String phrase, int n) {
		Date date = Calendar.getInstance().getTime();
		final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(date); // current date

		d1.setDate(strDate);
		Calendar res = Calendar.getInstance();
		phrase = phrase.toLowerCase();

		HashMap<String, NLP> ht = new HashMap<String, NLP>();

		ht.put("today", (p, x) -> {
		});
		ht.put("tomorrow", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, 1);
		});
		ht.put("yesterday", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -1);
		});
		ht.put("day after tomorrow", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, 2);
		});
		ht.put("day before yesterday", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -2);
		});
		ht.put("next week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, 1);
		});
		ht.put("last week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, -1);
		});
		ht.put("previous week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, -1);
		});
		ht.put("next month", (p, x) -> {
			res.add(Calendar.MONTH, 1);
		});
		ht.put("last month", (p, x) -> {
			res.add(Calendar.MONTH, -1);
		});
		ht.put("months after", (p, x) -> {
			res.add(Calendar.MONTH, n);
		});
		ht.put("months before", (p, x) -> {
			res.add(Calendar.MONTH, -n);
		});
		ht.put("next year", (p, x) -> {
			res.add(Calendar.YEAR, 1);
		});
		ht.put("last year", (p, x) -> {
			res.add(Calendar.YEAR, -1);
		});
		ht.put("days from now", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, n);
		});
		ht.put("weeks from now", (p, x) -> {
			res.add(Calendar.WEEK_OF_MONTH, n);
		});
		ht.put("months from now", (p, x) -> {
			res.add(Calendar.MONTH, n);
		});
		ht.put("years from now", (p, x) -> {
			res.add(Calendar.YEAR, n);
		});
		ht.put("days earlier", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -n);
		});
		ht.put("weeks earlier", (p, x) -> {
			res.add(Calendar.WEEK_OF_MONTH, -n);
		});
		ht.put("months earlier", (p, x) -> {
			res.add(Calendar.MONTH, -n);
		});
		ht.put("years earlier", (p, x) -> {
			res.add(Calendar.YEAR, -n);
		});

		NLP nlpconverter = null;
		if (ht.containsKey(phrase)) {
			nlpconverter = ht.get(phrase);
			nlpconverter.convertToDate(phrase, n);
		} else {
			throw new NoSuchElementException("Enter a valid natural language phrase");
		}
		return res;
	}

	private String format(String s) {
		String[] strs = s.split("-");
		for (String st : strs) {
			if (st.length() < 2)
				strs[0] = "0" + strs[0];
		}
		return strs[0] + "-" + strs[1] + "-" + strs[2];
	}
}
