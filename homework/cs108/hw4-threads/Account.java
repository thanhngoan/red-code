// Account.java

/*
 Simple, thread-safe Account class encapsulates
 a balance and a transaction count.
*/
public class Account {
	private int id;
	private int balance;
	private int transactions;
	protected Bank bank;
	
	public Account(Bank bank, int id, int balance) {
		this.bank = bank;
		this.id = id;
		this.balance = balance;
		transactions = 0;  
	}
	
	public int getBalance() { return balance; }
	
	public String toString() { 
		return "acct:" + id + " bal: " + balance + " trans:" + transactions;
	}
	

	/**
	 * Enacts the given transaction as the "from" bank account.
	 * @param trans transaction to enact
	 * @return
	 */
	void enactTransactionAsGiver(Transaction trans)
	{
		int amount = trans.amount;
		Account into = bank.findAccountById(trans.to);
		/* this could deadlock:
		synchronized (this) { synchronized (into) { } }
		*/
		synchronized (this)
		{
			int newBalance = balance - amount;
			boolean badtrans = this.balance >= bank.getLimit() && newBalance < bank.getLimit();
			
			this.transactions++;
			this.balance = newBalance;
			if (bank.recordingBadTransactions() && badtrans) // callback for bad transaction
				bank.addBad(trans);
		}
		synchronized (into)
		{
			into.transactions++;
			into.balance += amount;
		}
	}
	
}
