//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {

	public boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid=grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */

	private void clearColumn(int x){
		if (x==grid[0].length-1){
			for(int i=0; i<grid.length; i++){
				grid[i][x]=false;
			}
		} else  {
			for (int i = x; i < grid[0].length - 1; i++) {
				for(int j=0; j<grid.length; j++){
					grid[j][i]= grid[j][i+1];
				}
			}
			for(int i=0; i<grid.length; i++){
				grid[i][grid[0].length-1]=false;
			}
		}
	}


	public void clearRows(){

		boolean allTrue = true;
		for (int i = 0; i < grid[0].length;) {
			allTrue = true;
			for (int j = 0; j < grid.length; j++) {

				if (!grid[j][i]) {
					allTrue = false;
					{
						break;
					}

				}

			}
			if (allTrue) {

				clearColumn(i);
			} else i++;

		}


	}






	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid; // YOUR CODE HERE
	}
}
