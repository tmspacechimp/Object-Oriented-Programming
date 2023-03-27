import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */
public class Sudoku {
	// Provided grid data for main/testing
	// The instance variable strategy is up to you.
	
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
	private int[][] grid ;
	private boolean solved;
	private int[][] solution ;
	private int solutions;
	private List<Spot> spots;
	private long elapsed;

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
	 * (provided utility)
	 * @param text string of 81 numbers
	 * @return grid
	 */
	public static int[][] textToGrid(String text) {
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE) {
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		}
		
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


	// Provided -- the deliverable main().
	// You can edit to do easier cases, but turn in
	// solving hardGrid.
	public static void main(String[] args) {
		Sudoku sudoku;
		sudoku = new Sudoku(hardGrid);
		
		System.out.println(sudoku); // print the raw problem
		int count = sudoku.solve();
		System.out.println("solutions:" + count);
		System.out.println("elapsed:" + sudoku.getElapsed() + "ms");
		System.out.println(sudoku.getSolutionText());
	}
	
	
	

	/**
	 * Sets up based on the given ints.
	 */
	public Sudoku(int[][] ints) {
		grid = ints;
		solved = false;
		solutions = 0;
		solution = new int[SIZE][SIZE];
		if(!sanityCheck(grid)){
			solved=true;
		}
	}

	public static boolean sanityCheck(int[][] grid) {

		for(int i = 0; i< SIZE; i++){
			Set<Integer> vert = new HashSet<>();
			Set<Integer> hor = new HashSet<>();
			Set<Integer> sq = new HashSet<>();
			for(int j = 0; j< SIZE; j++) {
				if(grid[j][i]!=0){
					if (vert.contains(grid[j][i])) return false;
					vert.add(grid[j][i]);
				}

				if(grid[i][j]!=0){
					if (hor.contains(grid[i][j])) return false;
					hor.add(grid[i][j]);
				}

				int firstI = i/PART*PART;
				int firstJ = i%PART*PART;
				if(grid[firstI + j/PART][firstJ + j%PART]!=0){
					if (sq.contains(grid[firstI + j/PART][firstJ + j%PART])) return false;
					sq.add(grid[firstI + j/PART][firstJ + j%PART]);
				}

			}
		}
		return true;
	}


	public Sudoku(String text) {
		this(textToGrid(text));
	}

	public Sudoku(String... rows) {
		this(stringsToGrid(rows));
	}


	private void sortSpots() {
		spots = new ArrayList<>();
		Spot spot;
		for(int i=0; i< SIZE; i++){
			for(int j=0; j<SIZE; j++){
				if (grid[i][j] == 0) {
					spot = new Spot(j, i);
					spots.add(spot);
				}
			}
		}
		if(spots!=null)
			Collections.sort(spots);

	}


	/**
	 * Solves the puzzle, invoking the underlying recursive search.
	 */

	public int solve() {
		if(!solved) {
			long start = System.currentTimeMillis();
			sortSpots();
			rec(0);
			long end = System.currentTimeMillis();
			elapsed = end - start;
			solved = true;
			return solutions;
		}
		return solutions;


	}

	private void rec(int index) {
		if(solutions>= MAX_SOLUTIONS) return;
		if(index==spots.size()){
			if(solutions == 0) {
				int cnt=0;
				for(int[] i : grid) {
					System.arraycopy(i, 0, solution[cnt], 0, SIZE);
					cnt++;
				}
			}
			solutions++;
			return;
		}
		Spot s = spots.get(index);
		Set<Integer> pos = s.generatePossibilities();
		for(Integer i : pos){
			//System.out.println("x" +s.x+"y"+ s.y+ "i"+ i);
			s.set(i);
			rec(index+1);
			s.set(0);
		}
	}

	@Override
	public String toString() {
		return stringify(grid);
	}

	private String stringify(int[][] grid){
		StringBuilder str = new StringBuilder();
		for (int i =0; i< SIZE; i++){
			for (int j=0; j<SIZE; j++){
				str.append(grid[i][j]+ " ");
			}
			str.append("\n");
		}
		return str.toString();
		
	}

	public String getSolutionText() {
		if(solutions>0)
			return stringify(solution); 
		return "Your Puzzle Might Be Incorrect";


	}
	
	public long getElapsed() {
		return elapsed;
	}


	private class Spot implements Comparable {
		private int x,y;
		public int possibilities;

		public Spot(int x, int y){
			this.x = x;
			this.y = y;
			generatePossibilities();
		}



		public void set(int n){
			grid[y][x]= n;
		}

		private Set<Integer> generatePossibilities() {
			HashSet<Integer> pos = new HashSet<>();
			for (int i=1; i<=9; i++){
				pos.add(i);
			}
			int firstI = y/PART*PART;
			int firstJ = x/PART*PART;
			for(int i = 0; i< SIZE; i++){
				pos.remove(grid[firstI + i/PART][firstJ + i%PART]);
				pos.remove(grid[y][i]);
				pos.remove(grid[i][x]);
			}
			//System.out.println("v: " + grid[y][x] + " p: " + pos.toString());
			possibilities= pos.size();
			return pos;
		}

		@Override
		public int compareTo(Object o) {
			Spot spot = (Spot) o;
			return this.possibilities - spot.possibilities;
		}
	}



}
