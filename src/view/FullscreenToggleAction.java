package view;

import javax.swing.*;
import java.awt.event.ActionEvent;



/**
 * AbstractAction to make frame fullscreen or windowed
 *
 * @author gtheo
 */
class FullscreenToggleAction extends AbstractAction {

    private JFrame frame;

    /**
     * <b>Constructor</b><br>
     *
     * Instantiates a new Abstract Action.
     *
     * @preconditions none
     * @postconditions It stores a reference if the frame to use if an action is performed
     * @type Creator
     * @param frame <i>It is used to be resized if an action is Performed</i>
     */
    public FullscreenToggleAction (JFrame frame) {
        this.frame = frame ;
    }


    /**
     * <b>Action to make window Fullscreen</b><br>
     *
     * @preconditions none
     * @postconditions if the frame is at full screen mode, it becomes a Window and via versa.
     * @type Mutator
     * @param e <i>ActionEvent</i>
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        frame.dispose();

        if (!frame.isUndecorated()) {

            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        }else
        {
            frame.setUndecorated(false);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        frame.setVisible(true);
        frame.repaint();

    }
}