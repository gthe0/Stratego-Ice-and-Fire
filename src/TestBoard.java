import control.Controller;
import model.board.*;
import model.element.Element;
import model.piece.MovablePiece;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestBoard {

    Stratego Experiment ;


    @Test
    public void DefaultModeInitializationTest() {


        Experiment = new Stratego (0,new Controller());

        Board test = Experiment.getCurrentBoard();

        assertEquals(test.getMode(), 0);

        int n = 0;

        assertEquals(test.getTurnCounter(), 1);
        assertEquals(test.getNext(), null);
        assertEquals(test.getPrev(), null);

        for(int j = 5 ; j ==  5 ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) instanceof MovablePiece) n++;
            }


        assertNotEquals(n,5);
        assertNotEquals(n,4);
        assertNotEquals(n,3);
        assertNotEquals(n,2);
        assertNotEquals(n,1);
        assertNotEquals(n,0);

        n = 0 ;

        for(int j = 2 ; j ==  2 ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) instanceof MovablePiece) n++;
            }

        assertNotEquals(n,5);
        assertNotEquals(n,4);
        assertNotEquals(n,3);
        assertNotEquals(n,2);
        assertNotEquals(n,1);
        assertNotEquals(n,0);

        int blue = 0 , fire = 0 ;

        for(int j = 0 ; j < 8  ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) != null )
                    if(test.getPiece(new Position(i, j)).getElement() == Element.FIRE) fire++;
                    else blue ++ ;
            }

        assertEquals(fire,30 );
        assertEquals(blue,30 );

        assertEquals(test.getPlayersTurn(), Element.FIRE);

    }


    @Test
    public void AllInInitializationTest() {

        Experiment = new Stratego (1,new Controller());

        Board test = Experiment.getCurrentBoard();
        int n = 0;
        assertEquals(test.getMode(), 1);

        assertEquals(test.getTurnCounter(), 1);
        assertEquals(test.getNext(), null);
        assertEquals(test.getPrev(), null);

        for(int j = 5 ; j ==  5 ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) instanceof MovablePiece) n++;
            }


        assertNotEquals(n,5);
        assertNotEquals(n,4);
        assertNotEquals(n,3);
        assertNotEquals(n,2);
        assertNotEquals(n,1);
        assertNotEquals(n,0);

        n = 0 ;

        for(int j = 2 ; j ==  2 ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) instanceof MovablePiece) n++;
            }


        assertNotEquals(n,5);
        assertNotEquals(n,4);
        assertNotEquals(n,3);
        assertNotEquals(n,2);
        assertNotEquals(n,1);
        assertNotEquals(n,0);

        int blue = 0 , fire = 0 ;

        for(int j = 0 ; j < 8  ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) != null )
                    if(test.getPiece(new Position(i, j)).getElement() == Element.FIRE) fire++;
                    else blue ++ ;
            }

        assertEquals(fire,30 );
        assertEquals(blue,30 );


        assertEquals(test.getPlayersTurn(), Element.FIRE);
    }

    @Test
    public void HalfArmyInitializationTest() {

        Experiment = new Stratego (2,new Controller());

        Board test = Experiment.getCurrentBoard();
        int n = 0;

        assertEquals(test.getMode(), 2);

        assertEquals(test.getTurnCounter(), 1);
        assertEquals(test.getNext(), null);
        assertEquals(test.getPrev(), null);

        int blue = 0 , fire = 0 ;

        for(int j = 0 ; j < 8  ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) != null )
                    if(test.getPiece(new Position(i, j)).getElement() == Element.FIRE) fire++;
                    else blue ++ ;
            }

        assertEquals(test.getPlayersTurn(), Element.FIRE);
        assertEquals(fire,16 );
        assertEquals(blue,16 );

    }

    @Test
    public void AllModesInitializationTest() {

        Experiment = new Stratego (3,new Controller());

        Board test = Experiment.getCurrentBoard();
        int n = 0;

        assertEquals(test.getMode(), 3);

        assertEquals(test.getTurnCounter(), 1);
        assertEquals(test.getNext(), null);
        assertEquals(test.getPrev(), null);

        int blue = 0 , fire = 0 ;

        for(int j = 0 ; j < 8  ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                if(test.getPiece(new Position(i,j)) != null )
                    if(test.getPiece(new Position(i, j)).getElement() == Element.FIRE) fire++;
                    else blue ++ ;
            }

        assertEquals(test.getPlayersTurn(), Element.FIRE);
        assertEquals(fire,16 );
        assertEquals(blue,16 );

    }





}
