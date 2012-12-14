/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.components.CustomListPanel;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomListRenderer;
import devfortress.view.components.CustomButton;
import devfortress.view.components.GlassPanel;
import devfortress.view.components.CustomTable;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.Skill;
import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class HireDeveloperPanel extends JPanel implements Observer {

    private static final Color PNL_COLOR = Colors.LIGHTORANGE;
    private static final Color LIST_COLOR = Colors.DARKBLUE;
    private static final Color BTN_COLOR = Colors.DARKBLUE;
    private static final Color BTN_ON_MOUSE_COLOR = Colors.DARKBLUE2;
    private static final Color BTN_TEXT_COLOR = Colors.LIGHTBLUE;
    private static final Color TABLE_COLOR = Colors.ORANGE;
    private static final int X = 5;
    private static final int Y = 5;
    private static final int PNL_WIDTH = 785;
    private static final int PNL_HEIGHT = 555;
    private static final int ARCH = 20;
    private static final int ARCW = 20;
    private static final float alpha = .7f;
    private static final Color colour = Colors.MATENGA;
    private String playerName;
    //private variables
    private JList developerList;
    private JLabel devNameLbl, mainSkillLbl, workingPrj, status;
    private CustomButton hireBtn, closeBtn;
    private GlassPanel rightPanel;
    private JTable skillTable;
    private DefaultTableModel skillTableModel;
    private DefaultListModel devListModel;
    private Developer devToHire;
    private GameEngine model;

    public HireDeveloperPanel() {
        // Initialize variables
        rightPanel = new GlassPanel(25, 25, 480, 380, 1f, PNL_COLOR, 7, 7);
        devListModel = new DefaultListModel();
        skillTableModel = new DefaultTableModel(1, 2);
        devNameLbl = new JLabel("Developer Name");
        mainSkillLbl = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JLabel("Status");
        developerList = new JList();
        skillTable = new CustomTable(skillTableModel);
        hireBtn = new CustomButton("Hire Developer");
        closeBtn = new CustomButton("Close");
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());
        //Local variables
        GlassPanel hireDevHeader = new GlassPanel(800, 70);
        GlassPanel hireDevBottom = new GlassPanel(800, 70);
        List<CustomButton> btnList = new LinkedList<CustomButton>();
        btnList.add(hireBtn);
        CustomListPanel devListPanel = new CustomListPanel(developerList, btnList);
        JLabel developerDetail = new JLabel("Developer Details");
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        JPanel topPnl = new JPanel();
        JPanel topRightPnl = new JPanel();
        JPanel bottom = new JPanel();
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        JLabel title = new CustomLabel("Available Developers");
//        JTableHeader skillTableHeader = skillTable.getTableHeader();

        developerList.addListSelectionListener(new HireDeveloperListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devListModel);

        //-------Adjust look and feel
        developerList.setSelectionBackground(Colors.LIGHTORANGE);
        developerList.setSelectionForeground(Colors.REDORANGEDARK);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        hireBtn.setButtonSize(0, 0, 150, 35);
        hireBtn.setTextColour(BTN_TEXT_COLOR);
        hireBtn.setColour(BTN_COLOR);
        hireBtn.setOnMouseColor(BTN_ON_MOUSE_COLOR);
        skillTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        skillTable.setBorder(BorderFactory.createLineBorder(TABLE_COLOR, 1));
        skillTable.setRowHeight(25);
        devNameLbl.setFont(font);
        mainSkillLbl.setFont(font);
        workingPrj.setFont(font);
        status.setFont(font);
        closeBtn.setColour(BTN_COLOR);
        closeBtn.setOnMouseColor(BTN_COLOR.brighter());
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(topPnl, BorderLayout.CENTER);
        rightPanel.add(((CustomTable) skillTable).getTableScroll(), BorderLayout.NORTH);
        rightPanel.add(bottom, BorderLayout.SOUTH);

        topPnl.setBackground(PNL_COLOR);
        topPnl.setLayout(new GridLayout(1, 2));

        topRightPnl.setBackground(PNL_COLOR);
        topRightPnl.setPreferredSize(new Dimension(220, 100));
        topRightPnl.setLayout(new GridLayout(4, 1));

        devListPanel.setColor(LIST_COLOR);
        developerDetail.setForeground(LIST_COLOR);
        developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));



        bottom.setBackground(PNL_COLOR);
        title.setForeground(Colors.DARKBLUE);
        //add components
        topPnl.add(imageIcon);
        topPnl.add(topRightPnl);
        topRightPnl.add(devNameLbl);
        topRightPnl.add(mainSkillLbl);
        topRightPnl.add(workingPrj);
        topRightPnl.add(status);

        hireDevHeader.add(title);
        hireDevBottom.add(closeBtn);
        add(hireDevHeader, BorderLayout.NORTH);
        add(devListPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(hireDevBottom, BorderLayout.SOUTH);
    }
    //Getters and Setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Developer getDevToHire() {
        return devToHire;
    }

    public void setDevToHire(Developer devToHire) {
        this.devToHire = devToHire;
    }

    public void addCloseHirePaneEvent(MouseListener l) {
        closeBtn.addMouseListener(l);
    }

    public void addHireDeveloperEvent(MouseListener l) {
        hireBtn.addMouseListener(l);
    }

    private class HireDeveloperListEvent implements ListSelectionListener {

        private int selectedIndex;

        public HireDeveloperListEvent() {
            selectedIndex = developerList.getSelectedIndex();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (selectedIndex != developerList.getSelectedIndex()) {
                selectedIndex = developerList.getSelectedIndex();
                showDeveloper((Developer) developerList.getSelectedValue());
                setDevToHire((Developer) developerList.getSelectedValue());
            }
        }
    }

    public synchronized void showDeveloper(Developer dev) {
        if (dev == null) {
            rightPanel.setVisible(false);
        } else {
            rightPanel.setVisible(true);
            setDevName(dev.getName());
            String toolTip = dev.getName() + " is currently ";
            JLabel isHappy = new JLabel();
            JLabel isFull = new JLabel();
            if (dev.isHappy()) {
                isHappy.setIcon(new ImageIcon("images/happy.png"));
//                isHappy.repaint();
                toolTip += "happy"; 
            } else {
                isHappy.setIcon(new ImageIcon("images/unhappy.png"));
//                isHappy.repaint();
                toolTip += "not happy";
            }
            if (dev.isFed()) {
                isFull.setIcon(new ImageIcon("images/drunk.png"));
                toolTip += ""; 
            } else {
                isFull.setIcon(new ImageIcon("images/notDrunk.png"));
                toolTip += " and hungry";
            }
            if (dev.isDrunk()) {
                toolTip += " and drunk.";
            } else {
                toolTip += ".";
            }
            setStatus(s);
            Project p = dev.getWorkingProject();
            if (p != null) {
                setWorkingPrj(p.getName());
            } else {
                setWorkingPrj("Not working");
            }
            setMainSkill(dev.getMainSkill().toString());
            //Table
            skillTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            skillTable.getColumnModel().getColumn(0).setMinWidth(400);
            skillTable.getColumnModel().getColumn(1).setMaxWidth(20);
            while (skillTableModel.getRowCount() > 0) {
                skillTableModel.removeRow(skillTableModel.getRowCount() - 1);
            }
            for (Skill skill : dev.getSkills().values()) {
                Object[] rowData = {skill.getSkillInfo().getName(), skill.getLevel()};
                skillTableModel.addRow(rowData);
            }
//            table.getColumnModel().getColumn(1).setPreferredWidth(27);
            Object[] ids = {"Skill", "Level"};
            skillTableModel.setColumnIdentifiers(ids);
            skillTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            skillTable.getColumnModel().getColumn(1).setMaxWidth(70);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        model = (GameEngine) o;
        devListModel.clear();
        for (Developer dev : model.getMarketDevelopers()) {
            devListModel.addElement(dev);
        }
        showDeveloper((Developer) developerList.getSelectedValue());
    }

    //Getter and Setter
    public String getDevName() {
        return devNameLbl.getText();
    }

    public void setDevName(String devName) {
        this.devNameLbl.setText(devName);
        this.devNameLbl.repaint();

    }

    public DefaultListModel getDevModel() {
        return this.devListModel;
    }

    public String getMainSkill() {
        return mainSkillLbl.getText();
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkillLbl.setText(mainSkill);
        this.mainSkillLbl.repaint();
    }

    public String getStatus() {
        return status.getText();
    }

    public void setStatus(JLabel status) {
        this.status.setText(status.getText());
        this.status.repaint();
    }

    public String getWorkingPrj() {
        return workingPrj.getText();
    }

    public void setWorkingPrj(String workingPrj) {
        this.workingPrj.setText(workingPrj);
        this.workingPrj.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(X, Y, PNL_WIDTH, PNL_HEIGHT, ARCW, ARCH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PNL_WIDTH, PNL_HEIGHT);
    }
}
