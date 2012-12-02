/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Slot extends JPanel {

    private static int width = 150;
    private static int height = 150;
    private static int x = 0;
    private static int y = 0;
    private static Color colour = Colors.DARKBLUE;
    private String devName;
    private boolean isWorking;
    private BufferedImage avatar;

    public Slot() {
        devName = "Anne";
        isWorking = false;
        avatar = null;
        setOpaque(false);
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
        repaint();
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1f));
        if (!isWorking) {
            try {
                avatar = ImageIO.read(new File("images/computer.png"));
            } catch (IOException e) {
            }
        } else {
            try {
                avatar = ImageIO.read(new File("images/working.png"));
            } catch (IOException e) {
            }
            g.setFont(new Font("Century Gothic", Font.BOLD, 17));
            g.setColor(colour);
            int stringLen = (int) g.getFontMetrics().getStringBounds(devName, g).getWidth();
            g.drawString(devName, width / 2 - stringLen / 2, y + height + 20);
        }
        g2d.drawImage(avatar, x, y, width, height, this);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
