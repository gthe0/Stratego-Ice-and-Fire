package control;

import java.awt.*;
import java.util.ArrayList;
import model.board.*;

import model.element.Element;
import model.piece.Piece;
import model.player.Stratego_Player;
import view.*;

import javax.swing.*;


/**
 * Controller is the master of the game and controls all 
 * the operations executed
 * @author gtheo
 */
public class Controller {

	GameFrame viewer ;
	Stratego Model ;


	public Controller()
	{
		Model = new Stratego(0,this);
		viewer = new GameFrame(this);
	}


	/**
	 *  <i>Undo button clicked</i>
	 * 
	 * @preconditions none
	 * @postconditions Undoes the last move and refreshes the GUI
	 * @type Mutator
	 */	
	public void UndoClicked() {
		Model.UndoMoves();
    }
	

	
	/**
	 *  <i>Redo button clicked</i>
	 * 
	 * @preconditions none
	 * @postconditions Redoes the last move undone and refreshes the GUI
	 * @type Mutator
	 */
	public void RedoClicked() {
		Model.RedoMoves();
	}

	/**
	 *  <i>New Game Clicked</i>
	 * 
	 * @preconditions none
	 * @postconditions Destroys Game, chooses a mode and Creates a New Board
	 * @type Mutator
	 */	
	public void NewGameClicked(int mode){
		getModel().NewGame(mode);
    }



	/**
	 *  <i>Calls ReviveDialog</i>
	 * 
	 * @preconditions The Enemy Player has Captured Pieces.<br> Precondition is met elsewhere.
	 * @postconditions Chooses a Piece to Revive
	 * @type Mutator
	 */		
	public void ReviveChooseBox(Element element)
	{
		ReviveDialog rev = new ReviveDialog(element == Element.FIRE ? getModel().getPlayer2() : getModel().getPlayer1() ,viewer,this);
	}

	/**
	 *  <i>Getter of Current Board Displayed</i>
	 *
	 * @type Accesor
	 * @return Model.getCurrentBoard()
	 */
	public Board getCurrentBoard() {
		return Model.getCurrentBoard();
	}


	/**
	 *  <i>Getter of Model</i>
	 *
	 * @type Accesor
	 * @return Model
	 */
	public Stratego getModel() {
		return Model;
	}

	/**
	 *  Observes if the Square has a Piece and if it is Valid.
	 *
	 * @type Observer
	 * @return true if the move is valid, false otherwise.
	 */
	public boolean isPieceValid(int newX, int newY) {
		if ((newX != 2 && newX != 3 && newX != 6 && newX != 7) || (newY != 3 && newY != 4)) {

			if (getCurrentBoard().getSquares()[newY][newX] != null && getCurrentBoard().getSquares()[newY][newX].getElement() == getCurrentBoard().getPlayersTurn()) {

				return getCurrentBoard().getSquares()[newY][newX].getPower() != -1 && getCurrentBoard().getSquares()[newY][newX].getPower() != 0;

			}
		}
		return  false ;
	}


	/**
	 *  Observes if the Move we try to Execute is Valid.
	 *
	 * @type Observer
	 * @param move , The move that the player wants to execute.
	 * @return true if the move is valid, false otherwise.
	 */
	public boolean isMoveValid(Move move)
	{
		return  getCurrentBoard().isMoveLegal(move);
	}

	public void MakeMove(Move move)
	{
		getModel().makeMove(move);
		if(!getCurrentBoard().PlayerHasMoves()) EndGame(getCurrentBoard().getPlayersTurn().opponent());
		getCurrentBoard().endPlayerTurn();
		if(!getCurrentBoard().PlayerHasMoves()) EndGame(getCurrentBoard().getPlayersTurn().opponent());
	}

	/**
	 * It calls the Possible Moves method of the Piece in the Square.<br>
	 * If it has Possible Moves, it stores their Destinations in an array of Positions<br>
	 * and return them. Used to display possible Moves.
	 *
	 * @type Observer
	 * @return An array List of all Possible new Positions of the Piece.
	 */
	public ArrayList<Position> PossibleMoves(Position m1)
	{
		ArrayList<Position> newPos = new ArrayList<>();

		ArrayList<Move> temp= getCurrentBoard().getPiece(m1).PossibleMoves(getCurrentBoard(),m1);

		for (Move a:temp) {
			newPos.add(a.getDestination());
		}

		return newPos ;
	}

