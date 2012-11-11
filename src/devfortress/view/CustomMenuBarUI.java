/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author PC
 */
public class CustomMenuBarUI extends BasicMenuBarUI {

    //change background color of the menu bar
    @Override
    public void paint(Graphics g, JComponent c) {
        g.setColor(new Color(7, 53, 170));
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
