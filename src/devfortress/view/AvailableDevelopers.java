/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.MyScrollbarUI;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.Skill;
import devfortress.utilities.*;
import java.awt.*;
import java.awt.event.MouseListener;
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
public class AvailableDevelopers extends JPanel implements Observer {

    private static Color panelColor = Colour.LIGHTORANGE;
    private static Color listColor = Colour.DARKBLUE;
    private static Color btnColour = Colour.DARKBLUE;
    private static Color btnOnMouseColour = Colour.DARKBLUE2;
    private static Color btnTextColour = Colour.LIGHTBLUE;    
    private static Color tableColour = Colour.ORANGE;    
    private int x, y, width, height, arcH, arcW;
    private float alpha;
    private Color colour;
    private String playerName;
    //private variables
    private JList developerList;
    private JLabel devName, mainSkill, workingPrj, status;
    private CustomButton btnHire, closeButton;
    private GlassPanel rightPanel;
    private JTable table;
    private DefaultTableModel skillModel;
    private DefaultListModel devModel;
    private Developer devToHire;
    GameEngine model;

    public AvailableDevelopers() {
        this.x = 5;
        this.y = 5;
        this.width = 785;
        this.height = 555;
        this.arcW = 20;
        this.arcH = 20;
        this.alpha = .7f;
        this.colour = Colour.MATENGA;
        setOpaque(false);
        init();
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
    
    private void init() {
        setLayout(new BorderLayout());
        //global variables
        devModel = new DefaultListModel();
        skillModel = new DefaultTableModel(1, 2);
        rightPanel = new GlassPanel(25, 25, 480, 380, 1f, panelColor, 7, 7);
        developerList = new JList();
        developerList.addListSelectionListener(new AvailableDevelopers.MyListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devModel);
        java.util.List<CustomButton> btnList = new LinkedList<CustomButton>();
        btnHire = new CustomButton("Hire Developer");
        CustomList cl = new CustomList(developerList, btnList);
        JLabel developerDetail = new JLabel("Developer Details");
        JPanel top = new JPanel();
        JPanel topR = new JPanel();
        devName = new JLabel("Developer Name");
        mainSkill = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JLabel("Status");
        table = new CustomTable(skillModel);
        String picture = "images/i6.png";
        JPanel bottom = new JPanel();
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        GlassPanel hireDevHeader = new GlassPanel(800, 70);
        GlassPanel hireDevBottom = new GlassPanel(800, 70);
        closeButton = new CustomButton("Close");
        JLabel title = new CustomLabel("Available Developers");        

        //------Create a JList of developers
        //list
        //buttons
        btnList.add(btnHire);
        //add button(s) and list together
        //-------Adjust look and feel
        btnHire.setButtonSize(0, 0, 150, 35);
        cl.setColor(listColor);
        btnHire.setTextColour(btnTextColour);
        btnHire.setColour(btnColour);
        btnHire.setOnMouseColor(btnOnMouseColour);
        developerList.setSelectionBackground(Colour.LIGHTORANGE);
        developerList.setSelectionForeground(Colour.REDORANGEDARK);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        topR.setBackground(panelColor);
        topR.setPreferredSize(new Dimension(220, 100));
        topR.setLayout(new GridLayout(4, 1));
        top.setBackground(panelColor);
        top.setLayout(new GridLayout(1, 2));
        devName.setFont(font);
        mainSkill.setFont(font);
        workingPrj.setFont(font);
        status.setFont(font);
        developerDetail.setForeground(listColor);
        developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));
        table.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        table.setBorder(BorderFactory.createLineBorder(tableColour, 1));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(tableColour, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(tableColour);
        header.setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 215));
        tableScroll.setBackground(listColor);
        tableScroll.getViewport().setBackground(tableColour);
        bottom.setBackground(panelColor);
        bottom.setLayout(new FlowLayout());
        closeButton.setColour(btnColour);
        closeButton.setOnMouseColor(btnColour.brighter());
        title.setForeground(Colour.DARKBLUE);
        //add components
        top.add(imageIcon);
        topR.add(devName);
        topR.add(mainSkill);
        topR.add(workingPrj);
        topR.add(status);
        top.add(topR);
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(top, BorderLayout.CENTER);
        rightPanel.add(tableScroll, BorderLayout.NORTH);
        rightPanel.add(bottom, BorderLayout.SOUTH);
        hireDevHeader.add(title);
        hireDevBottom.add(closeButton);
        add(hireDevHeader, BorderLayout.NORTH);
        add(cl, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(hireDevBottom, BorderLayout.SOUTH);
        //add event
    }

    public void addCloseHirePaneEvent(MouseListener l) {
        closeButton.addMouseListener(l);
    }
    
    public void addHireDeveloperEvent(MouseListener l){
        btnHire.addMouseListener(l);
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
                setDevToHire((Developer)developerList.getSelectedValue());
            }
        }
    }

    public synchronized void showDeveloper(Developer dev) {
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
        model = (GameEngine) o;
        devModel.clear();
        for (Developer dev : model.getMarketDevelopers()) {
            devModel.addElement(dev);
        }
        showDeveloper((Developer) developerList.getSelectedValue());
    }
    

    //Getter and Setter
    public String getDevName() {
        return devName.getText();
    }

    public void setDevName(String devName) {
        this.devName.setText(devName);
        this.devName.repaint();

    }
    
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
