import junit.framework.TestCase;


public class BoardTest extends TestCase {
	Board b, btopclear, bundo1, bfill, bfloat;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated, stick_up, stick_flat;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	
	protected void setUp() throws Exception {
		b = new Board(3, 6);
		btopclear = new Board(4, 6);
		bundo1 = new Board(4, 6);
		bfill = new Board(4, 9);
		bfloat = new Board(6, 10);
		stick_up = new Piece(Piece.STICK_STR);
		stick_flat = stick_up.computeNextRotation();
		
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		
		b.place(pyr1, 0, 0);
	}
	
	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	/**
	 * But sticks on top and watch clearRows choke.
	 */
	public void testTopClear() {
		assertEquals(0, btopclear.getMaxHeight());
		btopclear.place(stick_up, 3,0);
		assertEquals(0, btopclear.getColumnHeight(0));
		assertEquals(0, btopclear.getColumnHeight(1));
		assertEquals(0, btopclear.getColumnHeight(2));
		assertEquals(4, btopclear.getColumnHeight(3));
		assertEquals(4, btopclear.getMaxHeight());
		btopclear.commit();
		btopclear.place(stick_flat, 0,4);
		assertEquals(5, btopclear.getColumnHeight(0));
		assertEquals(5, btopclear.getColumnHeight(1));
		assertEquals(5, btopclear.getColumnHeight(2));
		assertEquals(5, btopclear.getColumnHeight(3));
		assertEquals(1, btopclear.getRowWidth(0));
		assertEquals(1, btopclear.getRowWidth(1));
		assertEquals(1, btopclear.getRowWidth(2));
		assertEquals(1, btopclear.getRowWidth(3));
		assertEquals(4, btopclear.getRowWidth(4));
		assertEquals(5 ,btopclear.dropHeight(stick_flat, 0));
		assertEquals(5 ,btopclear.dropHeight(stick_up, 0));
		assertEquals(5 ,btopclear.dropHeight(pyr1, 0));
		assertEquals(5, btopclear.getMaxHeight());
		btopclear.clearRows();
		assertEquals(0, btopclear.getColumnHeight(0));
		assertEquals(0, btopclear.getColumnHeight(1));
		assertEquals(0, btopclear.getColumnHeight(2));
		assertEquals(4, btopclear.getColumnHeight(3));
		assertEquals(4, btopclear.getMaxHeight());
	}
	
	public void testUndo1() {
		int result = 0;
		result = bundo1.place(stick_up, 3,0);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(0, bundo1.getColumnHeight(0));
		assertEquals(0, bundo1.getColumnHeight(1));
		assertEquals(0, bundo1.getColumnHeight(2));
		assertEquals(4, bundo1.getColumnHeight(3));
		assertEquals(1, bundo1.getRowWidth(0));
		assertEquals(1, bundo1.getRowWidth(1));
		assertEquals(1, bundo1.getRowWidth(2));
		assertEquals(1, bundo1.getRowWidth(3));
		bundo1.commit();
		result = bundo1.place(stick_up, 2,0);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(0, bundo1.getColumnHeight(0));
		assertEquals(0, bundo1.getColumnHeight(1));
		assertEquals(4, bundo1.getColumnHeight(2));
		assertEquals(4, bundo1.getColumnHeight(3));
		assertEquals(2, bundo1.getRowWidth(0));
		assertEquals(2, bundo1.getRowWidth(1));
		assertEquals(2, bundo1.getRowWidth(2));
		assertEquals(2, bundo1.getRowWidth(3));
		bundo1.undo();
		assertEquals(0, bundo1.getColumnHeight(0));
		assertEquals(0, bundo1.getColumnHeight(1));
		assertEquals(0, bundo1.getColumnHeight(2));
		assertEquals(4, bundo1.getColumnHeight(3));
		result = bundo1.place(stick_up, 3,0);
		
		assertEquals(Board.PLACE_BAD, result);
		bundo1.undo();
		assertEquals(0, bundo1.getColumnHeight(0));
		assertEquals(0, bundo1.getColumnHeight(1));
		assertEquals(0, bundo1.getColumnHeight(2));
		assertEquals(4, bundo1.getColumnHeight(3));
		assertEquals(4, bundo1.getMaxHeight());
	}
	
	public void testFillManyRows() {
		int result = 0;
		result = bfill.place(stick_up, 0,0);
		assertEquals(Board.PLACE_OK, result);
		bfill.commit();
		result = bfill.place(stick_up, 1,0);
		assertEquals(Board.PLACE_OK, result);
		bfill.commit();
		result = bfill.place(stick_up, 2,0);
		assertEquals(Board.PLACE_OK, result);
		bfill.commit();
		result = bfill.place(stick_up, 3,0);
		assertEquals(Board.PLACE_ROW_FILLED, result);
		bfill.commit();
		assertEquals(4 ,bfill.dropHeight(pyr1, 0));
		result = bfill.place(pyr1, 0,4);
		assertEquals(Board.PLACE_OK, result);
		bfill.commit();
		result = bfill.place(stick_flat, 0, 4);
		assertEquals(Board.PLACE_BAD, result);
		bfill.undo();
		result = bfill.place(stick_flat, 0, 6);
		assertEquals(Board.PLACE_ROW_FILLED, result);
		bfill.commit();
		bfill.clearRows();
		assertEquals(1, bfill.getColumnHeight(0));
		assertEquals(2, bfill.getColumnHeight(1));
		assertEquals(1, bfill.getColumnHeight(2));
		assertEquals(0, bfill.getColumnHeight(3));
		assertEquals(2, bfill.getMaxHeight());
		assertEquals(2 ,bfill.dropHeight(pyr1, 0));
		assertEquals(1 ,bfill.dropHeight(pyr4, 0));
	}
	
	public void testFloaties() {
		int result = 0;
		result = bfloat.place(stick_up, 1,6);
		assertEquals(Board.PLACE_OK, result);
		bfloat.commit();
		bfloat.clearRows();
	}

	
	protected int totalFilledBricks(Board board) {
		int sum=0;
		for (int i=0; i < board.getHeight(); i++)
			for (int j=0; j < board.getWidth(); j++)
				if (board.getGrid(j, i))
					sum++;
		return sum;
	}
	
	// Makre  more tests, by putting together longer series of 
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.
	
	
}
