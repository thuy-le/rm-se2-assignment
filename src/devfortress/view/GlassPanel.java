/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */

//Create a class which could be transparent
public class GlassPanel extends JPanel {
    //declare variables
    public int x;
    public int y;
    public int width;
    public int height;
    public float alpha;
    public Color colour;
    public int arcW;
    public int arcH;
    //constructor
    GlassPanel(final int x, final int y, final int width, final int height, final float alpha, final Color colour, final int arcW, final int arcH){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.colour = colour;
        this.arcW = arcW;
        this.arcH = arcH;
        setOpaque(false);
    }
    //override paintComponent method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if(colour!=null)
        g2d.setColor(colour);
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }
    //override getPreferredSize method
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(width, height);
    }
}

