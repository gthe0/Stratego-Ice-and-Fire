package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Knight extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Knight instance with power equal to 8
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Knight(Element element) {
		super(8, element);
	}

}
