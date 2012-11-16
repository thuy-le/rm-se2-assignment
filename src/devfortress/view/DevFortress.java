/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colour;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class DevFortress extends JFrame {

    //declare constant variables
    public static final String strImagePath = "images/b7.jpg";
    //declare variables
    ImageIcon background;
    JPanel jpanel;
    JToolBar toolbar;
    DeveloperContainer developerContainer;
    ProjectContainer projectContainer;
    SystemContainer systemContainer;
    JTabbedPane containerTab;

    //constructor
    public DevFortress() {
        init();
    }

    //init method: initialize and arrange the components
    private void init() {
        //set background for JFrame;
        background = new ImageIcon(strImagePath);
        jpanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
                } else {
                    System.out.println("Background null");
                }
            }
        };
        setContentPane(jpanel);
        jpanel.setLayout(new BorderLayout());

        //initialize JToolbar:
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
        JLabel exitGame = new CustomLabel(new ImageIcon("images/exit.png"));
        exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitGame.setToolTipText("Exit Game");
        JLabel newGame = new CustomLabel(new ImageIcon("images/new2.png"));
        newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGame.setToolTipText("New Game");
        JLabel saveGame = new CustomLabel(new ImageIcon("images/save.png"));
        saveGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveGame.setToolTipText("Save Game");
        JLabel aboutGame = new CustomLabel(new ImageIcon("images/about.png"));
        aboutGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutGame.setToolTipText("About Us");
        toolbar.add(newGame);
        toolbar.add(saveGame);
        toolbar.add(aboutGame);
        toolbar.add(exitGame);

        //adjust look and feel
        exitGame.setOpaque(false);
        newGame.setOpaque(false);
        saveGame.setOpaque(false);
        aboutGame.setOpaque(false);

        //initialize JTabbedPane
        containerTab = new JTabbedPane();
        containerTab.setUI(new CustomTabbedPaneUI());
        containerTab.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        containerTab.setOpaque(false);
        //initialize System Container
        systemContainer = new SystemContainer();
        containerTab.add("System", systemContainer);
        //initialize the Developer Container
        developerContainer = new DeveloperContainer();
        containerTab.add("Developer", developerContainer);
        //initialize the Project Container
        projectContainer = new ProjectContainer();
        containerTab.add("Project", projectContainer);
        
        //add tool bar and tab pane to JFrame
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(containerTab, BorderLayout.CENTER);
        
        
        GlassPanel bottomPanel = new GlassPanel(13,0, 752,50,.85f,Colour.DARKBLUE,10,10);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new FlowLayout());
        JLabel dateLabel = new JLabel("16/11/2012");
        dateLabel.setFont(new Font("Century Gothic", Font.BOLD, 27));
        dateLabel.setForeground(Colour.LIGHTBLUE);
        JLabel nextTurn = new CustomLabel(new ImageIcon("images/right.png"));
        nextTurn.setToolTipText("Go to the next turn");        
        nextTurn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bottomPanel.add(dateLabel);
        bottomPanel.add(nextTurn);
        
        
        //pack
        pack();
        //JFrame default config;
        setTitle("❤ DevFortress");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String agrs[]) {
        DevFortress devFortress = new DevFortress();
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 650);
    }
}