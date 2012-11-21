/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.exceptions.InvalidDevDateException;
import devfortress.models.DevDate;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomList;
import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.models.Project;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.ProjectInterface;
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
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author PC
 */
public class TabbedPaneProject extends JPanel implements ProjectInterface, Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.YOUNGGREEN;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    static private final Color contentColor = Colour.LIGHTORANGE;
    private JList prjList;
    private DefaultListModel prjModel;
    private DefaultTableModel projTableModel;
    private CustomButton btnAdd, btnAddDev, btnRemoveDev;
    private GlassPanel rightPanel;
    private JLabel prjName, deadline, cost, info1, status;
    //constructor

    public TabbedPaneProject() {
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
        CustomList projectList = new CustomList(prjList, btnList);
        //-------Adjust look and feel
        btnAdd.setColour(Colour.DARKBLUE);
        projectList.setColor(Colour.DARKBLUE);
        btnAdd.setTextColour(Colour.LIGHTBLUE);
        prjList.setSelectionBackground(Colour.LIGHTGREEN);
        prjList.setSelectionForeground(Colour.DARKBLUE);
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
        String picture = "images/cancel.png";
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new CustomLabel(imgIcon);
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
        imageIcon.setToolTipText("Cancel this project");
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
        developerDetail.setForeground(Colour.DARKBLUE);
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
        JTable table = new CustomProjectTable(projTableModel, contentColor);

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

    public void showProject(Project project) {
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
                for (int i = 0; i < projTableModel.getRowCount(); i++) {
                    projTableModel.removeRow(i);
                }
                for (FunctionalArea fA : project.getAreas().values()) {
                    Object[] rowData = {fA.getName().toString(), fA.getFunctionPoints()};
                    projTableModel.addRow(rowData);
                }
                Object[] ids = {"Functional Area", "Function Points"};
                projTableModel.setColumnIdentifiers(ids);
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
        setBorder(BorderFactory.createLineBorder(Colour.GREEN, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.DARKGREEN, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.DARKGREEN);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colour.DARKGREEN);
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
