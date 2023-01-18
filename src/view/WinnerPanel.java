package view;

import control.Controller;
import model.element.Element;

import javax.swing.*;
import java.awt.*;



/**
 * WinnerPanel is the Panel used to display the Winner
 *
 * @author gtheo
 */
public class WinnerPanel extends JPanel {

    Controller controller ;

    /**
     * <b>Constructor</b><br>
     * 	It creates a new Panel to display the Winner on a GridBagLayout<br>
     * 	Based of the element that we pass
     * @preconditions none
     * @postconditions Instantiates a new Panel to display the winner
     * @type Creator
     * @param controller <i>It is used as a controller reference to bridge the Controller and the UI</i>
     * @param boxLayer <i>It is a reference to its Wrapper and it is used to remove it when new Game is clicked</i>
     * @param element <i>It is the element of the Player that Won the Game</i>
     * @param frame <i>Is is a reference to the frame that displays this Panel <br> and it 's used to remove this and its wrapper and then set Up a new Game </i>
     */
    WinnerPanel(Element element, Controller controller , GameFrame frame, JPanel boxLayer)
    {
        Color color = element == Element.FIRE ? new Color(207,16,32).darker() : new Color(165,242,243).darker();

        this.controller = controller;
        this.setLayout(new GridBagLayout());

        JLabel WinnerNameLabel = new JLabel(element == Element.FIRE ? "FIRE NATION WINS ! " : "ICE NATION WINS ! ");
        WinnerNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,90));
        WinnerNameLabel.setForeground(color);

        JLabel Continue = new JLabel("CONTINUE ?");
        Continue.setFont(new Font("Times New Roman",Font.PLAIN,90));
        Continue.setForeground(color);

        JButton Quit = new JButton("Quit");
        Quit.addActionListener((e)->
        {
            System.exit(0);
        });

        Quit.setBackground(color.darker());
        Quit.setForeground(Color.WHITE);

        JButton NewGame= new JButton("New Game");
        NewGame.addActionListener((e)->
        {
            int mode = JOptionPane.showOptionDialog(null,"Please choose a mode","Mode Chooser", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,new String[]{"DEFAULT","ALL-IN","HALF ARMY","ALL-IN HALF ARMY"},-1);
            if(mode != -1)
            {
                this.controller.NewGameClicked(mode);
                setVisible(false);
                boxLayer.setVisible(false);
                frame.remove(boxLayer);
                frame.remove(this);
                frame.SetUpGame();
                frame.repaint();
            }

        });

        NewGame.setBackground(color.darker());
        NewGame.setForeground(Color.WHITE);

        JPanel OptionBox = new JPanel();
        OptionBox.setBackground(color.brighter().brighter());

        OptionBox.add(NewGame);
        OptionBox.add(Quit);


        this.setBackground(color.brighter().brighter());

        this.add(WinnerNameLabel);
        this.add(Continue);
        this.add(OptionBox);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
    }

}
