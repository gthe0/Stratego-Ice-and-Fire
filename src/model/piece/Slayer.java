package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Slayer extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Slayer instance with power equal to 1
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Slayer( Element element) {
		super(1 , element);

	}


	/**
	 * It also checks if the enemy is dragon, if it is then return this.
	 * @type Mutator
	 */		
	@Override
	public Piece Attack(Piece opponent) 
	{
		if(opponent.getPower()==10)
			return this ;
		
		return super.Attack(opponent);
	}
	
}
