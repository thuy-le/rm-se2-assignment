/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

/**
 *
 * @author PC
 */
public class DevFortress extends JFrame {

    public static final String strImagePath = "images/b2.jpg";
    ImageIcon background;
    JPanel jpanel;
    JMenuBar menuBar;
    JMenu fileMenu, devMenu, prjMenu, aboutMenu;
    JMenuItem newMI, exitMI, viewAllDevMI, viewAllPrjMI;
    GlassPanel systemTab;
    GlassPanel developerTab;
    GlassPanel projectTab;
    JTabbedPane containerTab;

    public DevFortress() {
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

        //initialize JMenu:
        menuBar = new JMenuBar();
        Font f = new Font("Century Gothic", Font.BOLD, 16);
        Font f2 = new Font("Century Gothic", Font.PLAIN, 15);
        menuBar.setUI(new CustomMenuBarUI());
        UIManager.put("Menu.margin", new InsetsUIResource(0, 20, 0, 0));
        System.out.println(menuBar.getMargin());
        UIManager.put("MenuBar.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("Menu.foreground", MyColor.lightBlue1);
        UIManager.put("MenuItem.font", f2);
        UIManager.put("Menu.selectionBackground", MyColor.lightBlue1);
        UIManager.put("Menu.selectionForeground", MyColor.darkBlue2);
        
        menuBar.setPreferredSize(new Dimension(800, 38));
        JMenu blank = new JMenu("");
        blank.setEnabled(false);
        blank.setPreferredSize(new Dimension(15,0));
        menuBar.add(blank);
        
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

        systemTab = new GlassPanel(0, 5, 750, 420, 0.8f, MyColor.middleRed, 10, 10);

        developerTab = new GlassPanel(0, 5, 750, 420, 0.8f, MyColor.orange, 10, 10);

        projectTab = new GlassPanel(0, 5, 750, 420, 0.8f, MyColor.youngGreen, 10, 10);

        containerTab.add("System", systemTab);
        containerTab.add("Developer", developerTab);
        containerTab.add("Project", projectTab);

        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(containerTab, BorderLayout.CENTER);


    }

    public static void main(String agrs[]) {
        new DevFortress();
    }
}

class FillPainter implements Painter<JComponent> {

    private final Color color;

    FillPainter(Color c) {
        color = c;
    }

    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
        g.setColor(color);
        g.fillRect(0, 0, width - 1, height - 1);
    }
}