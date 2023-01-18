package model.board;

import java.util.ArrayList;
import java.util.Collections;

import model.element.Element;
import model.piece.*;
import model.player.Player;
import model.player.Stratego_Player;

/**
 * The Class that will be used as a board generator<br>
 * It implements Board_Interface
 * 
 * @author gtheo
 */
public class Board implements Board_Interface{
	
	private Element   playersTurn = Element.FIRE ;
	private Piece[][] squares;
	private Move      lastMove;
	private int 	  TurnCounter = 1 ;
	private Board prev ;
	private Board next ;
	private int   mode ;

	private Stratego_Player P1 , P2;

	private Piece PieceThatRevived;

	
	
	/**
     * <b>Constructor</b><br>
     * 	It creates a new Board
     * @invariant 1 &ge; TurnCounter
     * @preconditions none
     * @postconditions Instantaniates a new board
     * @type Creator
     * @param mode<i>It is used to know how many Pieces to add on board</i>
     */
	public Board(int mode)
	{

		this.PieceThatRevived = null ;

		P1 = new Stratego_Player(Element.FIRE) ;
		P2 = new Stratego_Player(Element.ICE)  ;

		this.squares = new Piece[boardHeight][boardWidth];	//It creates a double Piece array
		next = prev = null;
		ArrayList<Piece> temp=ShuffledPieces(Element.ICE , mode); // It creates a shuffled deck for the Ice guy

		int j = 0 ;
		for(int i = 0 ; i <3 ; i++)	//It puts the Pieces on the upper 3 rows
		{
			for (j = 0; j < 10; j++)
			{
				if(i<2 && i*10 + j == temp.size())
					break ;

				this.squares[i][j] = temp.get(i*10 + j);
			}

			if(i<2 && i*10 + j == temp.size())
				break ;
		}
      
        temp = null ;
		temp=ShuffledPieces(Element.FIRE , mode); // It creates a shuffled deck for the Fire dude


		for(int i = 7 ; i >= 5 ; i--)	//It puts the Pieces on the upper 3 rows
		{
			for ( j = 0; j < 10; j++)
			{

				if(i>5 &&(7 - i)*10 + j == temp.size())
					break ;
				this.squares[i][j] = temp.get((7 - i)*10 + j);
			}
			if(i>5 &&(7 - i)*10 + j == temp.size())
				break ;
		}

		this.mode = mode ;
    		
	}

	/**
     * <b>Copy Constructor</b><br>
     * 	It creates a copy of the Board
     * @preconditions none
     * @postconditions Copies the board and makes new Instance
     * @type Creator
     * @param board<i>Board to be copied</i>
     */
	public Board(Board board)
	{

		this.mode = board.getMode();
		this.playersTurn = board.playersTurn;	//Copies components of the passed board, to the new one
		this.lastMove = board.lastMove ;
		this.TurnCounter = board.TurnCounter ;
		this.squares = new Piece[boardHeight][boardWidth];
		this.P1 = new Stratego_Player(board.getP1());
		this.P2 = new Stratego_Player(board.getP2());

		for (int x = 0; x <boardWidth; x++) {
			for (int y = 0; y < boardHeight; y++) {
				this.squares[y][x] = board.squares[y][x];
			}
		}

		this.prev = board.getPrev();
		this.next = null;
		this.PieceThatRevived = null ;

	}
	
	
	 @Override
	 public boolean isMoveLegal(Move move) 
	 {      
		 Position destination = move.getDestination();
		 
		 if(destination.getX() >= getBoardWidth() || destination.getY() >= getBoardHeight() //Checks if new Position is within the board...
		 || destination.getX() <  0               || destination.getY() <  0              )
			 return false ;
		 
		 
		 for(Position illegalPosition: IllegalPositions) //...and if it is not on illegal grounds
		 {
			 if(destination.equal(illegalPosition)) 
			 {
				 return false ;
			 }
		 }
		 
	        Piece piece = getPiece(move.getStart());
	        //does the chosen piece belong to the current player and can it make the requested move?
	        return piece != null
	        	&& move.isNotDiagonal()
	            && piece.getElement() == getPlayersTurn()
	            && piece.isMoveLegal(this, move);
	    }
	
	 
	@Override
	public boolean PlayerHasMoves() 
	{
		return !getPlayerPieces(getPlayersTurn()).isEmpty()	//If the player has pieces and can move, then it is true
			&& !PlayerPossibleMoves(getPlayersTurn()).isEmpty();
			
	}
	
	/**
	* Finds all Possible Moves a player can Make.
	* Can be used for AI
	* 
	* @type Observer
	* @return An ArrayList of all possible moves 1 can make
	*/	
	private ArrayList<Move> PlayerPossibleMoves(Element playerElement) 
	{
		ArrayList<Move> possiblePos= new ArrayList<>();

		
		for(Position pos :  getPlayerPieces( getPlayersTurn()))
		{
			 Piece piece = getPiece(pos);
			 if(piece != null && piece.getElement() == playerElement) 
			 {
				 ArrayList<Move> temp = piece.PossibleMoves(this,pos);
			 
				 if(temp != null)
				 {
					 possiblePos.addAll(temp) ;
				 }
			 }
    
			 
		}
		
		return possiblePos ;
		
	}
	
