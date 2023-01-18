package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Mage extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Mage instance with power equal to 9
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Mage(Element element) {
		super(9, element);

	}

}
