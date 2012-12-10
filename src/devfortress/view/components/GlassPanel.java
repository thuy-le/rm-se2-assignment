package devfortress.view.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * A custom JPanel which could be transparent.
 *
 * @author Team Poseidon
 */
public class GlassPanel extends JPanel {
    //declare variables

    public int x;
    public int y;
    public int width;
    public int height;
    public float alpha;
    public Color colour;
    public int arcW;
    public int arcH;

    /**
     * Simple constructor of
     * <code>GlassPanel</code>.
     *
     * @param width width of the panel
     * @param height height of the panel
     */
    public GlassPanel(final int width, final int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.alpha = 0f;
        this.colour = null;
        this.arcW = 0;
        this.arcH = 0;
        setOpaque(false);
    }

    /**
     * Constructor of
     * <code>GlassPanel</code>.
     *
     * @param x position of the panel on x axis
     * @param y position of the panel on y axis
     * @param width width of the panel
     * @param height height of the panel
     * @param alpha opacity rate of the background
     * @param colour background color of the panel
     * @param arcW the horizontal diameter of the arc at the four corners
     * @param arcH the vertical diameter of the arc at the four corners
     */
    public GlassPanel(final int x, final int y, final int width, final int height, final float alpha, final Color colour, final int arcW, final int arcH) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.colour = colour;
        this.arcW = arcW;
        this.arcH = arcH;
        setOpaque(false);
        addContainerListener(new GlassPanelEvent());
    }
    //override paintComponent method

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
    //override getPreferredSize method

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    private class GlassPanelEvent implements ContainerListener {

        @Override
        public void componentAdded(ContainerEvent e) {
            if (e.getChild() instanceof JComponent) {
                JComponent source = (JComponent) e.getContainer();
                JComponent comp = (JComponent) e.getChild();
            }
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            //System.out.println("Component removed");
        }
    }
}