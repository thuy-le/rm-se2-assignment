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
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
    private JLabel devNameLbl, mainSkillLbl, workingPrj, salaryLbl, statusLbl;
    private JPanel status;
    private CustomButton hireBtn, closeBtn;
    private GlassPanel infoPanel;
    private JTable skillTable;
    private DefaultTableModel skillTableModel;
    private DefaultListModel devListModel;
    private Developer devToHire;
    private GameEngine model;
    private JLabel isHappy, isDrunk;
    private JLabel developerDetail;

    public HireDeveloperPanel() {
        // Initialize variables
        infoPanel = new GlassPanel(25, 25, 480, 380, 1f, PNL_COLOR, 7, 7);
        devListModel = new DefaultListModel();
        skillTableModel = new DefaultTableModel(1, 2);
        devNameLbl = new JLabel("Developer Name");
        mainSkillLbl = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JPanel(new GridLayout(1, 3));
        developerList = new JList();
        skillTable = new CustomTable(skillTableModel);
        hireBtn = new CustomButton("Hire Developer");
        closeBtn = new CustomButton("Close");
        developerDetail = new JLabel("");
        isHappy = new JLabel("");
        isDrunk = new JLabel("");
        statusLbl = new JLabel("Status: ");
        salaryLbl = new JLabel("");
        init();
    }

    private void init() {
        CustomListPanel devListPanel = new CustomListPanel(developerList, Arrays.asList(new CustomButton[]{hireBtn}));
        GlassPanel hireDevHeader = new GlassPanel(800, 70);
        GlassPanel hireDevBottom = new GlassPanel(800, 70);
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        JPanel developerInfoPanel = new JPanel();
        JPanel developerDetailsPanel = new JPanel();
        JPanel infoNorthPanel = new JPanel();
        JPanel bottom = new JPanel();
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        JLabel title = new CustomLabel("Available Developers");
        GlassPanel infoGroupPanel = new GlassPanel(10, 10, 500, 450, 1f, PNL_COLOR, 7, 7);

        //Data
        {
            setOpaque(false);
            developerList.addListSelectionListener(new HireDeveloperListEvent());
            developerList.setCellRenderer(new CustomListRenderer());
            developerList.setModel(devListModel);
        }

        //Style
        {
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
            closeBtn.setColour(BTN_COLOR);
            closeBtn.setOnMouseColor(BTN_COLOR.brighter());
            developerInfoPanel.setBackground(PNL_COLOR);
            developerDetailsPanel.setBackground(PNL_COLOR);
//            developerDetailsPanel.setPreferredSize(new Dimension(220, 100));
            devListPanel.setColor(LIST_COLOR);
            developerDetail.setForeground(LIST_COLOR);
            developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));
            developerDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
            bottom.setBackground(PNL_COLOR);
            title.setForeground(Colors.DARKBLUE);
            salaryLbl.setFont(font);
            statusLbl.setFont(font);
            status.setBackground(Colors.LIGHTORANGE);

            infoPanel.setBounds(15, 10, 490, 410);
            infoNorthPanel.setOpaque(false);

        }
        //Layout
        {
            setLayout(new BorderLayout());
            infoPanel.setLayout(new BorderLayout());

            developerInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            developerInfoPanel.add(imageIcon);
            developerInfoPanel.add(developerDetailsPanel);
            developerDetailsPanel.setLayout(new BoxLayout(developerDetailsPanel, BoxLayout.Y_AXIS));
            infoNorthPanel.setLayout(new BoxLayout(infoNorthPanel, BoxLayout.Y_AXIS));
            infoGroupPanel.setLayout(null);
            add(hireDevHeader, BorderLayout.NORTH);
            add(devListPanel, BorderLayout.WEST);
            add(infoGroupPanel, BorderLayout.CENTER);
            add(hireDevBottom, BorderLayout.SOUTH);
            hireDevHeader.add(title);
            infoGroupPanel.add(infoPanel);
           
            infoPanel.add(infoNorthPanel, BorderLayout.NORTH);
            infoPanel.add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
            infoPanel.add(bottom, BorderLayout.SOUTH);
            infoNorthPanel.add(developerDetail);
            infoNorthPanel.add(developerInfoPanel);
            //Add components
            
            developerDetailsPanel.add(devNameLbl);
            developerDetailsPanel.add(mainSkillLbl);
            developerDetailsPanel.add(salaryLbl);
            developerDetailsPanel.add(status);
            status.add(statusLbl);
            status.add(isHappy);
            status.add(isDrunk);
            
            hireDevBottom.add(closeBtn);

            
            
            
        }



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

    public void showDeveloper(Developer dev) {
        if (dev == null) {
            infoPanel.setVisible(false);
        } else {
            infoPanel.setVisible(true);
            developerDetail.setText(dev.getName());
            setDevName(dev.getName());
            String toolTip = dev.getName() + " is currently ";
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
                isDrunk.setIcon(new ImageIcon("images/drunk.png"));
                toolTip += "";
            } else {
                isDrunk.setIcon(new ImageIcon("images/notDrunk.png"));
                toolTip += " and hungry";
            }
            if (dev.isDrunk()) {
                toolTip += " and drunk.";
            } else {
                toolTip += ".";
            }
            status.setToolTipText(toolTip);

            Project p = dev.getWorkingProject();
            if (p != null) {
                setWorkingPrj(p.getName());
            } else {
                setWorkingPrj("Not working");
            }
            setMainSkill(dev.getMainSkill().toString());
            salaryLbl.setText("Salary: $" + dev.getSalary());
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
            skillTable.getColumnModel().getColumn(1).setMaxWidth(50);

        }
        developerList.setSelectedValue(dev, true);
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
        this.devNameLbl.setText("Name: " + devName);
        this.devNameLbl.repaint();

    }

    public DefaultListModel getDevModel() {
        return this.devListModel;
    }

    public String getMainSkill() {
        return mainSkillLbl.getText();
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkillLbl.setText("Main Skill: " + mainSkill);
        this.mainSkillLbl.repaint();
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
