package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Beast_Rider extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates a Beast_Rider instance with power equal to 7
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Beast_Rider( Element element) {
		super(7, element);

	}

}
