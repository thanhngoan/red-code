// Buffer.java

import java.util.concurrent.*;

/**
 Holds the transactions for the worker
 threads.
*/
public class Buffer {
	public static final int CAPACITY = 64;

	protected int removeIndex;
	protected int addIndex;
	protected Transaction[] queue;
	protected int storageCount;

	protected Semaphore canAdd;
	protected Semaphore canRemove;
	
	public int getSize() { return storageCount; }
	
	public int getCapacity()
	{
		return CAPACITY;
	}
	
	/**
	 * Initialize the buffer, including threading constructs.
	 *
	 */
	public Buffer()
	{
		synchronized (this)
		{
			queue = new Transaction[getCapacity()];
			storageCount = removeIndex = addIndex = 0;
			canAdd = new Semaphore(1);
			canRemove = new Semaphore(1);
			try {
				canRemove.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns an incremented index pointer that wraps around the array.
	 * NOT a mutator
	 * @param index index to increment
	 * @return  (index + 1) % getCapacity();
	 */
	protected int incrementWrappingIndex(int index)
	{
		return  (index + 1) % getCapacity();
	}
	
	/**
	 * Addes a transaction to the queue
	 * @param transaction transaction to add
	 * @throws InterruptedException 
	 */
	public void add(Transaction transaction)
	{
		try {
			canAdd.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) //avoid race conditions
		{
			storageCount++;
			queue[addIndex] = transaction;
			addIndex = incrementWrappingIndex(addIndex);

			if (getSize() == 1) // allow removing after adding a single element
				canRemove.release();

			if (getSize() == getCapacity())
				; //if we filled up the queue to max capacity, don't release the canAdd semaphore
			else
				canAdd.release();
		}
	}
	
	/**
	 * Pops a transaction off the front of the queue and returns it.
	 * @return the transaction popped from the stack
	 * @throws InterruptedException 
	 */
	public Transaction remove() throws InterruptedException
	{
		Transaction popped = null;
		canRemove.acquire(); //ensure we are allowed to remove from the buffer
		synchronized (this) //avoid race conditions
		{
			storageCount--;
			popped = queue[removeIndex]; //popped transaction
			queue[removeIndex] = null; // garbage collection
			removeIndex = incrementWrappingIndex(removeIndex);

			if (getSize() == getCapacity() - 1) //allow adding when we pop from max capacity
				canAdd.release();
		
			if (getSize() == 0) //restrict removing when the queue is empty
				;               // by not restoring removable semaphore
			else //so long as not empty
				canRemove.release(); //restore removable semaphore
		}
		return popped;
	}
}
