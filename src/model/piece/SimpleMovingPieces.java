package model.piece;

import model.board.Board;
import model.board.Move;
import model.element.Element;

/**
 * @author gtheo
 */
public abstract class SimpleMovingPieces extends MovablePiece {

	public SimpleMovingPieces(int power, Element element) {
		super(power, element);
	}

	/**
	 * For the simple moving Pieces, it also checks if the Distance is equal to 1.
	 * @type Observer
	 */	
	@Override
	public boolean isMoveLegal(Board gameState, Move move) 
	{	
		return super.isMoveLegal(gameState, move) 
			&& move.Distance() == 1 ;
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
