package com.sapient.DateTimeCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		menuOps.add("1. String to date conversion");
		menuOps.add("2. Add dates");
		menuOps.add("3. Substract dates");
		menuOps.add("4. Subtract n days");
		menuOps.add("5. Subtract n weeks");
		menuOps.add("6. Subtract n months");
		menuOps.add("7. Get day of the week");
		menuOps.add("8. Get week of the year");
		menuOps.add("9. Natural language phrase to date conversion");
		menuOps.add("10.Get current session history.");
		menuOps.add("11.Get previous sessions history.");
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

		try {
			fOp.closeFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String enterString() {
		System.out.println("Enter date in format dd-MM-yyyy");
		String s = scn.next();
		return s;

	}

	private Object goToOperation(int n) throws IOException {
		switch (n) {
		case 1: {
			String s = enterString();
			dOp.d1.setDate(s);
			fOp.write(s + ",STRING TO DATE," + dOp.d1.getDate().getTime().toString() + "\n");
			return dOp.d1.getDate().getTime();
		}
		case 2: {
			String s1 = enterString();
			dOp.d1.setDate(s1);
			String s2 = enterString();
			dOp.d2.setDate(s2);
			fOp.write(s1 + "," + s2 + ",ADD," + dOp.add(s1, s2).getTime().toString() + "\n");
			return dOp.add(s1, s2).getTime();
		}
		case 3: {
			String s1 = enterString();
			dOp.d1.setDate(s1);
			String s2 = enterString();
			dOp.d2.setDate(s2);
			fOp.write(s1 + "," + s2 + ",SUBTRACT," + dOp.subtract(s1, s2).toString() + "\n");
			return dOp.subtract(s1, s2);
		}
		case 4: {
			String s = enterString();
			dOp.d1.setDate(s);
			System.out.println("Enter n");
			int no = scn.nextInt();
			fOp.write(s + ",SUBTRACT N DAYS," + dOp.minusNDays(s, no).getTime().toString() + "\n");
			return dOp.minusNDays(s, no).getTime();
		}
		case 5: {
			String s = enterString();
			dOp.d1.setDate(s);
			System.out.println("Enter n");
			int no = scn.nextInt();
			fOp.write(s + ",SUBTRACT N WEEKS," + dOp.minusNWeeks(s, no).getTime().toString() + "\n");
			return dOp.minusNWeeks(s, no).getTime();

		}
		case 6: {
			String s = enterString();
			dOp.d1.setDate(s);
			System.out.println("Enter n");
			int no = scn.nextInt();
			fOp.write(s + ",SUBTRACT N MONTHS," + dOp.minusNMonths(s, no).getTime().toString() + "\n");
			return dOp.minusNMonths(s, no).getTime();
		}
		case 7: {
			String s = enterString();
			dOp.d1.setDate(s);
			fOp.write(s + ",DAY OF THE WEEK," + dOp.getDayOfTheWeek(s) + "\n");
			return dOp.getDayOfTheWeek(s);
		}
		case 8: {
			String s = enterString();
			dOp.d1.setDate(s);
			fOp.write(s + ",WEEK OF THE YEAR," + dOp.getWeekNumber(s) + "\n");
			return dOp.getWeekNumber(s);
		}
		case 9: {
			System.out.println("Enter n(0,if not required) :");
			int no = scn.nextInt();
			scn.nextLine();
			System.out.println("Enter phrase : ");
			String s = scn.nextLine();
			fOp.write(s + "," + no + ",NLP TO DATE," + dOp.NLPToDate(s, no).getTime().toString() + "\n");
			return dOp.NLPToDate(s, no).getTime();

		}
		case 10: {
			try {
				fOp.write("CURRENT SESSION HISTORY\n");
				return fOp.getSessionsHistory(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		case 11: {
			try {
				fOp.write("PREVIOUS SESSIONS HISTORY\n");
				return fOp.getSessionsHistory(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		default:
			return "Enter a valid choice!";
		}
	}

}
