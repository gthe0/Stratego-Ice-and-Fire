package model.board;

import model.element.Element;
import model.piece.Piece;

/**
 * An interface that can be used to generate boards for many games
 * 
 * @author gtheo
 */
public interface Board_Interface 
{
	static final int boardWidth  = 10;
	static final int boardHeight = 8 ;
	
	static final Position[] IllegalPositions = {new Position(2,3),
												new Position(3,3),
												new Position(6,3),
												new Position(7,3),
 												new Position(2,4),
 												new Position(3,4),
 												new Position(6,4),
 												new Position(7,4)};
		
	 /**
	 * Return's true if move is legal
	 * 
	 * @type Observer
	 * @return whether the move is Legal or not
	 */	
	 public boolean isMoveLegal(Move move);
	
	 

	 /**
	 * Return's true if the Player whose turn it is, has moves left
	 * 
	 * @type Observer
	 * @return whether the Player has Moves
	 */		 
	 public boolean PlayerHasMoves(); 
	 
	 
	 /**
	 *  Getter of Last Move played
	 * 
	 * @type Accesor
	 * @return the Last move played
	 */	
	 public Move getLastMove();
	 
	 
	/**
	 *  Getter of player's Turn
	 * 
	 * @type Accesor
	 * @return the Element of the player who's turn is now
	 */	
	 public Element getPlayersTurn();
     
	 /**
	 *  Setter of player's Turn
	 *  		  
	 * @type Mutator
	 * @param playersTurn ,the Element of the player whose turn it is
     */	
	 public void setPlayersTurn(Element playersTurn);
     
	/**
	* Setter of Last Move played
	* 
	* @type Mutator
	* @param lastMove,the Last move played
	*/	
	 public void setLastMove(Move lastMove);
	
	 /**
	 *  Getter of Piece
	 *  		  
	 * @type Accessor
	 * @param position,the position at which the Piece is
	 * @return the Piece located at position
     */		 
	 public Piece getPiece(Position position);
 	
	 /**
	 *  Setter of Piece
	 *  		  
	 * @type Mutator
	 * @param position,the position at which we want to put the Piece
	 * @param piece ,the piece we want to put at specified location
     */	
	 public void setPiece(Position position, Piece piece);
	
	 
    /**
	*  Getter of Board's Width
	* 
	* @type Accesor
	* @return boardWidth
	*/
	 public int getBoardWidth();
	 
	/**
	*  Getter of Board's Height
	* 
	* @type Accesor
	* @return boardHeight
	*/
	 public int getBoardHeight();
	 
	 
    /**
	*  Makes a move on the board
	* 
	* @type Mutator
	* @return a number representing an outcome:<br>
	* 			-1 if we attack a flag
	* 			 0 if nothing wrong happens
	* 			 1 if we try to move an Immovable object 
	*/
	 public int makeMove(Move move);
	 
	 
	 
	 
	    /**
		* It end's the turn, and gives it to the other player
		* 
		* @type Observer
		*/
	 	public void endPlayerTurn();
	 
	 	/**
		* It checks if path between start and destination is obscured by other objects
		* 
		* @type Observer
		*/
		public boolean isPathObstructed(Move move); 

	 
}
