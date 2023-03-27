import junit.framework.TestCase;




public class BoardTest extends TestCase {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;
	Piece l11, l12,l13,l14;
	Piece l21, l22,l23,l24;
	Piece stick1, stick2;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	
	protected void setUp() throws Exception {
		b = new Board(3, 6);
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		l11 = new Piece(Piece.L1_STR);
		l12 = l11.computeNextRotation();
		l13= l12.computeNextRotation();
		l14 = l13.computeNextRotation();

		l21 = new Piece(Piece.L2_STR);
		l22 = l21.computeNextRotation();
		l23= l22.computeNextRotation();
		l24 = l23.computeNextRotation();


		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();

		b.place(pyr1, 0, 0);
		b.commit();
		assertEquals(2,b.getMaxHeight());
	}
	
	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	// Makre  more tests, by putting together longer series of 
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.

	public void testPlace(){
		assertEquals(Board.PLACE_BAD, b.place(pyr2,0,0));
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(pyr2,2,1) );
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(sRotated,-1,1) );
		assertTrue(!b.getGrid(0,1));
		assertTrue(!b.getGrid(0,2));
		assertTrue(!b.getGrid(0,3));
		assertTrue(!b.getGrid(1,2));
		assertEquals(Board.PLACE_OK, b.place(pyr4,0,1));
		b.commit();

		assertTrue(!b.getGrid(2,1));
		assertTrue(!b.getGrid(2,2));
		assertTrue(!b.getGrid(2,3));
		assertTrue(!b.getGrid(2,4));


		assertEquals(Board.PLACE_ROW_FILLED, b.place(stick1, 2, 1));
		b.commit();
		assertEquals(3, b.clearRows());

		assertTrue(b.getGrid(2,1));
		assertTrue(b.getGrid(2,0));
		assertEquals(2, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
		assertEquals(2, b.getMaxHeight());
		assertEquals(2, b.getColumnHeight(2));
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(0, b.getColumnHeight(1));
	}



	public void testNarrowBoard(){
		Board narrow = new Board(2,8);

		assertEquals(2, narrow.getWidth());
		assertEquals(8, narrow.getHeight());

		assertEquals(Board.PLACE_OUT_BOUNDS, narrow.place(pyr3,0,0));
		assertEquals(Board.PLACE_OUT_BOUNDS, narrow.place(stick2,0,6));

		assertEquals(Board.PLACE_OK, narrow.place(stick1, 0,0));
		assertEquals(4,narrow.dropHeight(stick1, 0) );
		assertEquals(0,narrow.dropHeight(stick1, 1) );
		narrow.commit();
		assertEquals(Board.PLACE_ROW_FILLED, narrow.place(stick1, 1,0));
		narrow.commit();
		Piece square = new Piece(Piece.SQUARE_STR);

		assertEquals(Board.PLACE_OUT_BOUNDS, narrow.place(square, 1,3));
		assertEquals(Board.PLACE_BAD, narrow.place(square, 0,2));
		assertEquals(Board.PLACE_ROW_FILLED, narrow.place(square, 0,4));
		narrow.commit();
		assertEquals(Board.PLACE_ROW_FILLED, narrow.place(square, 0,6));
		narrow.clearRows();

		assertEquals(
				"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"|  |\n" +
						"----",
				narrow.toString());
		narrow.undo();
		assertEquals(6, narrow.getColumnHeight(0));
		assertEquals(6, narrow.getColumnHeight(1));
		narrow.undo();
		assertEquals(6, narrow.getColumnHeight(0));
		assertEquals(6, narrow.getColumnHeight(1));

	}




}



