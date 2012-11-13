/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colour;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author PC
 */
public class SystemContainer extends JPanel {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.middleRed;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 750;
    static private final int height = 420;
    static private final int arcW = 10;
    static private final int arcH = 10;

    //constructor
    public SystemContainer() {
        setOpaque(false);
        init();
    }

    private void init() {
        //set border layout to the container
        setLayout(new BorderLayout());

        //------Project:
        //Create a container for JList
        GlassPanel gp = new GlassPanel(20, 20, 249, 330, 1f, Colour.youngGreen, 10, 10);
        //Initialize items of JList
        String s[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        //create a JList with those items
        JList developerList = new JList(s);
        //create a scrollpane
        JScrollPane scrollPane = new JScrollPane(developerList);
        //adjust look and feel
        developerList.setSelectionBackground(Colour.lightOrange);
        developerList.setSelectionForeground(Colour.orange);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        scrollPane.setPreferredSize(new Dimension(220, 320));
        GlassPanel marginLeft = new GlassPanel(0, 0, 15, 360, 0f, null, 0, 0);
        gp.add(marginLeft, BorderLayout.WEST);//left margin
        //Button: Hire new developer
        GlassPanel bottom = new GlassPanel(0, 0, 250, 40, 0f, null, 0, 0);
        CustomButton btnHire = new CustomButton(0, 0, 150, 35, Colour.youngGreen, "Hire Developer");
        btnHire.setTextColour(Colour.brown);
        btnHire.addMouseListener(new CustomButtonEvent(Colour.youngGreen, Colour.lightGreen));
        //add components
        gp.add(developerList, BorderLayout.CENTER);
        gp.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(developerList);
        bottom.add(btnHire);
        gp.add(bottom, BorderLayout.SOUTH);
        add(gp, BorderLayout.WEST);

        //------Project:
        //Create a container for JList
        GlassPanel gp2 = new GlassPanel(15, 20, 230, 330, 1f, Colour.yellow, 10, 10);
        //Initialize items of JList
        String projects[] = {"Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6", "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12", "Project 13", "Project 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        //create a JList with those items
        JList projectList = new JList(projects);
        //create a scrollpane
        JScrollPane scrollPane2 = new JScrollPane(projectList);
        //adjust look and feel
        projectList.setSelectionBackground(Colour.lightOrange);
        projectList.setSelectionForeground(Colour.orange);
        projectList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        scrollPane2.setPreferredSize(new Dimension(220, 320));
        GlassPanel mLeft = new GlassPanel(0, 0, 0, 360, 0f, null, 0, 0);
        gp2.add(mLeft, BorderLayout.WEST);//left margin
        //Button: Add new Project
        GlassPanel bot = new GlassPanel(0, 0, 250, 40, 0f, null, 0, 0);
        CustomButton btnAdd = new CustomButton(0, 0, 150, 35, Colour.yellow, "Add Project");
        btnAdd.setTextColour(Colour.brown);
        btnAdd.addMouseListener(new CustomButtonEvent(Colour.yellow, Colour.orange));
        //add components
        gp2.add(projectList, BorderLayout.CENTER);
        gp2.add(scrollPane2, BorderLayout.CENTER);
        scrollPane2.getViewport().add(projectList);
        bot.add(btnAdd);
        gp2.add(bot, BorderLayout.SOUTH);
        add(gp2, BorderLayout.CENTER);

        //General Information of Player
        GlassPanel gp3 = new GlassPanel(-15, 20, 250, 390, .9f, Colour.middleRed, 5, 5);
        JLabel welcome = new JLabel("<html>Welcome Michael ❤ <br/>---------<br/> Your budget is: <br/>$25000000</html>");
        welcome.setFont(new Font("Century Gothic", Font.BOLD, 17));
        welcome.setForeground(Color.WHITE);
        GlassPanel marginTop = new GlassPanel(0, 0, 250, 15, 0f, null, 0, 0);
        gp3.add(marginTop, BorderLayout.NORTH);
        gp3.add(welcome, BorderLayout.CENTER);
        add(gp3, BorderLayout.EAST);

    }

    //override the paint component method
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
