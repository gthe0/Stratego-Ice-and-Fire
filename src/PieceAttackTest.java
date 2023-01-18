import model.element.Element;
import model.piece.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceAttackTest {

    @Test
    public void PieceSlayer()
    {
        Slayer a = new Slayer(Element.FIRE);
        Dragon dragon = new Dragon(Element.ICE);

        assertEquals(a.Attack(dragon),a);
        assertEquals(dragon.Attack(a),dragon);
    }

    @Test
    public void DwarfAttackTrap()
    {
        Dwarf a = new Dwarf(Element.FIRE);
        Trap trap = new Trap(Element.ICE);

        assertEquals(a.Attack(trap),a);
    }


    @Test
    public void TrapAttackOREqualPower()
    {
        Mage a = new Mage(Element.FIRE);
        Mage b = new Mage(Element.ICE);
        Knight c = new Knight(Element.ICE);
        Trap trap = new Trap(Element.ICE);

        assertEquals(a.Attack(trap),null);
        assertEquals(a.Attack(b),null);
        assertEquals(a.Attack(c),a);
    }

    @Test
    public void FlagAttack()
    {
        Flag a = new Flag(Element.FIRE);
        Mage b = new Mage(Element.ICE);

        assertEquals(b.Attack(a) instanceof Flag, true);
        assertEquals(b.Attack(a).getElement() , Element.ICE);
        assertEquals(b.Attack(a).getPower() , -1);
    }




}