	@Override
	public boolean isPathObstructed(Move move) 
	{
		if(!move.isNotDiagonal())		//First checks if move is straight
            return true;

	    int x = move.getX2() - move.getX1();
        int y = move.getY2() - move.getY1();

        if(x != 0)
            x /= Math.abs(x);//reduce positive/negative x to 1/-1
        if(y != 0)
            y /= Math.abs(y);//reduce positive/negative y to 1/-1

        Position testSquare = new Position(move.getX1() + x, move.getY1() + y);
		while(!testSquare.equal(move.getDestination())) {	//Checks if there are other pieces between the positions
			if(getPiece(testSquare) != null)
			{
                return true;
            }
            for(Position position : IllegalPositions)	//Checks if the piece moves through Illegal positions
            	if(testSquare.equal(position))
            		return true ;

            testSquare.setCoords(testSquare.getX() + x,testSquare.getY() + y );
        }
        return false;
    }
	
	/**
	* Finds all the Pieces of a Player.
	* Can be used for AI
	* 
	* @type Observer
	* @return An ArrayList of all the Positions of one's Pieces
	*/	
	private ArrayList<Position> getPlayerPieces(Element playerElement)	
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		for(int y = 0; y < getBoardHeight(); y++) {		//Checks each and every square for pieces
            for (int x = 0; x < getBoardWidth(); x++) 
            {
            	 Piece piece = getPiece(new Position(x,y));
            	 
                 if(piece != null && piece.getElement() == playerElement)	//If it's player's Piece, add its position in an ArrayList
                     positions.add(new Position(x,y));
            }
            
  		}
		return positions ;	//return the List
	}
	
	@Override
	public Move getLastMove() {
		return lastMove;
	}
	@Override
	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}
	@Override
	public Element getPlayersTurn() {
		return playersTurn;
	}
	@Override
	public void setPlayersTurn(Element playersTurn) {
		this.playersTurn = playersTurn;
	}
	@Override
	public Piece getPiece(Position position) 
	{
		return squares[position.getY()][position.getX()];
	}
	@Override
	public void setPiece(Position position, Piece piece) {
        squares[position.getY()][position.getX()] = piece;
	}
	@Override
	 public int getBoardWidth() 
	 {
        return boardWidth;
	 }
	@Override
	 public int getBoardHeight() 
	 {
		 return boardHeight;
	 }
	@Override
	  public void endPlayerTurn() 
	  {
		  TurnCounter ++ ;
		  playersTurn = playersTurn.opponent();
	  }
	  
	/**
	* Getter of Turn Counter
	* 
	* @type Accesor
	* @return TurnCounter
	*/
	 public int getTurnCounter() 
	 {
		 return TurnCounter;
	 }
	
	/**
	* Instantaniates and shuffles all the Pieces of a Player.
	* Can be used for AI
	* 
	* @type Mutator
	* @param mode, it is used to determine how many Pieces must be created
	* @param element, the Element of the owner
	* @return An ArrayList of all the shuffled Pieces.
	*/	
	  private ArrayList<Piece> ShuffledPieces(Element element , int mode)
	  {
		  ArrayList<Piece> ShuffledPieces = new ArrayList<Piece>();	//Creates a new ArrayList
		  
		  int x = (mode < 2) ? 1 : 2 ;	//Based of the mode, x equals 1 or 2
		  

		  ShuffledPieces.add(new Flag(element));
		  for(int i = 0 ; i < Math.floor(6/x); i++) //We add floor of the 6/x Pieces
		  {
			  ShuffledPieces.add(new Trap(element));
		  }

		  ShuffledPieces.add(new Dragon(element));	//Then we add all the Pieces
		  ShuffledPieces.add(new Mage(element));
		  ShuffledPieces.add(new Slayer(element));

		  for(int i = 0 ; i < Math.floor(2/x) ; i++) //We add floor of the 2/x Pieces
		  {
			  ShuffledPieces.add(new Knight(element));
			  ShuffledPieces.add(new Sorceress(element));
			  ShuffledPieces.add(new Beast(element));
			  ShuffledPieces.add(new Elf(element));
		  }

		  for(int i = 0 ; i < Math.floor(3/x) ; i++) //We add floor of the 3/x Pieces
		  {
			  ShuffledPieces.add(new Beast_Rider(element));
		  }


		  for(int i = 0 ; i < Math.floor(4/x) ; i++) //We add floor of the 4/x Pieces
		  {
			  ShuffledPieces.add(new Scout(element));
		  }

		  for(int i = 0 ; i < Math.floor(5/x) ; i++) //We add floor of the 5/x Pieces
		  {
			  ShuffledPieces.add(new Dwarf(element));
		  }

		  if(x==1)
		  {
		  Collections.shuffle(ShuffledPieces.subList(7,29));	//We shuffle the part of the list without Immovable Objects
		  Collections.shuffle(ShuffledPieces.subList(4,29)); 	//We shuffle Part of the list with 3 traps so that they may appear in the first row
		  Collections.shuffle(ShuffledPieces.subList(4,29));

		  Collections.shuffle(ShuffledPieces.subList(1,19));	//We shuffle first 2 backRows where there are Immovable Pieces except the flag,
		  Collections.shuffle(ShuffledPieces.subList(1,19));
		  Collections.shuffle(ShuffledPieces.subList(0,9 ));   //We shuffle the last row where is the flag.
		  														//that way there won't be more than 3 Immovable Pieces in the First Row and the flag will always be at the back row
		  }
		  else
		  {
			  Collections.shuffle(ShuffledPieces);
			  Collections.shuffle(ShuffledPieces);
		  }

		  return ShuffledPieces;
	  }
	
	
	  
	  
	@Override
	public int makeMove(Move move) 
	{	
		Piece temp;
			
		if(getPiece(move.getDestination())!= null) //If there is an enemy Piece on destination then attack it
		{	
			MovablePiece piece =(MovablePiece)getPiece(move.getStart());
			temp = piece.Attack(getPiece(move.getDestination()));	//If it returns a flag then return -1

			int trash = piece.getElement()==Element.FIRE ? P1.IncreaseAttacks() : P2.IncreaseAttacks() ;

			if(temp instanceof Flag)
				return -1;

			if(temp == null)
			{
				if(piece.getPower() ==getPiece(move.getDestination()).getPower())
				{
					if(piece.getElement() == Element.FIRE)
					{
						P2.AddToCaptured(piece);
						P1.AddToCaptured((MovablePiece) getPiece(move.getDestination()));
					}
					else
					{
						P1.AddToCaptured(piece);
						P2.AddToCaptured((MovablePiece) getPiece(move.getDestination()));
					}
				}
				else if(piece.getElement() == Element.FIRE)
				{
					P2.AddToCaptured(piece);
				}
				else
				{
					P1.AddToCaptured(piece);
				}
			}

			if(temp != null && piece.getElement() == temp.getElement() && piece.getPower() != 3)
			{

				if(P1.getElement() == piece.getElement())
				{
					P1.AddToCaptured((MovablePiece) getPiece(move.getDestination()));
					P1.IncreaseSucAttacks();
				}
				else if(P2.getElement() == piece.getElement())
				{
					P2.AddToCaptured((MovablePiece) getPiece(move.getDestination()));
					P2.IncreaseSucAttacks();
				}


				trash = 0 ;
			}
			else if (temp != null && piece.getElement() != temp.getElement())
			{

				if(temp.getPower() != -1) {


					if (P1.getElement() == piece.getElement())
					{
						P2.AddToCaptured(piece);
					}
					else if (P2.getElement() == piece.getElement())
					{
						P1.AddToCaptured(piece);
					}

				}

				trash = 1 ;
			}

			setPiece(move.getDestination(),temp); //else place on destination the winner and at start remove piece
			setLastMove(move);
			setPiece(move.getStart(),null);
			
			return trash ;
		}
		
		setPiece(move.getDestination(),getPiece(move.getStart()));	//place on destination the piece and at start remove it
		setLastMove(move);
		setPiece(move.getStart(),null);
		
		return 0 ;
		
	}


	/**
	 *  Getter of mode
	 *
	 * @type Accesor
	 * @return the mode chosen by the players
	 */
	public int getMode() {
		return mode;
	}


	/**
	 *  Getter of Previous Board Instance
	 *
	 * @type Accesor
	 * @return the Previous board of the double linked list
	 */
	public Board getPrev() {
		return prev;
	}


	/**
	 *  Getter of Next Board Instance
	 *
	 * @type Accesor
	 * @return  the Next board of the double linked list
	 */
	public Board getNext() {
		return next;
	}


	/**
	 *  Setter of next Board in the Double linked List
	 *
	 * @type Mutator
	 * @param next ,the next Board Instance that we created
	 */
	public void setNext(Board next) {
		this.next = next;
	}



	/**
	 *  Setter of prev Board in the Double linked List
	 *
	 * @type Mutator
	 * @param prev ,the prev Board Instance that we created
	 */
	public void setPrev(Board prev) {
		this.prev = prev;
	}


	/**
	 *  Getter of all squares
	 *
	 * @type Accesor
	 * @return squares
	 */
	public Piece[][] getSquares() {
		return squares;
	}

	/**
	 *  Getter of Fire Player
	 *
	 * @type Accesor
	 * @return P1
	 */
	public Stratego_Player getP1() {
		return P1;
	}


	/**
	 *  Getter of Ice Player
	 *
	 * @type Accesor
	 * @return P2
	 */
	public Stratego_Player getP2() {
		return P2;
	}


	/**
	 *  Getter of Piece that Revived
	 *
	 * @type Accesor
	 * @return PieceThatRevived
	 */
	public Piece getPieceThatRevived() {
		return PieceThatRevived;
	}

	/**
	 *  Setter of Piece that Revived
	 *
	 * @type Mutator
	 */
	public void setPieceThatRevived(Piece pieceThatRevived) {
		PieceThatRevived = pieceThatRevived;
	}
}
