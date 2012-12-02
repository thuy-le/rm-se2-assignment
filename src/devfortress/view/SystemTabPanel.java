/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.components.GlassPanel;
import devfortress.utilities.Colors;
import devfortress.utilities.ReadOnlyList;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.Slot;
import devfortress.view.interfaces.SystemTabView;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class SystemTabPanel extends JPanel implements SystemTabView, Observer {

    //initialize constant variables
    static private final String picture = "images/i5.png";
    static private final float alpha = 0.8f;
    static private final Color colour = Colors.MIDDLERED;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //declare private variables
    private JLabel budget, welcome;
    private JPanel subContainer;
    private JPanel leftNav;
    private ReadOnlyList<Developer> developers;
    private List<Slot> slots;
    private int page = 0;
    //constructor

    public SystemTabPanel(DeveloperTabPanel tabDev) {
        setOpaque(false);
        init();
    }
    
    private void createSeats(){
        subContainer.removeAll();
        subContainer.setLayout(new GridLayout(2, 3));
        if (developers != null) {
            for (int i = 0; i < developers.size(); i++) {
                Slot slot = new Slot();
                slot.setDevName(developers.get(i).getName());
                slot.setWorking(true);
                slots.add(slot);
            }
            for (Slot slot : slots) {
                subContainer.add(slot);
            }
            System.out.println("Pages: " + page);
        }
        if(slots.size()>0) {
            leftNav.setVisible(true);
        }
        else{
            leftNav.setVisible(false);
        }
    }

    private void init() {
        slots = new LinkedList<Slot>();
        /*
         * ########### initialize variables ##########
         */
        //$$$$$-----Global variables
        subContainer = new GlassPanel(550,400);
        leftNav = new GlassPanel(50,500);
        budget = new JLabel("$25000000");
        welcome = new JLabel();

        //$$$$$-----Local variable for UI
        Icon imgIcon = new ImageIcon(picture);
        GlassPanel marginTop = new GlassPanel(0, 0, 250, 15, 0f, null, 0, 0);
        GlassPanel gp3 = new GlassPanel(15, 15, 250, 390, .9f, Colors.YELLOW, 15, 15);
        JLabel imageIcon = new JLabel(imgIcon);
        imageIcon.setPreferredSize(new Dimension(200, 200));
        JLabel label = new JLabel("  Your budget is:");
        JLabel arrowUp = new CustomLabel("");
        JLabel arrowDown = new CustomLabel("");
        arrowUp.setIcon(new ImageIcon("images/arrowUp.png"));
        arrowDown.setIcon(new ImageIcon("images/arrowDown.png"));
        /*
         * ########## Adjust look and feel ##########
         */
        welcome.setFont(new Font("Century Gothic", Font.BOLD, 17));
        welcome.setForeground(Colors.DARKBLUE);
        budget.setFont(new Font("Century Gothic", Font.BOLD, 20));
        label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        /*
         * ########## set border layout to the container ##########
         */
        setLayout(new BorderLayout());
        gp3.setLayout(new FlowLayout());
        //-------Add components together
        leftNav.add(arrowUp, BorderLayout.NORTH);
        //leftNav.add(new GlassPanel(0, 200), BorderLayout.CENTER);
        leftNav.add(arrowDown, BorderLayout.SOUTH);
        add(leftNav, BorderLayout.WEST);
        add(subContainer, BorderLayout.CENTER);
        add(gp3, BorderLayout.EAST);
        gp3.add(marginTop, BorderLayout.NORTH);
        gp3.add(welcome);
        gp3.add(imageIcon);
        gp3.add(label);
        gp3.add(budget);
        
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
        g2d.drawRoundRect(x, y, width, height, width, height);
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void setBudget(long budget) {
        this.budget.setText("$" + budget);
    }

    @Override
    public void setPlayerName(String name) {
        String welcomeStr = "<html>Hi, " + name + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        developers = (ReadOnlyList) model.getDevelopers();
        createSeats();
        budget.setText(model.getBudget() + "");
        String welcomeStr = "<html>Hi, " + model.getPlayerName() + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
    }
}
