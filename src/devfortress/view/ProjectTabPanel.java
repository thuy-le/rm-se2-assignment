/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.exceptions.InvalidDevDateException;
import devfortress.models.DevDate;
import devfortress.models.Developer;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.*;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
public class ProjectTabPanel extends JPanel implements Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colors.YOUNGGREEN;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    static private final Color contentColor = Colors.LIGHTGREEN3;
    private JList prjList;
    private DefaultListModel prjModel;
    private DefaultTableModel projTableModel;
    private CustomButton btnAdd, btnAddDev, btnRemoveDev, btnCancelProject;
    private GlassPanel infoPanel;
    private JLabel prjName, deadline, cost, info1, status;
    private JTable table;
    private Project selectedProject;
    //constructor

    public ProjectTabPanel() {
        selectedProject = null;
        prjModel = new DefaultListModel();
        projTableModel = new DefaultTableModel(0, 2);
        prjList = new JList();
        btnAdd = new CustomButton("Add Project");
        prjName = new JLabel("Project Name");
        deadline = new JLabel("Deadline");
        cost = new JLabel("Cost");
        info1 = new JLabel("Info1");
        status = new JLabel("Status");
        table = new CustomProjectTable(projTableModel, contentColor);
        btnAddDev = new CustomButton("Add Developer");
        btnRemoveDev = new CustomButton("Remove Developer");
        btnCancelProject = new CustomButton("Cancel Project");
        infoPanel = new GlassPanel(25, 25, 480, 380, 1f, contentColor, 7, 7);
        init();
    }

    private void init() {
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        Font buttonFont = new Font("Century Gothic", Font.BOLD, 15);
        CustomListPanel projectListPanel = new CustomListPanel(prjList, Arrays.asList(new CustomButton[]{btnAdd}));
        JLabel headerLbl = new JLabel("Project Details");
        JPanel projectInfoPanel = new JPanel();
        JLabel imageIcon = new CustomLabel(new ImageIcon("images/i6.png"));
        JPanel projectDetailsPanel = new JPanel();
        JPanel infoCenterSouthPanel = new JPanel();
        JPanel infoNorthPanel = new JPanel();
        GlassPanel infoGroupPanel = new GlassPanel(10, 15, 500, 395, 1f, contentColor, 7, 7);
        //Data
        {
            prjList.setModel(prjModel);
            prjList.addListSelectionListener(new MyListEvent());

        }
        //Style
        {
            setOpaque(false);
            btnAdd.setColour(Colors.DARKBLUE);
            btnAdd.setTextColour(Colors.LIGHTBLUE);
            prjList.setSelectionBackground(Colors.LIGHTGREEN);
            prjList.setSelectionForeground(Colors.DARKBLUE);
            prjList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            prjName.setFont(font);
            deadline.setFont(font);
            info1.setFont(font);
            status.setFont(font);
            cost.setFont(font);
            infoPanel.setBounds(15, 15, 490, 395);
            btnAddDev.setButtonSize(0, 0, 140, 35);
            btnRemoveDev.setButtonSize(0, 0, 170, 35);
            btnCancelProject.setButtonSize(0, 0, 140, 35);

            btnAddDev.setCustomFont(buttonFont);
            btnRemoveDev.setCustomFont(buttonFont);
            btnCancelProject.setCustomFont(buttonFont);

            projectListPanel.setColor(Colors.DARKBLUE);
            projectInfoPanel.setOpaque(false);
            imageIcon.setOpaque(false);
            imageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            projectDetailsPanel.setBackground(contentColor);
            projectInfoPanel.setBackground(contentColor);
            headerLbl.setForeground(Colors.DARKBLUE);
            headerLbl.setFont(new Font("Century Gothic", Font.BOLD, 22));
            headerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoCenterSouthPanel.setBackground(contentColor);
            infoNorthPanel.setOpaque(false);
        }
        //Layout
        {
            //Layout Manager
            setLayout(new BorderLayout());
            infoPanel.setLayout(new BorderLayout());
            infoGroupPanel.setLayout(null);
            infoNorthPanel.setLayout(new BoxLayout(infoNorthPanel, BoxLayout.Y_AXIS));
            projectInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            infoCenterSouthPanel.setLayout(new FlowLayout());
            projectDetailsPanel.setLayout(new BoxLayout(projectDetailsPanel, BoxLayout.Y_AXIS));
            //Add components
            add(projectListPanel, BorderLayout.WEST);
            add(infoGroupPanel, BorderLayout.CENTER);
            infoGroupPanel.add(infoPanel);
            infoPanel.add(infoNorthPanel, BorderLayout.NORTH);
            infoPanel.add(((CustomProjectTable) table).getTableScroll(), BorderLayout.CENTER);
            infoPanel.add(infoCenterSouthPanel, BorderLayout.SOUTH);
            infoNorthPanel.add(headerLbl);
            infoNorthPanel.add(projectInfoPanel);
            projectInfoPanel.add(imageIcon);
            projectInfoPanel.add(projectDetailsPanel);
            projectDetailsPanel.add(prjName);
            projectDetailsPanel.add(deadline);
            projectDetailsPanel.add(info1);
            projectDetailsPanel.add(cost);
            projectDetailsPanel.add(status);
            infoCenterSouthPanel.add(btnAddDev);
            infoCenterSouthPanel.add(btnRemoveDev);
            infoCenterSouthPanel.add(btnCancelProject);

        }
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

    public void addNewProjectListener(MouseListener l) {
        btnAdd.addMouseListener(l);
    }

    public void cancelProjectListener(MouseListener l) {
        btnCancelProject.addMouseListener(l);
    }

    public void addDevToProjectListener(MouseListener l) {
        btnAddDev.addMouseListener(l);
    }

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
        if (!model.getProjects().contains(selectedProject)) {
            selectedProject = null;
        }
        showProject(selectedProject);
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public synchronized void showProject(Project project) {
        if (project == null) {
            infoPanel.setVisible(false);
        } else {
            try {
                infoPanel.setVisible(true);
                //set projectname... blah blah blah
                prjName.setText("Project: " + project.getName());
                DevDate acceptedDate = project.getAcceptedDate();
                DevDate deadlineDate = acceptedDate.addMonths(project.getDuration());
                deadline.setText("Deadline: " + deadlineDate.getWeek() + "/" + deadlineDate.getMonth() + "/" + deadlineDate.getYear());
                cost.setText("Budget: $" + project.getBudget());
                info1.setText("Level: " + project.getLevel());
                status.setText("Bonus: " + project.getBonus());
                //table
                while (projTableModel.getRowCount() > 0) {
                    projTableModel.removeRow(projTableModel.getRowCount() - 1);
                }
                for (FunctionalArea fA : project.getAreas().values()) {
                    Object[] rowData = {fA.getName().toString(), fA.getCompletedPoints() + "/" + fA.getFunctionPoints()};
                    projTableModel.addRow(rowData);
                }
                Object[] ids = {"Functional Area", "Points"};
                projTableModel.setColumnIdentifiers(ids);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                table.getColumnModel().getColumn(1).setMaxWidth(100);

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
                Project project = (Project) prjList.getSelectedValue();
                if (project != null) {
                    selectedProject = project;
                } else {
                    if (!GameEngine.getInstance().getProjects().contains(selectedProject)) {
                        selectedProject = null;
                    }
                }
                showProject(selectedProject);
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
