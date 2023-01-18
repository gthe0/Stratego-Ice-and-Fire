package model.piece;

import java.util.ArrayList;

import model.board.*;
import model.element.Element;

/**
*Class used for abstract Piece Functions and other common variables
* @author gtheo
*/
public abstract class Piece
{
	private final Element element ;
	private final int power  ;
	
	/**
     *  <b>Constructor</b>
     * @invariant <b>-1 &ge; power &ge; 10</b> It is used both as an ID and attack Power if unit can attack<br> 
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param power <i>It stands for the power of the Piece</i>
     * @param element<i>It is used as a team's ID</i>
     */
	public Piece(int power ,Element element)
	{
		if(power<-1 || power>10)
			throw new IllegalArgumentException() ;	//Here we enforce invariant
		
		this.element = element ;
		this.power = power;
	}
	

	/**
	 *  <i>Getter of power</i>
     * 
     * @type Accesor
     * @return power
     */	
	public int getPower() {
		return power;
	}


	/**
	 *  <i>Getter of Element</i>
	 * 
	 * @type Accesor
	 * @return element
	 */	
	
	public Element getElement() {
		return element;
	}


	/**
	 * Function used to monitor whether a move is <b>Legal</b> or not.
	 * 
	 * @type Observer
	 * @param gameState, It is the Current Board Instance.
	 * @param move, It is the move that we try to make.
	 * @return true if the move is viable and false if not.
	 */	
	public abstract boolean isMoveLegal(Board gameState, Move move);
	

	/**
	 * Function used to monitor to <b>Store</b> all possible Moves.
	 * 
	 * @type Observer
	 * @param gameState, It is the Current Board Instance.
	 * @param position, It is the Position of the Piece on the board.
	 * @return An ArrayList with all Possible moves of current piece.
	 */	
	public abstract ArrayList<Move> PossibleMoves(Board gameState,Position position);	

}