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
import javax.swing.table.JTableHeader;

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
    //constructor

    public TabbedPaneDeveloper() {
        setOpaque(false);
        init();
    }

    private void init() {
        //local variables
        String devStrings[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        DefaultListModel devModel = new DefaultListModel();
        GlassPanel rightPanel = new GlassPanel(25, 25, 480, 380, 1f, Colour.LIGHTORANGE, 7, 7);
        for (int i = 0; i < devStrings.length; i++) {
            Developer dev = new Developer(devStrings[i]);
            devModel.addElement(dev);
        }
        developerList = new JList();
        developerList.addListSelectionListener(new MyListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devModel);
        //buttons
        String picture = "images/i6.png";
        String data[][] = {{"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}};
        String col[] = {"Skill", "Level"};
        List<CustomButton> btnList = new LinkedList<>();
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
//        btnHire.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
//        btnFeed.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
//        btnParty.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        JPanel bottom = new JPanel();
        btnFireDev = new CustomButton("Fire");
        btnFeedDev = new CustomButton("Feed");
        btnPartyDev = new CustomButton("Drink");
        JTable table = new JTable(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //-------Add component
        add(cl, BorderLayout.WEST);

        //------Right Hand Side:
        //---Top:
        //Create a container for contents on the right
        //display developer name
        //the "top" panel contain (1) avatar, and basic details about developer
        //display avatar
        //display basic details
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
        table.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        table.setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.ORANGE);
        header.setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colour.ORANGE);
        tableScroll.getViewport().setBackground(Colour.ORANGE);
        bottom.setBackground(Colour.LIGHTORANGE);
        bottom.setLayout(new FlowLayout());
        btnFireDev.setButtonSize(0, 0, 70, 35);
//        btnFireDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnFeedDev.setButtonSize(0, 0, 70, 35);
//        btnFeedDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnPartyDev.setButtonSize(0, 0, 70, 35);
//        btnPartyDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        //add components
        bottom.add(btnFireDev);
        bottom.add(btnFeedDev);
        bottom.add(btnPartyDev);
        rightPanel.add(tableScroll, BorderLayout.NORTH);
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
    public void update(Observable o, Object arg) {
    }

    private class MyListEvent implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            Developer dev = (Developer) developerList.getSelectedValue();
            setDevName(dev.getName());
        }
    }
}
