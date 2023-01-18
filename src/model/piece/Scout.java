package model.piece;

import model.board.Board;
import model.board.Move;
import model.element.Element;

/**
 * @author gtheo
 */
public class Scout extends MovablePiece {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Scout instance with power equal to 2
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Scout(Element element) {
		super(2, element);
	}

	
	/**
	 * It also checks if path is obscured
	 * @type Observer
	 */	
	@Override
	public boolean isMoveLegal(Board gameState, Move move) 
	{
		return super.isMoveLegal(gameState, move)
			&& !gameState.isPathObstructed(move);
	}

	/**
	 * @type Observer
	 */		
	@Override
	public Piece Attack(Piece opponent) 
	{
		if(opponent.getPower() == 0) 
			return null ;
	
		if(opponent.getPower()== -1)
			return new Flag(opponent.getElement().opponent());
		
		if(opponent.getPower() == this.getPower()) 
			return null ;
		else
			return this.getPower() > opponent.getPower() ? this : opponent;
	}
	
}
