/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.*;
import java.awt.*;
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
    private static volatile AboutScreen instance = null;
    
    private AboutScreen() {
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
    
    public static AboutScreen getInstance(){
        if (instance == null) {
            synchronized (DevFortress.class) {
                if (instance == null) {
                    instance = new AboutScreen();
                }
            }
        }
        return instance;
    }
    
    private void init() {
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 100);
        GlassPanel content = new GlassPanel(500,500);      
        JLabel welcome = new CustomLabel("About DevFortress");

        CustomButton btnBack = new CustomButton("Back");
        
        //Global Variables        
               
        btnBack.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        btnBack.addMouseListener(new GameController());
        welcome.setForeground(Colour.DARKBLUE);
        
        content.add(welcome);       
        add(marginTop, BorderLayout.NORTH);
        content.add(btnBack);
        add(content, BorderLayout.CENTER);
    }
    
    @Override
    public void paintComponent(Graphics g){
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
}

