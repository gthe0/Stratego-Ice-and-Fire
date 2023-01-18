package model.piece;

import java.util.ArrayList;

import model.board.Board;
import model.board.Move;
import model.board.Position;
import model.element.Element;

/**
 * @author gtheo
 */
public abstract class ImmovablePiece extends Piece {

	
	public ImmovablePiece(int power, Element element) {
		super(power, element);
	}

	/**
	 * Overridden Function that always returns false because Piece is <b>Immovable</b>.
	 * @type Observer
	 */	
	@Override
	public boolean isMoveLegal(Board gameState, Move move) {
		return false;
	}

	/**
	 * Overridden Function that always returns null because Piece is <b>Immovable</b>.
	 * @type Observer
	 */	
	@Override
	public ArrayList<Move> PossibleMoves(Board gameState ,Position position) {
		return null;
	}

}
