package com.sapient.FeeCalculator;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
    	TransactionOperations tOps = new TransactionOperations();
    	tOps.prcoessTransactions();
    }
}
