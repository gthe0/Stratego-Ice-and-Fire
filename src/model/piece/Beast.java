package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Beast extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Beast instance with power equal to 5
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Beast(Element element) {
		super(5, element);
	}

}
