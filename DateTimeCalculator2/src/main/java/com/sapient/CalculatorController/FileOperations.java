package com.sapient.CalculatorController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.sapient.Pojo.OperationConstant;

public class FileOperations {
	final static private String current = "current-sessions.csv";
	final static private String permanent = "sessions.csv";
	private static final String BulkoutputFile = "BulkData.txt";
	private BufferedWriter perm;
	private BufferedWriter temp;
	private static Random random;

	public FileOperations() throws IOException {
		random = new Random();
		openFiles();
		write("INPUT1  INPUT2  OPERATION  RESULT");
	}

	public void write(String s) {
		try {
			this.perm.write(s + System.lineSeparator());
			this.temp.write(s + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeFiles() throws IOException {
		this.perm.close();
		this.temp.close();
		new File(current).deleteOnExit();
	}

	public void openFiles() throws IOException {
		this.perm = new BufferedWriter(new FileWriter(permanent, true));
		this.temp = new BufferedWriter(new FileWriter(current, true));
	}

	public ArrayList<String> getSessionsHistory(int i) throws IOException, FileNotFoundException {
		closeFiles();
		openFiles();
		BufferedReader reader = (i == 0) ? new BufferedReader(new FileReader(current))
				: new BufferedReader(new FileReader(permanent));
		String s;
		ArrayList<String> res = new ArrayList<String>();
		while ((s = reader.readLine()) != null) {
			res.add(s);
		}
		return res;

	}

	private ArrayList<String> generate() {
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < 300; i++) {

			int n = random.nextInt(10) + 1;
			String s = "";

			switch (n) {
			case 1: {
				String d1 = genDate();
				String d2 = genDate();
				s = 1 + " " + d1 + " " + d2 + " " + OperationConstant.ADD;
				break;
			}
			case 2: {
				String d1 = genDate();
				String d2 = genDate();
				s = 2 + " " + d1 + " " + d2 + " " + OperationConstant.SUBTRACT;
				break;
			}
			case 3: {
				String d = genDate();
				int num = genDay();
				s = 3 + " " + d + " " + num + " " + OperationConstant.MINUS_N_DAYS;
				break;
			}
			case 4: {
				String d = genDate();
				int num = genDay();
				s = 4 + " " + d + " " + num + " " + OperationConstant.MINUS_N_WEEKS;
				break;
			}
			case 5: {
				String d = genDate();
				int num = genDay();
				s = 5 + " " + d + " " + num + " " + OperationConstant.MINUS_N_MONTHS;
				break;
			}
			case 6: {
				String d = genDate();
				s = 6 + " " + d + " " + OperationConstant.DAY_OF_WEEK;
				break;
			}
			case 7: {
				String d = genDate();
				s = 7 + " " + d + " " + OperationConstant.WEEK_OF_YEAR;
				break;
			}
			case 8: {
				s = 8 + " " + genNLP() + " " + OperationConstant.PHRASE_CONVERSION;
				break;
			}
			case 9: {
				s = 9 + " " + OperationConstant.CURRENT_SESSION_HISTORY.toString();
				break;
			}
			case 10: {
				s = 10 + " " + OperationConstant.PREVIOUS_SESSIONS_HISTORY.toString();
				break;
			}
			}
			list.add(s);
		}

		return list;
	}

	private String genNLP() {
		ArrayList<String> nonNum = new ArrayList<String>();
		ArrayList<String> withNum = new ArrayList<String>();

		nonNum.add("today");
		nonNum.add("tomorrow");
		nonNum.add("yesterday");
		nonNum.add("day-after-tomorrow");
		nonNum.add("day-before-yesterday");
		nonNum.add("next-week");
		nonNum.add("last-week");
		nonNum.add("previous-week");
		nonNum.add("next-month");
		nonNum.add("last-month");
		nonNum.add("next-year");
		nonNum.add("last-year");

		withNum.add("days-from-now");
		withNum.add("days-earlier");
		withNum.add("weeks-from-now");
		withNum.add("weeks-earlier");
		withNum.add("months-after");
		withNum.add("months-before");
		withNum.add("months-from-now");
		withNum.add("months-earlier");
		withNum.add("years-from-now");
		withNum.add("years-earlier");

		int n = random.nextInt(nonNum.size() + withNum.size());

		if (0 <= n && n < nonNum.size()) {
			return nonNum.get(n);
		} else {
			n -= nonNum.size();
			String d = genDate();
			String[] tmp = d.split("-");
			String phrase = withNum.get(n);

			if (phrase.contains("days")) {
				return tmp[0] + "-" + phrase;

			} else if (phrase.contains("months")) {
				return tmp[1] + "-" + phrase;

			} else if (phrase.contains("years")) {
				return tmp[2] + "-" + phrase;

			} else {
				int week = Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]);
				return week + "-" + phrase;

			}
		}
	}

	private String genDate() {
		int y = 1500 + random.nextInt(2001);
		int m = random.nextInt(12) + 1;
		int d = -1;

		if (m == 2)
			d = random.nextInt(29) + 1;
		else if (m == 1 || m == 3 || m == 5 || m == 6 || m == 8 || m == 10 || m == 12)
			d = random.nextInt(31) + 1;
		else
			d = random.nextInt(30) + 1;

		return d + "-" + m + "-" + y;
	}

	private int genDay() {
		return random.nextInt(5000) + 1;
	}

	public ArrayList<String> GenerateBulkOutputFile() throws IOException {

		ArrayList<String> list = generate();
		BufferedWriter writer = new BufferedWriter(new FileWriter(BulkoutputFile));

		list.stream().forEach(e -> {
			try {
				writer.write(e + System.lineSeparator());

			} catch (IOException exp) {
				exp.printStackTrace();
			}
		});
		writer.close();
		return list;
	}
}
