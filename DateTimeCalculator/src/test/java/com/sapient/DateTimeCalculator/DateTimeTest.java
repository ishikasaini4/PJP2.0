package com.sapient.DateTimeCalculator;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class DateTimeTest {

	DateTimePojo dtPojo;
	DateOperations dOp;

	@Before
	public void setup() {
		dtPojo = new DateTimePojo();
		dOp = new DateOperations();
	}

	@Test
	public void stringToDateNormal() {
		Calendar c = dtPojo.stringToDate("12-10-2005");
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2005, 10 - 1, 12);
		assertEquals(exp, c);
	}

	@Test
	public void stringToDateSingleDigits() {
		Calendar c = dtPojo.stringToDate("2-1-2015");
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2015, 1 - 1, 2);
		assertEquals(exp, c);
	}

	@Test
	public void addTest() {
		Calendar c = dOp.add("1-1-2020", "14-10-2000");
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(4020, 9, 15);
		assertEquals(exp.getTime(), c.getTime());

	}

	@Test
	public void subtractTest1() {
		HashMap<String, Long> m = dOp.subtract("18-11-2016", "21-10-1999");
		HashMap<String, Long> exp = new HashMap<String, Long>();
		exp.put("DAYS", (long) 6238);
		exp.put("WEEKS", (long) 891);
		exp.put("MONTHS", (long) 204);
		exp.put("YEARS", (long) 17);
		assertEquals(exp, m);

	}

	@Test
	public void subtractTest2() {
		HashMap<String, Long> m = dOp.subtract("8-11-2000", "21-10-2020");
		HashMap<String, Long> exp = new HashMap<String, Long>();
		exp.put("DAYS", (long) 7287);
		exp.put("WEEKS", (long) 1041);
		exp.put("MONTHS", (long) 239);
		exp.put("YEARS", (long) 19);
		assertEquals(exp, m);
	}

	@Test
	public void minusNDaysTestNormal() {
		Calendar c = dOp.minusNDays("23-9-2020", 15);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 8);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNDaysTestCase0() {
		Calendar c = dOp.minusNDays("23-9-2020", 0);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 23);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNDaysTestCaseNegative() {
		Calendar c = dOp.minusNDays("23-9-2020", -2);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 25);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNWeeksTestNormal() {
		Calendar c = dOp.minusNWeeks("23-9-2020", 3);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 2);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNWeeksTestCase0() {
		Calendar c = dOp.minusNWeeks("23-9-2020", 0);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 23);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNWeeksTestCaseNegative() {
		Calendar c = dOp.minusNWeeks("23-9-2020", -2);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 9, 7);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNMonthsTestNormal() {
		Calendar c = dOp.minusNMonths("23-9-2020", 3);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 5, 23);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNMonthsTestCase0() {
		Calendar c = dOp.minusNMonths("23-9-2020", 0);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 8, 23);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	@Test
	public void minusNMonthsTestCaseNegative() {
		Calendar c = dOp.minusNMonths("23-9-2020", -2);
		Calendar exp = Calendar.getInstance();
		exp.clear();
		exp.set(2020, 10, 23);
		assertEquals(exp.getTime(), c.getTime());
	}
	
	
	@Test
	public void getDayOfWeekTest() {
		int d = dOp.getDayOfTheWeek("22-8-2020");
		assertEquals(7, d);
	}
	
	@Test
	public void getWeekNumberTest() {
		int d = dOp.getDayOfTheWeek("14-2-2020");
		assertEquals(6, d);
	}
	
	@Test
	public void NLPTest1() {
		Calendar c = dOp.NLPToDate("Today", 0);
		Calendar exp = Calendar.getInstance();
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	
	@Test
	public void NLPTest2() {
		Calendar c = dOp.NLPToDate("YESTERDAY", 0);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	
	@Test
	public void NLPTest3() {
		Calendar c = dOp.NLPToDate("Weeks From Now", 2);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.WEEK_OF_MONTH, 2);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	
	@Test
	public void NLPTest4() {
		Calendar c = dOp.NLPToDate("Weeks EARLIER", 5);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.WEEK_OF_MONTH, -5);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	@Test
	public void NLPTest5() {
		Calendar c = dOp.NLPToDate("Months From Now", 30);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.MONTH, 30);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	@Test
	public void NLPTest6() {
		Calendar c = dOp.NLPToDate("Months earlier", 215);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.MONTH, -215);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	@Test
	public void NLPTest7() {
		Calendar c = dOp.NLPToDate("Years From Now", 99);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.YEAR, 99);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	@Test
	public void NLPTest8() {
		Calendar c = dOp.NLPToDate("Years EarLier", 500);
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.YEAR, -500);
		c.set(Calendar.SECOND, 0);
		exp.set(Calendar.SECOND, 0);
		assertEquals(exp.getTime().toString(), c.getTime().toString());
	}
	
}
