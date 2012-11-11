/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class CustomButton extends JPanel {

    //declare constant variables 
    private static final int arcW = 5;
    private static final int arcH = 5;
    private static final Font f = new Font("Century Gothic", Font.BOLD, 17);
    
    //declare variables
    private int x;
    private int y;
    private int width;
    private int height;
    private Color colour;
    private String text;

    //constructor
    public CustomButton(final int x, final int y, final int width, final int height, final Color colour, final String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.text = text;
    }

    //accessors and mutators
    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    //override the paintComponent method --> draw the button
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
        g.setFont(f);
        g.setColor(new Color(255, 255, 255));
        FontMetrics fm = g.getFontMetrics();
        int fx = (width - fm.stringWidth(text)) / 2;
        int fy = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(text, fx, fy);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}

//button's custom events
class CustomButtonEvent extends MouseAdapter {

    private Color oldColour;
    private Color newColour;

    public CustomButtonEvent(final Color oldColour, final Color newColour) {
        this.oldColour = oldColour;
        this.newColour = newColour;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (e.getSource() instanceof CustomButton) {
            CustomButton cb = (CustomButton) e.getSource();
            cb.setColour(newColour);
            cb.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (e.getSource() instanceof CustomButton) {
            CustomButton cb = (CustomButton) e.getSource();
            cb.setColour(oldColour);
            cb.repaint();
        }
    }
}