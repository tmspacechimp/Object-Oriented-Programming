import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


 public class SudokuFrame extends JFrame {

	 private JTextArea puzzle, solution;
	 private  JButton check;
	 private  JCheckBox auto;
	 private  Box box;


	public SudokuFrame() {
		super("Sudoku Solver");
		init();
		addListeners();


	}



	 private void init() {

		 setLocationByPlatform(true);

		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 setVisible(true);
		 setLayout(new BorderLayout(4,4));

		 puzzle = new JTextArea(15,20);
		 solution= new JTextArea(15,20);
		 puzzle.setBorder(new TitledBorder("Puzzle"));

		 solution.setBorder(new TitledBorder("Solution"));
		 solution.setEditable(false);

		 add(puzzle, BorderLayout.CENTER);
		 add(solution, BorderLayout.EAST);

		 check = new JButton("Check");
		 auto = new JCheckBox("Auto Check");

		 box = Box.createHorizontalBox();
		 box.add(check);
		 box.add(auto);

		 add(box, BorderLayout.SOUTH);
		 pack();
	 }

	 private void addListeners() {

		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});



		puzzle.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(auto.isSelected())	solve();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(auto.isSelected())	solve();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(auto.isSelected())	solve();
			}
		});
	 }

	 private void solve() {
		try{
			Sudoku sudoku = new Sudoku(puzzle.getText());
			int solutions = sudoku.solve();
			if(solutions == 100){
				String text = new String(sudoku.getSolutionText() + "\n" + "Solutions: " + solutions + " or more"+ "\n" + "Elapsed: " + sudoku.getElapsed());
			}
			String text = new String(sudoku.getSolutionText() + "\n" + "Solutions: " + solutions + "\n" + "Elapsed: " + sudoku.getElapsed());

			solution.setText(text);

		} catch(Exception ex){
			solution.setText("Parsing problem");
		}
	 }


	 public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		SudokuFrame frame = new SudokuFrame();
	}

}
