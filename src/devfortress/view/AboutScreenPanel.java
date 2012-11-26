/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.GlassPanel;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michael
 */
public class AboutScreenPanel extends JPanel {

    private static final int x = 20;
    private static final int y = 25;
    private static final int width = 755;
    private static final int height = 520;
    private static final int arcH = 20;
    private static final int arcW = 20;
    private static final float alpha = .7f;
    private static final Color colour = Colors.YELLOW;
    private static final String INFO = "<html><center>Pham Ngoc Thach<br/>"
            + "To Bao Thien Quan<br/>"
            + "Tang Vinh Thanh<br/>"
            + "Le Huynh Ngoc Thuy</center></html>";
    private CustomButton btnBack;

    public AboutScreenPanel() {
        setOpaque(false);
        btnBack = new CustomButton("Back");
        init();
    }

    private void init() {
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 100);
        GlassPanel content = new GlassPanel(500, 500);
        JLabel welcome = new CustomLabel("<html>DevFortress v1.0</html>");
        GlassPanel infoWrapper = new GlassPanel(500, 200);
        JLabel info = new JLabel(INFO) {

            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);
            }
        };
        welcome.setForeground(Colors.DARKBLUE);
        info.setFont(new Font("Century Gocthic", Font.PLAIN, 22));
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
    }
}
