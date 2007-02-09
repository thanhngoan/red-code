import junit.framework.TestCase;


public class DBBindingTest extends TestCase {
	public void testSimpleTest(){
		DBBinding oneTwo = new DBBinding("one        : two      ");
		assertEquals("one", oneTwo.getKey());
		assertEquals("two", oneTwo.getValue());
	}
	
	public void trivialTest2() {

		DBBinding oneTwo = new DBBinding("one:two");
		assertEquals("one", oneTwo.getKey());
		assertEquals("two", oneTwo.getValue());
		oneTwo = new DBBinding("one", "two");
		assertEquals("one", oneTwo.getKey());
		assertEquals("two", oneTwo.getValue());
	}
}
