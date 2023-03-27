import junit.framework.TestCase;

public class SudokuTest extends TestCase {
    public void testProvidedSudokuCases(){
        Sudoku s = new Sudoku(Sudoku.easyGrid);
        assertEquals(1, s.solve());
        assertTrue(s.getElapsed()>0);
        String str = "1 6 4 0 0 0 0 0 2 \n"+
                "2 0 0 4 0 3 9 1 0 \n"+
                "0 0 5 0 8 0 4 0 7 \n"+
                "0 9 0 0 0 6 5 0 0 \n"+
                "5 0 0 1 0 2 0 0 8 \n"+
                "0 0 8 9 0 0 0 3 0 \n"+
                "8 0 9 0 4 0 2 0 0 \n"+
                "0 7 3 5 0 9 0 0 1 \n"+
                "4 0 0 0 0 0 6 7 9 \n";

        assertEquals(str, s.toString());
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        s = new Sudoku(Sudoku.mediumGrid);
        assertEquals(1, s.solve());
        assertTrue(s.getElapsed()>0);
        str = "5 3 0 0 7 0 0 0 0 \n"+
                "6 0 0 1 9 5 0 0 0 \n"+
                "0 9 8 0 0 0 0 6 0 \n"+
                "8 0 0 0 6 0 0 0 3 \n"+
                "4 0 0 8 0 3 0 0 1 \n"+
                "7 0 0 0 2 0 0 0 6 \n"+
                "0 6 0 0 0 0 2 8 0 \n"+
                "0 0 0 4 1 9 0 0 5 \n"+
                "0 0 0 0 8 0 0 7 9 \n";

        assertEquals(str, s.toString());
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        s = new Sudoku(Sudoku.hardGrid);
        assertEquals(1, s.solve());
        assertTrue(s.getElapsed()>0);
        str = "3 7 0 0 0 0 0 8 0 \n"+
                "0 0 1 0 9 3 0 0 0 \n"+
                "0 4 0 7 8 0 0 0 3 \n"+
                "0 9 3 8 0 0 0 1 2 \n"+
                "0 0 0 0 4 0 0 0 0 \n"+
                "5 2 0 0 0 6 7 9 0 \n"+
                "6 0 0 0 2 1 0 4 0 \n"+
                "0 0 0 5 3 0 9 0 0 \n"+
                "0 3 0 0 0 0 0 5 1 \n";

        assertEquals(str, s.toString());
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));
    }

    public void testHarderSudokuCases(){
        Sudoku s = new Sudoku("3 0 0 0 0 0 0 8 0 \n"+
                "0 0 1 0 9 3 0 0 0 \n"+
                "0 4 0 7 8 0 0 0 3 \n"+
                "0 9 3 8 0 0 0 1 2 \n"+
                "0 0 0 0 4 0 0 0 0 \n"+
                "5 2 0 0 0 6 7 9 0 \n"+
                "6 0 0 0 2 1 0 4 0 \n"+
                "0 0 0 5 3 0 9 0 0 \n"+
                "0 3 0 0 0 0 0 5 1 \n");
        assertEquals(6, s.solve());
        assertTrue(s.getElapsed()>0);
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        s = new Sudoku("0 0 0 0 0 0 0 8 0",
                "0 0 1 0 9 3 0 0 0",
                "0 4 0 7 8 0 0 0 3",
                "0 9 3 8 0 0 0 1 2",
                "0 0 0 0 4 0 0 0 0",
                "5 2 0 0 0 6 7 9 0",
                "6 0 0 0 2 1 0 4 0",
                "0 0 0 5 3 0 9 0 0",
                "0 0 0 0 0 0 0 5 1");
        assertEquals(12, s.solve());
        assertTrue(s.getElapsed()>0);
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        int[][] ints = {
                {0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 1, 0, 9, 3, 0, 0, 0},
                {0, 4, 0, 7, 8, 0, 0, 0, 3},
                {0, 9, 3, 8, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 4, 0, 0, 0, 0},
                {5, 2, 0, 0, 0, 0, 7, 9, 0},
                {6, 0, 0, 0, 2, 1, 0, 4, 0},
                {0, 0, 0, 5, 3, 0, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        s = new Sudoku(ints);
        assertEquals(29, s.solve());
        assertTrue(s.getElapsed()>0);
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        ints = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 4, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0,0 , 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        s = new Sudoku(ints);
        assertEquals(100, s.solve());
        assertTrue(s.getElapsed()>0);
        assertTrue(Sudoku.sanityCheck(Sudoku.textToGrid(s.getSolutionText())));

        ints = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 4, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0,0 , 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        s = new Sudoku(ints);
        assertEquals(0, s.solve());

        assertTrue(s.getElapsed()==0);
        assertEquals(s.getSolutionText(),"Your Puzzle Might Be Incorrect");

    }




}
