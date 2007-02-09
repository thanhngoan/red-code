import java.util.Collection;
import java.util.Stack;

import junit.framework.TestCase;

public class SudokuTest extends TestCase {

	public static final int[][] easyGrid = Sudoku.stringsToGrid(
			"1 6 4 0 0 0 0 0 2",
			"2 0 0 4 0 3 9 1 0",
			"0 0 5 0 8 0 4 0 7",
			"0 9 0 0 0 6 5 0 0",
			"5 0 0 1 0 2 0 0 8",
			"0 0 8 9 0 0 0 3 0",
			"8 0 9 0 4 0 2 0 0",
			"0 7 3 5 0 9 0 0 1",
			"4 0 0 0 0 0 6 7 9");
	
	public void testBasic()
	{
		Sudoku sudoku = new Sudoku(easyGrid);

		assertEquals(4, sudoku.getGridValue(1,1));
		assertEquals(0, sudoku.getGridValue(2,1));
		assertEquals(3, sudoku.getGridValue(3,2));
		assertEquals(9, sudoku.getGridValue(9,1));
		assertEquals(5, sudoku.getGridValue(4,2));
	}	
	public void testStringRep()
	{
		Sudoku sudoku = new Sudoku(easyGrid);

		assertEquals(
				"1 6 4 0 0 0 0 0 2\n" +
				"2 0 0 4 0 3 9 1 0\n" +
				"0 0 5 0 8 0 4 0 7\n" +
				"0 9 0 0 0 6 5 0 0\n" +
				"5 0 0 1 0 2 0 0 8\n" +
				"0 0 8 9 0 0 0 3 0\n" +
				"8 0 9 0 4 0 2 0 0\n" +
				"0 7 3 5 0 9 0 0 1\n" +
				"4 0 0 0 0 0 6 7 9\n", sudoku.toString());
	}
	
	public void testStacker()
	{
		int nonzerocount = 0;
		Sudoku sudoku = new Sudoku(easyGrid);
		Stack<Sudoku.Spot> stack = sudoku.computeSpotsStack();
		for (Sudoku.Spot item : stack)
		{
			if (sudoku.getGridValue(item.x, item.y) != 0)
				nonzerocount++;
		}
		assertEquals(81, stack.size());
		assertEquals(nonzerocount, sudoku.filledSpaces());
	}
	
	public void testPossibilitiesCorrectness()
	{
		Sudoku sudoku = new Sudoku(easyGrid);
		for (int i=1; i < 10; i++)
			for (int j=1; j < 10; j++)
				if (sudoku.getGridValue(i, j) == 0)
					assertNotSame(0, sudoku.createSpot(i, j).possibleNumbers().size());
		
	}
	
	public void testValidity()
	{
		Sudoku sudoku = new Sudoku(easyGrid);
		//assertEquals(true, sudoku.isBoardValid());
	}

	public void testAnalyzer1()
	{
		Sudoku sudoku = new Sudoku(easyGrid);
		Sudoku.Spot s2x1 = sudoku.createSpot(2,1);
		assertEquals(true, s2x1.rowLacksNumber(8));
		assertEquals(true, s2x1.colLacksNumber(8));
		assertEquals(false, s2x1.squareLacksNumber(8));
		assertEquals(true, s2x1.rowLacksNumber(2));
		assertEquals(true, s2x1.colLacksNumber(2));
		assertEquals(true, s2x1.squareLacksNumber(2));
		
		assertEquals(true, s2x1.rowLacksNumber(5));
		assertEquals(true, s2x1.colLacksNumber(5));
		assertEquals(true, s2x1.squareLacksNumber(5));
		
		Collection<Integer> possibleValues = s2x1.possibleNumbers();
		
		assertEquals(0, sudoku.getGridValue(2, 1));
		assertEquals(7, sudoku.getGridValue(2, 2));
		assertEquals("[1, 2, 5]", possibleValues.toString());
	}
	
	public void testAnalyzer3()
	{
		assertEquals(36, new Sudoku(easyGrid).filledSpaces());
	}
	
	public void testAnalyzer2()
	{
		/*
		 * 	"1 6 4 0 0 0 0 0 2",
			"2 0 0 4 0 3 9 1 0",
			"0 0 5 0 8 0 4 0 7",
			"0 9 0 0 0 6 5 0 0",
			"5 0 0 1 0 2 0 0 8",
			"0 0 8 9 0 0 0 3 0",
			"8 0 9 0 4 0 2 0 0",
			"0 7 3 5 0 9 0 0 1",
			"4 0 0 0 0 0 6 7 9");
		 */
		Sudoku sudoku = new Sudoku(easyGrid);
		Sudoku.Spot s2x1 = sudoku.createSpot(2,1);
		Sudoku.Spot s1x2 = sudoku.createSpot(1,2);

		assertEquals("[1, 2, 5]", s2x1.possibleNumbers().toString());
		assertEquals("[6]", s1x2.possibleNumbers().toString());
	}
	

	public void testSolver()
	{
		Sudoku sudoku = new Sudoku(easyGrid);
		String orig = sudoku.toString();
		assertEquals(1, sudoku.solve());
		String now = sudoku.toString();
		assertEquals(orig, now);
		

		sudoku = new Sudoku(Sudoku.hardGrid);
		orig = sudoku.toString();
		assertEquals(1, sudoku.solve());
		now = sudoku.toString();
		assertEquals(orig, now);
		

		sudoku = new Sudoku(Sudoku.hardGridMod);
		orig = sudoku.toString();
		assertEquals(6, sudoku.solve());
		now = sudoku.toString();
		assertEquals(orig, now);

		sudoku = new Sudoku(Sudoku.mediumGrid);
		orig = sudoku.toString();
		assertEquals(1, sudoku.solve());
		now = sudoku.toString();
		assertEquals(orig, now);
		
	}
}
