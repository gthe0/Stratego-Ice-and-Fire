package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Sorceress extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Sorceress instance with power equal to 6
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Sorceress (Element element) {
		super(6, element);

	}

}
