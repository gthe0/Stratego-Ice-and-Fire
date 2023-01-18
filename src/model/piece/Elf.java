package model.piece;

import model.element.Element;

/**
 * @author gtheo
 */
public class Elf extends SimpleMovingPieces {

	/**
     * <b>Constructor</b><br>
     * 	It creates an Elf instance with power equal to 4
     * @preconditions Follow the invariants
     * @postconditions Initializes the the power and the element.
     * @type Creator
     * @param element<i>It is used as a team's ID</i>
     */
	public Elf(Element element) {
		super(4, element);

	}

}
