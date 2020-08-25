package com.sapient.IncomeCalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileOperations {
	private static final String writefile = "IncomeUSD_File.csv";
	private String readFile;

	public FileOperations(String inputFile) throws IOException {
		readFile = inputFile;
	}

	private String processAvgIncome(double value, double n) {
		value = Math.round(value * 100.0) / 100.0;
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		double avgIncome = value / n;
		return numberFormat.format(avgIncome);

	}

	public void writeToFile(Map<String, Double[]> m) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(writefile));
		writer.write("City/Country,Gender,Average Income(in USD)" + System.lineSeparator());

		Set<String> list = m.keySet();
		List<String> keyList = new ArrayList<String>(list);
		Collections.sort(keyList);

		for (String key : keyList) {
			writer.write(key + "," + processAvgIncome(m.get(key)[0], m.get(key)[1]) + System.lineSeparator());
		}

		writer.close();
		System.out.println("IncomeUSD file created successfully.");
	}

	public ArrayList<Income> readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(readFile));
		String s;
		int count = 0;
		ArrayList<Income> list = new ArrayList<Income>();

		while ((s = reader.readLine()) != null) {

			if (count == 0) {
				count++;
				continue;
			}

			String[] tmp = s.split(",");
			list.add(new Income(tmp[0], tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4])));
		}
		reader.close();
		System.out.println("File Read successfully.");
		return list;

	}
}
