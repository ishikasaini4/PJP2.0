package com.sapient.DateTimeCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Menu {
	private static Scanner scn;
	private FileOperations fOp;
	private DateOperations dOp;
	private ArrayList<String> menuOps;

	public Menu() throws IOException {
		scn = new Scanner(System.in);
		menuOps = new ArrayList<String>();
		dOp = new DateOperations();
		fOp = new FileOperations();
		fillmenu();
	}

	private void fillmenu() {
		menuOps.add("---------------------------MENU----------------------------");
		menuOps.add("1. Add dates");
		menuOps.add("2. Substract dates");
		menuOps.add("3. Subtract n days");
		menuOps.add("4. Subtract n weeks");
		menuOps.add("5. Subtract n months");
		menuOps.add("6. Get day of the week");
		menuOps.add("7. Get week of the year");
		menuOps.add("8. Natural language phrase to date conversion");
		menuOps.add("9 .Get current session history.");
		menuOps.add("10.Get previous sessions history.");
	}

	public void displayMenu() throws IOException {
		int n = -1;

		while (n != 0) {
			for (String string : menuOps) {
				System.out.println(string);
			}
			System.out.println("\nEnter a choice (0 for exit) : ");
			n = scn.nextInt();

			if (n == 0)
				break;

			Object res = goToOperation(n);
			System.out.println(res);
		}
		
		fOp.closeFiles();
		
	}

	public Object goToOperation(int n) throws NullPointerException{
		HashMap<Integer, BiFunction<Object, Object, Object>> m = new HashMap<Integer, BiFunction<Object, Object, Object>>();

		m.put(1, (s1, s2) -> {
			Calendar c = dOp.add((String) s1, (String) s2);
			fOp.write(s1 + "," + s2 + ",ADD," + c.getTime().toString() + "\n");
			return c.getTime();
		});

		m.put(2, (s1, s2) -> {
			String res = dOp.subtract((String) s1, (String) s2).toString();
			fOp.write(s1 + "," + s2 + ",SUBTRACT," + res + "\n");
			return res;
		});

		m.put(3, (s, n1) -> {
			Calendar c = dOp.minusNDays((String) s, (int) n1);
			fOp.write(s + ",SUBTRACT N DAYS," + c.getTime().toString() + "\n");
			return c.getTime();
		});

		m.put(4, (s, n2) -> {
			Calendar c = dOp.minusNWeeks((String) s, (int) n2);
			fOp.write(s + ",SUBTRACT N WEEKS," + c.getTime().toString() + "\n");
			return c.getTime();
		});

		m.put(5, (s, n3) -> {
			Calendar c = dOp.minusNMonths((String) s, (int) n3);
			fOp.write(s + ",SUBTRACT N MONTHS," + c.getTime().toString() + "\n");
			return c.getTime();
		});

		m.put(6, (s, n4) -> {
			int res = dOp.getDayOfTheWeek((String) s);
			fOp.write(s + ",DAY OF THE WEEK," + res + "\n");
			return res;
		});

		m.put(7, (s, n5) -> {
			int res = dOp.getWeekNumber((String) s);
			fOp.write(s + ",WEEK OF THE YEAR," + res + "\n");
			return res;
		});
		m.put(8, (s, n6) -> {
			Calendar c = dOp.NLPToDate((String) s, (int) n6);
			fOp.write(s + "," + n6 + ",NLP TO DATE," + c.getTime().toString() + "\n");
			return c.getTime();
		});

		m.put(9, (a, b) -> {
			try {
				fOp.write("CURRENT SESSION HISTORY\n");
				return fOp.getSessionsHistory(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		});

		m.put(10, (c, d) -> {
			try {
				fOp.write("PREVIOUS SESSIONS HISTORY\n");
				return fOp.getSessionsHistory(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		});
		
		return m.get(n).apply(enterString(), enterNumber());
	}

	
	
	private String enterString() {
		System.out.println("Enter date in format dd-MM-yyyy");
		String s = scn.next();
		return s;

	}

	private Object enterNumber() {
		System.out.println("Enter n (if not required enter -1): ");
		int n = scn.nextInt();
		return n;
	}
}
