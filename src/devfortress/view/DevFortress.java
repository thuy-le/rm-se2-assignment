/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.GlassPanel;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class DevFortress extends JFrame {

    //declare constant variables
    public static final String strImagePath = "images/b7.jpg";
    //declare variables
    private ImageIcon background;
    private JPanel jpanel;
    private static volatile DevFortress instance = null;
    //constructor

    private DevFortress() {
        init();
    }

    public static DevFortress getInstance() {
        if (instance == null) {
            synchronized (DevFortress.class) {
                if (instance == null) {
                    instance = new DevFortress();
                }
            }
        }
        return instance;
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
    }

    public static void main(String agrs[]) {
        DevFortress df = DevFortress.getInstance();
        //JFrame default config;
        df.setLayout(new BorderLayout());
        df.getContentPane().add(WelcomeScreen.getInstance(), BorderLayout.CENTER);
        df.getContentPane().add(NavigationPane.getInstance().getToolbar(), BorderLayout.NORTH);
        NavigationPane.getInstance().getToolbar().setVisible(false);
        df.getContentPane().add(InfomationPane.getInstance().getInfoPanel(), BorderLayout.SOUTH);
        InfomationPane.getInstance().getInfoPanel().setVisible(false);
        df.setTitle("❤ DevFortress");
        df.setVisible(true);
        df.setDefaultCloseOperation(EXIT_ON_CLOSE);
        df.setResizable(false);
        df.pack();
        df.setLocationRelativeTo(null);
        
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}