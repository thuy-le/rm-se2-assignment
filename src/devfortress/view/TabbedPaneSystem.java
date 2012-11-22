/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomList;
import devfortress.utilities.CustomButton;
import devfortress.utilities.Colour;
import devfortress.view.interfaces.SystemInterface;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class TabbedPaneSystem extends JPanel implements SystemInterface, Observer {

    //initialize constant variables
    static private final String picture = "images/i5.png";
    static private final float alpha = 0.8f;
    static private final Color colour = Colour.MIDDLERED;
    static private final int x = 0;
    static private final int y = 5;
    static private final int width = 793;
    static private final int height = 418;
    static private final int arcW = 10;
    static private final int arcH = 10;
    //declare private variables
    private JList devList, prjList;
    private CustomButton btnHire, btnAdd;
    private JLabel budget, welcome;
    private final DefaultListModel devModel, prjModel;
    private TabbedPaneDeveloper tabDev;

    //constructor
    public TabbedPaneSystem(TabbedPaneDeveloper tabDev) {
        setOpaque(false);
        devModel = new DefaultListModel();
        prjModel = new DefaultListModel();
        this.tabDev = tabDev;
        init();
    }

    private void init() {
        /*
         * ########### initialize variables ##########
         */
        //$$$$$-----Global variables
        budget = new JLabel("$25000000");
        welcome = new JLabel();
        devList = new JList();
        prjList = new JList();
        btnHire = new CustomButton("Hire Developer");
        btnAdd = new CustomButton("Add Project");

        //$$$$$-----Local variable for UI
        List<CustomButton> listDevBtn = new LinkedList<CustomButton>();
        List<CustomButton> listPrjBtn = new LinkedList<CustomButton>();
        listDevBtn.add(btnHire);
        listPrjBtn.add(btnAdd);
        Font font = new Font("Century Gothic", Font.PLAIN, 16);
        Icon imgIcon = new ImageIcon(picture);
        GlassPanel marginTop = new GlassPanel(0, 0, 250, 15, 0f, null, 0, 0);
        GlassPanel gp3 = new GlassPanel(15, 15, 250, 390, .9f, Colour.YELLOW, 15, 15);
        JLabel imageIcon = new JLabel(imgIcon);
        imageIcon.setPreferredSize(new Dimension(200, 200));
        JLabel label = new JLabel("  Your budget is:");
        devList.setModel(devModel);
        prjList.setModel(prjModel);
        //add list and button(s) together
        CustomList developerList = new CustomList(devList, listDevBtn);
        CustomList projectList = new CustomList(prjList, listPrjBtn);
        /*
         * ########## Adjust look and feel ##########
         */
        developerList.setColor(Colour.DARKBLUE);
        devList.setFont(font);
        devList.setSelectionBackground(Colour.LIGHTRED);
        devList.setSelectionForeground(Colour.DARKPINK);
        btnHire.setColour(Colour.DARKBLUE);
        btnHire.setTextColour(Colour.LIGHTBLUE);
        projectList.setColor(Colour.DARKBLUE);
        prjList.setFont(font);
        prjList.setSelectionBackground(Colour.LIGHTRED);
        prjList.setSelectionForeground(Colour.DARKPINK);
        btnAdd.setColour(Colour.DARKBLUE);
        btnAdd.setTextColour(Colour.LIGHTBLUE);
        welcome.setFont(new Font("Century Gothic", Font.BOLD, 17));
        welcome.setForeground(Colour.DARKBLUE);
        budget.setFont(new Font("Century Gothic", Font.BOLD, 20));
        label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
//        String welcomeStr = "<html>Hi, ◕‿◕</html>";
//        welcome.setText(welcomeStr);
        /*
         * ########## set border layout to the container ##########
         */
        setLayout(new BorderLayout());
        gp3.setLayout(new FlowLayout());
        //-------Add components together
        add(developerList, BorderLayout.WEST);
        add(projectList, BorderLayout.CENTER);
        add(gp3, BorderLayout.EAST);
        gp3.add(marginTop, BorderLayout.NORTH);
        gp3.add(welcome);
        gp3.add(imageIcon);
        gp3.add(label);
        gp3.add(budget);
        
        devList.addListSelectionListener(new MyListEvent());
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

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void addBtnAddProjectListener(MouseListener l) {
        btnAdd.addMouseListener(l);
    }

    @Override
    public void addBtnHireDevListener(MouseListener l) {
        btnHire.addMouseListener(l);
    }

    @Override
    public void addDevListListener(MouseListener l) {
        devList.addMouseListener(l);
    }

    @Override
    public void addProjectListListener(MouseListener l) {
        prjList.addMouseListener(l);
    }
    
    public DefaultListModel getDevModel(){
        return devModel;
    }

    @Override
    public void setBudget(long budget) {
        this.budget.setText("$" + budget);
    }

    @Override
    public void setPlayerName(String name) {
        String welcomeStr = "<html>Hi, " + name + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        budget.setText(model.getBudget() + "");
        devModel.removeAllElements();
        prjModel.removeAllElements();
        for (Developer dev : model.getDevelopers()) {
            devModel.addElement(dev);
        }
        for (Project p : model.getProjects()) {
            prjModel.addElement(p);
        }
        String welcomeStr = "<html>Hi, " + model.getPlayerName() + " ◕‿◕</html>";
        welcome.setText(welcomeStr);
        

    }
    
    private class MyListEvent implements ListSelectionListener {

        private int selectedIndex;

        public MyListEvent() {
            selectedIndex = devList.getSelectedIndex();
        }
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (selectedIndex != devList.getSelectedIndex()) {
                selectedIndex = devList.getSelectedIndex();
                JTabbedPane tp = (JTabbedPane) getParent();
                tp.setSelectedIndex(1);
                tabDev.showDeveloper((Developer)devList.getSelectedValue());
            }
        }
    }
}
