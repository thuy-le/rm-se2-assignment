/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.exceptions.InvalidDevDateException;
import devfortress.models.DevDate;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.view.components.GlassPanel;
import devfortress.view.components.CustomListPanel;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.*;
import devfortress.view.interfaces.ProjectTabView;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author PC
 */
public class ProjectTabPanel extends JPanel implements ProjectTabView, Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colors.YOUNGGREEN;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    static private final Color contentColor = Colors.LIGHTORANGE;
    private JList prjList;
    private DefaultListModel prjModel;
    private DefaultTableModel projTableModel;
    private CustomButton btnAdd, btnAddDev, btnRemoveDev;
    private GlassPanel rightPanel;
    private JLabel prjName, deadline, cost, info1, status;
    private JTable table;
    //constructor

    public ProjectTabPanel() {
        setOpaque(false);
        init();
    }

    private void init() {
        //set border layout to the container
        setLayout(new BorderLayout());

        //------Create a JList of developers
        //list
        prjModel = new DefaultListModel();
        projTableModel = new DefaultTableModel(0, 2);

        prjList = new JList();
        prjList.setModel(prjModel);
        prjList.addListSelectionListener(new MyListEvent());
        //buttons
        java.util.List<CustomButton> btnList = new LinkedList<CustomButton>();
        btnAdd = new CustomButton("Add Project");
        btnList.add(btnAdd);
        //add button(s) and list together
        CustomListPanel projectList = new CustomListPanel(prjList, btnList);
        //-------Adjust look and feel
        btnAdd.setColour(Colors.DARKBLUE);
        projectList.setColor(Colors.DARKBLUE);
        btnAdd.setTextColour(Colors.LIGHTBLUE);
        prjList.setSelectionBackground(Colors.LIGHTGREEN);
        prjList.setSelectionForeground(Colors.DARKBLUE);
        prjList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        //-------Add component
        add(projectList, BorderLayout.WEST);
        //Create a container for contents on the right
        rightPanel = new GlassPanel(25, 25, 480, 380, 1f, contentColor, 7, 7);
        //display developer name
        JLabel developerDetail = new JLabel("Project Details");
        //the "top" panel contain (1) avatar, and basic details about developer
        JPanel top = new JPanel();
        top.setOpaque(false);
        //display cancel button
        JLabel imageIcon = new CustomLabel(new ImageIcon("images/cancel.png"), "Cancel this project");
        imageIcon.setOpaque(false);
        imageIcon.setPreferredSize(new Dimension(50, 50));
        imageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //display basic details
        JPanel topR = new JPanel();
        prjName = new JLabel("Project Name");
        deadline = new JLabel("Deadline");
        cost = new JLabel("Cost");
        info1 = new JLabel("Info1");
        status = new JLabel("Status");
        //adjust look and feel
        topR.setBackground(contentColor);
        topR.setPreferredSize(new Dimension(220, 120));
        topR.setLayout(new GridLayout(5, 1));
        top.setBackground(contentColor);
        top.setLayout(new GridLayout(1, 2));
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        prjName.setFont(font);
        deadline.setFont(font);
        info1.setFont(font);
        status.setFont(font);
        cost.setFont(font);
        developerDetail.setForeground(Colors.DARKBLUE);
        developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));
        //add components
        topR.add(prjName);
        topR.add(deadline);
        topR.add(info1);
        topR.add(cost);
        topR.add(status);
        top.add(topR);
        top.add(imageIcon);
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(top, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);

        //---Bottom: contains a list of developers who belong to the project
        //Create a table:
        table = new CustomProjectTable(projTableModel, contentColor);

        //add buttons
        JPanel bottom = new JPanel();
        btnAddDev = new CustomButton("Add Developer");
        btnRemoveDev = new CustomButton("Remove Developer");

        //adjust look and feel:

        bottom.setBackground(contentColor);
        bottom.setLayout(new FlowLayout());
        btnAddDev.setButtonSize(0, 0, 175, 35);
        btnRemoveDev.setButtonSize(0, 0, 175, 35);
        //add components
        bottom.add(btnAddDev);
        bottom.add(btnRemoveDev);
        rightPanel.add(((CustomProjectTable) table).getTableScroll(), BorderLayout.NORTH);
        rightPanel.add(bottom, BorderLayout.SOUTH);
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

    @Override
    public void addNewProjectListener(MouseListener l) {
        btnAdd.addMouseListener(l);
    }

    @Override
    public void cancelProjectListener(MouseListener l) {
    }

    @Override
    public void addDevToProjectListener(MouseListener l) {
        btnAddDev.addMouseListener(l);
    }

    @Override
    public void removeDevFromProjectListener(MouseListener l) {
        btnRemoveDev.addMouseListener(l);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        prjModel.clear();
        for (Project proj : model.getProjects()) {
            prjModel.addElement(proj);
        }
        showProject((Project) prjList.getSelectedValue());
    }

    public synchronized void showProject(Project project) {
        if (project == null) {
            rightPanel.setVisible(false);
        } else {
            try {
                rightPanel.setVisible(true);
                //set projectname... blah blah blah
                prjName = new JLabel(project.getName());
                DevDate today = GameEngine.getInstance().getDate();
                DevDate date = new DevDate(today.getYear(), today.getMonth(), today.getWeek());
                for (int i = 0; i < project.getDuration(); i++) {
                    date.nextMonth();
                }
                deadline.setText("Deadline: " + date.getWeek() + "/" + date.getMonth() + "/" + date.getYear());
                cost.setText("Budget: " + project.getBudget());
                info1.setText("Level: " + project.getLevel());
                status.setText("Bonus: " + project.getBonus());
                //table
                while (projTableModel.getRowCount() > 0) {
                    projTableModel.removeRow(projTableModel.getRowCount() - 1);
                }
                int count = 1;
                for (FunctionalArea fA : project.getAreas().values()) {
                    Object[] rowData = {fA.getName().toString(), fA.getFunctionPoints()};
                    projTableModel.addRow(rowData);
                }
                Object[] ids = {"Functional Area", "Points"};
                projTableModel.setColumnIdentifiers(ids);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                table.getColumnModel().getColumn(1).setMaxWidth(70);

            } catch (InvalidDevDateException ex) {
            }
        }
    }

    private class MyListEvent implements ListSelectionListener {

        private int selectedIndex;

        public MyListEvent() {
            selectedIndex = prjList.getSelectedIndex();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (selectedIndex != prjList.getSelectedIndex()) {
                selectedIndex = prjList.getSelectedIndex();
                showProject((Project) prjList.getSelectedValue());
            }
        }
    }
}

class CustomProjectTable extends JTable {

    private JScrollPane tableScroll;

    public CustomProjectTable(TableModel dm, Color contentColor) {
        super(dm);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setBorder(BorderFactory.createLineBorder(Colors.GREEN, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colors.DARKGREEN, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colors.DARKGREEN);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        JScrollBar sb = tableScroll.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colors.DARKGREEN);
        tableScroll.getViewport().setBackground(contentColor);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
