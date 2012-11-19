/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.controllers.GameViewController;
import devfortress.utilities.*;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michael
 */
public class AboutScreen extends JPanel {

    private int x, y, width, height, arcH, arcW;
    private float alpha;
    private Color colour;
    private CustomButton btnBack;
//    private static volatile AboutScreen instance = null;

    public AboutScreen() {
        this.x = 20;
        this.y = 25;
        this.width = 755;
        this.height = 520;
        this.arcW = 20;
        this.arcH = 20;
        this.alpha = .7f;
        this.colour = Colour.YELLOW;
        setOpaque(false);
        init();
    }
//
//    public static AboutScreen getInstance() {
//        if (instance == null) {
//            synchronized (AboutScreen.class) {
//                if (instance == null) {
//                    instance = new AboutScreen();
//                }
//            }
//        }
//        return instance;
//    }

    private void init() {
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 100);
        GlassPanel content = new GlassPanel(500, 500);
        JLabel welcome = new CustomLabel("<html>DevFortress v1.0</html>");
        GlassPanel infoWrapper = new GlassPanel(500, 200);
        JLabel info = new JLabel("<html><center>"
                + "Pham Ngoc Thach "
                + "<br/> "
                + "To Bao Thien Quan "
                + "<br/> "
                + "Nguyen Vinh Thanh "
                + "<br/> "
                + "Le Huynh Ngoc Thuy"
                + "</center></html>") {

            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);
            }
        };

        btnBack = new CustomButton("Back");
        //Global Variables        
        btnBack.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
//        btnBack.addMouseListener(new GameViewController());
        welcome.setForeground(Colour.DARKBLUE);
        info.setFont(new Font("Century Gocthic", Font.PLAIN, 22));
        //add components
        infoWrapper.add(info);
        content.add(welcome);
        content.add(infoWrapper);
        content.add(btnBack);
        add(marginTop, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
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

    public void addBackListener(MouseListener l) {
        btnBack.addMouseListener(l);
        System.out.println("Mouse Listener added");
    }
}
