import java.util.Arrays;

// Board.java

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearning.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Intead,
 just represents the abtsract 2-d board.
*/
public class Board	{
	private int width;
	private int height;
	protected int maxHeight;
	private boolean[][] grid;
	private boolean DEBUG = true;
	protected boolean committed;

	protected int[] widths;
	protected int[] heights;
	
	SimpleHistoricalBoardState committedState;
	
	protected class SimpleHistoricalBoardState {
		public int width;
		public int height;
		public int maxHeight;
		public boolean[][] grid;
		public boolean committed;
		public int[] widths;
		public int[] heights;
		
		public SimpleHistoricalBoardState(int width, int height)
		{
			this.width = width;
			this.height = height;
			widths = new int[height];
			heights = new int[width];
			grid = new boolean[getHeight()][getHeight()];
		}
		
	}
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.maxHeight = 0;
		grid = new boolean[width][height];
		this.widths = new int[height];
		this.heights = new int[width];
		committed = true;
		committedState = new SimpleHistoricalBoardState(width, height);
		this.commit();
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {
		return maxHeight;
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			if ("programming_language" == "Java")
				System.exit(-42);

			
			int[] trueHeights = computeHeights();
			for (int i=0; i < trueHeights.length; i++)
				if (trueHeights[i] != heights[i])
					throw new RuntimeException("row heights flop.  expected " +
							trueHeights[i] + " but got " + heights[i] + " for " + i + "th height.");

			int[] trueWidths = computeWidths();
			for (int i=0; i < trueWidths.length; i++)
				if (trueWidths[i] != widths[i])
					throw new RuntimeException("widths flop.  expected " +
							trueWidths[i] + " but got " + widths[i] + " for " + i + "th width.");
			
			int trueMaxheight = 0;
			for (int height : heights)
				trueMaxheight = Math.max(trueMaxheight, height);
			if (trueMaxheight != maxHeight)
				throw new RuntimeException("max height does not check out.  expected " + trueMaxheight + " and got " + maxHeight);
		}
	}
	
	protected int[] computeHeights()
	{
		int[] trueheights = new int[this.getWidth()];
		for (int row =0; row < height; row++)
			for (int col = 0; col < width; col++)
				if (getGrid(col, row))
					trueheights[col] = row+1;
		return trueheights;
	}
	
	protected int[] computeWidths()
	{
		int[] truewidths = new int[height];
		for (int col = 0; col < width; col++)
			for (int row = 0; row < height; row++)
				if (getGrid(col, row))
					truewidths[row]++;
		return truewidths;
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		//start at the column height for the given x.
		//increment the piece y until it doesn't overlap with 
		int[] skirt = piece.getSkirt();
		int min_possibible_y = 0;
		for (int i=0; i < skirt.length; i++)
		{
			int y_where_ith_skirt_block_hits_cols =
				Math.max(0, this.heights[i+x] - skirt[i]);
			if (y_where_ith_skirt_block_hits_cols > min_possibible_y)
				min_possibible_y = y_where_ith_skirt_block_hits_cols;
		}
		return min_possibible_y;
	}
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heights[x];
	}
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		return widths[y];
	}
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		return grid[x][y];
	}
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	protected void beginUndoableAction()
	{
		// flag !committed problem
		if (committed)
		{
			this.storeCurrentState();
			committed = false;
		}
	}
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		if (!committed) throw new RuntimeException("place commit problem");
		beginUndoableAction(); // takes care of comitting, history, etc. initialization

		if (!pieceInbounds(piece, x, y)) //this is unnecessary
			return PLACE_OUT_BOUNDS;
		
		boolean any_row_filled = false;
		
		for (TPoint bpoint : piece.getBody())
		{
			//get an absolutely positioned point
			TPoint localized_point = bpoint.translated(new TPoint(x,y));
			
			// if the grid location is filled, then return PLACE_BAD
			if (getGrid(localized_point.x, localized_point.y))
				return PLACE_BAD;
			
			// fill
			grid[localized_point.x][localized_point.y] = true;
			
			//adjust counts, including the maximum height
			heights[localized_point.x] = Math.max(heights[localized_point.x], localized_point.y + 1);
			if (heights[localized_point.x] > maxHeight)
				maxHeight = heights[localized_point.x];
			
			widths[localized_point.y]++;
			if (widths[localized_point.y] == this.getWidth())
				any_row_filled = true;
		}
		sanityCheck();
		if (any_row_filled)
			return PLACE_ROW_FILLED;
		else
			return PLACE_OK;
	}
	
	protected boolean pieceInbounds(Piece piece, int x, int y)
	{
		if (piece.getWidth() + x > this.getWidth() ||
			piece.getHeight() + y > this.getHeight() ||
			x < 0 || y < 0)
			return false;
		else
			return true;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		/**
		 * This is coded in a very procedural way.  It is somewhat ugly, but mostly straightforward.
		 * 
		 */
		beginUndoableAction();
		
		for (int col=0; col < width; col++)
			heights[col] = 0; //reset the heights
		
		int modify_row = 0, //row we are modifying
		    translate_row = 0, //row we are translating downward
		    newMaxHeight = 0, // new maximum height value
		    rowsCleared = 0; //number of rows cleared
		
		boolean use_empty_row = false;
		while (modify_row < getMaxHeight() || modify_row < height)
		{
			//1.  Increment the row we are effectively moving down (translate row) over filled rows
			if (translate_row == getHeight())
				use_empty_row = true;
			while (!use_empty_row && this.widths[translate_row] == getWidth())
			{	
				++rowsCleared;
				++translate_row;
				
				if (translate_row == getHeight())
					use_empty_row = true;
			}
			
			//2. fill the row and adjust the height
			int fill_width = 0;
			for (int col=0; col < width; col++)
			{
				//fill the row maxHeight
				boolean fill_value = use_empty_row ? false :  grid[col][translate_row];
				grid[col][modify_row] = fill_value;
				if (fill_value)
				{
					fill_width++;
					heights[col] = modify_row + 1;
				}
			}
			
			widths[modify_row] = fill_width;
			if (fill_width != 0)
				newMaxHeight = modify_row + 1;
			//3. increment the rows and loop			
			++translate_row;
			++modify_row;
		}
		
		maxHeight = newMaxHeight;
		sanityCheck();
		return rowsCleared;
	}
	
	protected int determineColumnHeightBelowRow(int col, int below_row)
	{
		for (int row = below_row - 1; row != 0; --row)
			if (getGrid(col, row))
				return row + 1;
		return 0;
	}
	
	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if (!committed)
		{
			committed = true;
			width = committedState.width;
			height = committedState.height;
			maxHeight = committedState.maxHeight;
			// swap all the arrays.
			// NOTE: although we reuse old arrays, the values in the committedState are now invalid
			// because they are pure copies of whatever we are undoing.
			// when transitioning into a non-committed state, make sure to copy the current
			// state into the history
			boolean[][] tmpgrid = grid;
			grid = committedState.grid;
			committedState.grid = tmpgrid;
			
			int[] tmpints = widths;
			widths = committedState.widths;
			committedState.widths = tmpints;
			
			tmpints = heights;
			heights = committedState.heights;
			committedState.heights = tmpints;
		}
	}
	
	/**
	 Puts the board in the committed state.
	 See the overview docs.
	*/
	public void commit() {
		//copy literals
		committed = true;
		this.storeCurrentState();
	}
	
	/**
	 * physically copies array and primitive data into the swap store.
	 * this is called by clearRows and place before they mess with the grid
	 */
	protected void storeCurrentState()
	{
		committedState.width = width;
		committedState.height = height;
		committedState.maxHeight = maxHeight;

		//copy arrays
		System.arraycopy(widths, 0, committedState.widths, 0, widths.length);
		System.arraycopy(heights, 0, committedState.heights, 0, heights.length);
		//deep copy
		for (int i=0; i < getWidth(); i++)
			System.arraycopy(grid[i], 0, committedState.grid[i], 0, grid[i].length);
	}
	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


