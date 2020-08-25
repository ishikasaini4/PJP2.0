package com.sapient.FeeCalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FileOperations {
	private static final String writefile = "Transactions_Fee_File.csv";
	private static final String readFile = "Sample_Data_Fee_Calculator.csv";
	private BufferedWriter writer;

	public FileOperations() throws IOException {
		writer = new BufferedWriter(new FileWriter(writefile));
		writer.write(
				"Client ID, Transaction Type, Transaction Date, Priority, Processing Free" + System.lineSeparator());
	}

	public void writeToFile(Transaction s) throws IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			writer.write(s.getClientId() + "," + s.getType() + "," + sdf.format(s.getDate().getTime()) + ","
					+ s.getPriority() + "," + s.getFee() + System.lineSeparator());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeFile() throws IOException {
		writer.close();
		System.out.println("Transactions file created successfully.");
	}

	public ArrayList<Transaction> readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(readFile));
		String s;
		int count = 0;
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		while ((s = reader.readLine()) != null) {
			count++;
			if (count == 1)
				continue;

			String[] t = s.split(",");
			list.add(new Transaction(t[0], t[1], t[2], t[3], t[6], stringToCalendar(t[4]), 0));
		}
		reader.close();
		System.out.println("File Read successfully.");
		return list;

	}

	private Calendar stringToCalendar(String date) {
		String[] t = date.split("/");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(t[2]), Integer.parseInt(t[0]) - 1, Integer.parseInt(t[1]), 0, 0, 0);
		return c;
	}
}
