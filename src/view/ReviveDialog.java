package view;

import control.Controller;
import model.element.Element;
import model.piece.Piece;
import model.player.Stratego_Player;

import javax.swing.*;
import java.awt.*;

/**
 * A class that will be used to generate a box with revive options
 *
 * @author gtheo
 */
public class ReviveDialog extends JDialog {

	Controller controller;
	JButton[] buttonForOption ;
	Stratego_Player Player ;


	/**
	 * <b>Constructor</b><br>
	 * Creates a new Revive dialog Window  <br />
	 * @postconditions Creates a new Revive dialog window
	 * @type Creator
	 * @param controller <i>A reference to the controller to call the ReviveUnitClicked Method</i>
	 * @param P <i>A reference to the Stratego_Player who wants to revive a unit</i>
	 * @param owner <i>The main frame which is owner of the Dialog</i>
	 */
	public ReviveDialog(Stratego_Player P, JFrame owner, Controller controller) {
		super(owner, "Revive options");

		this.controller = controller;
		Player = P ;

		buttonForOption = new JButton[Player.getCaptureSize()];

		if (Player.getCaptureSize() != 0)
			setUpDialog();
	}


	/**
	 * It is used to set up the Revive Dialog Box for the Pieces in the Captured List of the player
	 *
	 * @type Mutator
	 */
	private void setUpDialog()
	{
		MapImage Pieces = new MapImage(Player.getElement().opponent());

		Color color = Player.getElement() != Element.FIRE ? new Color(207, 16, 32).brighter() : new Color(165, 242, 243);

		this.setLayout(new GridLayout(5,5));
		this.setBackground(color);


		int i = 0 ;
		for (Piece p : Player.getCaptured()) {

			buttonForOption[i] = new JButton(new ImageIcon(Pieces.get(p.getPower()).getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			buttonForOption[i].addActionListener(e ->
			{
				controller.RevivedUnitClicked(findButton( e.getSource()),Player.getElement());
				dispose();
			});

			buttonForOption[i].setBackground(color);

			add(buttonForOption[i]);
			i++ ;
		}
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(400,300);

		this.setVisible(true);
	}

	/**
	 * Finds the index of the button clicked.
	 *
	 * @type Observer
	 * @param c the Object / button pressed
	 * @return the index of the button clicked, if found or -1 if not
	 */
	private int findButton(Object c)
	{
		for (int i = 0; i < buttonForOption.length; i++)
		{
			if(c.equals(buttonForOption[i]))
				return i ;
		}

		return -1;
	}


}
