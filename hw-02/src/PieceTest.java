import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece s, sRotated;
	private Piece l11, l12,l13,l14;
	private Piece l21, l22,l23,l24;


	protected void setUp() throws Exception {
		super.setUp();
		
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


	}

	// Here are some sample tests to get you started


	public void testEqualsAndNextRotation(){



		Piece pyrTmp = new Piece(pyr1.getBody());
		assertTrue(pyrTmp.equals(pyr1) );

		pyrTmp = pyr4.computeNextRotation();
		assertTrue(pyrTmp.equals(pyr1));

		// iregularly defined piece
		// should return true anyway
		pyrTmp = new Piece("2 0  0 0  1 1  1 0");
		assertTrue(pyrTmp.equals(pyr1));

		// just to show that equals doesn't return true everytime

		assertFalse(s.equals(sRotated));

		Piece square = new Piece(Piece.SQUARE_STR);
		Piece square1 = square.computeNextRotation();
		assertTrue(square1.equals(square));


	}


	public void testFastRoatations(){
		// stick piece: one rotation should yield false, two should yield true
		Piece[] arr = Piece.getPieces();
		assertFalse(arr[Piece.STICK].equals(arr[Piece.STICK].fastRotation()));
		assertTrue(arr[Piece.STICK].equals(arr[Piece.STICK].fastRotation().fastRotation()));

		// all four rotations of l2
		assertTrue(arr[Piece.L2].equals(l21));
		assertTrue(arr[Piece.L2].fastRotation().equals(l22));
		assertTrue(arr[Piece.L2].fastRotation().fastRotation().equals(l23));
		assertTrue(arr[Piece.L2].fastRotation().fastRotation().fastRotation().equals(l24));
		assertTrue(arr[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation().equals(arr[Piece.L2]));
		assertTrue(arr[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation().fastRotation().equals(arr[Piece.L2].fastRotation()));

		assertTrue(arr[Piece.SQUARE].equals(arr[Piece.SQUARE].fastRotation()));
		assertTrue(arr[Piece.SQUARE].equals(arr[Piece.SQUARE].fastRotation().fastRotation()));

	}






	public void testJustSampleSize() {

		// tests some of the starting figures without any rotations
		// gives you a chance to test sizes before coding out rotation functions

		Piece pc = new Piece(Piece.PYRAMID_STR);

		assertEquals(3, pc.getWidth());
		assertEquals(2, pc.getHeight());

		pc = new Piece(Piece.S1_STR);

		assertEquals(3, pc.getWidth());
		assertEquals(2, pc.getHeight());

		pc = new Piece(Piece.L1_STR);
		assertEquals(2, pc.getWidth());
		assertEquals(3, pc.getHeight());

		pc = new Piece(Piece.STICK_STR);
		assertEquals(1, pc.getWidth());
		assertEquals(4, pc.getHeight());


	}



	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
	}



	
	// Test the skirt returned by a few pieces



	public void testJustSampleSkirt() {
		// tests some of the starting figures without any rotations
		// gives you a chance to test skirts before coding out rotation functions
		Piece pc = new Piece(Piece.PYRAMID_STR);
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pc.getSkirt()));

		pc = new Piece(Piece.STICK_STR);
		assertTrue(Arrays.equals(new int[] {0}, pc.getSkirt()));

		pc = new Piece(Piece.S1_STR);
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, pc.getSkirt()));

		pc = new Piece(Piece.S2_STR);
		assertTrue(Arrays.equals(new int[] {1, 0, 0}, pc.getSkirt()));

	}


	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.

		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
	}





}
