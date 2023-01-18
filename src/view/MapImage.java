package view;

import model.element.Element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * A class that will be used to create a Map of powers and Images for each Player
 *
 * @author gtheo
 */
public class MapImage extends HashMap<Integer, Image>
{
    /**
     * <b>Constructor</b><br>
     *
     * @postconditions Creates a new Map of the Powers and the Images
     * @type creator
     * @param element ,The element of the Images we Want to put in the Map
     */
    public MapImage(Element element) {


        try {
            if(element == Element.FIRE)
            {
                this.put(-2, ImageIO.read(new File("images/Fire/redHidden.png")));
                this.put(-1, ImageIO.read(new File("images/Fire/flagR.png")));
                this.put(0, ImageIO.read(new File("images/Fire/trapR.png")));
                this.put(1, ImageIO.read(new File("images/Fire/slayerR.png")));
                this.put(2, ImageIO.read(new File("images/Fire/scoutB.png")));
                this.put(3, ImageIO.read(new File("images/Fire/dwarfR.png")));
                this.put(4, ImageIO.read(new File("images/Fire/elfR.png")));
                this.put(5, ImageIO.read(new File("images/Fire/lavaBeast.png")));
                this.put(6, ImageIO.read(new File("images/Fire/sorceressR.png")));
                this.put(7, ImageIO.read(new File("images/Fire/BeastRiderR.png")));
                this.put(8, ImageIO.read(new File("images/Fire/knightR.png")));
                this.put(9, ImageIO.read(new File("images/Fire/mageR.png")));
                this.put(10, ImageIO.read(new File("images/Fire/dragonR.png")));
            }
            else
            {
                this.put(-2 ,ImageIO.read(new File("images/Ice/blueHidden.png")));
                this.put(-1, ImageIO.read(new File("images/Ice/flagB.png")));
                this.put(0, ImageIO.read(new File("images/Ice/trapB.png")));
                this.put(1, ImageIO.read(new File("images/Ice/slayerB.png")));
                this.put(2, ImageIO.read(new File("images/Ice/scoutB.png")));
                this.put(3, ImageIO.read(new File("images/Ice/dwarfB.png")));
                this.put(4, ImageIO.read(new File("images/Ice/elfB.png")));
                this.put(5, ImageIO.read(new File("images/Ice/yeti.png")));
                this.put(6, ImageIO.read(new File("images/Ice/sorceressB.png")));
                this.put(7, ImageIO.read(new File("images/Ice/BeastRiderB.png")));
                this.put(8, ImageIO.read(new File("images/Ice/knightB.png")));
                this.put(9, ImageIO.read(new File("images/Ice/mageB.png")));
                this.put(10, ImageIO.read(new File("images/Ice/dragonB.png")));
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}