package devfortress.utilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

    private Color deSelectColor;
    private int inclTab = 4;
    private int anchoFocoH = 4;
    private Polygon shape;
    private boolean hideSingleTab = true;

    public CustomTabbedPaneUI() {
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
    }

    public CustomTabbedPaneUI(boolean hideSingleTb) {
        this();
        hideSingleTab = hideSingleTb;
    }

    public static ComponentUI createUI(JComponent c) {
        return new CustomTabbedPaneUI();
    }

    // override the paintTabBackground method
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2D = (Graphics2D) g;
        //since in the paint method, tabbed pane is set to be transparent, these 2 lines is needed to make the tabs (only the tabs) visible;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //paint the shape of tabs
        int xp[] = new int[]{x, x, x + 3, x + w - inclTab - 6, x + w - inclTab - 2, x + w - inclTab, x + w - inclTab, x};
        int yp[] = new int[]{y + h, y + 3, y, y, y + 1, y + 3, y + h, y + h};
        shape = new Polygon(xp, yp, xp.length);
        if (isSelected) {
            if (tabIndex == 0) {
                g2D.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, .7f));
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.LIGHTRED, 0, y + 15 + h / 2, Colour.LIGHTRED2);
                g2D.setPaint(gradientShadowTmp);
            } else if (tabIndex == 1) {
                g2D.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, .7f));
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.LIGHTORANGE, 0, y + 15 + h / 2, Colour.ORANGE);
                g2D.setPaint(gradientShadowTmp);
            } else {
                g2D.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, .7f));
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.LIGHTGREEN, 0, y + 15 + h / 2, Colour.YOUNGGREEN);
                g2D.setPaint(gradientShadowTmp);
            }
        } else {
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g2D.setColor(deSelectColor);
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.DARKBLUE2, 0, y + h / 2, Colour.DARKBLUE2);
                g2D.setPaint(gradientShadowTmp);
            } else {
                if (tabIndex == 1) {
                    GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.ORANGE, 0, y + 15 + h / 2, Colour.ORANGE);
                    g2D.setPaint(gradientShadowTmp);
                } else {
                    GradientPaint gradientShadowTmp = new GradientPaint(0, 0, Colour.DARKBLUE, 0, y + 15 + h / 2, Colour.LIGHTBLUE6);
                    g2D.setPaint(gradientShadowTmp);
                }
            }
        }
        g2D.fill(shape);
        if (runCount > 1) {
            g2D.fill(shape);
        }
        g2D.fill(shape);
    }

    //override the paintText method of BasicTabbedPaneUI class to config tabs' texts
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        font = new Font("Century Gothic", Font.BOLD, 18);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            v.paint(g, textRect);
        } else {
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                if (tabPane.getSelectedIndex() != tabIndex) {
                    g.setColor(Colour.LIGHTBLUE);
                } else {
                    g.setColor(Colour.DARKGREY);
                }
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 15, textRect.y + metrics.getAscent());
            } else {
                g.setColor(Color.WHITE);
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 15, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 16, textRect.y + metrics.getAscent() - 1);
            }
        }
    }

    //re-assign the width of the tabs
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return 70 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }

    //re-assign the height of the tabs
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        if (tabPlacement == LEFT || tabPlacement == RIGHT) {
            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
        } else {
            return anchoFocoH + super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
        }
    }

    //config tab border
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }

    //config focus indicator
    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8f));
       if(!isSelected){
        g.setColor(new Color(7, 53, 170));
        g.drawPolygon(shape);
       }
    }

    //override the paint method -> make the tabbed pane transparent
    @Override
    public void paint(Graphics g, JComponent c) {
        g.drawRect(0,0,0,0);
        if (tabPane.getTabCount() > 1 || !hideSingleTab) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            c.setSize(795, 457);
            super.paint(g, c);
        }
    }
}