import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import junit.framework.TestCase;


public class ChunkTest extends TestCase {
	
    public void setup()
    {
    	
    }

    public void testAdd()
    {
    	ChunkList<String> lst = new ChunkList<String>();
    	lst.add("ON1E TWO TWEEE FOUR");
    	lst.add("ONE2 TWO TWEEE FOUR");
    	lst.add("ONE 3TWO TWEEE FOUR");
    	lst.add("ONE T4WO TWEEE FOUR");
    	lst.add("ONE TW5O TWEEE FOUR");
    	lst.add("ONE TWO6 TWEEE FOUR");
    	lst.add("ONE TWO 7TWEEE FOUR");
    	lst.add("ONE TWO T8WEEE FOUR");
    	lst.add("ONE TWO TW9EEE FOUR");
    	lst.add("ONE TWO TWE0EE FOUR");
    	lst.add("ONE TWO TWEE-E FOUR");
    	lst.add("ONE TWO TWEEE= FOUR");
    	lst.add("ONE TWO TWEEE 13FOUR");
    	lst.add("ONE TWO TWEEE FO14UR");
    	assertEquals(14, lst.size());
    }
    
    public void testIteration()
    {

    	ChunkList<String> lst = new ChunkList<String>();
    	String nine = "nine";
    	String eight = "eight";
    	lst.add("1");
    	lst.add("1");
    	lst.add("1");
    	lst.add("4");
    	lst.add("5");
    	lst.add("1");
    	lst.add("1");
    	lst.add(eight);
    	lst.add(nine);

    	for (Iterator iter = lst.iterator(); iter.hasNext();)
    	{
    		iter.next();
    	}
    }
    public void testAddRemove()
    {
    	ChunkList<String> lst = new ChunkList<String>();
    	String nine = "nine";
    	String eight = "eight";
    	lst.add("1");
    	assertEquals("1", lst.iterator().next());
    	lst.add("2");
    	lst.add("3");
    	lst.add("4");
    	lst.add("5");
    	lst.add("6");
    	lst.add("7");
    	lst.add(eight);
    	lst.add(nine);
    	assertEquals(9, lst.size());
    	lst.remove(nine);
    	assertEquals(8, lst.size());

    	for (Object obj : lst)
    		assertNotNull(obj);
    	assertEquals(lst.size(), iterativelyTestSize(lst));
    	lst.add("9");
    	lst.add("10");
    	lst.add("11");
    	lst.add("12");
    	lst.add("13");
    	lst.add("14");
    	lst.add("15");
    	lst.remove(eight);
    	assertEquals(14, lst.size());
    	assertEquals(lst.size(), iterativelyTestSize(lst));
    }
    

    public void testRemove1()
    {
    	ChunkList<Integer> lst = new ChunkList<Integer>();

    	for (int i=0; i < 24; i++)
    		lst.add(new Integer(i));
    	Iterator<ChunkList> iter = lst.iterator();

    	// remove first chunk
    	for (int i=0; i < 8; i++)
    	{
    		iter.next();
    		iter.remove();
    	}
    	// ensure everything is all right
    	assertEquals(24 - 8, lst.size());
    	assertNotSame(lst.head, lst.tail);
    	assertEquals(lst.size(), iterativelyTestSize(lst));
    	
    	iter = lst.iterator();

    	// remove all but last
    	int origsize = lst.size();
    	for (int i=0; i + 1 < origsize; i++)
    	{
    		iter.next();
    		iter.remove();
    	}
    	assertEquals(1, lst.size());
    	assertEquals(lst.head, lst.tail);
    }
    
    public int iterativelyTestSize(Collection<?> collect)
    {
    	int count=0;
    	for (Object obj : collect)
    		count++;
    	return count;
    }
    
    public void verifySameListStructure(Collection one, Collection two)
    {
    	assertEquals(one.size(), two.size());
    	for (Iterator iter1 = one.iterator(),
    		 iter2 = two.iterator();; )
    	{
    		//System.out.println("size: " + ((ChunkList<String>) iter1).size());
    		
    		assertEquals(iter1.hasNext(), iter2.hasNext());
    		if (!iter1.hasNext())
    			break;
    		assertEquals(iter1.next(), iter2.next());
    	}
    	
    }
    
    public static final int RAND_SEED = 42*13;
    public static final double ADD_PROBABILITY = 0.7;
    
    public static final int NUM_ITERATIONS = 500;
    
    public void testAgainstAL1()
    {
    	ArrayList<String> l1 = new ArrayList<String>();
    	ChunkList<String> l2 = new ChunkList<String>();
    	Random rand = new Random(RAND_SEED);
    	
    	//number of times to do a test
    	for (int i=0; i < NUM_ITERATIONS; i++)
    	{
    		boolean add_p = rand.nextDouble() < ADD_PROBABILITY;
    		if (add_p || l1.size() == 0)
    		{
    			String x = new String("dog" + i);
    			l1.add(x);
    			l2.add(x);
    		}
    		else
    		{
    			int index = (int) (rand.nextDouble() * (l1.size() - 1));
    			assert(index > 0 && index< l1.size());
    			int j=0;
    			for (Iterator iter1 = l1.iterator(),
    		    		 iter2 = l2.iterator(); iter1.hasNext(); )
    		    	{
    					assertEquals(iter1.next(), iter2.next());
	    				if (index == j)
	    				{
	    					iter1.remove();
	    					iter2.remove();
	    				}
    		    	}
    		}
    		verifySameListStructure(l1, l2);
    	}
    }
}
