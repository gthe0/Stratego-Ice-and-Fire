import control.Controller;
import model.board.*;
import model.element.Element;
import model.piece.MovablePiece;

import model.piece.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import static junit.framework.TestCase.*;


public class MovementTest
{

    Stratego Experiment ;
    Board test ;

    @Before
    public void ConstructStratego()
    {
        Experiment = new Stratego(0,new Controller());
        test = Experiment.getCurrentBoard();

        for(int j = 0 ; j < 8  ; j++)
            for (int i = 0 ; i < 10; i++)
            {
                test.setPiece(new Position(i,j) , null);
            }
    }

    @Test
    public void GeneralMovementTest()
    {

        test.setPiece(new Position(2,5),new Dwarf(Element.FIRE));
        assertEquals(test.getPiece(new Position(2,5)).PossibleMoves(test, new Position(2,5)).size(), 3);
        assertEquals(test.isMoveLegal(new Move(new Position(2,5),new Position(1,5))),true);

        test.makeMove(new Move(new Position(2,5),new Position(1,5)));

        assertEquals(test.getPiece(new Position(1,5)) instanceof Dwarf, true);
        assertEquals(test.getPiece(new Position(1,5)).PossibleMoves(test, new Position(2,5)).size(), 0);
    }

    @Test
    public void ScoutMovementTest()
    {

        test.setPiece(new Position(0,0),new Scout(Element.FIRE));
        assertEquals(test.getPiece(new Position(0,0)).PossibleMoves(test, new Position(0,0)).size(), 16);
        assertEquals(test.isMoveLegal(new Move(new Position(0,0),new Position(0,7))),true);

        test.makeMove(new Move(new Position(0,0),new Position(0,7)));

        assertEquals(test.getPiece(new Position(0,7)) instanceof Scout, true);

    }



}
