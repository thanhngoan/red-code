import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;


public class ChunkList<E> extends AbstractCollection<E> {
	protected int size;
	final protected static int ARRAY_SIZE = 8;

	protected Chunk head;
	protected Chunk tail;
	
	protected class Chunk {
		int fillSize;
		E[] items;
		Chunk next;
		
		@SuppressWarnings("unchecked")
		public Chunk() {
			fillSize = 0;
			items = (E[]) new Object[ARRAY_SIZE];
		}
		public boolean isFull() {
			return fillSize == items.length;
		}
		
		public void removeNth(int n) {
			items[n] = null;
			//move all items back one
			for (int i=n+1; i < fillSize; i++)
				items[i-1] = items[i];
			//nullify the last pointer
			items[fillSize - 1] = null;
			fillSize--;
		}
		
		public E getNth(int n) {
			if (n < fillSize)
				return items[n];
			else
				throw new IndexOutOfBoundsException("Attempted to access index " + n + " in " + fillSize + " bounded chunk.");
		}

		public boolean isEmpty() {
			return fillSize == 0;
		}
		
		/**
		 * must ensure that array is not full before adding anything to it
		 * @param item to add
		 */
		public void add(E item) {
			assert(fillSize != items.length);
			items[fillSize++] = item;
		}
		
		protected void setNext(Chunk c) { next = c; }
	}
	
	/**  Le conventions de le chunklist:
	 *   * the tail and head pointer can be the same
	 *   * the 
	 *
	 */
	public ChunkList()
	{
		size = 0;
	}
	
	public Iterator iterator() {
		
		return new Iterator(){
			Chunk previousChunk = null;
			Chunk ancientChunk = null; //2 chunks ago
			Chunk curChunk = head;
			int index = 0; //stores the index of the current 'next' value
			public boolean hasNext() {
				return curChunk != null && //must have a valid current chunk
					   (index < curChunk.fillSize //the index value is within the current chunk 
					    || curChunk.next != null); //or there is a non-empty next
			}

			public Object next() {
				//null current chunk always denotes invalid iterator
				if (curChunk == null)
					return null;
				//take next from current chunk if not out of bounds
				if (index < curChunk.fillSize)
				{
					return curChunk.getNth(index++);
				}
				//take next from the next chunk
				else if (curChunk.next != null)
				{
					assert(curChunk.next.fillSize > 0);
					//take 
					index = 0;
					ancientChunk = previousChunk;
					previousChunk = curChunk;
					curChunk= curChunk.next;
					return curChunk.getNth(index++);
				}
				//null next chunk and oob in current chunk
				else
				{
					curChunk = null; //this is now an invalid iterator
					//index++;
					return null;
				}
			}
			
			protected void manageChunkDeleted(Chunk deleted, Chunk nowbefore, Chunk nowafter)
			{
				Chunk furthest_right = nowafter != null ? nowafter : nowbefore;
				Chunk furthest_left = nowbefore != null ? nowbefore : nowafter;
				
				//keep the main chunklist pointers in check
				if (head == deleted)
					head = furthest_left;
				if (tail == deleted)
					tail = furthest_right;
				// link the before and after chunks if necessary
				if (nowbefore != null)
					nowbefore.next = nowafter;
				if (deleted == curChunk)
				{
					curChunk = nowafter;
					index = 0;
				}
			}

			public void remove() {
				//remove from within the current array
				if (index != 0)
				{
					size--;
					curChunk.removeNth(--index);
					if (curChunk.isEmpty())
						manageChunkDeleted(curChunk, previousChunk, curChunk.next);
				}
				//remove from the previous chunk
				else
				{
					size--;
					previousChunk.removeNth(previousChunk.fillSize - 1);
					//emptied a chunk.  make sure to delete it and propagate
					//the news to the main class
					if (previousChunk.isEmpty())
						manageChunkDeleted(previousChunk, ancientChunk, curChunk);
				}
			}
		};
	}

	public int size() {
		return size;
	}
	
	public boolean add(E item)
	{
		//increment the number of items
		size++;
		
		//chunk is the chunk we'll insert at
		Chunk chunk = null;
		if (tail == null)
		{
			assert(head == null);
			chunk = head = tail = new Chunk();
		}
		else
			chunk = tail;
		
		//when full, spawn another chunk
		if (chunk.isFull())
		{
			Chunk newtail = new Chunk();
			chunk.setNext(newtail);
			newtail.add(item);
			tail = newtail;
		}
		else
		{
			chunk.add(item);
		}
		return true;
	}

}
