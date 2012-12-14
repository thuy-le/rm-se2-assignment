/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.view.components.CustomLabel;
import devfortress.utilities.Colors;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class NavigationToolBar extends JToolBar implements Observer {

    private JLabel exitGame, newGame, aboutGame, saveGame, loadGame, seperator, budget;

    public NavigationToolBar() {
        setBorder(BorderFactory.createEmptyBorder());
        exitGame = new CustomLabel(new ImageIcon("images/exit.png"), "Exit Game");
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame = new CustomLabel(new ImageIcon("images/new2.png"), "New Game");
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame = new CustomLabel(new ImageIcon("images/save.png"), "Save Game");
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadGame = new CustomLabel(new ImageIcon("images/save.png"), "Load Game");
        loadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame = new CustomLabel(new ImageIcon("images/about.png"), "About Us");
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seperator = new JLabel(new ImageIcon(generateSeperator()));
        budget = new JLabel("");
        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        loadGame.setOpaque(false);
        aboutGame.setOpaque(false);
        seperator.setSize(new Dimension(200, 55));
        budget.setForeground(Color.WHITE);
        budget.setFont(new Font("Century Gothic", Font.PLAIN, 22));
        //add components
        add(newGame);
        add(saveGame);
        add(loadGame);
        add(aboutGame);
        add(exitGame);
        add(seperator);
        add(budget);
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

    public void addLoadGameListener(MouseListener l) {
        loadGame.addMouseListener(l);
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

    public Image generateSeperator() {
        BufferedImage bi = new BufferedImage(350, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        g2d.setPaint(Colors.DARKBLUE);
        g2d.fillRect(0, 0, 350, 50);
        g2d.dispose();
        return bi;
    }

    @Override
    public void update(Observable o, Object arg) {
        budget.setText("<html>Budget: <b>$" + +((GameEngine) o).getBudget() + "</b></html>");
    }
}