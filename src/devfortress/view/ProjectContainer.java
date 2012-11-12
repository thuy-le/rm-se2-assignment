/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class ProjectContainer extends JPanel {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.youngGreen;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 750;
    static private final int height = 420;
    static private final int arcW = 10;
    static private final int arcH = 10;

    //constructor
    public ProjectContainer() {
        setOpaque(false);
        init();
    }

    private void init() {
        //set border layout to the container
        setLayout(new BorderLayout());

        //-------Left Hand Side:
            //Create a container for contents on the left
        GlassPanel gp = new GlassPanel(20, 20, 279, 330, 1f, Colour.darkGreen, 10, 10);
            //Initialize items of JList
        String s[] = {"Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6", "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12", "Project 13", "Project 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
            //create a JList with those items
        JList projectList = new JList(s);
            //create a scrollpane
        JScrollPane scrollPane = new JScrollPane(projectList);
            //adjust look and feel
        projectList.setSelectionBackground(Colour.lightGreen);
        projectList.setSelectionForeground(Colour.darkGreen);
        projectList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        scrollPane.setPreferredSize(new Dimension(250, 320));
        GlassPanel marginLeft = new GlassPanel(0, 0, 15, 360, 0f, Colour.darkBlue2, 0, 0);
        gp.add(marginLeft, BorderLayout.WEST);
            //Button: Add new Project
        GlassPanel bottom = new GlassPanel(0, 0, 250, 40, 0f, null, 0, 0);
        CustomButton btnAdd = new CustomButton(0, 0, 150, 35, Colour.darkGreen, "Add Project");
        btnAdd.addMouseListener(new CustomButtonEvent(Colour.darkGreen, Colour.darkGreenYellow));
            //add components
        gp.add(projectList, BorderLayout.CENTER);
        gp.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(projectList);
        add(gp, BorderLayout.WEST);
        bottom.add(btnAdd);
        gp.add(bottom, BorderLayout.SOUTH);

        //------Right Hand Side:
        //---Top:
            //Create a container for contents on the right
        GlassPanel rightPanel = new GlassPanel(25, 25, 425, 380, 1f, Colour.lightOrange, 5, 5);
            //display developer name
        JLabel developerDetail = new JLabel("Project Details");
        //the "top" panel contain (1) avatar, and basic details about developer
        JPanel top = new JPanel();
        top.setOpaque(false);
            //display cancel button
        String picture = "images/cancel.png";
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new JLabel(imgIcon) {
            @Override
            public JToolTip createToolTip() {
                JToolTip tooltip = super.createToolTip();
                tooltip.setFont(new Font("Century Gothic", Font.BOLD, 16));
                tooltip.setForeground(Colour.darkGreen);
                tooltip.setBorder(BorderFactory.createLineBorder(Colour.darkGreen, 1));
                tooltip.setOpaque(false);
                return tooltip;
            }
        };
        imageIcon.setOpaque(false);
        imageIcon.setPreferredSize(new Dimension(50, 50));
        imageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //display basic details
        JPanel topR = new JPanel();
        JLabel devName = new JLabel("Project Name");
        JLabel mainSkill = new JLabel("Deadline");
        JLabel workingPrj = new JLabel("Cost");
        JLabel status = new JLabel("Status");
            //adjust look and feel
        imageIcon.setToolTipText("Cancel this project");
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
        topR.add(devName);
        topR.add(mainSkill);
        topR.add(workingPrj);
        topR.add(status);
        top.add(topR);
        top.add(imageIcon);
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(top, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);

        //---Bottom: contains a list of developers who belong to the project
            //Create a table:
        String data[][] = {{"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}};
        String col[] = {"Developer", "Skill"};
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
        tableScroll.setPreferredSize(new Dimension(400, 220));
        tableScroll.setBackground(Colour.lightOrange);
        tableScroll.getViewport().setBackground(Colour.lightOrange);
        tableScroll.setBounds(30, 0, 380, 150);
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
