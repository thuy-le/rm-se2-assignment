/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.MyScrollbarUI;
import devfortress.models.Developer;
import devfortress.utilities.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class AvailableDevelopers extends JPanel {

    private int x, y, width, height, arcH, arcW;
    private float alpha;
    private Color colour;
    private String playerName;
    private JTextField playerTxt;
    private CustomButton submitName;
    //private variables
    private java.util.List<Developer> developers;
    private JList developerList;
    private JLabel devName, mainSkill, workingPrj, status;
    private CustomButton btnHire, btnFeed, btnParty, btnFireDev, btnFeedDev, btnPartyDev, closeButton;

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

    private void init() {
        setLayout(new BorderLayout());
        //global variables
        developerList = new JList();
        devName = new JLabel("Developer Name");
        mainSkill = new JLabel("Main Skill (Level)");
        workingPrj = new JLabel("Working Project");
        status = new JLabel("Status");
        btnHire = new CustomButton("Hire");
        btnFeed = new CustomButton("Feed");
        btnParty = new CustomButton("Party");
        btnFireDev = new CustomButton("Fire");
        btnFeedDev = new CustomButton("Feed");
        btnPartyDev = new CustomButton("Party");

        //local variables
        Color panelColor = Colour.LIGHTBLUE2.brighter();
        Color listColor = Colour.BROWN;
        Color btnColour = Colour.DARKGREEN.darker();
        Color btnOnMouseColour = Colour.DARKGREEN;
        Color btnTextColour = Colour.LIGHTGREEN;
        String devStrings[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        DefaultListModel devModel = new DefaultListModel();
        GlassPanel rightPanel = new GlassPanel(25, 25, 480, 380, 1f, panelColor, 7, 7);
        String picture = "images/i6.png";
        String data[][] = {{"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}};
        String col[] = {"Skill", "Level"};
        java.util.List<CustomButton> btnList = new LinkedList<>();
        CustomList cl = new CustomList(developerList, btnList);
        JLabel developerDetail = new JLabel("Developer Details");
        JPanel top = new JPanel();
        JPanel topR = new JPanel();
        JPanel bottom = new JPanel();
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        JTable table = new JTable(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        GlassPanel hireDevHeader = new GlassPanel(800, 70);
        GlassPanel hireDevBottom = new GlassPanel(800, 70);
        closeButton = new CustomButton("Close");
        JLabel title = new CustomLabel("Available Developers");

        for (int i = 0; i < devStrings.length; i++) {
            Developer dev = new Developer();
            devModel.addElement(dev);
        }

        developerList.addListSelectionListener(new AvailableDevelopers.MyListEvent());
        developerList.setCellRenderer(new CustomListRenderer());
        developerList.setModel(devModel);

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
        cl.setColor(listColor);
        btnHire.setTextColour(btnTextColour);
        btnFeed.setTextColour(btnTextColour);
        btnParty.setTextColour(btnTextColour);
        btnHire.setColour(btnColour);
        btnHire.setOnMouseColor(btnOnMouseColour);
        btnFeed.setColour(btnColour);
        btnFeed.setOnMouseColor(btnOnMouseColour);
        btnParty.setColour(btnColour);
        btnParty.setOnMouseColor(btnOnMouseColour);
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
        table.setBorder(BorderFactory.createLineBorder(listColor, 1));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(listColor, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(listColor);
        header.setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(listColor);
        tableScroll.getViewport().setBackground(listColor);
        bottom.setBackground(panelColor);
        bottom.setLayout(new FlowLayout());
        btnFireDev.setButtonSize(0, 0, 70, 35);
        btnFeedDev.setButtonSize(0, 0, 70, 35);
        btnPartyDev.setButtonSize(0, 0, 70, 35);
        btnFireDev.setTextColour(btnTextColour);
        btnFeedDev.setTextColour(btnTextColour);
        btnFeedDev.setTextColour(btnTextColour);
        btnFireDev.setColour(btnColour);
        btnFireDev.setOnMouseColor(btnOnMouseColour);
        btnFeedDev.setColour(btnColour);
        btnFeedDev.setOnMouseColor(btnOnMouseColour);
        btnPartyDev.setColour(btnColour);
        btnPartyDev.setOnMouseColor(btnOnMouseColour);
        closeButton.setColour(Colour.BROWN);
        closeButton.setOnMouseColor(Colour.BROWN.brighter());
        title.setForeground(Colour.BROWN);
        //add components
        top.add(imageIcon);
        topR.add(devName);
        topR.add(mainSkill);
        topR.add(workingPrj);
        topR.add(status);
        top.add(topR);
        bottom.add(btnFireDev);
        bottom.add(btnFeedDev);
        bottom.add(btnPartyDev);
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

    private class MyListEvent implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            Developer dev = (Developer) developerList.getSelectedValue();
            setDevName(dev.getName());
        }
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

    public void addSubmitNameListener(MouseListener l) {
        submitName.addMouseListener(l);
    }
}
