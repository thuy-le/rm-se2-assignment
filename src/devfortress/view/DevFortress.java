/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.InsetsUIResource;

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
    JMenuBar menuBar;
    JMenu fileMenu, devMenu, prjMenu, aboutMenu;
    JMenuItem newMI, exitMI, viewAllDevMI, viewAllPrjMI;
    GlassPanel systemTab;
    GlassPanel developerTab;
    GlassPanel projectTab;
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
        
        //JFrame default config;
        jpanel.setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //initialize JMenu:
        menuBar = new JMenuBar();
        
        //customize look and feel
        menuBar.setUI(new CustomMenuBarUI());
        menuBar.setBorderPainted(true);
        menuBar.setBorder(BorderFactory.createLineBorder(MyColor.blueGrey, 1, true));
        UIManager.put("MenuBar.font", new Font("Century Gothic", Font.BOLD, 16));
        UIManager.put("Menu.font", new Font("Century Gothic", Font.BOLD, 16));
        UIManager.put("MenuItem.font", new Font("Century Gothic", Font.PLAIN, 15));
        UIManager.put("Menu.foreground", MyColor.lightBlue1);
        UIManager.put("Menu.selectionBackground", MyColor.lightBlue1);
        UIManager.put("Menu.selectionForeground", MyColor.darkBlue2);
        menuBar.setPreferredSize(new Dimension(800, 38));
        JMenu blank = new JMenu("");
        blank.setEnabled(false);
        blank.setPreferredSize(new Dimension(15, 0));
        menuBar.add(blank);

        //add items to JMenu
        fileMenu = new JMenu("File");
        newMI = new JMenuItem("New");
        exitMI = new JMenuItem("Exit");
        fileMenu.add(newMI);
        fileMenu.add(exitMI);
        menuBar.add(fileMenu);
        devMenu = new JMenu("Developer");
        viewAllDevMI = new JMenuItem("View All Developers");
        devMenu.add(viewAllDevMI);
        menuBar.add(devMenu);
        prjMenu = new JMenu("Project");
        viewAllPrjMI = new JMenuItem("View All Projects");
        prjMenu.add(viewAllPrjMI);
        menuBar.add(prjMenu);
        aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        //initialize JTabbedPane
        containerTab = new JTabbedPane();
        containerTab.setUI(new CustomTabbedPaneUI());
        containerTab.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        containerTab.setOpaque(false);

        //initialize System Container
        systemTab = new GlassPanel(0, 5, 750, 420, 0.8f, MyColor.middleRed, 10, 10);
        //add System Container to JTabbedPane
        containerTab.add("System", systemTab);
        //initialize the Developer Container
        DeveloperContainer devTab = new DeveloperContainer();
        //add Developer Container to JTabbedPane
        containerTab.add("Developer", devTab);
        //initialize the Project Container
        projectTab = new GlassPanel(0, 5, 750, 420, 0.8f, MyColor.youngGreen, 10, 10);        
        //Add project Container to JTabbedPane
        containerTab.add("Project", projectTab);

        //add menu bar and tab pane to JFrame
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(containerTab, BorderLayout.CENTER);

        //pack
        pack();
    }

    public static void main(String agrs[]) {
        new DevFortress();
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}