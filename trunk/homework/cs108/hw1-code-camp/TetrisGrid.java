import java.lang.reflect.Array;
import java.util.Collections;

//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
	protected boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * 
	 * That means this class performs destructive operations on the passed grid object!  
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		for (int row=0; row < gridHeight(); row++) {
			while (isRowFilled(row))
				shiftRowsDown(row);
		}
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}
	
	/**
	 * Mutator.  Drops all rows above rowToRemoveIndex down a level, and fills the top row with
	 * false (empty) values.
	 * @param rowToRemoveIndex
	 */
	protected void shiftRowsDown(int rowToRemoveIndex) {
		for (int row=rowToRemoveIndex; row < gridHeight(); row++)
		{
			if (row+1 != gridHeight()) //replace
			{
				for (int col=0; col < gridWidth(); col++)
					grid[col][row] = grid[col][row+1];
			}
			else //clear the top row
			{
				for (int col=0; col < gridWidth(); col++)
					grid[col][row] = false;
			}
		}
	}

	protected int gridHeight() {
		return gridWidth() == 0 ? 0 : grid[0].length; 
	}
	
	protected int gridWidth() {
		return grid.length;
	}

	/**
	 * @param rowIndex
	 * @return whether all elements are true in given row
	 */
	protected boolean isRowFilled(int rowIndex)
	{
		
		for (int col=0; col < gridWidth(); col++)
			if (this.grid[col][rowIndex] == false)
				return false;
		return true;
	}
}
