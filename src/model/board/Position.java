package model.board;

/**
*<i>Class used to Document position</i>
* @author gtheo
*/

public class Position {

	/**@invariant 0 &ge; yPos &g; Board Height */ //class invariants 
	/**@invariant 0 &ge; xPos &g; Board Width */ //class invariants 	
	private int xPos, yPos;
	
	/**
     *  <b>Constructor</b>
     * @invariant <b>0 &ge; yPos &g; Board Height</b> where Board Height is the maximum vertical size of the Board<br> It is the y coordinate on the Board<br> 
     * @invariant <b>0 &ge; xPos &g; Board Width</b> where Board Width is the maximum horizontal size of the Board<br> It is the x coordinate on the Board<br> 
     * @preconditions Follow the invariants
     * @postconditions Creates the coordinate Instance
     * @type Creator
     * @param x <i>It stands for the x coordinate</i>
     * @param y <i>It stands for the y coordinate</i>
     */
	public Position(int x, int y) 
	{
		xPos = x;
		yPos = y;
	}
	
	/**
	 *  <i>Setter of coordinates</i><br>
	 *  Sets the coordinates of x and y
	 *  
     * @preconditions Check Invariants
     * @type Muttator
     * 
     */	
	public void setCoords(int x, int y)
	{	
		xPos = x;
		yPos = y;
	}
	
	
	/**
	 *  <i>Check's if this is equal to another Position</i>
     * 
     * @type Observer
     * @return true if p‘s coordinates are equal to the Object’s 
     */	
	
	public boolean equal(Position p)
	{
		return getX() == p.getX()
			&& getY() == p.getY();
	}
	

	/**
	 *  <i>Getter of x coordinate</i>
     * 
     * @type Accesor
     * @return xPos 
     */	
	public int getX()
	{	return xPos;	}
	
	
	/**
	 *  <i>Getter of y coordinate</i>
     * 
     * @type Accesor
     * @return yPos 
     */	
	public int  getY()
	{	return yPos;	}
	

}
