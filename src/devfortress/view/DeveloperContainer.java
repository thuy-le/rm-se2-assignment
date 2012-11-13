/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colour;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class DeveloperContainer extends JPanel {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.orange;
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
        //set border layout to the container
        setLayout(new BorderLayout());

        //-------Left Hand Side:
        //Create a container for JList
        GlassPanel gp = new GlassPanel(20, 20, 279, 330, 1f, Colour.middleRed, 10, 10);
        //Initialize items of JList
        String s[] = {"Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5", "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10", "Developer 11", "Developer 12", "Developer 13", "Developer 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        //create a JList with those items
        JList developerList = new JList(s);
        //create a scrollpane
        JScrollPane scrollPane = new JScrollPane(developerList);
        //adjust look and feel
        developerList.setSelectionBackground(Colour.lightRed);
        developerList.setSelectionForeground(Colour.darkBlue);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        scrollPane.setPreferredSize(new Dimension(250, 320));
        GlassPanel marginLeft = new GlassPanel(0, 0, 15, 360, 0f, Colour.darkBlue2, 0, 0);
        gp.add(marginLeft, BorderLayout.WEST);
        //add list container to developer container
        add(gp, BorderLayout.WEST);
        //Button: Hire new developer
        GlassPanel bottom = new GlassPanel(0, 0, 250, 40, 0f, null, 0, 0);
        CustomButton btnHire = new CustomButton(0, 0, 150, 35, Colour.redOrangeDark, "Hire Developer");
        btnHire.addMouseListener(new CustomButtonEvent(Colour.redOrangeDark, Colour.redOrange));
        //add components
        bottom.add(btnHire);
        gp.add(developerList, BorderLayout.CENTER);
        gp.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(developerList);
        gp.add(bottom, BorderLayout.SOUTH);
        

        //------Right Hand Side:
        //---Top:
            //Create a container for contents on the right
        GlassPanel rightPanel = new GlassPanel(25, 25, 425, 380, 1f, Colour.lightOrange, 5, 5);
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
        topR.setBackground(Colour.lightOrange);
        topR.setPreferredSize(new Dimension(200, 100));
        topR.setLayout(new GridLayout(4, 1));
        top.setBackground(Colour.lightOrange);
        top.setLayout(new GridLayout(1, 2));
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        devName.setFont(font);
        mainSkill.setFont(font);
        workingPrj.setFont(font);
        status.setFont(font);
        developerDetail.setForeground(Colour.brown);
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
        String data[][] = {{"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}, {"Skill 1", "Level 7"}};
        String col[] = {"Skill", "Level"};
        JTable table = new JTable(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
            //adjust look and feel:
        table.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        table.setBorder(BorderFactory.createLineBorder(Colour.brown, 1));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.brown, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.orange);
        header.setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension (400,220));
        tableScroll.setBackground(Colour.lightOrange);
        tableScroll.getViewport().setBackground(Colour.lightOrange);
        tableScroll.setBounds(30,0,380,150);
        tableScroll.getViewport().setViewPosition(new Point(0, 0));
        
            //add components
        rightPanel.add(tableScroll, BorderLayout.NORTH);
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
}
