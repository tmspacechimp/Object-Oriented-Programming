import junit.framework.TestCase;
import java.util.*;

public class TetrisGridTest extends TestCase {

	public void testGetGrid(){
		boolean[][] before ={{}};
		TetrisGrid tetris = new TetrisGrid(before);
		assertTrue(Arrays.deepEquals(before, tetris.getGrid()));

		boolean[][] before1 =
				{
						{true, true, false },
						{false, true, true }
				};
		TetrisGrid tetris1= new TetrisGrid(before1);
		assertTrue(Arrays.deepEquals(before1, tetris1.getGrid()));

	}


	// Provided simple clearRows() test
	// width 2, height 3 grid
	public void testClear1() {
		boolean[][] before =
				{
						{true, true, false },
						{false, true, true }
				};

		boolean[][] after =
				{
						{true, false, false},
						{false, true, false}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );

		boolean[][] before1 ={
				{true, true, false, false, true},
				{false, true, false, false, true}
		};

		boolean[][] after1 ={
				{true, false, false, false, false},
				{false, false, false, false, false}
		};

		TetrisGrid tetris1 = new TetrisGrid(before1);

		tetris1.clearRows();
		assertTrue( Arrays.deepEquals(after1, tetris1.getGrid()) );
	}



	public void testClear2() {
		boolean[][] before ={
				{true}

		};

		boolean[][] after ={
				{false}
		};

		TetrisGrid tetris = new TetrisGrid(before);

		tetris.clearRows();
		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );

		boolean[][] before1 ={
				{true},
				{true},
				{true},
				{true}
		};

		boolean[][] after1 ={
				{false},
				{false},
				{false},
				{false}
		};


		TetrisGrid tetris1 = new TetrisGrid(before1);

		tetris1.clearRows();
		assertTrue( Arrays.deepEquals(after1, tetris1.getGrid()) );



	}

	public void testClear3() {
		boolean[][] before =
				{
						{true, true, true },
						{false, true, true },
						{true, false, true },
						{false, true, true },
						{false, false, true },
						{true, true, true }

				};

		boolean[][] after =
				{
						{true, true, false },
						{false, true, false },
						{true, false, false },
						{false, true, false },
						{false, false, false },
						{true, true, false }
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );

		boolean[][] before1 ={
				{true, true, false, true, true},
				{false, true, true, true, false},
				{true, true, false, true, true},
				{false, true, true, true, false},
				{true, true, false, true, true},
				{false, true, false, true, true}
		};

		boolean[][] after1 ={
				{true, false, true, false, false},
				{false, true, false, false, false},
				{true, false, true, false, false},
				{false, true, false, false, false},
				{true, false, true, false, false},
				{false, false, true, false, false}
		};

		TetrisGrid tetris1 = new TetrisGrid(before1);

		tetris1.clearRows();
		assertTrue( Arrays.deepEquals(after1, tetris1.getGrid()) );
	}
}