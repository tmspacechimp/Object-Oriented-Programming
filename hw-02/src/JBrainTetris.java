import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JBrainTetris extends JTetris{
    private Brain brain;
    private Brain.Move brainMove;
    private JCheckBox brainMode;
    private JPanel little;
    private JSlider adversary;
    private JLabel status;
    private Random random;



    public JBrainTetris(int pixels){
        super(pixels);
        brain = new DefaultBrain();
        brainMove = new Brain.Move();
        random = new Random();


    }


    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JBrainTetris tetris = new JBrainTetris(16);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setVisible(true);
    }

    @Override
    public JComponent createControlPanel() {
        JComponent panel = super.createControlPanel();
        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);

        //adversary

        little = new JPanel();
        little.add(new JLabel("Adversary:"));
        adversary = new JSlider(0, 100, 0); // min, max, current
        adversary.setPreferredSize(new Dimension(100,15));
        little.add(adversary);
        status = new JLabel("ok");

        panel.add(little);

        return panel;
    }


    @Override
    public void tick(int verb){
        if(brainMode.isSelected()){
            if(verb==DOWN){
                if (!currentPiece.equals(brainMove.piece))
                    super.tick(ROTATE);
                if(currentX == brainMove.x && currentY > brainMove.y){
                    super.tick(DROP);
                    board.commit();
                }

                if(currentX > brainMove.x)
                    super.tick(LEFT);
                if(currentX< brainMove.x)
                    super.tick(RIGHT);
            }
        super.tick(verb);

        }
    }

    @Override
    public Piece pickNextPiece() {
        int rand = random.nextInt(100);
        if (rand >= adversary.getValue()){
            status.setText("ok");
            return super.pickNextPiece();
        }
        status.setText("*ok*");
        Piece[] arr = Piece.getPieces();
        int worstIndex=0;
        double worseScore= -1;
        for(int i=0; i< arr.length; i++){
            double curScore = brain.bestMove(board,arr[i],
                    board.getHeight(),null).score;
            if(curScore>worseScore){
                worseScore= curScore;
                worstIndex=i;
            }
        }
        return brain.bestMove(board,arr[worstIndex],
                board.getHeight(),null).piece;
    }



    @Override
    public void addNewPiece() {
        super.addNewPiece();
        board.undo();
        brainMove= brain.bestMove(board, currentPiece,
                board.getHeight(),brainMove);
        if(brainMove==null)
            gameOn = false;
    }
}