	/**
	 *  <i>Revives a Unit</i>
	 *
	 * @preconditions none
	 * @postconditions Revives a Piece and refreshes a GUI
	 * @type Mutator
	 */
	public void RevivedUnitClicked(int button, Element element)
	{
		Stratego_Player player = element == Element.FIRE ? getModel().getPlayer1() : getModel().getPlayer2();
		getModel().Revive(player.getCaptured().get(button));
		player.getCaptured().remove(button);
		viewer.repaint();
	}


	/**
	 * At the end of each turn , the display of Information Menu on the right(GMenu)<br>
	 * is changed, ant thus we need to update the Display for each turn.
	 *
	 * @type Mutator
	 * @param element , the Element of the Player whose turn it is.
	 * @param Grid , the JLabels we want to set up for Display
	 * @return Grid
	 */
	public JLabel[] ChangeJLabel(JLabel []Grid,Element element)
	{
		int[] PiecePowerHistogram = new int[11];
		Grid = new JLabel[20];

		if(element == Element.FIRE)
		{
			for (Piece P : getModel().getPlayer1().getCaptured()) {
				PiecePowerHistogram[P.getPower()]++;
			}


			Grid[0] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/slayerB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[2] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/scoutB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[4] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/dwarfB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[6] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/elfB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[8] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/Yeti.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[10] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/sorceressB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[12] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/BeastRiderB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[14] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/knightB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[16] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/mageB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[18] = new JLabel(new ImageIcon(new ImageIcon("images/Ice/dragonB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		}
		else
		{
			for (Piece P : getModel().getPlayer2().getCaptured()) {
				PiecePowerHistogram[P.getPower()]++;
			}

			Grid[0]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/slayerR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[2]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/scoutB.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[4]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/dwarfR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[6]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/elfR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[8]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/lavaBeast.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[10]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/sorceressR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[12]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/BeastRiderR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[14]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/knightR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[16]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/mageR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			Grid[18]= new JLabel(new ImageIcon(new ImageIcon("images/Fire/dragonR.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		}
			Grid[1] = new JLabel(PiecePowerHistogram[1] + "");
			Grid[1].setForeground(Color.WHITE);
			Grid[3] = new JLabel(PiecePowerHistogram[2] + "");
			Grid[3].setForeground(Color.WHITE);
			Grid[5] = new JLabel(PiecePowerHistogram[3] + "");
			Grid[5].setForeground(Color.WHITE);
			Grid[7] = new JLabel(PiecePowerHistogram[4] + "");
			Grid[7].setForeground(Color.WHITE);
			Grid[9] = new JLabel(PiecePowerHistogram[5] + "");
			Grid[9].setForeground(Color.WHITE);
			Grid[11] = new JLabel(PiecePowerHistogram[6] + "");
			Grid[11].setForeground(Color.WHITE);
			Grid[13] = new JLabel(PiecePowerHistogram[7] + "");
			Grid[13].setForeground(Color.WHITE);
			Grid[15] = new JLabel(PiecePowerHistogram[8] + "");
			Grid[15].setForeground(Color.WHITE);
			Grid[17] = new JLabel(PiecePowerHistogram[9] + "");
			Grid[17].setForeground(Color.WHITE);
			Grid[19] = new JLabel(PiecePowerHistogram[10] + "");
			Grid[19].setForeground(Color.WHITE);

		return Grid;
	}

	/**
	 *  Observes if the Move we try to Execute has an enemy piece on destination.<br>
	 *	Used to Fire a delay and to reveal the piece.
	 *
	 * @type Observer
	 * @param position , The Destination of the move that the player wants to execute.
	 * @return the power of the Piece in the position or -2 if the square is Empty
	 */
	public int CheckForEnemy(Position position) {

		Piece p = getCurrentBoard().getPiece(position);

		return 	p != null ? p.getPower() : -2;
	}


	/**
	 *	It changes the main frame of the game to Display a Winner's screen<br>
	 *	with 2 options, to either Quit and terminate the program or make a new game.
	 *
	 * @type Mutator
	 * @param playersTurn , the Element of the Player who has turn and Won.
	 */
	public void EndGame(Element playersTurn) {
		viewer.ShowWinner(playersTurn);
	}



	public static void main (String[] args)
	{
		 Controller controller = new Controller();
		 controller.viewer.setVisible(true);
	}


}
