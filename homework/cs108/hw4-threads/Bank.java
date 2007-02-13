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
	
	protected int getAccountCapacity() { return ACCOUNTS; }
	
	public class Worker {
		public void runLoop() throws InterruptedException
		{
			while (true)
			{
				Transaction trans = buffer.remove();
				if (trans == null)
					break;
				accounts[trans.from].transferFunds(accounts[trans.to], trans.amount);
			}
		}
	}
	
	public Bank()
	{
		buffer = new Buffer();
		accounts = new Account[getAccountCapacity()];
		//create accounts
		for (int i=0; i < getAccountCapacity(); i++)
			accounts[i] = new Account(this, i, 0);
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

	/*
	 Processes one file of transaction data
	 -fork off workers
	 -read file into the buffer
	 -wait for the workers to finish
	*/
	public void processFile(String file, int numWorkers) {
	}

	
	
	/*
	 Looks at commandline args and calls Bank processing.
	*/
	public static void main(String[] args) {
		// deal with command-lines args
		if (args.length == 0) {
			System.out.println("Args: transaction-file [num-workers [limit]]");
			System.exit(1);
		}
		
		String file = args[0];
		
		int numWorkers = 1;
		if (args.length >= 2) {
			numWorkers = Integer.parseInt(args[1]);
		}
		
		// YOUR CODE HERE
	}
}

