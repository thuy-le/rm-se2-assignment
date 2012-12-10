/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class CustomButton extends JPanel {

    //declare constant variables 
    private static final int ARCW = 5;
    private static final int ARCH = 5;
    private static final int BTN_WIDTH = 150;
    private static final int BTN_HEIGHT = 35;
    //declare variables
    private int x;
    private int y;
    private int width;
    private int height;
    private int arcH;
    private int arcW;
    private float alpha;
    private Color colour;
    private Color textColour;
    private Color onMouseColor;
    private String text;
    private boolean isActive;
    private Font font;

    //constructor
    public CustomButton(String text) {
        this.x = 0;
        this.y = 0;
        this.width = BTN_WIDTH;
        this.height = BTN_HEIGHT;
        this.colour = Colors.DARKBLUE;
        this.text = text;
        this.textColour = Colors.LIGHTBLUE;
        this.onMouseColor = Colors.DARKBLUE2;
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.isActive = true;
        this.alpha = 1f;
        this.arcH = ARCH;
        this.arcW = ARCW;
        this.font = new Font("Century Gothic", Font.BOLD, 17);
        addMouseListener(new CustomButtonEvent(colour, onMouseColor));
    }

    //accessors and mutators
    public void setAlpha(float a){
        this.alpha = a;
        repaint();
    }
    
    public void setCustomFont(Font f){
        this.font = f;
        repaint();
    }
    
    public void setColour(Color colour) {
        this.colour = colour;
        repaint();
    }

    public void setOnMouseColor(Color onMouseColor) {
        addMouseListener(new CustomButtonEvent(colour, onMouseColor));
        this.onMouseColor = onMouseColor;
        repaint();
    }

    public void setTextColour(Color c) {
        this.textColour = c;
        repaint();
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }
    
    public void setArc(int arc){
        this.arcH = arc;
        repaint();
    }

    /**
     * Set position and size of the button.
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void setButtonSize(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        repaint();
    }

    public void disableButton() {
        this.isActive = false;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }

    public void enableButton() {
        this.isActive = true;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        repaint();
    }

    public boolean isActive() {
        return isActive;
    }

    //override the paintComponent method --> draw the button
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            if (isActive) {
                g2d.setColor(colour);
            } else {
                g2d.setColor(Colors.MIDDLEGREY);
            }
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
        g.setFont(font);
        if (isActive) {
            g.setColor(textColour);
        } else {
            g.setColor(Colors.LIGHTGREY);
        }
        FontMetrics fm = g.getFontMetrics();
        int fx = (width + x*2 - fm.stringWidth(text)) / 2;
        int fy = (fm.getAscent() + (height + y - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(text, fx, fy);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    private class CustomButtonEvent extends MouseAdapter {

        private Color colour;
        private Color onMouseColour;
        private CustomButton btn;

        public CustomButtonEvent(Color oldColour, Color newColour) {
            this.onMouseColour = oldColour;
            this.colour = newColour;
            this.btn = CustomButton.this;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (btn.isActive()) {
                btn.setColour(colour);
                btn.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (btn.isActive()) {
                btn.setColour(onMouseColour);
                btn.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (btn.isActive()) {
                btn.setColour(onMouseColour);
                btn.repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
}
