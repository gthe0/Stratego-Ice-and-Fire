package model.player;

import java.util.ArrayList;

import model.element.Element;
import model.piece.*;

/**
 * @author gtheo
 */
public class Stratego_Player implements Player {

	private final Element element ;
	private int Revives ;
	private ArrayList<Piece> Captured ;
	private int Attacks;
	private int succAttacks;


	/**
     * <b>Constructor</b><br>
     * @invariant <b> 0 &ge; revives &ge; 2 <br> 0 &ge; Attacks <br> 0 &ge; succAttacks </b>
     * @preconditions Follow the invariants
     * @postconditions Initializes the Player Instance.
     * @type Creator
     * @param P <i>It is used as team's ID</i>
     */
	public Stratego_Player(Stratego_Player P)
	{
		this.element = P.element ;
		this.Revives = P.Revives;
		this.Captured = new ArrayList<Piece>();
		this.Captured.addAll(P.getCaptured());
		this.Attacks =  P.getAttacks();
		this.succAttacks = P.getSucAttacks() ;
	}

	public Stratego_Player(Element element)
	{
		this.element = element ;
		Revives = Max_Revives ;
		Captured = new ArrayList<Piece>();
		Attacks =  0;
		succAttacks = 0 ;
	}

	@Override
	public int getRevives() {
		return Revives;
	}

	@Override
	public void ReduceRevives() {
		Revives -- ;
	}

	@Override
	public void AddToCaptured(MovablePiece piece) 
	{
		if(getElement()==piece.getElement() || Captured.contains(piece))
			return ;
		
		Captured.add(piece);
		
	}

	/**
	 *  <i>Getter of Element</i>
	 * 
	 * @type Accesor
	 * @return element
	 */	
	public Element getElement() {
		return element;
	}

	@Override
	public ArrayList<Piece> getCaptured() {
		return Captured;
	}

	@Override
	public int getCaptureSize()
	{
		return Captured.size();
	}

	@Override
	public int getAttacks() {
		return Attacks;
	}

	@Override
	public int IncreaseAttacks() {
		return Attacks ++;
	}

	@Override
	public int getSucAttacks() {
		return succAttacks;
	}

	@Override
	public int IncreaseSucAttacks() {
		return succAttacks++;
	}
}
