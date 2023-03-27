
// Test cases for CharGrid -- a few basic tests are provided.

import junit.framework.TestCase;

public class CharGridTest extends TestCase {
	
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	public void testCharArea3() {
		char[][] grid = new char[][] {
				{'c'},

		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.charArea('c'));

		char[][] grid1 = new char[][] {
				{},
		};
		CharGrid cg1 = new CharGrid(grid);
		assertEquals(0, cg1.charArea('a'));

	}

	public void testCharArea4() {
		char[][] grid = new char[][] {
				{' ',' ','p',' ',' ',' ',' ',' ',' '},
				{' ',' ','p',' ',' ',' ',' ','x',' '},
				{'p','p','p','p','p',' ','x','x','x'},
				{' ',' ','p',' ',' ','y',' ','x',' '},
				{' ',' ','p',' ','y','y','y',' ',' '},
				{'z','z','z','z','z','y','z','z','z'},
				{' ','x','x',' ',' ','y',' ',' ',' '}
		};

		CharGrid cg = new CharGrid(grid);
		assertEquals(25, cg.charArea('p'));
		assertEquals(9, cg.charArea('z'));
		assertEquals(0, cg.charArea('e'));
	}


	public void testCountPlus1() {
		char[][] grid = new char[][] {
				{'m', 't', 'm'},
				{'t', 't', 't'},
				{'m', 't', 'm'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());

		char[][] grid1 = new char[][] {
				{'m', 't', 'm'},
				{'t', 'm', 't'},
				{'m', 't', 'm'}
		};

		CharGrid cg1 = new CharGrid(grid1);

		assertEquals(0, cg1.countPlus());

		char[][] grid2 = new char[][] {
				{}
		};

		CharGrid cg2 = new CharGrid(grid2);

		assertEquals(0, cg2.countPlus());

	}



	public void testCountPlus2() {
		char[][] grid = new char[][] {
				{' ',' ','p',' ',' ',' ',' ',' ',' '},
				{' ',' ','p',' ',' ',' ',' ','x',' '},
				{'p','p','p','p','p',' ','x','x','x'},
				{' ',' ','p',' ',' ','y',' ','x',' '},
				{' ',' ','p',' ','y','y','y',' ',' '},
				{'z','z','z','z','z','y','z','z','z'},
				{' ','x','x',' ',' ','y',' ',' ',' '}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(2, cg.countPlus());

		char[][] grid1 = new char[][] {
				{' ',' ','p',' ',' ',' ',' ','x',' '},
				{' ',' ','p',' ',' ',' ',' ','x',' '},
				{'p','p','p','p','p','p','x','x','x'},
				{' ',' ','p',' ',' ','y',' ','x',' '},
				{' ',' ','p',' ','y','y','y',' ',' '},
				{'z','z','z','z','z','y','z','z','z'},
				{' ','x','x',' ',' ','y',' ',' ',' '}
		};

		CharGrid cg1 = new CharGrid(grid1);

		assertEquals(0, cg1.countPlus());
	}




}
