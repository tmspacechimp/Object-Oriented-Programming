// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

import java.util.Vector;

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
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {

		int up=-1,down=-1,left=-1,right=-1;



		for (int i=0; i<grid.length; i++){

			for(int j=0; j<grid[0].length ; j++){
				if ( grid[i][j]==ch){
					if(up==-1)	up=i;
					if(down==-1)	down=i;
					if(left==-1)	left=j;
					if(right==-1)	right=j;
					if(i< up)	up=i;
					if(i>down)	down=i;
					if(j< left)	left=j;
					if(j> right)	right=j;
				}

			}
		}
		if (up ==-1) return 0;
		return (down-up+1)*(right-left+1);
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */

	private int cntUp(int first, int second){
		char ch= grid[first][second];
		int cnt=0;
		int coordinate=first;
		while(coordinate!=0){
			if (grid[coordinate-1][second] == ch){
				cnt++;
				coordinate--;

			}	else break;
		}
		return cnt;
	}

	private int cntDown(int first, int second){
		char ch= grid[first][second];
		int cnt=0;
		int coordinate=first;
		while(coordinate!=grid.length-1 ){
			if (grid[coordinate+1][second] == ch){
				cnt++;
				coordinate++;

			}	else break;
		}
		return cnt;
	}

	private int cntLeft(int first, int second){
		char ch= grid[first][second];
		int cnt=0;
		int coordinate=second;
		while(coordinate!=0){
			if (grid[first][coordinate-1] == ch){
				cnt++;
				coordinate--;

			}	else break;
		}
		return cnt;
	}

	private int cntRight(int first, int second){
		char ch= grid[first][second];
		int cnt=0;
		int coordinate=second;
		while(coordinate!=grid[0].length-1){
			if (grid[first][coordinate+1] == ch){
				cnt++;
				coordinate++;

			}	else break;
		}
		return cnt;
	}

	private boolean isPlus(int first, int second){
		if(cntUp(first, second)== cntDown(first, second) &&
			cntDown(first, second)== cntLeft(first, second) &&
			cntRight(first, second)== cntLeft(first, second) &&
			cntUp(first, second)!=0)
			return true;
		return false;
	}

	public int countPlus() {
		int cnt=0;

		for(int i=0; i< grid.length; i++){
			for (int j=0; j<grid[0].length; j++){
				if (grid[i][j]!=' ') {
					if (isPlus(i, j)) cnt++;
				}
			}
		}
		return cnt;
	}
	
}
