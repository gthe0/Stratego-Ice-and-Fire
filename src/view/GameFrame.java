package view;

import control.Controller;
import model.element.Element;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;



/**
 * GameFrame is the Frame used to display everything
 *
 * @author gtheo
 */
public class GameFrame extends JFrame{

    private final Controller controller;
    private StrategoPanel GamePanel ;
    private GMenu InfoMenu ;
    private JPanel container ;



    /**
     * <b>Constructor</b><br>
     * 	It creates a new Frame for the Game
     * @preconditions none
     * @postconditions Instantiates a new board
     * @type Creator
     * @param controller <i>It is used as a controller reference to bridge the Controller and the UI</i>
     */
    public GameFrame(Controller controller) {

        this.controller = controller ;
        this.setSize(700,500);
        SetUpGame();

    }


    /**
     *	It SetUps the Frame Bar with its Buttons.<br>
     *
     *  The Buttons are : <br>The <b>newGame</b> button that creates a new Game with 4 modes to choose<br>
     *                        The <b>Undo</b> button which fires an Undo Signal in the Controller<br>
     *                        The <b>Redo</b> button which fires a Redo Signal in the Controller<br>
     *
     * These are set up with Lambda expressions
     *
     * @type Mutator
     */
    private void SetUpFrameBar()
    {
        JMenuBar bar = new JMenuBar();

        JButton newGame = new JButton("New Game");
        JButton redo = new JButton("Redo");
        JButton undo = new JButton("Undo");

        newGame.addActionListener((e)->
        {
            int mode = JOptionPane.showOptionDialog(null,"Please choose a mode","Mode Chooser", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,new String[]{"DEFAULT","ALL-IN","HALF ARMY","ALL-IN HALF ARMY"},-1);
            if(mode != -1)
            {
                this.controller.NewGameClicked(mode);
                GamePanel.setHasSelected(false);
            }
            repaint();
        });

        redo.addActionListener((e)->
        {
            this.controller.RedoClicked();
            GamePanel.setHasSelected(false);
            repaint();
        });

        undo.addActionListener((e)->
        {
            this.controller.UndoClicked();
            GamePanel.setHasSelected(false);
            repaint();
        });

        redo.setFocusPainted(false);
        undo.setFocusPainted(false);
        newGame.setFocusPainted(false);

        bar.add(newGame);
        bar.add(undo);
        bar.add(redo);

        this.setJMenuBar(bar);
    }


    /**
     *	It SetUps the Main container with the GMenu and the GameFrame
     *
     * @type Mutator
     */
    private void SetUpContainer()
    {
        container = new JPanel();
        container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));
        container.setBorder(new EmptyBorder(10,10,10,10));
        container.add(Box.createHorizontalGlue());
        container.add(GamePanel);
        container.add(Box.createHorizontalStrut(10));
        container.add(Box.createHorizontalGlue());
        container.add(InfoMenu);
        container.setBackground(new Color(236, 174, 152));
    }

    /**
     *	It SetUps the Winning screen by removing the MenuBar and the Container and<br>
     *	Replacing them with a WinnerPanel and refreshing the GUI (repaints the frame).
     *
     * @type Mutator
     */
    public void ShowWinner(Element element)
    {
        container.setVisible(false);
        this.getJMenuBar().setVisible(false);

        JPanel boxLayer = new JPanel();
        boxLayer.setLayout(new BoxLayout(boxLayer,BoxLayout.X_AXIS));
        WinnerPanel Winner = new WinnerPanel(element, controller, this,boxLayer);

        boxLayer.add(Box.createHorizontalGlue());
        boxLayer.add(Winner);
        boxLayer.add(Box.createHorizontalGlue());

        Color color = element == Element.FIRE ? new Color(207,16,32) : new Color(165,242,243);

        boxLayer.setBackground(color.brighter());

        add(boxLayer);

        boxLayer.setVisible(true);

        repaint();
    }

    /**
     *	It SetUps the Main Game<br> It is used in the Constructor and in the WinnerPanel to set up a new Game
     *
     * @type Mutator
     */
    public void SetUpGame()
    {
        InfoMenu = new GMenu(controller);
        GamePanel = new StrategoPanel(controller);

        try {
            this.setIconImage(ImageIO.read(new File("images/S.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.SetUpFrameBar();
        this.SetUpContainer();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(700,500));
        this.add(container);
        addKeyBinding(this.getRootPane(), "F11", new FullscreenToggleAction(this));
    }



    /**
     *	It adds the option to make a full screen mode in the Frame by pressing F11
     *
     * @type Mutator
     */
    public void addKeyBinding(JComponent c, String key, final Action action) {
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
        c.getActionMap().put(key, action);
        c.setFocusable(true);
    }

}