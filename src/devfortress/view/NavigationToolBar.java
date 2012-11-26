/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.components.CustomLabel;
import devfortress.utilities.Colors;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class NavigationToolBar extends JToolBar {

    private CustomLabel exitGame, newGame, aboutGame, saveGame;

    public NavigationToolBar() {
        setBorder(BorderFactory.createEmptyBorder());
        exitGame = new CustomLabel(new ImageIcon("images/exit.png"), "Exit Game");
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame = new CustomLabel(new ImageIcon("images/new2.png"), "New Game");
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame = new CustomLabel(new ImageIcon("images/save.png"), "Save Game");
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame = new CustomLabel(new ImageIcon("images/about.png"), "About Us");
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        aboutGame.setOpaque(false);
        //add components
        add(newGame);
        add(saveGame);
        add(aboutGame);
        add(exitGame);
    }

    public void addExitGameListener(MouseListener l) {
        exitGame.addMouseListener(l);
    }

    public void addNewGameListener(MouseListener l) {
        newGame.addMouseListener(l);
    }

    public void addAboutGameListener(MouseListener l) {
        aboutGame.addMouseListener(l);
    }

    public void addSaveGameListener(MouseListener l) {
        saveGame.addMouseListener(l);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, Colors.DARKBLUE2, 0, getHeight(), Colors.DARKBLUE));
        g2.fillRect(0, 0, getWidth(), 55);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), 55);
    }
}