/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.interfaces.WelcomePanelView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class DevFortressMainFrame extends JFrame implements WelcomePanelView {

    public static final String BG_IMAGE_PATH = "images/b7.jpg";
    private ImageIcon background;
    private JPanel contentPane;

    //constructor
    public DevFortressMainFrame(WelcomePanel welCm, NavigationToolBar navBar, InfomationPanel infoPnl) {
        //initialization
        background = new ImageIcon(BG_IMAGE_PATH);
        contentPane = new CustomJPanel();
        //Content Pane default config;
        setContentPane(contentPane);
        //GroupLayout layout = new GroupLayout(getContentPane());
        //this.getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                .addComponent(navBar))
//                );
        contentPane.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        getContentPane().add(welCm.getContainer(), BorderLayout.CENTER);
        getContentPane().add(navBar, BorderLayout.NORTH);
        getContentPane().add(infoPnl, BorderLayout.SOUTH);
        infoPnl.setVisible(false);
        navBar.setVisible(false);
        setTitle("❤DevFortress");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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