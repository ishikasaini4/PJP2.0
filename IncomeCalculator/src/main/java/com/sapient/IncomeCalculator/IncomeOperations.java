package com.sapient.IncomeCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IncomeOperations {
	private Map<String, Double[]> map;
	private FileOperations fOps;

	public IncomeOperations(String s) throws IOException {
		map = new HashMap<String, Double[]>();
		fOps = new FileOperations(s);
	}

	private Double currencyToUSD(String cur) {
		Map<String, Double> m = new HashMap<String, Double>();

		m.put("INR", (double) 66);
		m.put("GBP", 0.67);
		m.put("SGP", 1.5);
		m.put("HKD", (double) 8);
		m.put("USD", (double) 1);

		return (m.containsKey(cur)) ? m.get(cur) : null;

	}

	public void processInformation() throws IOException {

		ArrayList<Income> list = fOps.readFile();

		for (Income i : list) {

			String key = ((i.getCountry().length() < 1) ? i.getCity() : i.getCountry()) + "," + i.getGender();
			double incInUSD = i.getAmount() / currencyToUSD(i.getCurrency());

			if (this.map.containsKey(key)) {
				double value = this.map.get(key)[0] + incInUSD;
				double n = this.map.get(key)[1] + 1;

				this.map.get(key)[0] = value;
				this.map.get(key)[1] = n;

				this.map.put(key, this.map.get(key));
			} else {
				Double[] tmp = new Double[2];
				tmp[0] = incInUSD;
				tmp[1] = (double) 1;
				this.map.put(key, tmp);
			}
		}
		fOps.writeToFile(this.map);
	}

}
