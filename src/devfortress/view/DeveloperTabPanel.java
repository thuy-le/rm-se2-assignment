/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.Skill;
import devfortress.utilities.Colors;
import devfortress.view.components.*;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
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
public class DeveloperTabPanel extends JPanel implements Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final String picture = "images/i6.png";
    static private final Color colour = Colors.ORANGE;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //private variables
    private JList developerList;
    private JLabel devName, mainSkill, workingPrj, status, salaryLbl;
    private JPanel statusPanel;
    public CustomButton btnFeed, btnParty;
    private CustomButton btnHire, btnFireDev, btnFeedDev, btnPartyDev, btnTrain;
    private GlassPanel infoPanel;
    private CustomDeveloperTable table;
    private DefaultTableModel skillModel;
    private DefaultListModel devModel;
    private JLabel isHappy;
    private JLabel isDrunk;
    private Developer selectedDeveloper;
    //constructor

    public DeveloperTabPanel() {
        selectedDeveloper = null;
        isHappy = new JLabel("");
        isDrunk = new JLabel("");
        devModel = new DefaultListModel();
        skillModel = new DefaultTableModel(1, 2);
        infoPanel = new GlassPanel(25, 25, 480, 380, 1f, Colors.LIGHTORANGE, 7, 7);
        developerList = new JList();
        statusPanel = new JPanel();
        btnHire = new CustomButton("Hire");
        btnFeed = new CustomButton("Feed");
        btnParty = new CustomButton("Party");
        devName = new JLabel("Developer Name");
        mainSkill = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JLabel("Status: ");
        btnFireDev = new CustomButton("Fire");
        btnFeedDev = new CustomButton("Feed");
        btnPartyDev = new CustomButton("Drink");
        btnTrain = new CustomButton("Train");
        table = new CustomDeveloperTable(skillModel, Colors.LIGHTORANGE);
        salaryLbl = new JLabel("Salary: ");
        init();
    }

    private void init() {
        JPanel developerInfoPanel = new JPanel();
        JPanel developerDetailsPanel = new JPanel();
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        CustomListPanel devsListPanel = new CustomListPanel(developerList, Arrays.asList(new CustomButton[]{btnHire, btnFeed, btnParty}));
        JPanel buttonPanel = new JPanel();
        JPanel infoNorthPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        GlassPanel infoGroupPanel = new GlassPanel(10, 10, 500, 400, 1f, Colors.LIGHTORANGE, 7, 7);

        //Data
        {
            developerList.addListSelectionListener(new MyListEvent());
            developerList.setCellRenderer(new CustomListRenderer());
            developerList.setModel(devModel);
        }
        // Style
        {
            setOpaque(false);
            btnHire.setButtonSize(0, 0, 55, 35);
            btnFeed.setButtonSize(0, 0, 55, 35);
            btnParty.setButtonSize(0, 0, 55, 35);
            devsListPanel.setColor(Colors.DARKBLUE);
            btnHire.setTextColour(Colors.LIGHTBLUE);
            btnFeed.setTextColour(Colors.LIGHTBLUE);
            btnParty.setTextColour(Colors.LIGHTBLUE);
            developerList.setSelectionBackground(Colors.LIGHTORANGE);
            developerList.setSelectionForeground(Colors.REDORANGEDARK);
            developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            developerDetailsPanel.setBackground(Colors.LIGHTORANGE);
            developerDetailsPanel.setPreferredSize(new Dimension(220, 100));
            developerInfoPanel.setBackground(Colors.LIGHTORANGE);
            mainSkill.setFont(font);
            workingPrj.setFont(font);
            status.setFont(font);
            devName.setForeground(Colors.DARKBLUE);
            devName.setFont(new Font("Century Gothic", Font.BOLD, 22));
            devName.setAlignmentX(Component.CENTER_ALIGNMENT);
            statusPanel.setBackground(Colors.LIGHTORANGE);
            statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            salaryLbl.setFont(font);
            infoNorthPanel.setOpaque(false);
            buttonPanel.setBackground(Colors.LIGHTORANGE);
            buttonPanel.setLayout(new FlowLayout());
            btnFireDev.setButtonSize(0, 0, 70, 35);
            btnFeedDev.setButtonSize(0, 0, 70, 35);
            btnPartyDev.setButtonSize(0, 0, 70, 35);
            btnTrain.setButtonSize(0, 0, 70, 35);
            tablePanel.setBackground(Colors.LIGHTORANGE);
            tablePanel.setOpaque(false);
            infoPanel.setBounds(15, 10, 490, 410);
            infoPanel.setBackground(Colors.LIGHTORANGE);
        }
        //Layout
        {
            //Layout Manager
            setLayout(new BorderLayout());
            infoPanel.setLayout(new BorderLayout());
            developerInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            developerDetailsPanel.setLayout(new BoxLayout(developerDetailsPanel, BoxLayout.Y_AXIS));
            statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            infoNorthPanel.setLayout(new BoxLayout(infoNorthPanel, BoxLayout.Y_AXIS));
            infoGroupPanel.setLayout(null);
            //AddComponent
            add(devsListPanel, BorderLayout.WEST);
            add(infoGroupPanel, BorderLayout.CENTER);
            infoGroupPanel.add(infoPanel);
            infoPanel.add(infoNorthPanel, BorderLayout.NORTH);
            infoPanel.add(tablePanel, BorderLayout.CENTER);
            infoNorthPanel.add(devName);
            infoNorthPanel.add(developerInfoPanel);
            developerInfoPanel.add(imageIcon);
            developerInfoPanel.add(developerDetailsPanel);
            developerDetailsPanel.add(salaryLbl);
            developerDetailsPanel.add(mainSkill);
            developerDetailsPanel.add(workingPrj);
            developerDetailsPanel.add(statusPanel);
            statusPanel.add(status);
            statusPanel.add(isHappy);
            statusPanel.add(isDrunk);
            tablePanel.setLayout(new BorderLayout());
            tablePanel.add(((CustomDeveloperTable) table).getTableScroll(), BorderLayout.CENTER);
            tablePanel.add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.add(btnFireDev);
            buttonPanel.add(btnFeedDev);
            buttonPanel.add(btnPartyDev);
            buttonPanel.add(btnTrain);
        }
    }

    //override the paint component method
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.drawRoundRect(x, y, width, height, width, height);
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    //Getter and Setter
    public String getDevName() {
        return devName.getText();
    }

    public void setDevName(String devName) {
        this.devName.setText(devName);
        this.devName.repaint();
    }

    public void setDevModel(DefaultListModel model) {
        this.devModel = model;
    }

    public DefaultListModel getDevModel() {
        return this.devModel;
    }

    public String getMainSkill() {
        return mainSkill.getText();
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkill.setText("Main Skill: " + mainSkill);
        this.mainSkill.repaint();
    }

    public String getStatus() {
        return status.getText();
    }

    public void setStatus(String status) {
        this.status.setText(status);
        this.status.repaint();
    }

    public String getWorkingPrj() {
        return workingPrj.getText();
    }

    public void setWorkingPrj(String workingPrj) {
        this.workingPrj.setText(workingPrj);
        this.workingPrj.repaint();
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public int getSelectedIndex() {
        return developerList.getSelectedIndex();
    }

    public void setSelectedDeveloper(int index) {
        developerList.setSelectedIndex(index);
    }

    public Developer getSelectedDeveloper() {
        return selectedDeveloper;
    }

    public void addHireDevListener(MouseListener l) {
        btnHire.addMouseListener(l);
    }

    public void addFeedAllDevListener(MouseListener l) {
        btnFeed.addMouseListener(l);
    }

    public void addTrainDeveloperListener(MouseListener l) {
        btnTrain.addMouseListener(l);
    }

    public void addPartyListener(MouseListener l) {
        btnParty.addMouseListener(l);
    }

    public void addFeedSelectedDevListener(MouseListener l) {
        btnFeedDev.addMouseListener(l);
    }

    public void addFireDevListener(MouseListener l) {
        btnFireDev.addMouseListener(l);
    }

    public void addGiveBeerListener(MouseListener l) {
        btnPartyDev.addMouseListener(l);
    }

    public void showDeveloper(Developer dev) {
        if (dev == null) {
            infoPanel.setVisible(false);
        } else {
            infoPanel.setVisible(true);
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
            statusPanel.setToolTipText(toolTip);

            Project p = dev.getWorkingProject();
            if (p != null) {
                setWorkingPrj("Working on: " + p.getName());
            } else {
                setWorkingPrj("Not working");
            }
            setMainSkill(dev.getMainSkill().toString());
            salaryLbl.setText("Salary: $" + dev.getSalary());
            while (skillModel.getRowCount() > 0) {
                skillModel.removeRow(skillModel.getRowCount() - 1);
            }
            for (Skill skill : dev.getSkills().values()) {
                Object[] rowData = {skill.getSkillInfo().getName(), skill.getLevel()};
                skillModel.addRow(rowData);
            }
            Object[] ids = {"Skill", "Level"};
            skillModel.setColumnIdentifiers(ids);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table.getColumnModel().getColumn(1).setMaxWidth(100);

        }
        developerList.setSelectedValue(dev, true);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        devModel.clear();
        for (Developer dev : model.getDevelopers()) {
            devModel.addElement(dev);
        }
        if (!model.getDevelopers().contains(selectedDeveloper)) {
            selectedDeveloper = null;
        }
        if (model.getDevelopers().size() > 0) {
            btnFeed.enableButton();
            btnParty.enableButton();
        } else {
            btnFeed.disableButton();
            btnParty.disableButton();
        }
        showDeveloper(selectedDeveloper);
    }

    private class MyListEvent implements ListSelectionListener {

        private int selectedIndex;

        public MyListEvent() {
            selectedIndex = developerList.getSelectedIndex();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (selectedIndex != developerList.getSelectedIndex()) {
                selectedIndex = developerList.getSelectedIndex();
                Developer developer = (Developer) developerList.getSelectedValue();
                if (developer != null) {
                    selectedDeveloper = developer;
                } else {
                    if (!GameEngine.getInstance().getDevelopers().contains(selectedDeveloper)) {
                        selectedDeveloper = null;
                    }
                }
                showDeveloper(selectedDeveloper);
                showDeveloper(selectedDeveloper);
            }
        }
    }
}
