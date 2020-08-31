package com.sapient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;

public class DateTimeBO{
	public String add(String a, String b) {

		DateTimePojo d1 = new DateTimePojo(a);
		DateTimePojo d2 = new DateTimePojo(b);
		Calendar res = (Calendar) d1.getDate().clone();

		res.add(Calendar.YEAR, d2.getDate().get(Calendar.YEAR));
		res.add(Calendar.MONTH, d2.getDate().get(Calendar.MONTH));
		res.add(Calendar.DAY_OF_MONTH, d2.getDate().get(Calendar.DAY_OF_MONTH));
		return dateToString(res);

	}

	public String subtract(String a, String b) {
		String res = "";

		if (a.length() < 10)
			a = formatDateInput(a);
		if (b.length() < 10)
			b = formatDateInput(b);

		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate da = LocalDate.parse(a, f);
		LocalDate db = LocalDate.parse(b, f);

		LocalDate l, s;

		if (da.isBefore(db)) {
			l = da;
			s = db;
		} else {
			l = db;
			s = da;
		}

		res = "DAYS: " + ChronoUnit.DAYS.between(l, s) + " WEEKS: " + ChronoUnit.WEEKS.between(l, s) + " MONTHS: "
				+ ChronoUnit.MONTHS.between(l, s) + " YEARS: " + ChronoUnit.YEARS.between(l, s);
		return res;

	}

	public String minusNDays(String date, int n) {
		DateTimePojo d1 = new DateTimePojo(date);
		Calendar res = d1.getDate();
		res.add(Calendar.DATE, -n);
		return dateToString(res);
	}

	public String minusNWeeks(String date, int n) {
		DateTimePojo d1 = new DateTimePojo(date);
		Calendar res = d1.getDate();
		res.add(Calendar.WEEK_OF_YEAR, -n);
		return dateToString(res);
	}

	public String minusNMonths(String date, int n) {
		DateTimePojo d1 = new DateTimePojo(date);
		Calendar res = d1.getDate();
		res.add(Calendar.MONTH, -n);
		return dateToString(res);
	}

	public String getDayOfTheWeek(String date) {
		DateTimePojo d1 = new DateTimePojo(date);
		return d1.getDate().get(Calendar.DAY_OF_WEEK)+"";
	}

	public String getWeekNumber(String date) {
		DateTimePojo d1 = new DateTimePojo(date);
		return d1.getDate().get(Calendar.WEEK_OF_YEAR)+"";
	}

	public String NLPToDate(String phrase) {

		final int n = phrase.matches(".*\\d.*") ? Integer.parseInt(phrase.substring(0, phrase.indexOf(' '))) : 0;
		phrase = (n != 0) ? phrase.substring(phrase.indexOf(' ') + 1) : phrase;

		Calendar res = Calendar.getInstance();
		phrase = phrase.toLowerCase();

		HashMap<String, NLP> hm = new HashMap<String, NLP>();

		hm.put("today", (p, x) -> {
		});
		hm.put("tomorrow", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, 1);
		});
		hm.put("yesterday", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -1);
		});
		hm.put("day after tomorrow", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, 2);
		});
		hm.put("day before yesterday", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -2);
		});
		hm.put("next week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, 1);
		});
		hm.put("last week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, -1);
		});
		hm.put("previous week", (p, x) -> {
			res.add(Calendar.WEEK_OF_YEAR, -1);
		});
		hm.put("next month", (p, x) -> {
			res.add(Calendar.MONTH, 1);
		});
		hm.put("last month", (p, x) -> {
			res.add(Calendar.MONTH, -1);
		});
		hm.put("months after", (p, x) -> {
			res.add(Calendar.MONTH, n);
		});
		hm.put("months before", (p, x) -> {
			res.add(Calendar.MONTH, -n);
		});
		hm.put("next year", (p, x) -> {
			res.add(Calendar.YEAR, 1);
		});
		hm.put("last year", (p, x) -> {
			res.add(Calendar.YEAR, -1);
		});
		hm.put("days from now", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, n);
		});
		hm.put("weeks from now", (p, x) -> {
			res.add(Calendar.WEEK_OF_MONTH, n);
		});
		hm.put("months from now", (p, x) -> {
			res.add(Calendar.MONTH, n);
		});
		hm.put("years from now", (p, x) -> {
			res.add(Calendar.YEAR, n);
		});
		hm.put("days earlier", (p, x) -> {
			res.add(Calendar.DAY_OF_MONTH, -n);
		});
		hm.put("weeks earlier", (p, x) -> {
			res.add(Calendar.WEEK_OF_MONTH, -n);
		});
		hm.put("months earlier", (p, x) -> {
			res.add(Calendar.MONTH, -n);
		});
		hm.put("years earlier", (p, x) -> {
			res.add(Calendar.YEAR, -n);
		});

		if (hm.containsKey(phrase)) {
			NLP nlpconverter = hm.get(phrase);
			nlpconverter.convertToDate(phrase, n);
		}
		return dateToString(res);
	}

	public String dateToString(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(c.getTime());
	}

	private String formatDateInput(String s) {
		String[] strs = s.split("-");
		for (int i = 0; i < strs.length - 1; i++) {
			if (strs[i].length() < 2)
				strs[i] = "0" + strs[i];
		}
		return strs[0] + "-" + strs[1] + "-" + strs[2];
	}

}
