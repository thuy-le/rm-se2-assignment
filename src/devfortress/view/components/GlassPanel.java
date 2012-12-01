/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * A custom JPanel which could be transparent.
 * @author Team Poseidon
 */
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
    public GlassPanel(final int width, final int height){
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.alpha = 0f;
        this.colour = null;
        this.arcW = 0;
        this.arcH = 0;
        setOpaque(false);
    }
    public GlassPanel(final int x, final int y, final int width, final int height, final float alpha, final Color colour, final int arcW, final int arcH){
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

