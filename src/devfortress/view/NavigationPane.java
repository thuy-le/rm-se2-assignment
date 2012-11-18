/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.CustomLabel;
import devfortress.utilities.Colour;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class NavigationPane {

    private JToolBar toolbar;
    private static volatile NavigationPane instance = null;

    private NavigationPane() {        
        init();
    }

    public static NavigationPane getInstance() {
        if (instance == null) {
            synchronized (NavigationPane.class) {
                if (instance == null) {
                    instance = new NavigationPane();
                }
            }
        }
        return instance;
    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    private void init() {
        toolbar = new JToolBar() {
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
        toolbar.setBorder(BorderFactory.createEmptyBorder());
        CustomLabel exitGame = new CustomLabel(new ImageIcon("images/exit.png"));
        exitGame.setLabelName("exit");
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitGame.setToolTipText("Exit Game");
        
        CustomLabel newGame = new CustomLabel(new ImageIcon("images/new2.png"));
        newGame.setLabelName("new");
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame.setToolTipText("New Game");
        
        CustomLabel saveGame = new CustomLabel(new ImageIcon("images/save.png"));
        saveGame.setLabelName("save");
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame.setToolTipText("Save Game");
        
        CustomLabel aboutGame = new CustomLabel(new ImageIcon("images/about.png"));
        aboutGame.setLabelName("about");
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame.setToolTipText("About Us");
        
        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        aboutGame.setOpaque(false);
        //add action events
        exitGame.addMouseListener(new GameController());
        newGame.addMouseListener(new GameController());
        saveGame.addMouseListener(new GameController());
        aboutGame.addMouseListener(new GameController());
        
        //add components
        toolbar.add(newGame);
        toolbar.add(saveGame);
        toolbar.add(aboutGame);
        toolbar.add(exitGame);
    }
}
