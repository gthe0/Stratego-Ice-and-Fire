package model.element;

/**
*<i>Class used to Document the Elements of each Player</i>
* @author gtheo
*/

public enum Element {

	FIRE, ICE ;
	
	/**
	*Get the Element of the opponent
	*@type Observer
	*@return oponent's Element
	*/
	public Element opponent() {
        return this == FIRE ? ICE: FIRE;
    }
}
