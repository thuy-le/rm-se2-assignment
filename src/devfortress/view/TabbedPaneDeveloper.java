/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomList;
import devfortress.utilities.CustomListRenderer;
import devfortress.utilities.CustomButton;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.Skill;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.DeveloperInterface;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class TabbedPaneDeveloper extends JPanel implements DeveloperInterface, Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final String picture = "images/i6.png";
    static private final Color colour = Colour.ORANGE;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //private variables
    private JList developerList;
    private JLabel devName, mainSkill, workingPrj, status;
    private CustomButton btnHire, btnFeed, btnParty, btnFireDev, btnFeedDev, btnPartyDev, btnTrain;
    private GlassPanel rightPanel;
    private JTable table;
    private DefaultTableModel skillModel;
    private DefaultListModel devModel;
    //constructor

    public TabbedPaneDeveloper() {
        setOpaque(false);
        init();
    }

    private void init() {
        devModel = new DefaultListModel();
        skillModel = new DefaultTableModel(1, 2);
        rightPanel = new GlassPanel(25, 25, 480, 380, 1f, Colour.LIGHTORANGE, 7, 7);
        developerList = new JList();
        developerList.addListSelectionListener(new MyListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devModel);
        //buttons

        List<CustomButton> btnList = new LinkedList<CustomButton>();
        btnHire = new CustomButton("Hire");
        btnFeed = new CustomButton("Feed");
        btnParty = new CustomButton("Party");
        CustomList cl = new CustomList(developerList, btnList);
        JLabel developerDetail = new JLabel("Developer Details");
        JPanel top = new JPanel();
        JPanel topR = new JPanel();
        devName = new JLabel("Developer Name");
        mainSkill = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JLabel("Status");
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        setLayout(new BorderLayout());
        //------Create a JList of developers
        btnList.add(btnHire);
        btnList.add(btnFeed);
        btnList.add(btnParty);
        //add button(s) and list together
        //-------Adjust look and feel
        btnHire.setButtonSize(0, 0, 55, 35);
        btnFeed.setButtonSize(0, 0, 55, 35);
        btnParty.setButtonSize(0, 0, 55, 35);
        cl.setColor(Colour.DARKBLUE);
        btnHire.setTextColour(Colour.LIGHTBLUE);
        btnFeed.setTextColour(Colour.LIGHTBLUE);
        btnParty.setTextColour(Colour.LIGHTBLUE);
        developerList.setSelectionBackground(Colour.LIGHTORANGE);
        developerList.setSelectionForeground(Colour.REDORANGEDARK);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        JPanel bottom = new JPanel();
        btnFireDev = new CustomButton("Fire");
        btnFeedDev = new CustomButton("Feed");
        btnPartyDev = new CustomButton("Drink");
        btnTrain = new CustomButton("Train");
        table = new CustomTable(skillModel);
        //-------Add component
        add(cl, BorderLayout.WEST);

        //------Right Hand Side:
        //adjust look and feel
        topR.setBackground(Colour.LIGHTORANGE);
        topR.setPreferredSize(new Dimension(220, 100));
        topR.setLayout(new GridLayout(4, 1));
        top.setBackground(Colour.LIGHTORANGE);
        top.setLayout(new GridLayout(1, 2));
        devName.setFont(font);
        mainSkill.setFont(font);
        workingPrj.setFont(font);
        status.setFont(font);
        developerDetail.setForeground(Colour.DARKBLUE);
        developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));
        //add components
        top.add(imageIcon);
        topR.add(devName);
        topR.add(mainSkill);
        topR.add(workingPrj);
        topR.add(status);
        top.add(topR);
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(top, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);
        //---Bottom:
        //adjust look and feel:
        bottom.setBackground(Colour.LIGHTORANGE);
        bottom.setLayout(new FlowLayout());
        btnFireDev.setButtonSize(0, 0, 70, 35);
        btnFeedDev.setButtonSize(0, 0, 70, 35);
        btnPartyDev.setButtonSize(0, 0, 70, 35);
        btnTrain.setButtonSize(0, 0, 70, 35);
        //add components
        bottom.add(btnFireDev);
        bottom.add(btnFeedDev);
        bottom.add(btnPartyDev);
        bottom.add(btnTrain);

        rightPanel.add(((CustomTable) table).getTableScroll(), BorderLayout.NORTH);
        rightPanel.add(bottom, BorderLayout.SOUTH);

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
    
    public void setDevModel(DefaultListModel model){
        this.devModel = model;
    }
    
    @Override
    public DefaultListModel getDevModel(){
        return this.devModel;
    }

    public String getMainSkill() {
        return mainSkill.getText();
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkill.setText(mainSkill);
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

    @Override
    public int getSelectedIndex() {
        return developerList.getSelectedIndex();
    }

    @Override
    public void setSelectedDeveloper(int index) {
        developerList.setSelectedIndex(index);
    }

    @Override
    public Developer getSelectedDeveloper() {
        return (Developer) developerList.getSelectedValue();
    }

    @Override
    public void addHireDevListener(MouseListener l) {
        btnHire.addMouseListener(l);
    }

    @Override
    public void addFeedAllDevListener(MouseListener l) {
        btnFeed.addMouseListener(l);
    }

    @Override
    public void addTrainDeveloperListener(MouseListener l) {
        btnTrain.addMouseListener(l);
    }

    @Override
    public void addPartyListener(MouseListener l) {
        btnParty.addMouseListener(l);
    }

    @Override
    public void addFeedSelectedDevListener(MouseListener l) {
        btnFeedDev.addMouseListener(l);
    }

    @Override
    public void addFireDevListener(MouseListener l) {
        btnFireDev.addMouseListener(l);
    }

    @Override
    public void addGiveBeerListener(MouseListener l) {
        btnPartyDev.addMouseListener(l);
    }

    @Override
    public void showDeveloper(Developer dev) {
        if (dev == null) {
            rightPanel.setVisible(false);
        } else {
            rightPanel.setVisible(true);
            setDevName(dev.getName());
            String s = "<html>Status: " + (dev.isHappy() ? "☺" : "☹");
            s += (dev.isDrunk() ? ",☻" : "");
            s += (dev.isFed() ? ",☀" : ",☠");
            s += "</html>";
            setStatus(s);
            Project p = dev.getWorkingProject();
            if (p != null) {
                setWorkingPrj(p.getName());
            } else {
                setWorkingPrj("Not working");
            }
            setMainSkill(dev.getMainSkill().toString());
            //Table
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table.getColumnModel().getColumn(0).setMinWidth(400);
            table.getColumnModel().getColumn(1).setMaxWidth(20);
            while (skillModel.getRowCount() > 0) {
                skillModel.removeRow(skillModel.getRowCount() - 1);
            }
            for (Skill skill : dev.getSkills().values()) {
                Object[] rowData = {skill.getSkillInfo().getName(), skill.getLevel()};
                skillModel.addRow(rowData);
            }
//            table.getColumnModel().getColumn(1).setPreferredWidth(27);
            Object[] ids = {"Skill", "Level"};
            skillModel.setColumnIdentifiers(ids);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table.getColumnModel().getColumn(1).setMaxWidth(50);

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        devModel.clear();
        for (Developer dev : model.getDevelopers()) {
            devModel.addElement(dev);
        }
        showDeveloper((Developer) developerList.getSelectedValue());
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
                showDeveloper((Developer) developerList.getSelectedValue());
            }
        }
    }
}
