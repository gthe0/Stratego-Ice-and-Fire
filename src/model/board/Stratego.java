package model.board;

import control.Controller;
import model.element.Element;
import model.piece.MovablePiece;
import model.piece.Piece;
import model.player.*;


/**
*<i>Wrapper Class used to coordinate the whole model</i>
* @author gtheo
*/
public class Stratego {


	private Board currentBoard;

	private Controller controller ;

	/**
     * <b>Constructor</b><br>
     * 	It creates a new Stratego Data Controller
     * @preconditions none
     * @postconditions Instantaniates a new board, the Controller reference
     * @type Creator
     * @param mode<i>It is used to know how many Pieces to add on board</i>
     */
	public Stratego(int mode, Controller controller)
	{
		currentBoard = new Board(mode);
		this.controller = controller;
	}
	
	
	/**
	* Makes a move on the Board if it's legal
	* If the move is possible it checks if there
	* is a Piece on the destination. If there is nothing
	* it makes the move, if there is, it attacks the Piece.
	* If it's the flag then we are the winner
	* 
	* @type Observer
	*/	
	 public void makeMove(Move move) 
	 {

	        	int x = 0 ;
	        	
	            Board copy = new Board(currentBoard);
	            x = copy.makeMove(move);  

	            if(x==-1)
				{
					controller.EndGame(getCurrentBoard().getPlayersTurn());
				}

				if(x==0 && (move.getY2() == 0 || move.getY2() == 7))
				{
					Piece piece = copy.getPiece(move.getDestination());
					MovablePiece piece1 = (MovablePiece) piece;

					if(piece != null && !piece1.DidItRevive())
					{
						if( piece.getElement() == Element.FIRE)
						{
							if(move.getY2() == 0)
							{
							controller.ReviveChooseBox(piece.getElement());
							piece1.Revive(true);
							copy.setPieceThatRevived(piece1);
							}
						}
						else
						{
							if(move.getY2() == 7)
							{
								controller.ReviveChooseBox(piece.getElement());
								piece1.Revive(true);
								copy.setPieceThatRevived(piece1);
							}
						}
					}
				}

				copy.setPrev(currentBoard);
				currentBoard.setNext(copy);
	            currentBoard = copy ;

	 }
	 
	 
	 
		/**
		* Undoes the last move made
		* @preconditions The current board Instance must not be the First in the list
		* @postconditions It changes the instance to the previous on the list
		* @type Mutator
		*/	
	 public void UndoMoves() 
	 {

		if(currentBoard.getPrev() != null)
		{
			if(currentBoard.getPieceThatRevived() != null)
			{
				MovablePiece temp= (MovablePiece) currentBoard.getPieceThatRevived();
				temp.Revive(false);
				currentBoard.setPieceThatRevived(null);
			}
			currentBoard = currentBoard.getPrev();
		}

	 }

		/**
		* Redoes the last move undone, if we did not make any other
		* 
		* @preconditions The current board Instance must not be the Last in the list
		* @postconditions It changes the instance to the next on the list
		* @type Mutator
		*/	
	 public void RedoMoves() 
	 {
		 if(currentBoard.getNext() != null)
		 {
			 currentBoard = currentBoard.getNext();
		 }

	 }

		/**
		* Makes a new Game
		* 
		* @preconditions none
		* @postconditions Makes a new game, and deletes everything from the current one
		* @type Mutator
		* @param mode, the mode that the player chose
		*/	
	 public void NewGame(int mode)
	 {
		 currentBoard = new Board(mode);
	 }
	  	 

		/**
		* Revives a piece
		* 
		* @preconditions Must not be alive and must be Movable
		* @postconditions Reputs the piece on the board
		* @type Mutator
		* @param piece to be revived
		*/	
	 public void Revive(Piece piece) 
	 {
		 if(piece.getElement()== getPlayer2().getElement())
		{
			if(getPlayer2().getRevives() <= 0)
				return ;

			getPlayer2().ReduceRevives();

			boolean tobreak = false ;

			 for(int x = 0; x <currentBoard.getBoardWidth(); x++) {
				 for ( int y = 0; y < 3; y++)
				 {
					 if(currentBoard.getPiece(new Position(x,y))==null) 
					 {
						 currentBoard.setPiece(new Position(x,y),piece);
						 tobreak = true;
						 break ;
					 }
				 }
				 if(tobreak)
					 break;
			 }
		 }
		 else 
		 {
			 if(getPlayer1().getRevives() <= 0)
				return ;

			 getPlayer1().ReduceRevives();

			 boolean tobreak = false ;

			for(int x = 0; x <currentBoard.getBoardWidth(); x++) {
		 	 		for (int y = 5; y < currentBoard.getBoardHeight(); y++)
		 	 		{
		 	 			if(currentBoard.getPiece(new Position(x,y))==null)
		 	 			{
		 	 				currentBoard.setPiece(new Position(x,y),piece);
							tobreak = true ;
							break ;
		 	 			}
		 	 		}
				if(tobreak)
					break;
			}
		 }
	 }



	/**
	 *  <i>Getter of Current Board Displayed</i>
	 *
	 * @type Accesor
	 * @return currentBoard
	 */
	public Board getCurrentBoard() {
		return currentBoard;
	}


	/**
	 *  <i>Getter of Player 1 instance in the currentBoard</i>
	 *
	 * @type Accesor
	 * @return currentBoard.getP1()
	 */
	public Stratego_Player getPlayer1() {
		return currentBoard.getP1();
	}

	/**
	 *  <i>Getter of Player 2 instance in the currentBoard</i>
	 *
	 * @type Accesor
	 * @return currentBoard.getP2()
	 */
	public Stratego_Player getPlayer2() {
		return currentBoard.getP2();
	}
}