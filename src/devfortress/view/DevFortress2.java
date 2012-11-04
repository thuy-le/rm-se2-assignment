/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class DevFortress2 extends JFrame {

    public static final String strImagePath = "images/b2.jpg";
    ImageIcon background;
    JPanel jpanel;
    GlassPanel systemTab;
    JPanel developerTab;
    JPanel projectTab;
    JTabbedPane containerTab;
    
    public DevFortress2(){
        init();
    }

    private void init() {
        //set background for JFrame;
        background = new ImageIcon(strImagePath);
        setSize(800, 600);
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
        //JFrame default config;
        jpanel.setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //initialize JTabbedPane
        containerTab = new JTabbedPane();
        containerTab.setUI(new CustomTabbedPaneUI());
        containerTab.setSize(750,550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        containerTab.setOpaque(false);
        
        systemTab = new GlassPanel(0,0,750,550,0.5f,MyColor.greenBlue);
        
        developerTab = new JPanel();
        developerTab.setSize(750,550);
        projectTab = new JPanel();
        projectTab.setSize(750,550);
        
        containerTab.add("System", systemTab);
        containerTab.add("Developer", developerTab);
        containerTab.add("Project", projectTab);
        
        getContentPane().add(containerTab, BorderLayout.CENTER);
        
    }
    
    public static void main(String agrs[]){
        new DevFortress2();
    }
}
