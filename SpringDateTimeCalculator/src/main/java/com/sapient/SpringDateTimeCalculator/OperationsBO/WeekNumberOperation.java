package com.sapient.SpringDateTimeCalculator.OperationsBO;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekNumberOperation {
	static public int weekNumber(LocalDate d1) {
		return d1.get(WeekFields.of(Locale.getDefault()).weekOfYear());
	}
}
