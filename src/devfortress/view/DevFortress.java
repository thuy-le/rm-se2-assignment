/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.interfaces.WelcomeScreenInterface;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class DevFortress extends JFrame implements WelcomeScreenInterface {

    //declare constant variables
    public static final String strImagePath = "images/b7.jpg";
    //declare variables
    private ImageIcon background;
    private JPanel contentPane;

    //constructor
    /** Quan's Constructor **/
    public DevFortress(WelcomeScreen welCm, NavigationPane navPne, InfomationPane infoPne) {
        //initialization
        background = new ImageIcon(strImagePath);
        contentPane = new CustomJPanel();
        //Content Pane default config;
        contentPane.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setContentPane(contentPane);
        getContentPane().add(welCm, BorderLayout.CENTER);
        getContentPane().add(navPne.getToolbar(), BorderLayout.NORTH);
        getContentPane().add(infoPne.getInfoPanel(), BorderLayout.SOUTH);
        infoPne.getInfoPanel().setVisible(false);
        navPne.getToolbar().setVisible(false);
        setTitle("❤DevFortress");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private DevFortress() {
        //set background for JFrame;
        background = new ImageIcon(strImagePath);
        contentPane = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout()); //JFrame default config;

    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    private class CustomJPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        }
    };
}