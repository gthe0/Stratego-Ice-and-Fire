package model.board;


/**
*<i>Class used to Document Movement</i>
* @author gtheo
*/

public class Move {

    private final   Position start, destination;
	   
    /**
     *  <b>Constructor</b>
     * 
     * @preconditions none
     * @postconditions It stores the starting position and the destination of the Move performed.
     * @type Creator
     * @param start <i>It stands for the starting position</i>
     * @param destination <i>It stands for the destination , the position we want to move</i>
     */
    public Move(Position start, Position destination) 
    {
        this.start = start;
        this.destination = destination;
    }

    /**
	 * Check's if the movement is vertical 
     * 
     * @type Observer
     * @return true if x coordinates of the start and destination are the same
     */	
    public boolean isVertical() 
    {
        return getX1() == getX2();
    }
    
    /**
	 * Check's if the movement is horizontal 
     * 
     * @type Observer
     * @return true if y coordinates of the start and destination are the same
     */	
    public boolean isHorizontal() 
    {
        return getY1() == getY2();
    }
    
    /**
	 * Check's if the piece goes upwards 
     * 
     * @type Observer
     * @return true if destination's y coordinate is bigger than start's y coordinate
     */	
    public boolean isUp() {
        return getY2() - getY1() > 0;
    }
    
    /**
	 * Check's if the piece goes upwards 
     * 
     * @type Observer
     * @return true if destination's y coordinate is smaller than start's y coordinate
     */	
    public boolean isDown() {
        return getY2() - getY1() < 0;
    }
    
    /**
	 * Check's if the piece goes upwards 
     * 
     * @type Observer
     * @return true if destination's x coordinate is smaller than start's x coordinate
     */	
    public boolean isLeft() {
        return getX2() - getX1() < 0;
    }
    
    /**
	 * Check's if the piece goes upwards 
     * 
     * @type Observer
     * @return true if destination's x coordinate is bigger than start's x coordinate
     */	
    public boolean isRight() {
        return getX2() - getX1() > 0;
    }
    
    /**
	 * Check's if the piece is not Moving diagonally
	 *
     * @type Observer
     * @return true if either X or Y coordinates remain the same but not both(Exclusive or)
     */	
    public boolean isNotDiagonal() 
    {      
    	return getX1() == getX2() ^ getY1() == getY2();
    }
    
    /**
	 * Calculates the distance between
	 *
	 * @preconditions Move is not Diagonal
     * @postconditions It calculates the Distance crossed.
     * @type Observer
     * @return It calculates the distance.
     */	
    public int Distance() 
    {
    	if(!isNotDiagonal())
    		return 0;
    	
    	if(isHorizontal())
    		return Math.abs(getX1() - getX2());
    	
    	if(isVertical())
        	return Math.abs(getY1() - getY2());

    	return 0 ;		
    }

    /**
	 * Getter of Starting Position
     * 
     * @type Accesor
     * @return start 
     */	
	public Position getStart() {
		return start;
	}

		/**
		 * Getter of Destination
	     * 
	     * @type Accesor
	     * @return destination
	     */	
	public Position getDestination() {
		return destination;
	}

	 /**
	  * Getter of Starting position's x coordinate
	  * 
	  * @type Accesor
	  * @return start.xPos 
	  */	
	public int getX1() 
	{
        return getStart().getX();
    }

	/**
	 * Getter of Starting position's y coordinate
	 * 
	 * @type Accesor
	 * @return start.yPos 
	 */
	public int getY1() 
	{
		return getStart().getY();
	}

	/**
	 * Getter of destination's x coordinate
	 * 
	 * @type Accesor
	 * @return destination.xPos 
	 */	
	public int getX2() 
	{
		return getDestination().getX();    
	}
	
	/**
	 * Getter of destination's y coordinate
	 * 
	 * @type Accesor
	 * @return destination.yPos 
	 */	
	public int getY2() 
	{
		return getDestination().getY();
    }
	
    
    
}
