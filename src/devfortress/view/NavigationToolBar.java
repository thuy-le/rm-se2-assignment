/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.view.components.CustomLabel;
import devfortress.utilities.Colors;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class NavigationToolBar extends JToolBar implements Observer {

    private JLabel exitGame, newGame, aboutGame, saveGame, loadGame, setting, budget;
    private JPanel budgetPanel;

    public NavigationToolBar() {
        setBorder(BorderFactory.createEmptyBorder());
        exitGame = new CustomLabel(new ImageIcon("images/exit.png"), "Exit Game");
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame = new CustomLabel(new ImageIcon("images/new2.png"), "New Game");
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame = new CustomLabel(new ImageIcon("images/save.png"), "Save Game");
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadGame = new CustomLabel(new ImageIcon("images/open.png"), "Load Game");
        loadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame = new CustomLabel(new ImageIcon("images/about.png"), "About Us");
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setting = new CustomLabel(new ImageIcon("images/setting.png"), "Setting");
        setting.setCursor(new Cursor(Cursor.HAND_CURSOR));
        budget = new JLabel();
        budgetPanel = new JPanel();
        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        loadGame.setOpaque(false);
        aboutGame.setOpaque(false);
        setting.setOpaque(false);
        budget.setForeground(Color.WHITE);
        budget.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        //add components
        newGame.setBounds(10, 10, newGame.getWidth(), newGame.getHeight());

        add(newGame);
        add(saveGame);
        add(loadGame);
        add(aboutGame);
        add(setting);
        add(exitGame);
        add(budgetPanel);
        budgetPanel.setOpaque(false);
        budgetPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        budgetPanel.add(budget);
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

    public void addSettingListener(MouseListener l) {
        setting.addMouseListener(l);
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

    @Override
    public void update(Observable o, Object arg) {
        budget.setText("<html>Budget: <b>$" + +((GameEngine) o).getBudget() + "</b></html>");
    }
}