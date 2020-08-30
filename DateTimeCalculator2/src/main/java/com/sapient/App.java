package com.sapient;

import java.io.IOException;

import com.sapient.CalculatorController.Menu;

public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Perform operations on bulk data? (press 0 for yes, 1 for no)");
		int choice = Reader.scanner.nextInt();
		Menu menu = new Menu();

		if (choice == 0) {
			menu.performOnBulkData();
		} else {
			menu.displayMenu();
		}

	}

}
