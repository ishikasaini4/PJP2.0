package com.sapient.FeeCalculator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransactionOperations {
	private FileOperations fOps;

	public TransactionOperations(String s) throws IOException {
		fOps = new FileOperations(s);
	}

	private void fillMap(ArrayList<Transaction> list) { 		// also processes intra day transactions
		Map<String, Transaction> map = new HashMap<String, Transaction>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		list.stream().forEach(t -> {
			
			String s = t.getClientId() + t.getSecurityId() + sdf.format(t.getDate().getTime());

			if (!map.containsKey(s)) {
				map.put(s, t);

			} else {
				Set<String> set = new HashSet<>(Arrays.asList("buy", "sell"));
				String type = map.get(s).getType();
				String tType = t.getType();

				if (type.equalsIgnoreCase(tType)==false && set.contains(type.toLowerCase()) && set.contains(tType.toLowerCase())) {
					map.get(s).setFee(10);
					t.setFee(10);
				}
			}
		});
	}

	public void prcoessTransactions() throws IOException {

		ArrayList<Transaction> list = fOps.readFile();
		fillMap(list);

		list.stream().forEach(t -> {
			if (t.getPriority().equalsIgnoreCase("y")) {
				t.setFee(t.getFee() + 500);
			} else {
				if (t.getType().equalsIgnoreCase("buy") || t.getType().equalsIgnoreCase("deposit")) {
					t.setFee(t.getFee() + 50);
				} else {
					t.setFee(t.getFee() + 100);
				}
			}
			try {
				fOps.writeToFile(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		fOps.closeFile();
	}

}
