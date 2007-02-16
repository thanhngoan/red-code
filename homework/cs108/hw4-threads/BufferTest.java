import junit.framework.TestCase;


public class BufferTest extends TestCase {

	public void testBuffer() {
		Buffer buff = new Buffer();
		assertEquals(Buffer.CAPACITY, buff.getCapacity());
		assertEquals(0, buff.getSize());
	}

	public void testAdd() {
		Buffer buff = new Buffer();
		buff.add(null);
		assertEquals(1, buff.getSize());
		buff.add(null);
		assertEquals(2, buff.getSize());
	}
	
	public void testWrappingAdd() {
		Buffer buff = new Buffer();
		for (int x = 0; x < 23; x++)
		{
			for (int i=0; i < 42; i++)
			{
				assertEquals(i, buff.getSize());
				buff.add(null);
			}
			assertEquals(42, buff.getSize());
			for (int i=0; i < 42; i++)
			{
				assertEquals(42 - i, buff.getSize());
				buff.remove();
			}
			assertEquals(0, buff.getSize());
		}
		assertEquals(0, buff.getSize());
	}

}
