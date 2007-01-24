import junit.framework.TestCase;

import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class TPointTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	// Here are some sample tests to get you started
	
	public void testRotation() {
		TPoint one_one = new TPoint(1,1);
		double ninety = Math.toRadians(90);
		assertEquals(one_one, one_one.rotated(Math.PI * 2));
		assertEquals(new TPoint(-1, 1), one_one.rotated(ninety));
		assertEquals(new TPoint(-1, 2), new TPoint(2, 1).rotated(ninety));
		assertEquals(new TPoint(0, 0), new TPoint(0, 0).rotated(ninety));
	}
	
	public void testTranslation() {
		assertEquals(new TPoint(1, 1), new TPoint(0, 0).translated(new TPoint(1,1)));
		assertEquals(new TPoint(6, 1), new TPoint(5, 0).translated(new TPoint(1,1)));
		assertEquals(new TPoint(42, -42), new TPoint(84, 0).translated(new TPoint(-42,-42)));
	}
	
	
}

