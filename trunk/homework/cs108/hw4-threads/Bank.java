// Bank.java

/*
 Creates a bunch of accounts and uses threads
 to post transactions to the accounts concurrently.
*/

import java.io.*;
import java.util.*;

public class Bank {
	public static final int ACCOUNTS = 20;	 // number of accounts
	
	protected Buffer buffer;
	protected Account[] accounts;
	protected Collection<Worker> workers;
	protected Collection<TransactionRecord> atypicalTransactions;
	protected int limit;
	protected boolean limitSpecified;
	
	protected int getAccountCapacity() { return ACCOUNTS; }
	
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
	
	public class BadTransactionRecord extends TransactionRecord {
		public BadTransactionRecord(Transaction t, int balance)
		{
			transaction = t;
			closingBalance = balance;
		}
	}
	
	public class Worker extends Thread {
		public void run()
		{
			while (true)
			{
				try {
					Transaction trans = buffer.remove();
					if (trans == null)
						break;
					accounts[trans.from].enactTransactionAsGiver(trans);
				}
				catch (InterruptedException e)
				{
					
				}
			}
		}
	}
	
	protected int numWorkers;
	
	public Bank()
	{
		buffer = new Buffer();
		accounts = new Account[getAccountCapacity()];
		//create accounts
		for (int i=0; i < getAccountCapacity(); i++)
			accounts[i] = new Account(this, i, 0);
	}

	public void initWorkers(int numWorkers)
	{
		workers = new LinkedList<Worker>();
		for (int i=0; i < numWorkers; i++)
		{
			Worker w = new Worker();
			workers.add(w);
			w.start();
		}
	}
	/*
	 Reads transaction data (from/to/amt) from a file for processing.
	 (provided code)
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

	private void setLimit(int limit) {
		this.limit = limit;
	}

	/*
	 Processes one file of transaction data
	 -fork off workers
	 -read file into the buffer
	 -wait for the workers to finish
	*/
	public void processFile(String file, int numWorkers) throws InterruptedException {
		initWorkers(numWorkers);
		readFile(file);
		for (int i=0; i < numWorkers; i++)
			buffer.add(null);
		for (Worker worker : workers)
			worker.join();
	}
	
	protected void configureBadTransactions(boolean record, int limit)
	{
		this.limit = limit;
		if (record)
		{
			atypicalTransactions = new LinkedList<TransactionRecord>();
		}
		else

			atypicalTransactions = null;
	}
	
	public boolean recordingBadTransactions() { return atypicalTransactions != null; }
	
	public void addBad(Transaction trans)
	{
		atypicalTransactions.add(
				new BadTransactionRecord(trans,
				accounts[trans.from].getBalance()));
	}

	public Account findAccountById(int id) {
		return accounts[id];
	}
	
	public void printBadTransactions()
	{
		System.out.println("Bad transactions...");
		for (TransactionRecord transrec : atypicalTransactions)
			System.out.println(transrec);
	}

	/*
	 Looks at commandline args and calls Bank processing.
	*/
	public static void main(String[] args) throws InterruptedException {
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
		
		Bank bank = new Bank();
		bank.configureBadTransactions(limit_specified, limit);
		bank.processFile(file, numWorkers);
		for (Account account : bank.accounts)
			System.out.println(account);
		if (bank.recordingBadTransactions())
			bank.printBadTransactions();
		
	}

}

