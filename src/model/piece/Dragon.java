package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Dragon extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Dragon instance with power equal to 10
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Dragon(Element element) {
		super(10, element);
	}

}
