// JBrainTetris

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 Subclass of JTetris that adds in brain features.
 Works with a few key overrides.
 JTetris.main() can create either a JTetris or a JBrainTetris.
*/
public class JBrainTetris extends JTetris {

    protected JSlider   adversarySlider;
    protected JLabel    adversaryStatus;
	protected JCheckBox brainCheckbox;
	protected JCheckBox friendlyCheckbox;
	protected JSlider   friendlySlider;
	Brain hal2000;
	protected static boolean AUTO_DROP = false; //this is not implemented yet, but should be toggled to allow the piece to be dropped
	protected int countBeforeLastTick;		 // how many pieces played so far
	protected Brain.Move brainMove;
    public JBrainTetris(int width, int height) {
        super(width, height);
        hal2000 = new LameBrain();
        countBeforeLastTick = count;
    }

	public Container createControlPanel() {
		
		//add brain label
		Container panel = super.createControlPanel();
		panel.add(new JLabel("Brain:"));
		brainCheckbox = new JCheckBox("Brain active");
	    panel.add(brainCheckbox);
	    
	    //add adversary panel for controlling the AI
	    JPanel little = new JPanel();
	    little.add(new JLabel("Adversary:"));
	    adversarySlider = new JSlider(0, 100, 0); // min, max, current
	    adversarySlider.setPreferredSize(new Dimension(100,15));
	    little.add(adversarySlider);
	    panel.add(little);


	    //add friend panel for controlling the friendly AI
	    little = new JPanel();
	    little.add(new JLabel("Friend:"));
	    friendlySlider = new JSlider(0, 100, 0); // min, max, current
	    friendlySlider.setPreferredSize(new Dimension(100,15));
	    little.add(friendlySlider);
	    panel.add(little);
	    
	    //status text
	    adversaryStatus = new JLabel("ok");
	    panel.add(adversaryStatus);
	    
	    return panel;
	}
	

	/**
	 Selects the next piece to use using the random generator
	 set in startGame().
	*/
	public Piece pickNextPiece() {
		
		double decider = random.nextDouble() * 100;
		double adslide = adversarySlider.getValue(),
			   friendslide = friendlySlider.getValue();
		
		
		// if the adversary and friendsliders sum to < 100, this might evaluate to true
		boolean give_natural_piece = decider >= adslide + friendslide;
		if (give_natural_piece)
		{
			adversaryStatus.setText("ok");
			return super.pickNextPiece();
		}
		//otherwise do a weighted random decision between a friendly or adversarial choice
		else if (random.nextDouble() * friendslide > random.nextDouble() * adslide)
		{
			adversaryStatus.setText(":)");
			return computeBestPossiblePiece();
		}
		else
		{
			adversaryStatus.setText("*ok*");
			return computeWorstPossiblePiece();
		}
	}
	
	/**
	 * @return the worst possible piece to play next, given the incredible ai of the brain
	 */
	protected Piece computeWorstPossiblePiece()
	{
		double maxScore = 0;
		Piece bestPiece = null; 
		Piece[] pieces = Piece.getPieces();
		Brain.Move move = null;
		for (Piece piece : pieces)
		{
			board.undo();
			move = hal2000.bestMove(board, piece, HEIGHT, move);
			if (move != null && move.score > maxScore)
			{
				maxScore = move.score;
				bestPiece = piece;
			}
		}
		
		if (bestPiece != null)
			return bestPiece;
		else if (pieces.length != 0)
			return pieces[0];
		else
			return null;
		
	}
	
	/**
	 * @return the worst possible piece to play next, given the incredible ai of the brain
	 */
	protected Piece computeBestPossiblePiece()
	{
		double minScore = 0;
		Piece bestPiece = null; 
		Piece[] pieces = Piece.getPieces();
		Brain.Move move = null;
		for (Piece piece : pieces)
		{
			board.undo();
			move = hal2000.bestMove(board, piece, HEIGHT, move);
			if (move != null && move.score < minScore)
			{
				minScore = move.score;
				bestPiece = piece;
			}
		}
		if (bestPiece != null)
			return bestPiece;
		else if (pieces.length != 0)
			return pieces[0];
		else
			return null;
		
	}


	/**
	 Called to change the position of the current piece.
	 Each key press call this once with the verbs
	 LEFT RIGHT ROTATE DROP for the user moves,
	 and the timer calls it with the verb DOWN to move
	 the piece down one square.

	 Before this is called, the piece is at some location in the board.
	 This advances the piece to be at its next location.
	 
	 Overriden by the brain when it plays.
	*/
	public void tick(int verb) {
		//for keeping track of when a piece is added
		boolean count_changed = countBeforeLastTick != count;
		countBeforeLastTick = count;
		
		//the brain should only be active when moving a piece downwards
		boolean use_brain = verb == DOWN && this.brainCheckbox.isSelected();
		//hal2000.bestMove(this.board, this.currentPiece,)
		if (use_brain)
		{
			//make sure we have the latest move from the brain
			computeBrainMoveIfNecessary(count_changed);
			int brainVerb = -1;
			if (brainMove != null)
			{
				//this section reads through like english
				boolean rotate_piece = brainMove.piece != currentPiece;
				if (rotate_piece)
					brainVerb = ROTATE;
				else
					if (brainMove.x < currentX)
						brainVerb = LEFT;
					else if (brainMove.x > currentX)
						brainVerb = RIGHT;
					else
						brainVerb = AUTO_DROP ? DROP : DOWN;
			}
			if (brainVerb != -1)
				super.tick(brainVerb);
			if (brainVerb != DOWN)
				super.tick(DOWN);
		}
		else // (not usebrain)
		{
			brainMove = null;
			super.tick(verb);
		}
	}
	
	/**
	 * 
	 * @param count_changed if a piece has been added or not
	 */
	protected void computeBrainMoveIfNecessary(boolean count_changed)
	{
		if (count_changed)
		{
			board.undo();
			brainMove = hal2000.bestMove(board, currentPiece, HEIGHT, null);
		}
	}
}