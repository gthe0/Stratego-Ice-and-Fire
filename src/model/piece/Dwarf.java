package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Dwarf extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Dwarf instance with power equal to 3
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Dwarf(Element element) 
	{
		super(3, element);
	}


	/**
	 * The only difference is that if it's a Trap we return this.
	 * @type Mutator
	 */		
	@Override
	public Piece Attack(Piece opponent) 
	{
		if(opponent.getPower() == 0) 
			return this ;
	
		if(opponent.getPower()== -1)
			return new Flag(opponent.getElement().opponent());
		
		if(opponent.getPower() == this.getPower()) 
			return null ;
		else
			return this.getPower() > opponent.getPower() ? this : opponent;
	}
	
	
}
