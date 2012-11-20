/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.enumerations.AreaName;
import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomList;
import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.models.FunctionalArea;
import devfortress.models.Project;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.ProjectInterface;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class TabbedPaneProject extends JPanel implements ProjectInterface, Observer {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.YOUNGGREEN;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    static private final Color contentColor = Colour.LIGHTORANGE;
    private static volatile TabbedPaneProject instance = null;

    public static TabbedPaneProject getInstance() {
        if (instance == null) {
            synchronized (TabbedPaneProject.class) {
                if (instance == null) {
                    instance = new TabbedPaneProject();
                }
            }
        }
        return instance;
    }

    //constructor
    public TabbedPaneProject() {
        setOpaque(false);
        init();
    }

    private void init() {
        //set border layout to the container
        setLayout(new BorderLayout());

        //------Create a JList of developers
        //list
        String projects[] = {"Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6", "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12", "Project 13", "Project 14", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "e"};
        DefaultListModel prjModel = new DefaultListModel();
        for (int i = 0; i < projects.length; i++) {
            EnumMap<AreaName, FunctionalArea> prjFA = new EnumMap<>(AreaName.class);
            prjFA.put(AreaName.CODING, new FunctionalArea(AreaName.CODING, 10, 0, true));
            Project prj = new Project(30, 2, prjFA);
            prjModel.addElement(prj);
        }
        JList prjList = new JList();
        prjList.setModel(prjModel);
        //buttons
        java.util.List<CustomButton> btnList = new LinkedList<>();
        CustomButton btnAdd = new CustomButton("Add Project");
        btnList.add(btnAdd);
        //add button(s) and list together
        CustomList projectList = new CustomList(prjList, btnList);
        //-------Adjust look and feel
        btnAdd.setColour(Colour.DARKBLUE);
        projectList.setColor(Colour.DARKBLUE);
        btnAdd.setTextColour(Colour.LIGHTBLUE);
//        btnAdd.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        prjList.setSelectionBackground(Colour.LIGHTGREEN);
        prjList.setSelectionForeground(Colour.DARKBLUE);
        prjList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        //-------Add component
        add(projectList, BorderLayout.WEST);

        //------Right Hand Side:
        //---Top:
        //Create a container for contents on the right
        GlassPanel rightPanel = new GlassPanel(25, 25, 480, 380, 1f, contentColor, 7, 7);
        //display developer name
        JLabel developerDetail = new JLabel("Project Details");
        //the "top" panel contain (1) avatar, and basic details about developer
        JPanel top = new JPanel();
        top.setOpaque(false);
        //display cancel button
        String picture = "images/cancel.png";
        Icon imgIcon = new ImageIcon(picture);
        JLabel imageIcon = new CustomLabel(imgIcon);
        imageIcon.setOpaque(false);
        imageIcon.setPreferredSize(new Dimension(50, 50));
        imageIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //display basic details
        JPanel topR = new JPanel();
        JLabel prjName = new JLabel("Project Name");
        JLabel deadline = new JLabel("Deadline");
        JLabel cost = new JLabel("Cost");
        JLabel info1 = new JLabel("Info1");
        JLabel status = new JLabel("Status");
        //adjust look and feel
        imageIcon.setToolTipText("Cancel this project");
        topR.setBackground(contentColor);
        topR.setPreferredSize(new Dimension(220, 120));
        topR.setLayout(new GridLayout(5, 1));
        top.setBackground(contentColor);
        top.setLayout(new GridLayout(1, 2));
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        prjName.setFont(font);
        deadline.setFont(font);
        info1.setFont(font);
        status.setFont(font);
        cost.setFont(font);
        developerDetail.setForeground(Colour.DARKBLUE);
        developerDetail.setFont(new Font("Century Gothic", Font.BOLD, 22));
        //add components
        topR.add(prjName);
        topR.add(deadline);
        topR.add(info1);
        topR.add(cost);
        topR.add(status);
        top.add(topR);
        top.add(imageIcon);
        rightPanel.add(developerDetail, BorderLayout.NORTH);
        rightPanel.add(top, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);

        //---Bottom: contains a list of developers who belong to the project
        //Create a table:
        String data[][] = {{"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}, {"Developer 1", "Skill"}};
        String col[] = {"Developer", "Skill"};
        JTable table = new JTable(data, col) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //add buttons
        JPanel bottom = new JPanel();
        CustomButton btnAddDev = new CustomButton("Add Developer");
        CustomButton btnRemoveDev = new CustomButton("Remove Developer");

        //adjust look and feel:
        table.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        table.setBorder(BorderFactory.createLineBorder(Colour.GREEN, 1));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.DARKGREEN, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.DARKGREEN);
        header.setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colour.DARKGREEN);
        tableScroll.getViewport().setBackground(contentColor);
        bottom.setBackground(contentColor);
        bottom.setLayout(new FlowLayout());
        btnAddDev.setButtonSize(0, 0, 175, 35);
//        btnAddDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnRemoveDev.setButtonSize(0, 0, 175, 35);
//        btnRemoveDev.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        //add components
        bottom.add(btnAddDev);
        bottom.add(btnRemoveDev);
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
        g2d.drawRoundRect(x, y, width, height, width, height);
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void addNewProjectListener(MouseListener l) {
    }

    @Override
    public void cancelProjectListener(MouseListener l) {
    }

    @Override
    public void addDevToProjectListener(MouseListener l) {
    }

    @Override
    public void removeDevFromProjectListener(MouseListener l) {
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
