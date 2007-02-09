import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */
public class Sudoku {
	
	// Provided grid data for main/testing
	
	// Provided easy 1 6 grid
	// (can paste this text into the GUI too)
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
	
	
	// Provided medium 5 3 grid
	public static final int[][] mediumGrid = Sudoku.stringsToGrid(
	 "530070000",
	 "600195000",
	 "098000060",
	 "800060003",
	 "400803001",
	 "700020006",
	 "060000280",
	 "000419005",
	 "000080079");
	
	// Provided hard 3 7 grid
	// 1 solution this way, 6 solutions if the 7 is changed to 0
	public static final int[][] hardGrid = Sudoku.stringsToGrid(
	"3 7 0 0 0 0 0 8 0",
	"0 0 1 0 9 3 0 0 0",
	"0 4 0 7 8 0 0 0 3",
	"0 9 3 8 0 0 0 1 2",
	"0 0 0 0 4 0 0 0 0",
	"5 2 0 0 0 6 7 9 0",
	"6 0 0 0 2 1 0 4 0",
	"0 0 0 5 3 0 9 0 0",
	"0 3 0 0 0 0 0 5 1");
	
	
	public static final int SIZE = 9;  // size of the whole 9x9 puzzle
	public static final int PART = 3;  // size of each 3x3 part
	public static final int MAX_SOLUTIONS = 100;
	
	// Provided various static utility methods to
	// convert data formats to int[][] grid.
	
	/**
	 * Returns a 2-d grid parsed from strings, one string per row.
	 * The "..." is a Java 5 feature that essentially
	 * makes "rows" a String[] array.
	 * (provided utility)
	 * @param rows array of row strings
	 * @return grid
	 */
	public static int[][] stringsToGrid(String... rows) {
		int[][] result = new int[rows.length][];
		for (int row = 0; row<rows.length; row++) {
			result[row] = stringToInts(rows[row]);
		}
		return result;
	}
	
	
	/**
	 * Given a single string containing 81 numbers, returns a 9x9 grid.
	 * Skips all the non-numbers in the text.
	 * (provided code)
	 * @param text string of 81 numbers
	 * @return grid
	 */
	public static int[][] textToGrid(String text) {
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE)
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		
		int[][] result = new int[SIZE][SIZE];
		int count = 0;
		for (int row = 0; row<SIZE; row++) {
			for (int col=0; col<SIZE; col++) {
				result[row][col] = nums[count];
				count++;
			}
		}
		return result;
	}
	
	
	/**
	 * Given a string containing digits, like "1 23 4",
	 * returns an int[] of those digits {1 2 3 4}.
	 * (provided utility)
	 * @param string string containing ints
	 * @return array of ints
	 */
	public static int[] stringToInts(String string) {
		int[] a = new int[string.length()];
		int found = 0;
		for (int i=0; i<string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				a[found] = Integer.parseInt(string.substring(i, i+1));
				found++;
			}
		}
		int[] result = new int[found];
		System.arraycopy(a, 0, result, 0, found);
		return result;
	}
	
	int[][] grid;

	public Sudoku (int[][] grid)
	{
		this.grid = grid;
	}
	public Sudoku (String text)
	{
		this.grid = Sudoku.textToGrid(text);
	}
	
	public String toString() {
		String out = "";
		for (int j=getHeight()-1; j != 0; j--)
		{
			String[] row = new String[getWidth()];
			for (int i=0; i < getWidth(); i++)
				row[i] = Integer.toString(grid[i][j]);
			
			out += RedUtil.join(" ", row) + "\n";
		}
		return out;
	}

	protected int getWidth() { return grid.length; }
	protected int getHeight() { return grid[0].length; }


	
	public static void main(String[] args) {
		Sudoku sudoku;
		
		// YOUR CODE HERE
		
		System.out.println(sudoku); // print the raw problem
		System.out.println("solutions:" + sudoku.solve());
		System.out.println(sudoku.getSolutionText());
	}
	

	protected class Spot {
		int x; int y; // from 1 to 9


		protected boolean rowLacksNumber(int n)
		{
			for (int i=1; i <= 9; i++)
			{
				if (grid[x][i] == n)
					return false;
			}
			return true;
		}
		protected boolean colLacksNumber(int n)
		{
			for (int i=1; i <= 9; i++)
			{
				if (grid[i][y] == n)
					return false;
			}
			return true;
		}
		
		protected int lowerBoundOn1or4or7(int n)
		{
			if (n < 4)       return 0;
			else if ( n < 7) return 4;
			else             return 7;				
		}
		
		protected boolean squareLacksNumber(int n)
		{
			int i = lowerBoundOn1or4or7(x);
			int j = lowerBoundOn1or4or7(y);
			for (; i <= i + 3; i++)
			{
				for (; j <= j + 3; j++)
				if (grid[i][j] == n)
					return false;
			}
			return true;
		}
		
		public Collection<Integer> possibleNumbers(int[][] board)
		{
			ArrayList<Integer> possibilities = new ArrayList<Integer>();
			
			for (int i=1; i<= 9; i++)
			{
				if (rowLacksNumber(i) &&
					colLacksNumber(i) && 
					squareLacksNumber(i))
					possibilities.add(i);
			}
			
			return possibilities;
		}
	}
	
}
