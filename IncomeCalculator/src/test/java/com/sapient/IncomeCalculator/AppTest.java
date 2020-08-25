package com.sapient.IncomeCalculator;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private IncomeOperations iOps;

	@Before
	public void setup() throws IOException {
		iOps = new IncomeOperations("SampleInputFile.csv");

	}

	@Test
	public void testOperations() throws IOException {
		iOps.processInformation();
		BufferedReader reader1 = new BufferedReader(new FileReader("SampleOutputFile.csv"));
		BufferedReader reader2 = new BufferedReader(new FileReader("IncomeUSD_File.csv"));

		String s1 = "";
		String s2 = "";

		while ((s1 = reader1.readLine()) != null && (s2 = reader2.readLine()) != null) {
			assertEquals(s1, s2);
		}
		reader1.close();
		reader2.close();
	}
}
