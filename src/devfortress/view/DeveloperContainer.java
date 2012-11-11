/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author PC
 */
public class DeveloperContainer extends JPanel {

    //initialize constant variables
    static private final float alpha = 0.8f;
    static private final Color colour = MyColor.orange;
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
        //Create a container for JList
        GlassPanel gp = new GlassPanel(20, 20, 279, 330, 1f, MyColor.middleRed, 10, 10);
        //Initialize items of JList
        String s[] = {"Developer 1", "Developer 2", "Developer 3","Developer 4", "Developer 5", "Developer 6","Developer 7", "Developer 8", "Developer 9","Developer 10", "Developer 11", "Developer 12","Developer 13", "Developer 14", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","e"};
        //create a JList with those items
        JList developerList = new JList(s);
        //create a scrollpane
        JScrollPane scrollPane = new JScrollPane(developerList);
        //config the look and feel of JList
        developerList.setSelectionBackground(MyColor.lightRed);
        developerList.setSelectionForeground(MyColor.darkBlue);
        developerList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        scrollPane.setPreferredSize(new Dimension(250,320));
        //margin (look and feel)
        GlassPanel marginLeft = new GlassPanel(0,0,15,360, 0f, MyColor.darkBlue2,0,0);
        gp.add(marginLeft, BorderLayout.WEST);
        //add the list and scrollpane to their containter
        gp.add(developerList, BorderLayout.CENTER);
        gp.add(scrollPane, BorderLayout.CENTER);
        //add jList to the scrollPane
        scrollPane.getViewport().add(developerList);
        //add list container to developer container
        add(gp, BorderLayout.WEST);
        
        //create a button to hire new developer
        GlassPanel bottom = new GlassPanel(0,0,250,40,0f,null,0,0);
        CustomButton btn = new CustomButton(0,0,150,35,MyColor.redOrangeDark, "Hire Developer");
        btn.addMouseListener(new CustomButtonEvent(MyColor.redOrangeDark, MyColor.redOrange));
        bottom.add(btn);
        gp.add(bottom, BorderLayout.SOUTH);
        
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
