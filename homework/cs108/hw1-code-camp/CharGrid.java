// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.
//import java.util.ArrayList;
public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	public char charAt(int x, int y) { return this.grid[x][y]; }
	public char charAt(IntPoint2 point) { return this.grid[point.getX()][point.getY()]; }
	
	public boolean isPointInside(IntPoint2 point)
	{
		int x = point.getX(), y = point.getY();
		return 0 <= x && x <= this.gridWidth() && 0 <= y && y <= this.gridHeight();
	}

	public int gridHeight() { return this.grid[0].length; }
	public int gridWidth() { return this.grid.length; }
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int left_most_x = -1, right_most_x = -1,
			top_most_y = -1, bottom_most_y = -1;
		
		for (int x=0; x< this.gridWidth(); x++)
		{
			for (int y=0; y<this.gridHeight(); y++)
			{
				char current_char = this.charAt(x,y);
				if (ch == current_char)
				{
					if (left_most_x == -1)
					{
						left_most_x = x;
						right_most_x = x;
						top_most_y = y;
						bottom_most_y = y;
					}
					if (x < left_most_x)    left_most_x = x;
					if (x > right_most_x)   right_most_x = x;
					if (y < top_most_y)     top_most_y = y;
					if (y > bottom_most_y)  bottom_most_y = y;
				}
			}
		}
		
		if (left_most_x == -1)
			return 0;
		return (right_most_x - left_most_x) * (bottom_most_y - top_most_y);
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int count = 0;
		for (int x=0; x< this.gridWidth(); x++)
			for (int y=0; y<this.gridHeight(); y++)
				if (existsPlusAtPoint(new IntPoint2(x, y)))
					count++;
		return count;
	}
	
	protected boolean existsPlusAtPoint(IntPoint2 point)
	{
		//char center_char = this.grid[point.getX()][point.getY()];
		int acceptableArmLength = armLength(point, IntPoint2.UP);
		IntPoint2[] other_directions = {IntPoint2.DOWN, IntPoint2.LEFT, IntPoint2.RIGHT};
		for (IntPoint2 dir : other_directions)
		{
			if (armLength(point, dir) != acceptableArmLength)
				return false;
		}
		return true;
	}
	
	protected int armLength(IntPoint2 center_point, IntPoint2 unit_direction)
	{
		int arm_length = -1;
		char arm_char = this.charAt(center_point);
		//IntPoint2[] directions = { new IntPoint2(0, 1)};
		for (IntPoint2 current_point = center_point;
			 this.isPointInside(current_point);
			 current_point = current_point.add(unit_direction))
		{
			if (arm_char == this.charAt(current_point))
				arm_char++;
			else
				break;
		}
		return arm_length;
	}
	
}
