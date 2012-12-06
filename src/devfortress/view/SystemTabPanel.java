/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.components.GlassPanel;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.Slot;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class SystemTabPanel extends JPanel implements Observer {

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
    private List<Developer> developers;
    private LinkedList<Slot> slots;
    private LinkedList<LinkedList<Slot>> subSlots;
    private int numPCs = 0;
    private int page = 0;
    private int currentPage = 0;
    private CustomLabel upArrow;
    private CustomLabel downArrow;
    //constructor

    public SystemTabPanel(DeveloperTabPanel tabDev) {
        setOpaque(false);
        init();
    }

    private void createSeats() {
        int slotCount = 0;
        subContainer.removeAll();
        slots.clear();
        subSlots.clear();
        subContainer.setLayout(new GridLayout(2, 3));
        //creating slots
        for (int i = 0; i < developers.size(); i++) {
            Slot slot = new Slot();
            slot.setDevName(developers.get(i).getName());
            slot.setWorking(true);
            slots.add(slot);
        }
        int pcs = numPCs - developers.size();
        for (int i = 0; i < pcs; i++) {
            Slot slot = new Slot();
            slot.setWorking(false);
            slots.add(slot);
        }
        //count number of pages
        if (slots.size() > 0) {
            page = (int) Math.ceil((double) slots.size() / 6);
        }
        if (page > 1) {
            leftNav.setVisible(true);
        } else {
            leftNav.setVisible(false);
        }
        //divide slots into sub slots
        for (int j = 0; j < page; j++) {
            LinkedList<Slot> sl = new LinkedList<Slot>();
            for (int i = 0; i < 6 && i < slots.size(); i++) {
                try {
                    sl.add(slots.get(slotCount++));
                } catch (Exception ex) {
                }
            }
            subSlots.add(sl);
        }
        if (subSlots != null) {
            LinkedList<Slot> sl = new LinkedList<Slot>();
            try {
                sl = subSlots.get(currentPage);
                for (int j = 0; j < sl.size(); j++) {
                    subContainer.add(sl.get(j));
                }
            } catch (Exception ex) {
            }

            System.out.println("Pages: " + page);
        }

    }

    private void init() {
        slots = new LinkedList<Slot>();
        subSlots = new LinkedList<LinkedList<Slot>>();
        /*
         * ########### initialize variables ##########
         */
        //$$$$$-----Global variables
        subContainer = new GlassPanel(550, 400);
        leftNav = new GlassPanel(50, 500);
        budget = new JLabel("$25000000");
        welcome = new JLabel();

        //$$$$$-----Local variable for UI
        Icon imgIcon = new ImageIcon(picture);
        GlassPanel marginTop = new GlassPanel(0, 0, 250, 15, 0f, null, 0, 0);
        GlassPanel gp3 = new GlassPanel(15, 15, 250, 390, .9f, Colors.YELLOW, 15, 15);
        JLabel imageIcon = new JLabel(imgIcon);
        imageIcon.setPreferredSize(new Dimension(200, 200));
        JLabel label = new JLabel("  Your budget is:");
        upArrow = new CustomLabel("");
        downArrow = new CustomLabel("");
        upArrow.setIcon(new ImageIcon("images/arrowUp.png"));
        downArrow.setIcon(new ImageIcon("images/arrowDown.png"));
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
        leftNav.add(upArrow, BorderLayout.NORTH);
        leftNav.add(downArrow, BorderLayout.SOUTH);
        add(leftNav, BorderLayout.WEST);
        add(subContainer, BorderLayout.CENTER);
        add(gp3, BorderLayout.EAST);
        gp3.add(marginTop, BorderLayout.NORTH);
        gp3.add(welcome);
        gp3.add(imageIcon);
        gp3.add(label);
        gp3.add(budget);

        //
        setUpArrowListener();
        setDownArrowListener();

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

    public void setBudget(long budget) {
        this.budget.setText("$" + budget);
    }

    public void setPlayerName(String name) {
        String welcomeStr = "<html>Hi, " + name + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPage() {
        return page;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        developers = model.getDevelopers();
        numPCs = model.getNumPCs();
        createSeats();
        budget.setText(model.getBudget() + "");
        String welcomeStr = "<html>Hi, " + model.getPlayerName() + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
    }

    public void setUpArrowListener() {
        upArrow.addMouseListener(new UpArrowNavigator());
    }

    public void setDownArrowListener() {
        downArrow.addMouseListener(new DownArrowNavigator());
    }

    public void refresh() {
        repaint();
    }

    private class UpArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                currentPage--;
                createSeats();
                subContainer.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                upArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                upArrow.setIcon(new ImageIcon("images/arrowUpOnMouse.png"));
                upArrow.repaint();
            } else {
                upArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            upArrow.setIcon(new ImageIcon("images/arrowUp.png"));
            upArrow.repaint();
        }
    }

    private class DownArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                currentPage++;
                createSeats();
                subContainer.repaint();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                downArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                downArrow.setIcon(new ImageIcon("images/arrowDownOnMouse.png"));
                downArrow.repaint();
            } else {
                downArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            downArrow.setIcon(new ImageIcon("images/arrowDown.png"));
            downArrow.repaint();
        }
    }
}
