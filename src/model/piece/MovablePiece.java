package model.piece;

import java.util.ArrayList;

import model.board.Board;
import model.board.Move;
import model.board.Position;
import model.element.Element;

/**
 * @author gtheo
 */
public abstract class MovablePiece extends Piece {


	private boolean HasRevived ;
	
	/**
	 * It calls super constructor and it also initializes HasRevived.
	 * @type Creator
	 */	
	public MovablePiece(int power, Element element) {
		super(power, element);
		HasRevived = false ;
	}
	
	/**
	 * It makes a general check if the desired square has any friendly pieces on it.<br>
	 * If not it returns <b>true</b>
	 * @type Observer
	 */	
	@Override
	public boolean isMoveLegal(Board gameState, Move move) 
	{
		boolean Valid_Mode = true;

		if(gameState.getMode() == 1 ||gameState.getMode() == 3 )
		{
			Valid_Mode = gameState.getPlayersTurn() == Element.FIRE ? move.getY2() - move.getY1() <=0 : move.getY2() - move.getY1() >= 0 ;
		}

		return  (   gameState.getPiece(move.getDestination())== null
				|| gameState.getPiece(move.getDestination()).getElement()== getElement().opponent())
				&& Valid_Mode;
	}

	
	/**
	 * It checks for every square if the Piece can move there.<br>
	 * If it can it stores it in the ArrayList.
	 * @type Observer
	 */	
	@Override
	public ArrayList<Move> PossibleMoves(Board gameState,Position position)
	{
		ArrayList<Move> newPositions = new ArrayList<Move>();
		
		for(int y = 0; y <gameState.getBoardHeight(); y++) {
            for (int x = 0; x < gameState.getBoardWidth(); x++)
            {
            	if(gameState.isMoveLegal(new Move(position, new Position(x,y))))
            		newPositions.add(new Move(position, new Position(x,y)));
            }
       }
		
			return newPositions ;
	}


	/**
	 * Checks if the piece has revived.
	 * @type Accessor
	 * @return HasRevived
	 */	
	public boolean DidItRevive() {
		return HasRevived;
	}

	/**
	 * If it has revived, it sets it to true.
	 * @type Mutator
	 */	
	public void Revive(boolean rev) {
		HasRevived = rev;
	}
	
	

	/**
	 * It checks which Piece would win a battle and it returns the Winner.
	 * @type Observer
	 * @param opponent The piece which is attacked
	 * @return A piece, the winner of the fight.
	 */	
	public abstract Piece Attack(Piece opponent);
	

}
