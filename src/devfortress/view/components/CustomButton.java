/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colour;
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
    private static final int arcW = 5;
    private static final int arcH = 5;
    private static final int BTN_WIDTH = 150;
    private static final int BTN_HEIGHT = 35;
    private static final Font f = new Font("Century Gothic", Font.BOLD, 17);
    //declare variables
    private int x;
    private int y;
    private int width;
    private int height;
    private Color colour;
    private Color textColour;
    private Color onMouseColor;
    private String text;
    private boolean isActive;

    //constructor
    public CustomButton(String text) {
        this.x = 0;
        this.y = 0;
        this.width = BTN_WIDTH;
        this.height = BTN_HEIGHT;
        this.colour = Colour.DARKBLUE;
        this.text = text;
        this.textColour = Colour.LIGHTBLUE;
        this.onMouseColor = Colour.DARKBLUE2;
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.isActive = true;
        addMouseListener(new CustomButtonEvent(colour, onMouseColor));
    }

    //accessors and mutators
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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (colour != null) {
            if (isActive) {
                g2d.setColor(colour);
            } else {
                g2d.setColor(Colour.MIDDLEGREY);
            }
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
        g.setFont(f);
        if (isActive) {
            g.setColor(textColour);
        } else {
            g.setColor(Colour.LIGHTGREY);
        }
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

    private class CustomButtonEvent extends MouseAdapter {

        private Color colour;
        private Color onMouseColour;
        private CustomButton btn;

        public CustomButtonEvent(Color oldColour, Color newColour) {;
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
