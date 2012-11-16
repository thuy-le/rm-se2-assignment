/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.DeveloperInterface;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class DeveloperContainer extends JPanel implements DeveloperInterface{

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.ORANGE;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 750;
    static private final int height = 420;
    static private final int arcW = 10;
    static private final int arcH = 10;

    //constructor
    public DeveloperContainer() {
        setOpaque(false);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        //------Create a JList of developers
        //list
        String s[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        JList developerList = new JList(s);
        //buttons
        List<CustomButton> btnList = new LinkedList<>();
        CustomButton btnHire = new CustomButton("Hire");
        btnList.add(btnHire);
        CustomButton btnFeed = new CustomButton("Feed");
        btnList.add(btnFeed);
        CustomButton btnParty = new CustomButton("Party");
        btnList.add(btnParty);

        //add button(s) and list together
        CustomList cl = new CustomList(developerList, btnList);
        //-------Adjust look and feel
        btnHire.setButtonSize(0, 0, 55, 35);
        btnFeed.setButtonSize(0, 0, 55, 35);
        btnParty.setButtonSize(0, 0, 55, 35);
        cl.setColor(Colour.DARKBLUE);
        btnHire.setTextColour(Colour.LIGHTBLUE);
        btnHire.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnFeed.setTextColour(Colour.LIGHTBLUE);
        btnFeed.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnParty.setTextColour(Colour.LIGHTBLUE);
        btnParty.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        developerList.setSelectionBackground(Colour.LIGHTORANGE);
        developerList.setSelectionForeground(Colour.REDORANGEDARK);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        //-------Add component
        add(cl, BorderLayout.WEST);

        //------Right Hand Side:
        //---Top:
        //Create a container for contents on the right
        GlassPanel rightPanel = new GlassPanel(25, 25, 450, 380, 1f, Colour.LIGHTORANGE, 5, 5);
        //display developer name
        JLabel developerDetail = new JLabel("Developer Details");
        //the "top" panel contain (1) avatar, and basic details about developer
        JPanel top = new JPanel();
        //display avatar
        String picture = "images/i6.png";
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon);
        //display basic details
        JPanel topR = new JPanel();
        JLabel devName = new JLabel("Developer Name");
        JLabel mainSkill = new JLabel("Main Skill (Level)");
        JLabel workingPrj = new JLabel("Working Project");
        JLabel status = new JLabel("Status");
        //adjust look and feel
        topR.setBackground(Colour.LIGHTORANGE);
        topR.setPreferredSize(new Dimension(200, 100));
        topR.setLayout(new GridLayout(4, 1));
        top.setBackground(Colour.LIGHTORANGE);
        top.setLayout(new GridLayout(1, 2));
        Font font = new Font("Century Gothic", Font.BOLD, 17);
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
        String data[][] = {{"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}};
        String col[] = {"Skill", "Level"};
        JTable table = new JTable(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //add buttons
        JPanel bottom = new JPanel();
        CustomButton btnFireDev = new CustomButton("Fire");
        CustomButton btnFeedDev = new CustomButton("Feed");
        CustomButton btnPartyDev = new CustomButton("Party");
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
        tableScroll.setPreferredSize(new Dimension(400, 180));
        tableScroll.setBackground(Colour.ORANGE);
        tableScroll.getViewport().setBackground(Colour.ORANGE);
        tableScroll.setBounds(30, 0, 380, 150);
        tableScroll.getViewport().setViewPosition(new Point(0, 0));
        bottom.setPreferredSize(new Dimension(440, 45));
        bottom.setBackground(Colour.LIGHTORANGE);
        bottom.setLayout(new FlowLayout());
        btnFireDev.setButtonSize(0, 0, 70, 35);
        btnFireDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnFeedDev.setButtonSize(0, 0, 70, 35);
        btnFeedDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnPartyDev.setButtonSize(0, 0, 70, 35);
        btnPartyDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void hireNewDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fireDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void feedDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void party(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void viewDeveloper(Developer developer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void feedAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void partyAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
