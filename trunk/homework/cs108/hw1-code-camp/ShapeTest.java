
import junit.framework.TestCase;
	
public class ShapeTest extends TestCase{

	public void testRequired() {
		Shape a = new Shape("0 0 0 1 1 1 1 0"),
		  b = new Shape("10 10 10 11 11 11 11 10"),
		  c = new Shape("0.5 0.5 0.5 -10 1.5 0"),
		  d = new Shape("0.5 0.5 0.75 0.75 0.75 0.2");

		assertFalse(a.crosses(b));
		assertTrue(a.crosses(c));
		assertFalse(a.crosses(d));	
		assertEquals(0, a.encircles(b));
		assertEquals(1, a.encircles(c));
		assertEquals(2, a.encircles(d));
	}

	public void testConcentricBoxes() {
		Shape smaller = new Shape("0 0 0 1 1 1 1 0"),
			bigger = new Shape("-1 -1    1 -1    1 1    -1 1");

		assertFalse(smaller.crosses(bigger));
		assertEquals(2, bigger.encircles(smaller));
		assertEquals(2, smaller.encircles(bigger));
	}

	public void testTouchingBoxes() {
		Shape smaller = new Shape("0  -1    2 -1    2  1     0  1"),
			bigger = new Shape(   "-1.5 -1    .5 -1    .5  1    -1.5  1");

		assertTrue(smaller.crosses(bigger));
		assertTrue(bigger.crosses(smaller));
		assertEquals(1, bigger.encircles(smaller));
		assertEquals(1, smaller.encircles(bigger));
	}

}
