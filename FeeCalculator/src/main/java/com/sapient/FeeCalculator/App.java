package com.sapient.FeeCalculator;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter input file: ");
		String s = scn.next();
		TransactionOperations tOps = new TransactionOperations(s);
		tOps.prcoessTransactions();
	}
}
