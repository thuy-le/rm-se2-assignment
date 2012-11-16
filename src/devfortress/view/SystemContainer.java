/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.Project;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.SystemInterface;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class SystemContainer extends JPanel implements SystemInterface{

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.MIDDLERED;
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
        String playerName = "Michael";
        //set border layout to the container
        setLayout(new BorderLayout());

        //-------Create a JList of developers
        String s[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
            //list
        JList devList = new JList(s);
            //buttons
        List<CustomButton> listDevBtn = new LinkedList<>();
        CustomButton btnHire = new CustomButton("Hire Developer");
        listDevBtn.add(btnHire);
            //add list and button(s) together
        CustomList developerList = new CustomList(devList, listDevBtn);
  
        //-------Create a JList of project
            //list
        String projects[] = {"Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6", "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12", "Project 13", "Project 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        JList prjList = new JList(projects);
            //buttons
        List<CustomButton> listPrjBtn = new LinkedList<>();
        CustomButton btnAdd = new CustomButton("Add Project");
        listPrjBtn.add(btnAdd);
            //add list and button(s) together
        CustomList projectList = new CustomList(prjList, listPrjBtn);
        
        //-------General Information of Player
        GlassPanel gp3 = new GlassPanel(15, 15, 250, 390, .9f, Colour.YELLOW, 15, 15);
        gp3.setLayout(new FlowLayout());
        String welcomeStr = "<html>Hi, " + playerName + " ◕‿◕</html>";
        String picture = "images/i5.png";
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        imageIcon.setPreferredSize(new Dimension(200,200));
        JLabel welcome = new JLabel(welcomeStr);
        JLabel label = new JLabel("  Your budget is:");
        //label.setPreferredSize(new Dimension(200,30));
        JLabel budget = new JLabel("$25000000");
        //-------Adjust look and feel
        Font font = new Font("Century Gothic", Font.PLAIN, 16);
        developerList.setColor(Colour.DARKBLUE);
        devList.setFont(font);
        devList.setSelectionBackground(Colour.LIGHTRED);
        devList.setSelectionForeground(Colour.DARKPINK);
        btnHire.setColour(Colour.DARKBLUE);
        btnHire.setTextColour(Colour.LIGHTBLUE);
        btnHire.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        projectList.setColor(Colour.DARKBLUE);
        prjList.setFont(font);
        prjList.setSelectionBackground(Colour.LIGHTRED);
        prjList.setSelectionForeground(Colour.DARKPINK);
        btnAdd.setColour(Colour.DARKBLUE);
        btnAdd.setTextColour(Colour.LIGHTBLUE);
        btnAdd.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        welcome.setFont(new Font("Century Gothic", Font.BOLD, 17));
        welcome.setForeground(Colour.DARKBLUE);
        budget.setFont(new Font("Century Gothic", Font.BOLD, 20));
        label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        GlassPanel marginTop = new GlassPanel(0, 0, 250, 15, 0f, null, 0, 0);
        gp3.add(marginTop, BorderLayout.NORTH);
        //-------Add components together
        add(developerList, BorderLayout.WEST);
        add(projectList, BorderLayout.CENTER);
        gp3.add(welcome);
        gp3.add(imageIcon);
        gp3.add(label);
        gp3.add(budget);
        
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

    @Override
    public void viewDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void viewProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hireNewDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNewProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
