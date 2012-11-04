package devfortress.view;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.text.View;
import sun.swing.SwingUtilities2;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

    private Color selectColor;
    private Color deSelectColor;
    private int inclTab = 4;
    private int anchoFocoV = inclTab;
    private int anchoFocoH = 4;
    private int anchoCarpetas = 18;
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

    @Override
    protected void installDefaults() {
        super.installDefaults();
        selectColor = MyColor.darkPink;
        deSelectColor = MyColor.lightRed;
        tabAreaInsets.right = anchoCarpetas;
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        if (runCount > 1) {
            int lines[] = new int[runCount];
            for (int i = 0; i < runCount; i++) {
                lines[i] = rects[tabRuns[i]].y + (tabPlacement == TOP ? maxTabHeight : 0);
            }
            Arrays.sort(lines);
            if (tabPlacement == TOP) {
                int fila = runCount;
                for (int i = 0; i < lines.length - 1; i++, fila--) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 2, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + 3);
                    if (i < lines.length - 2) {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i + 1]);
                        carp.addPoint(0, lines[i + 1]);
                    } else {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + rects[selectedIndex].height);
                        carp.addPoint(0, lines[i] + rects[selectedIndex].height);
                    }
                    carp.addPoint(0, lines[i]);
                    Graphics2D g2d = (Graphics2D) g;
                    ((Graphics2D) g).setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    ((Graphics2D) g).setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER, 1f));
                    g.setColor(hazAlfa(fila));
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            } else {
                int fila = 0;
                for (int i = 0; i < lines.length - 1; i++, fila++) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i + 1] - 3);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 3, lines[i + 1]);
                    carp.addPoint(0, lines[i + 1]);
                    carp.addPoint(0, lines[i]);
                    g.setColor(hazAlfa(fila + 3));
                    ((Graphics2D) g).setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    ((Graphics2D) g).setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER, 1f));
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            }
        }
        super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2D = (Graphics2D) g;
        GradientPaint gradientShadow;
        int xp[] = null; // Para la forma
        int yp[] = null;
        switch (tabPlacement) {
            case LEFT:
                xp = new int[]{x, x, x + w, x + w, x};
                yp = new int[]{y, y + h - 3, y + h - 3, y, y};
                gradientShadow = new GradientPaint(x, y, MyColor.lightBlue1, x, y + h, MyColor.lightBlue1);
                break;
            case RIGHT:
                xp = new int[]{x, x, x + w - 2, x + w - 2, x};
                yp = new int[]{y, y + h - 3, y + h - 3, y, y};
                gradientShadow = new GradientPaint(x, y, MyColor.lightBlue1, x, y + h, MyColor.lightBlue1);
                break;
            case BOTTOM:
                xp = new int[]{x, x, x + 3, x + w - inclTab - 6, x + w - inclTab - 2, x + w - inclTab, x + w - 3, x};
                yp = new int[]{y, y + h - 3, y + h, y + h, y + h - 1, y + h - 3, y, y};
                gradientShadow = new GradientPaint(x, y, MyColor.lightBlue1, x, y + h, MyColor.lightBlue1);
                break;
            case TOP:
            default:
                xp = new int[]{x, x, x + 3, x + w - inclTab - 6, x + w - inclTab - 2, x + w - inclTab, x + w - inclTab, x};
                yp = new int[]{y + h, y + 3, y, y, y + 1, y + 3, y + h, y + h};
                gradientShadow = new GradientPaint(0, 0, new Color(113, 174, 250), 0, y + h / 2, new Color(223, 236, 252));
                break;
        }
        // ;
        shape = new Polygon(xp, yp, xp.length);
        if (isSelected) {
            //g2D.setColor(MyColor.darkGreenYellow);
            g2D.setPaint(gradientShadow);
        } else {
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g2D.setColor(deSelectColor);
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, new Color(7, 53, 170), 0, y + h / 2, new Color(7, 53, 170));
                g2D.setPaint(gradientShadowTmp);
            } else {
                GradientPaint gradientShadowTmp = new GradientPaint(0, 0, new Color(240, 255, 210), 0, y + 15 + h / 2, new Color(171, 251, 11));
                g2D.setPaint(gradientShadowTmp);
            }
        }
        g2D.fill(shape);
        if (runCount > 1) {
            g2D.setColor(hazAlfa(getRunForTab(tabPane.getTabCount(), tabIndex) - 1));
            g2D.fill(shape);
        }
        g2D.fill(shape);
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        //super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
        font = new Font("Century Gothic", Font.BOLD, 18);
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            // html
            v.paint(g, textRect);
        } else {
            // plain text
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                //g.setColor(tabPane.getForegroundAt(tabIndex));
                if (tabPane.getSelectedIndex() != tabIndex) {
                    g.setColor(MyColor.lightBlue1);
                } else {
                    g.setColor(new Color(7, 53, 170));
                }
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 15, textRect.y + metrics.getAscent());
            } else { // tab disabled
                g.setColor(Color.WHITE);
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 15, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 16, textRect.y + metrics.getAscent() - 1);
            }
        }
    }

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return 70 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        if (tabPlacement == LEFT || tabPlacement == RIGHT) {
            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
        } else {
            return anchoFocoH + super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
        }
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        if (tabPane.hasFocus() && isSelected) {
            g.setColor(new Color(7, 53, 170));
            g.drawPolygon(shape);
        }
    }

    protected Color hazAlfa(int fila) {
        int alfa = 5;
        if (fila >= 0) {
            alfa = 50 + (fila > 7 ? 70 : 10 * fila);
        }
        return new Color(222, 252, 223, alfa);
    }

    public void paint(Graphics g, JComponent c) {
        if (tabPane.getTabCount() > 1 || !hideSingleTab) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            super.paint(g, c);
        }
    }


    protected Insets getContentBorderInsets(int tabPlacement) {
        Insets insets = super.getContentBorderInsets(tabPlacement);
        int normalWidth = insets.top;

        if (tabPlacement == TOP) {
            return new Insets(normalWidth, 0, 0, 0);
        } else if (tabPlacement == LEFT) {
            return new Insets(0, normalWidth, 0, 0);
        } else if (tabPlacement == RIGHT) {
            return new Insets(0, 0, 0, normalWidth);
        } else if (tabPlacement == BOTTOM) {
            return new Insets(0, 0, normalWidth, 0);
        } else {
            throw new IllegalArgumentException("'" + tabPlacement + "' is not a valid tab placement");
        }
    }

    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
            int selectedIndex, int x, int y, int w, int h) {
        if (tabPlacement == TOP) {
            super.paintContentBorderTopEdge(g, tabPlacement, selectedIndex, x,
                    y, w, h);
        }
    }

    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
            int selectedIndex, int x, int y, int w, int h) {
        if (tabPlacement == LEFT) {
            super.paintContentBorderLeftEdge(g, tabPlacement, selectedIndex, x,
                    y, w, h);
        }
    }

    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
            int selectedIndex, int x, int y, int w, int h) {
        if (tabPlacement == BOTTOM) {
            super.paintContentBorderBottomEdge(g, tabPlacement, selectedIndex, x,
                    y, w, h);
        }
    }

    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
            int selectedIndex, int x, int y, int w, int h) {
        if (tabPlacement == RIGHT) {
            super.paintContentBorderRightEdge(g, tabPlacement, selectedIndex, x,
                    y, w, h);
        }
    }
}