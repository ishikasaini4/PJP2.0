package com.sapient.SpringDateTimeCalculator.OperationsBO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class DateFromPhraseOperation {

	 public static LocalDate handleOperation(LocalDate givenDate, String phrase) throws IOException {

		final int n = phrase.matches(".*\\d.*") ? Integer.parseInt(phrase.substring(0, phrase.indexOf(' '))) : 0;
		phrase = (n != 0) ? phrase.substring(phrase.indexOf(' ') + 1) : phrase;

		final Calendar res = Calendar.getInstance();
		phrase = phrase.toLowerCase();

		HashMap<String, NLP> hm = new HashMap<String, NLP>();

		hm.put("today", new NLP() {
			public void convertToDate(String p, int x) {
			}
		});
		hm.put("tomorrow", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, 1);
			}
		});
		hm.put("yesterday", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, -1);
			}
		});
		hm.put("day after tomorrow", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, 2);
			}
		});
		hm.put("day before yesterday", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, -2);
			}
		});
		hm.put("next week", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.WEEK_OF_YEAR, 1);
			}
		});
		hm.put("last week", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.WEEK_OF_YEAR, -1);
			}
		});
		hm.put("previous week", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.WEEK_OF_YEAR, -1);
			}
		});
		hm.put("next month", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, 1);
			}
		});
		hm.put("last month", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, -1);
			}
		});
		hm.put("months after", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, n);
			}
		});
		hm.put("months before", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, -n);
			}
		});
		hm.put("next year", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.YEAR, 1);
			}
		});
		hm.put("last year", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.YEAR, -1);
			}
		});
		hm.put("days from now", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, n);
			}
		});
		hm.put("weeks from now", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.WEEK_OF_MONTH, n);
			}
		});
		hm.put("months from now", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, n);
			}
		});
		hm.put("years from now", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.YEAR, n);
			}
		});
		hm.put("days earlier", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.DAY_OF_MONTH, -n);
			}
		});
		hm.put("weeks earlier", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.WEEK_OF_MONTH, -n);
			}
		});
		hm.put("months earlier", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.MONTH, -n);
			}
		});
		hm.put("years earlier", new NLP() {
			public void convertToDate(String p, int x) {
				res.add(Calendar.YEAR, -n);
			}
		});

		if (hm.containsKey(phrase)) {
			NLP nlpconverter = hm.get(phrase);
			nlpconverter.convertToDate(phrase, n);
		} else {
			throw new NoSuchElementException("Please enter a valid Natural language phrase.");
		}
		return stringToDate(res);
	}

	public static String dateToString(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(c.getTime());
	}
	
	public static LocalDate stringToDate(Calendar c) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse((CharSequence) c.getTime(), formatter);
		return localDate;
	}
}
