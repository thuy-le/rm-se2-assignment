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
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
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
public class TabbedPaneDeveloper extends JPanel implements DeveloperInterface, Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.ORANGE;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //private variables
    private List<Developer> developers;
    private JList developerList;
    private JLabel devName, mainSkill, workingPrj, status;
    private CustomButton btnHire, btnFeed, btnParty, btnFireDev, btnFeedDev, btnPartyDev;
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
        //local variables
//        String devStrings[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        devModel = new DefaultListModel();
        skillModel = new DefaultTableModel(1, 2);
        rightPanel = new GlassPanel(25, 25, 480, 380, 1f, Colour.LIGHTORANGE, 7, 7);
//        for (int i = 0; i < devStrings.length; i++) {
//            Developer dev = new Developer(devStrings[i]);
//            Developer dev = new Developer();
//            devModel.addElement(dev);
//        }
        developerList = new JList();
        developerList.addListSelectionListener(new MyListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devModel);
        //buttons
        String picture = "images/i6.png";
//        String data[][] = {{"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}};
        String col[] = {"Skill", "Level"};
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
        //list
        //buttons
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
        //Create a table:

        //add buttons

        //adjust look and feel:


        bottom.setBackground(Colour.LIGHTORANGE);
        bottom.setLayout(new FlowLayout());
        btnFireDev.setButtonSize(0, 0, 70, 35);
        btnFeedDev.setButtonSize(0, 0, 70, 35);
        btnPartyDev.setButtonSize(0, 0, 70, 35);
        //add components
        bottom.add(btnFireDev);
        bottom.add(btnFeedDev);
        bottom.add(btnPartyDev);
        rightPanel.add(((CustomTable) table).getTableScroll(), BorderLayout.NORTH);
        rightPanel.add(bottom, BorderLayout.SOUTH);

    }

    //override the paint component method
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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

            for (int i = 0; i < skillModel.getRowCount(); i++) {
                skillModel.removeRow(i);
            }
            for (Skill skill : dev.getSkills().values()) {
                Object[] rowData = {skill.getSkillInfo().getName(), skill.getLevel()};
                skillModel.addRow(rowData);
            }
            Object[] ids = {"Skill", "Level"};
            skillModel.setColumnIdentifiers(ids);

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

class CustomTable extends JTable {

    private JScrollPane tableScroll;

    public CustomTable(TableModel dm) {
        super(dm);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.ORANGE);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colour.ORANGE);
        tableScroll.getViewport().setBackground(Colour.ORANGE);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
