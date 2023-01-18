package view;

import control.Controller;
import model.board.Board;
import model.board.Move;
import model.board.Position;
import model.element.Element;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StrategoPanel extends JPanel {

    private int xDimensions, yDimensions;
    private MapImage FirePieces, IcePieces;
    Position m1,m2;
    boolean HasSelected ;
    MouseListener mouselistener;
    Controller controller ;


    /**
     * <b>Constructor</b><br>
     * 	It creates a new StrategoPanel for the Game.<br>
     *  It also adds to it a mouseListener
     * @preconditions none
     * @postconditions Instantiates a new board GUI
     * @type Creator
     * @param controller <i>It is used as a controller reference to bridge the Controller and the UI</i>
     */
    public StrategoPanel(Controller controller)
    {
        this.controller =controller;
        FirePieces  = new MapImage(Element.FIRE);
        IcePieces   = new MapImage(Element.ICE);

        mouselistener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            /**
             *	It OverRides mousePressed<br>
             *
             *  If we Press a square and there are no others selected,<br>
             *  then it will check if it is a valid selection and it will select it.<br>
             *  Else the Piece selected must make a valid move, so we must click a valid <b>HIGHLIGHTED</b><br>
             *  square. If we don't the Piece will be deselected else, the move will be made.<br>
             *  If the Move's Destination has a Piece on it, then it creates a Delay and reveals it.<br>
             *  After this, it executed the Attack from the constructor.
             *
             * @type Mutator
             */
            @Override
            public void mousePressed(MouseEvent e) {

                int newX = (int) ((10 * (e.getX())) / getWidth() );
                int newY = (int) ((8 * (e.getY())) / getHeight() );


                if(!HasSelected)
                {
                    HasSelected = controller.isPieceValid(newX,newY);
                    m1 = new Position(newX,newY);
                }
                else
                {
                    m2   = new Position(newX,newY);

                    if(controller.isMoveValid(new Move(m1,m2)))
                    {
                        int powerID = controller.CheckForEnemy(m2);

                        if(powerID > -1)
                        {
                            Graphics  g = getGraphics();
                            Element element = controller.getCurrentBoard().getPiece(m2).getElement();
                            if(element == Element.FIRE) g.drawImage(FirePieces.get(powerID), m2.getX()*xDimensions + xDimensions/10, yDimensions* m2.getY(), xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);
                            else g.drawImage(IcePieces.get(powerID), m2.getX()*xDimensions + xDimensions/10, yDimensions* m2.getY(), xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);

                            long start = System.currentTimeMillis();
                            long end = start + 400;
                            while (System.currentTimeMillis() < end);
                        }

                        controller.MakeMove(new Move(m1,m2));
                    }

                    m1 = null ; m2 = null ;
                    HasSelected = false ;
                }
                getParent().repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        this.setMaximumSize(new Dimension(928,1028));
        this.setPreferredSize(new Dimension(928,1028));


        this.addMouseListener(mouselistener);
    }

    /**
     *  The Paint method , sends repaint Requests
     *
     * @param g The graphics display input
     */
    public void paint(Graphics g) {
        //displays Stratego board
        boardBackground(g);
        displayPieces(g);
    }

    /**
     * Called by the paint method.
     * It calculates the width and height of each square to be drawn <br>
     * based on the width and height of the panel.<br>
     *
     * It also Highlights the Possible Moves a Piece can Make.<br>
     * I am not afraid of Memory leaks due to Garbage Collector.
     *
     * @type Mutator
     * @param g The graphics display input
     */
    private void boardBackground(Graphics g)
    {

        // 10X8 Board
        xDimensions=( getWidth() / 10);
        yDimensions=( getHeight()/ 8);

        int count = 0;
        //Dark Ice/Fire and light Ice/Fire Background squares
        for(int i=0; i< 10;i++)
        {
            for (int j=0; j<8; j++)
            {

                Color temp =  j < 4 ? new Color(165,242,243) : new Color(207,16,32) ;

                if( (i == 2 || i==3 || i == 6 || i == 7)&& ( j == 3 || j == 4 ))
                {
                    g.setColor(Color.darkGray.darker());
                    g.fillRect(i * xDimensions, j * yDimensions, xDimensions, yDimensions);
                }
                else if(count%2==0)
                {
                    g.setColor(temp);
                    g.fillRect(i * xDimensions, j * yDimensions, xDimensions, yDimensions);
                }
                else
                {
                    g.setColor(temp.darker());
                    g.fillRect(i * xDimensions, j * yDimensions, xDimensions, yDimensions);
                }
                count++;
            }
            count++;
        }

        if(HasSelected)
        {
            //Highlight the square clicked by user...
            g.setColor(Color.yellow);
            for (Position temp: controller.PossibleMoves(m1)) {  //...and the Positions that the Piece can go to.
                g.drawRect(temp.getX()* xDimensions, temp.getY()* yDimensions, xDimensions, yDimensions);
            }

            g.setColor(m1.getY()<4 ? Color.RED : Color.BLACK);
            g.drawRect(m1.getX()* xDimensions, m1.getY()* yDimensions, xDimensions, yDimensions);

        }


    }


    /**
     * Called by the paint method.
     * It calculates the width and height of each Image to be drawn <br>
     * based on the width and height of the panel.<br>
     *
     * It also Hides the Pieces of the Player who does not have a turn.<br>
     *
     * @type Mutator
     * @param g The graphics display input
     */
    private void displayPieces(Graphics g)
    {
        Board square = controller.getCurrentBoard();

        for(int i = 0 ; i < 8 ; i++)
            for(int j = 0 ; j < 10 ; j ++)
            {
                if(square.getSquares()[i][j] != null)
                {
                    if(square.getSquares()[i][j].getElement() == square.getPlayersTurn())
                    {
                        if(square.getPlayersTurn() == Element.FIRE)
                             g.drawImage(FirePieces.get(square.getSquares()[i][j].getPower()), j*xDimensions + xDimensions/10, yDimensions*i, xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);
                        else
                            g.drawImage(IcePieces.get(square.getSquares()[i][j].getPower()), j*xDimensions + xDimensions/10, yDimensions*i, xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);

                    }
                    else
                    {
                        if(square.getPlayersTurn() == Element.FIRE)
                            g.drawImage(IcePieces.get(-2), j*xDimensions + xDimensions/10, yDimensions*i, xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);
                        else
                            g.drawImage(FirePieces.get(-2), j*xDimensions + xDimensions/10, yDimensions*i, xDimensions - xDimensions/5 ,yDimensions-yDimensions/20,null);
                    }
                }
            }

    }


    /**
     * Setter of HasSelected<br>
     *
     * It sets the HasSelected variable based on the input
     *
     * @type Mutator
     * @param hasSelected , it can be true or false
     */
    public void setHasSelected(boolean hasSelected) {
        HasSelected = hasSelected;
    }

}