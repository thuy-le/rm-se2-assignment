/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.models.Event;
import devfortress.models.Project;
import devfortress.view.components.GlassPanel;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomTable;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class EventTabPanel extends JPanel implements Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colors.MIDDLERED;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //declare private variables
    private JPanel subContainer;
    private JPanel top;
    private int page = 0;
    private int currentPage = 0;
    private JLabel leftArrow;
    private JLabel rightArrow;
    private List<Project> listProject;
    private JLabel projectName;
    private JTable eventTable;
    private DefaultTableModel tableModel;

    //constructor
    public EventTabPanel() {
        setOpaque(false);
        init();
    }

    private void showEvent() {
        if (listProject.size() > 0) {
            page = listProject.size();
        }
        System.out.println("List size: " + listProject.size());
        if (page >= 0) {
            if (page > 1) {
                leftArrow.setVisible(true);
                rightArrow.setVisible(true);
            } else {
                leftArrow.setVisible(false);
                rightArrow.setVisible(false);
            }

            Project currentProject = listProject.get(currentPage);
            projectName.setText(currentProject.getName());
            int i = 1;
            System.out.println("Event Size: " + currentProject.getEvents().size());
            for (Event event : currentProject.getEvents()) {
                Object[] rowData = {i, event.getDescription()};
                tableModel.addRow(rowData);
                i++;
            }


        }
    }

    private void init() {
        /*
         * ########### initialize variables ##########
         */
        //$$$$$-----Global variables
        subContainer = new GlassPanel(550, 400);
        top = new GlassPanel(550, 55);
        projectName = new JLabel("") {

            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);
            }
        };
        tableModel = new DefaultTableModel(1, 2);
        eventTable = new CustomTable(tableModel);
        //$$$$$-----Local variable for UI
        GlassPanel marginTop = new GlassPanel(250, 15);
        GlassPanel gp3 = new GlassPanel(15, 15, 250, 390, .9f, Colors.YELLOW, 15, 15);
        leftArrow = new CustomLabel("");
        rightArrow = new CustomLabel("");
        leftArrow.setIcon(new ImageIcon("images/arrowLeft.png"));
        rightArrow.setIcon(new ImageIcon("images/arrowRight.png"));
        /*
         * ########## Adjust look and feel ##########
         */
        projectName.setForeground(Colors.DARKBLUE);
        projectName.setFont(new Font("Century Gothic", Font.BOLD, 32));
        /*
         * ########## set border layout to the container ##########
         */
        setLayout(new BorderLayout());
        gp3.setLayout(new FlowLayout());
        //-------Add components together
        //leftNav.add(upArrow, BorderLayout.WEST);
        //leftNav.add(downArrow, BorderLayout.EAST);
        add(subContainer, BorderLayout.CENTER);
        add(gp3, BorderLayout.EAST);
        gp3.add(marginTop, BorderLayout.NORTH);
        //
        JPanel nameContainer = new GlassPanel(380, 50);
        nameContainer.add(projectName);
        top.add(leftArrow, BorderLayout.WEST);
        top.add(nameContainer, BorderLayout.CENTER);
        top.add(rightArrow, BorderLayout.EAST);
        subContainer.add(top, BorderLayout.NORTH);
        subContainer.add(eventTable, BorderLayout.CENTER);
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

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPage() {
        return page;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        listProject = model.getProjects();
        showEvent();
        String welcomeStr = "<html>Hi, " + model.getPlayerName() + " ◕‿◕</html>";
    }

    public void setUpArrowListener() {
        leftArrow.addMouseListener(new UpArrowNavigator());
    }

    public void setDownArrowListener() {
        rightArrow.addMouseListener(new DownArrowNavigator());
    }

    private class UpArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                currentPage--;
                showEvent();
                subContainer.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                leftArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                leftArrow.setIcon(new ImageIcon("images/arrowLeftOnMouse.png"));
                leftArrow.repaint();
            } else {
                leftArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            leftArrow.setIcon(new ImageIcon("images/arrowLeft.png"));
            leftArrow.repaint();
        }
    }

    private class DownArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                currentPage++;
                showEvent();
                subContainer.repaint();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                rightArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rightArrow.setIcon(new ImageIcon("images/arrowRightOnMouse.png"));
                rightArrow.repaint();
            } else {
                rightArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rightArrow.setIcon(new ImageIcon("images/arrowRight.png"));
            rightArrow.repaint();
        }
    }
}
