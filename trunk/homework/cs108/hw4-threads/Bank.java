// Bank.java

/*
 Creates a bunch of accounts and uses threads
 to post transactions to the accounts concurrently.
*/

import java.io.*;
import java.util.*;

public class Bank {
	public static final int ACCOUNTS = 20;	 // number of accounts
	
	protected Buffer buffer; // holds the transaction queue
	protected Account[] accounts; //holds all the accounts
	//protected Collection<Worker> workers; //all the worker threads
	protected Collection<TransactionRecord> atypicalTransactions; //all the strange transactions
	protected int limit; //amount under which bank account transactions should be reported
	protected boolean limitSpecified; // true if the user specified a limit argument
	
	/**
	 * Returns the number of accounts this bank can support.
	 * @return
	 */
	protected int getAccountCapacity() { return ACCOUNTS; }
	
	/**
	 * A simple record of a transaction.
	 */
	public class TransactionRecord
	{
		public Transaction transaction;
		public int closingBalance;
		public String toString()
		{
			return "from:" + transaction.from +
				  " to:"   + transaction.to +
				  " bal:" + closingBalance;
		}
	}
	
	/**
	 * Record of a transaction gone wrong
	 * @author red
	 */
	public class BadTransactionRecord extends TransactionRecord {
		public BadTransactionRecord(Transaction t, int balance)
		{
			transaction = t;
			closingBalance = balance;
		}
	}
	
	/**
	 * A bank "employee" thread responsible for running transactions.
	 * @author red
	 */
	public class Worker extends Thread
	{
		public void run()
		{
			while (true)
			{
				Transaction trans = buffer.remove();
				if (trans == null)
					break;
				accounts[trans.from].enactTransactionAsGiver(trans);
			}
		}
	}
	
	/**
	 * Boring constructor.
	 *
	 */
	public Bank()
	{
		buffer = new Buffer();
		accounts = new Account[getAccountCapacity()];
		//create accounts
		for (int i=0; i < getAccountCapacity(); i++)
			accounts[i] = new Account(this, i, 0);
	}

	/**
	 * Initializes a set number of workers and returns a collection of them.
	 * @param numWorkers
	 * @return
	 */
	public Collection<Worker> initWorkers(int numWorkers)
	{
		Collection<Worker> workers = new LinkedList<Worker>();
		for (int i=0; i < numWorkers; i++)
		{
			Worker w = new Worker();
			workers.add(w);
			w.start();
		}
		return workers;
	}
	/**
	 * Reads transaction data (from/to/amt) from a file for processing.
	 */
	public void readFile(String file) {
			try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			// Use stream tokenizer to get successive words from file
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			
			while (true) {
				int read = tokenizer.nextToken();
				if (read == StreamTokenizer.TT_EOF) break;  // detect EOF
				int from = (int)tokenizer.nval;
				
				tokenizer.nextToken();
				int to = (int)tokenizer.nval;
				
				tokenizer.nextToken();
				int amount = (int)tokenizer.nval;
				
				Transaction trans = new Transaction(from, to, amount); 
				buffer.add(trans);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public int getLimit() { return limit; }

	/**
	  * Processes one file of transaction data
	  * -fork off workers
	  * -read file into the buffer
	  * -wait for the workers to finish
	  */
	public void processFile(String file, int numWorkers) {
		
		Collection<Worker> workers = initWorkers(numWorkers);
		readFile(file);
		for (int i=0; i < numWorkers; i++)
			buffer.add(null);
		for (Worker worker : workers)
			try {
				worker.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * @param record whether to record bad transactions or not
	 * @param limit balance under which bad transactions should be logged
	 */
	protected void configureBadTransactions(boolean record, int limit)
	{
		this.limit = limit;
		if (record)
			atypicalTransactions = new LinkedList<TransactionRecord>();
		else
			atypicalTransactions = null;
	}
	
	/**
	 * 
	 * @return if we are recording/outputting bad transactions
	 */
	public boolean recordingBadTransactions() { return atypicalTransactions != null; }
	
	/** Records a bad transaction.
	 * 
	 * @param trans bad transaction
	 */
	public void addBad(Transaction trans)
	{
		atypicalTransactions.add(
				new BadTransactionRecord(trans,	accounts[trans.from].getBalance()));
	}
	
	/**
	 * Prints a list of bad transactions to std out.
	 *
	 */
	public void printBadTransactions()
	{
		System.out.println("Bad transactions...");
		for (TransactionRecord transrec : atypicalTransactions)
			System.out.println(transrec);
	}

	/**
	 * 
	 * @param id id of account to find
	 * @return account with id
	 */
	public Account findAccountById(int id) {
		return accounts[id];
	}

	/**
	  * Looks at commandline args and calls Bank processing.
	  */
	public static void main(String[] args) {
		// deal with command-lines args
		if (args.length == 0) {
			System.out.println("Args: transaction-file [num-workers [limit]]");
			System.exit(1);
		}
		
		String file = args[0];
		
		int numWorkers = 1;
		int limit = 0;
		boolean limit_specified = false;
		if (args.length >= 2) {
			numWorkers = Integer.parseInt(args[1]);
			if (args.length >= 3)
			{
				limit = Integer.parseInt(args[2]);
				limit_specified = true;
			}
		}
		//create bank, configure options, print results
		Bank bank = new Bank();
		bank.configureBadTransactions(limit_specified, limit);
		bank.processFile(file, numWorkers);
		for (Account account : bank.accounts)
			System.out.println(account);
		if (bank.recordingBadTransactions())
			bank.printBadTransactions();
	}

}

