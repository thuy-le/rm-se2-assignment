/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import devfortress.utilities.Colour;
import devfortress.view.interfaces.CustomListInterface;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class CustomList extends JPanel implements CustomListInterface {

    //declare static variables:
    private int x;
    private int y;
    private int width;
    private int height;
    private int arcW;
    private int arcH;
    private float alpha;
    private Color color;
    private JList jList;
    private List<CustomButton> buttons;

    public CustomList(final JList jList, final List<CustomButton> buttons) {
        //
        x = 10;
        y = 15;
        width = 272;
        height = 410;
        arcW = 7;
        arcH = 7;
        alpha = .9f;
        this.color = Colour.LIGHTBLUE;
        //
        this.jList = jList;
        this.buttons = buttons;
        //
        setLayout(new BorderLayout());
        init();
        setOpaque(false);
    }

    private void init() {
        removeAll();
        GlassPanel topPanel = new GlassPanel(x, y, width - 20, height - 55, alpha, color, arcW, arcH);
        JScrollPane scrollPane = new JScrollPane(jList);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
        GlassPanel bottomPanel = new GlassPanel(x, 0, width - 20, 80, 0f, null, 0, 0);
        bottomPanel.setLayout(new FlowLayout());
        for (CustomButton customButton : buttons) {
            bottomPanel.add(customButton);
        }
        //adjust look and feel
        jList.setOpaque(false);
        jList.setCellRenderer(new CustomListRenderer());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(width - 30, height - 80));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GlassPanel marginTop = new GlassPanel(0, 0, width, 10, 0f, null, 0, 0);
        topPanel.add(marginTop, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
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
        return new Dimension(width, height);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
        init();
    }

    @Override
    public void viewItem(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
