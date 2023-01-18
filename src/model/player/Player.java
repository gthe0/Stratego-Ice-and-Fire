package model.player;

import model.element.Element;
import model.piece.*;

import java.util.ArrayList;

/**
 * @author gtheo
 */
public interface Player 
{

	int Max_Revives = 2 ;
	

	/**
	 *  <i>Getter of Element</i>
	 * 
	 * @type Accesor
	 * @return element
	 */	
	
	Element getElement(); 
	
	
	/**
	 *  <i>Getter of Revives</i>
	 * 
	 * @type Accesor
	 * @return revives
	 */	
	int getRevives();
	
	/**
	 *  <i>Reduces number of revives</i>
	 * 
	 * @type Mutator
	 */	
	void ReduceRevives();
	
	/**
	 *  <i>Records dead/captured Pieces</i>
	 * 
	 * @preconditions Must not be in the Captured List, and must be the same element
	 * @postconditions Adds the piece in the Captured List
	 * @type Mutator
	 * @param piece, the defeated piece of the player
	 */	
	void AddToCaptured(MovablePiece piece);

	/**
	 *  <i>Incrementor of Attakcs</i><br>
	 *
	 * It Increments the number of the Attacks by 1
	 *
	 * @type Mutator
	 * @return the number of the attacks before Incrementing
	 */
	int IncreaseAttacks();

	/**
	 *  <i>Getter of Attakcs</i>
	 *
	 * @type Accesor
	 * @return the number of attacks made by the Player
	 */
	int getAttacks() ;


	/**
	 *  <i>Incrementor of successful Attakcs</i><br>
	 *
	 * It Increments the number of the successful Attacks by 1
	 *
	 * @type Mutator
	 * @return the number of the successful attacks before Incrementing
	 */
	int IncreaseSucAttacks();


	/**
	 *  <i>Getter of Successful Attakcs</i>
	 *
	 * @type Accesor
	 * @return the number of successful attacks made by the Player
	 */
	int getSucAttacks();

	/**
	 *  <i>Getter of Captured List</i>
	 *
	 * @type Accesor
	 * @return Captured
	 */
	public ArrayList<Piece> getCaptured();

	/**
	 *  <i>Getter of Captured List size</i>
	 *
	 * @type Accesor
	 * @return Captured.size()
	 */
	public int getCaptureSize();

}
