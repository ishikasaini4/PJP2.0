package com.sapient.SpringDateTimeCalculator.OperationsBO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayOfWeekOperation {
	static public String dayOfWeek(LocalDate d1) {
		return d1.format(DateTimeFormatter.ofPattern("EEEE"));
	}
}
