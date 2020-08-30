package com.sapient.CalculatorController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.sapient.Reader;
import com.sapient.Database.DatabaseManager;
import com.sapient.Pojo.OperationConstant;
import com.sapient.Pojo.operations;

public class Menu {

	private static ArrayList<String> menuOps;
	private FileOperations fOp;
	private DatabaseManager Dm;
	private DateOperations dOp;
	private String time;

	public Menu() throws IOException {
		menuOps = new ArrayList<String>();
		fOp = new FileOperations();
		dOp = new DateOperations();
		Dm = new DatabaseManager();
		fillmenu();
	}

	private void fillmenu() { // fill menu list
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

	@SuppressWarnings("unchecked")
	public void displayMenu() throws IOException { // display menu to user
		int n = -1;

		while (true) {
			menuOps.stream().forEach(option -> {
				System.out.println(option);
			});

			System.out.println("\n*Enter 0 for Exit*");
			n = enterNumber();
			if (n == 0)
				break;

			time = Calendar.getInstance().getTime().toString();
			Object res = goToOperation(n);

			// here is the problem
			if (res != null && res instanceof List<?>) {
				((ArrayList<operations>) res).stream().forEach(e -> {
					System.out.println(e);
				});
			} else {
				System.out.println(res);
			}
		}
		fOp.closeFiles();
		Dm.closeSession();

	}

	public Object goToOperation(int n) throws IOException {

		switch (n) {
		case 1: {
			String s1 = enterString();
			String s2 = enterString();
			String c = dOp.add(s1, s2);
			processOperationParams(s1, s2, OperationConstant.ADD, c);
			return c;
		}
		case 2: {
			String s1 = enterString();
			String s2 = enterString();
			HashMap<String, Long> res = dOp.subtract(s1, s2);
			processOperationParams(s1, s2, OperationConstant.SUBTRACT, res);
			return res.toString();
		}
		case 3: {
			String s = enterString();
			int num = enterNumber();
			String c = dOp.minusNDays(s, num);
			processOperationParams(s, n, OperationConstant.MINUS_N_DAYS, c);
			return c;
		}
		case 4: {
			String s = enterString();
			int num = enterNumber();
			String c = dOp.minusNWeeks(s, num);
			processOperationParams(s, num, OperationConstant.MINUS_N_WEEKS, c);
			return c;
		}
		case 5: {
			String s = enterString();
			int num = enterNumber();
			String c = dOp.minusNMonths(s, num);
			processOperationParams(s, num, OperationConstant.MINUS_N_MONTHS, c);
			return c;

		}
		case 6: {
			String s = enterString();
			int res = dOp.getDayOfTheWeek(s);
			processOperationParams(s, null, OperationConstant.DAY_OF_WEEK, res);
			return res;

		}
		case 7: {
			String s = enterString();
			int res = dOp.getWeekNumber((String) s);
			processOperationParams(s, null, OperationConstant.WEEK_OF_YEAR, res);
			return res;

		}
		case 8: {
			String phrase = enterPhrase();
			String c = dOp.NLPToDate(phrase);
			processOperationParams(phrase, null, OperationConstant.PHRASE_CONVERSION, c);
			return c;

		}
		case 9: {
			processOperationParams(null, null, OperationConstant.CURRENT_SESSION_HISTORY, null);
			return Dm.retrieve(0);
		}
		case 10: {
			processOperationParams(null, null, OperationConstant.PREVIOUS_SESSIONS_HISTORY, null);
			return Dm.retrieve(1);

		}
		default:
			return "Please enter a valid choice.";
		}
	}

	private void processOperationParams(Object in1, Object in2, Object op, Object res) {

		String input1 = (in1 != null) ? input1 = in1.toString() : null;
		String input2 = (in2 != null) ? in2.toString() : null;
		String opr = (op != null) ? op.toString() : null;
		String result = (res != null)
				? ((res.getClass() == Calendar.class) ? dOp.dateToString((Calendar) res) : res.toString())
				: null;

		result = res != null && result.indexOf(',') > -1 ? result.replace(',', ' ') : result;

		String s = time + "," + input1 + "," + input2 + "," + opr + "," + result;
		fOp.write(s); // store to file
		Dm.insert(s); // save to DB

	}

	private String enterString() {
		System.out.println("Enter date in format dd-MM-yyyy: ");
		String s = Reader.scanner.next();
		return s;

	}

	private String enterPhrase() {
		System.out.println("Enter phrase: ");
		String s = Reader.scanner.nextLine();
		return s;
	}

	private int enterNumber() {
		System.out.println("Enter n: ");
		int n = Reader.scanner.nextInt();
		Reader.scanner.nextLine();
		return n;
	}

	public void performOnBulkData() throws IOException {
		ArrayList<String> list = fOp.GenerateBulkOutputFile();

		for (String s : list) {

			String tmp[] = s.split(" ");
			int choice = Integer.parseInt(tmp[0]);
			String res = "";
			time = Calendar.getInstance().getTime().toString();

			switch (choice) {
			case 1: {
				res = dOp.add(tmp[1], tmp[2]);
				break;
			}
			case 2: {
				res = dOp.subtract(tmp[1], tmp[2]).toString();
				break;

			}
			case 3: {
				res = dOp.minusNDays(tmp[1], Integer.parseInt(tmp[2]));
				break;
			}
			case 4: {
				res = dOp.minusNWeeks(tmp[1], Integer.parseInt(tmp[2]));
				break;
			}
			case 5: {
				res = dOp.minusNMonths(tmp[1], Integer.parseInt(tmp[2]));
				break;
			}
			case 6: {
				res = dOp.getDayOfTheWeek(tmp[1]) + "";
				break;
			}
			case 7: {
				res = dOp.getDayOfTheWeek(tmp[1]) + "";
				break;
			}
			case 8: {
				tmp[1] = tmp[1].replace('-', ' ');
				res = dOp.NLPToDate(tmp[1]);
				break;
			}
			case 9: {
				System.out.println(fOp.getSessionsHistory(0));
				break;

			}
			case 10: {
				System.out.println(fOp.getSessionsHistory(1));
				break;
			}
			}
			if (choice < 9) {
				processOperationParams(tmp[1], tmp.length >= 3 ? tmp[2] : null, tmp.length >= 4 ? tmp[3] : null, res);
			} else {
				processOperationParams(null, null, tmp[1], null);
			}

		}

	}
}
