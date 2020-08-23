package com.sapient.DateTimeCalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
	final static private String current = "current-sessions.txt";
	final static private String permanent = "sessions.txt";
	private BufferedWriter perm;
	private BufferedWriter temp;

	public FileOperations() throws IOException {
		openFiles();
	}

	public void write(String s) throws IOException {
		this.perm.write(s);
		this.temp.write(s);
	}

	public void closeFiles() throws IOException {
		this.perm.close();
		this.temp.close();
		new File("current-sessions.txt").deleteOnExit();
	}

	public void openFiles() throws IOException {
		this.perm = new BufferedWriter(new FileWriter("sessions.txt", true));
		this.temp = new BufferedWriter(new FileWriter("current-sessions.txt", true));
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

}
