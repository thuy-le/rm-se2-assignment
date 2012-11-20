/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.CustomLabel;
import devfortress.utilities.Colour;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class NavigationPane {

    private JToolBar toolbar;
    private CustomLabel exitGame, newGame, aboutGame, saveGame;

    public NavigationPane() {
        toolbar = new CustomJToolBar();
        toolbar.setBorder(BorderFactory.createEmptyBorder());
        exitGame = new CustomLabel(new ImageIcon("images/exit.png"));
        exitGame.setLabelName("exit");
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitGame.setToolTipText("Exit Game");

        newGame = new CustomLabel(new ImageIcon("images/new2.png"));
        newGame.setLabelName("new");
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame.setToolTipText("New Game");

        saveGame = new CustomLabel(new ImageIcon("images/save.png"));
        saveGame.setLabelName("save");
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame.setToolTipText("Save Game");

        aboutGame = new CustomLabel(new ImageIcon("images/about.png"));
        aboutGame.setLabelName("about");
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame.setToolTipText("About Us");

        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        aboutGame.setOpaque(false);
        //add components
        toolbar.add(newGame);
        toolbar.add(saveGame);
        toolbar.add(aboutGame);
        toolbar.add(exitGame);
    }

    public JToolBar getToolbar() {
        return toolbar;
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
}

class CustomJToolBar extends JToolBar {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, Colour.DARKBLUE2, 0, getHeight(), Colour.DARKBLUE));
        g2.fillRect(0, 0, getWidth(), 55);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), 55);
    }
};