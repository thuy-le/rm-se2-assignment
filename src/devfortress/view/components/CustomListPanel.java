/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class CustomListPanel extends JPanel {

    //declare static variables:
    private static final int X = 10;
    private static final int Y = 15;
    private static final int PNL_WIDTH = 272;
    private static final int PNL_HEIGHT = 410;
    private static final int ARCW = 7;
    private static final int ARCH = 7;
    private static final float ALPHA = .9f;
    private Color color;
    private JList jList;
    private List<CustomButton> buttons;

    public CustomListPanel(JList jList, List<CustomButton> buttons) {
        this.color = Colors.LIGHTBLUE;
        this.jList = jList;
        this.buttons = buttons;
        setLayout(new BorderLayout());
        init();
        setOpaque(false);
    }

    private void init() {
        removeAll();
        GlassPanel topPanel = new GlassPanel(X, Y, PNL_WIDTH - 20, PNL_HEIGHT - 55, ALPHA, color, ARCW, ARCH);
        JScrollPane scrollPane = new JScrollPane(jList);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
        GlassPanel bottomPanel = new GlassPanel(X, 0, PNL_WIDTH - 20, 80, 0f, null, 0, 0);
        bottomPanel.setLayout(new FlowLayout());
        for (CustomButton customButton : buttons) {
            bottomPanel.add(customButton);
        }
        //adjust look and feel
        jList.setOpaque(false);
        jList.setCellRenderer(new CustomListRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setPreferredSize(new Dimension(PNL_WIDTH - 30, PNL_HEIGHT - 80));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GlassPanel marginTop = new GlassPanel(0, 0, PNL_WIDTH, 10, 0f, null, 0, 0);
        topPanel.add(marginTop, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        //add components
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
        scrollPane.getViewport().add(jList);
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PNL_WIDTH, PNL_HEIGHT);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
        init();
    }
}
