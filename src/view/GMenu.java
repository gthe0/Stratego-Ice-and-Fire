package view;

import control.Controller;
import model.element.Element;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;

/**
 *
 * A class that will be used to generate a General Menu
 * @author gtheo
 */
public class GMenu extends JPanel {

	JLabel Player, Turn, Statistics_Label, Revives , Attack_label ;
	Controller controller;

	JLabel[] PicLabel1,PicLabel2 ;

	Font PlayerFont, GeneralFont, StatsAndCapturesFont ;
	JPanel PlayerNamePanel, TurnCounter, StatisticsP, StatPanel, AttackP , CaptureGrid1 , CaptureGrid2;

	/**
	 * <b>Constructor</b><br>
	 * Creates a new Menu Panel
	 * @postconditions Creates a new Menu Panel
	 * @type creator
	 * @param controller , It stores a reference of the Controller to bridge it with the UI
	 */
	public GMenu(Controller controller)
	{
		this.setMaximumSize(new Dimension(300,1028));
		this.setPreferredSize(new Dimension(300,1028));
		this.setBackground(Color.darkGray);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		CaptureGrid1 = new JPanel();
		CaptureGrid1.setLayout(new GridLayout(5,4));

		CaptureGrid2 = new JPanel();
		CaptureGrid2.setLayout(new GridLayout(5,4));

		PicLabel1 = controller.ChangeJLabel(PicLabel1,Element.FIRE);
		PicLabel2 = controller.ChangeJLabel(PicLabel2,Element.ICE);

		for(int i = 0 ; i <20 ; i ++)
		{
			CaptureGrid1.add(PicLabel1[i],i);
		}

		for(int i = 0 ; i <20 ; i ++)
		{
			CaptureGrid2.add(PicLabel2[i],i);
		}


		PlayerFont = new Font("Times New Roman",Font.PLAIN,45);
		Player = new JLabel("FIRE PLAYER");
		Player.setForeground(Color.WHITE);
		Player.setFont(PlayerFont);

		GeneralFont = new Font("Times New Roman",Font.PLAIN,20);
		Attack_label = new JLabel("Successful Attacks : " + controller.getModel().getPlayer1().getAttacks() + "%");
		Attack_label.setForeground(Color.WHITE);
		Attack_label.setFont(GeneralFont);

		Revives = new JLabel("Revives Left: "+controller.getModel().getPlayer1().getRevives());
		Revives.setForeground(Color.WHITE);
		Revives.setFont(GeneralFont);

		JPanel Box_for_Rev = new JPanel();
		Box_for_Rev.setLayout(new BoxLayout(Box_for_Rev,BoxLayout.X_AXIS));
		Box_for_Rev.add(Box.createHorizontalStrut(5));
		Box_for_Rev.add(Revives);
		Box_for_Rev.setBackground(Color.darkGray);

		StatPanel = new JPanel();
		StatPanel.setLayout(new BorderLayout());
		StatPanel.add(Box_for_Rev,BorderLayout.NORTH);
		StatPanel.setBackground(Color.darkGray);

		Turn = new JLabel("Turn : "+controller.getCurrentBoard().getTurnCounter());
		Turn.setForeground(Color.WHITE);
		Turn.setFont(GeneralFont);

		StatsAndCapturesFont = new Font("Times New Roman",Font.PLAIN,30);
		Statistics_Label = new JLabel("STATISTICS");
		Statistics_Label.setForeground(Color.WHITE);
		Statistics_Label.setFont(StatsAndCapturesFont);

		this.controller = controller ;

		PlayerNamePanel = new JPanel();
		PlayerNamePanel.add(Player);
		PlayerNamePanel.setBackground(Color.darkGray);
		PlayerNamePanel.setMaximumSize(new Dimension(300,100));

		AttackP = new JPanel();
		AttackP.add(Attack_label,BorderLayout.CENTER);
		AttackP.setBackground(Color.darkGray);

		TurnCounter= new JPanel();
		TurnCounter.add(Turn);
		TurnCounter.setBackground(Color.darkGray);
		TurnCounter.setMaximumSize(new Dimension(300,100));

		StatPanel.add(Box.createVerticalStrut(5));
		StatPanel.add(AttackP);

		JPanel BoxForLabel = new JPanel();

		BoxForLabel.setLayout(new BoxLayout(BoxForLabel, BoxLayout.X_AXIS));
		BoxForLabel.add(Box.createHorizontalGlue());
		BoxForLabel.add(Statistics_Label);
		BoxForLabel.add(Box.createHorizontalGlue());
		BoxForLabel.setBackground(Color.darkGray);

		StatisticsP = new JPanel();
		StatisticsP.setLayout(new BorderLayout());
		StatisticsP.add(BoxForLabel,BorderLayout.NORTH);
		StatisticsP.add(StatPanel,BorderLayout.WEST);
		StatisticsP.setBackground(Color.darkGray);
		StatisticsP.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(Color.black)));

		CaptureGrid1.setBackground(Color.darkGray);
		CaptureGrid1.setMaximumSize(new Dimension(300,300));
		CaptureGrid1.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(new Color(207,16,32).brighter())));

		CaptureGrid2.setBackground(Color.darkGray);
		CaptureGrid2.setMaximumSize(new Dimension(300,300));
		CaptureGrid2.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(new Color(165,242,243))));

		this.setBorder(new LineBorder(Color.black));

		StatisticsP.setMaximumSize(new Dimension(300,120));

		this.add(PlayerNamePanel);
		this.add(TurnCounter);
		this.add(StatisticsP);
		this.add(CaptureGrid1);
		this.add(CaptureGrid2);

		CaptureGrid2.setVisible(false);
		this.setVisible(true);
	}


	/**
	 *  The Paint method , sends repaint Requests<br>
	 *
	 * 	Here, in each repaint request it checks whose turn it is and repaints accordingly.
	 *
	 * @param g The graphics display input
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Turn.setText("Turn : "+controller.getCurrentBoard().getTurnCounter());
		if(controller.getCurrentBoard().getPlayersTurn() == Element.FIRE)
		{
			this.setBorder(new LineBorder(new Color(207,16,32).brighter()));
			StatisticsP.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(new Color(207,16,32).brighter())));

			if(!CaptureGrid1.isVisible())
			{
				CaptureGrid1.removeAll();
				PicLabel1 = controller.ChangeJLabel(PicLabel1,Element.FIRE);

				for(int i = 0 ; i <20 ; i ++)
				{
					CaptureGrid1.add(PicLabel1[i],i);
				}

				CaptureGrid1.setVisible(true);
				CaptureGrid2.setVisible(false);
			}

			int attacks = controller.getModel().getPlayer1().getAttacks() == 0 ? 1 : controller.getModel().getPlayer1().getAttacks();
			double toPrint =  (double) (controller.getModel().getPlayer1().getSucAttacks() * 100d) /(double) attacks;
			DecimalFormat a = new DecimalFormat("0.00");

			Player.setText("FIRE PLAYER");
			Revives.setText("Revives Left: "+controller.getModel().getPlayer1().getRevives());
			Attack_label.setText("Successful Attacks : " + a.format(toPrint)+ "%");
		}
		else
		{

			this.setBorder(new LineBorder(new Color(165,242,243)));
			StatisticsP.setBorder(new CompoundBorder(new EmptyBorder(10,10,10,10),new LineBorder(new Color(165,242,243))));

			if(!CaptureGrid2.isVisible())
			{
				CaptureGrid2.removeAll();
				PicLabel2 = controller.ChangeJLabel(PicLabel2,Element.ICE);

				for(int i = 0 ; i <20 ; i ++)
				{
					CaptureGrid2.add(PicLabel2[i],i);
				}


				CaptureGrid2.setVisible(true);
				CaptureGrid1.setVisible(false);
			}

			int attacks = controller.getModel().getPlayer2().getAttacks() == 0 ? 1 : controller.getModel().getPlayer2().getAttacks();
			double toPrint =  controller.getModel().getPlayer2().getSucAttacks() * 100d /(double) attacks;
			DecimalFormat a = new DecimalFormat("0.00");

			Player.setText(" ICE PLAYER ");
			Revives.setText("Revives Left: "+controller.getModel().getPlayer2().getRevives());
			Attack_label.setText("Successful Attacks : " + a.format(toPrint)  + "%");
		}
	}







}