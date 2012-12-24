/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.DevDate;
import devfortress.models.GameEngine;
import devfortress.view.components.GlassPanel;
import devfortress.view.components.CustomLabel;
import devfortress.utilities.Colors;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class InfomationPanel extends GlassPanel implements Observer {

    private static final String NEXT_WEEK_TOOLTIP_TEXT = "Jump to next week";
    private static final String ICON_PATH = "images/right.png";
    private static final String HOVER_ICON_PATH = "images/rightHover.png";
    private static final String FONT = "Century Gothic";
    private JLabel dateLbl, nextTurnBtn;

    public InfomationPanel() {
        super(0, 0, 795, 50, .85f, Colors.DARKBLUE, 10, 10);
        dateLbl = new JLabel() {

            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);

            }
        };
        nextTurnBtn = new CustomLabel(new ImageIcon(ICON_PATH), NEXT_WEEK_TOOLTIP_TEXT);
        dateLbl.setFont(new Font(FONT, Font.PLAIN, 22));
        dateLbl.setForeground(Colors.LIGHTBLUE);
        nextTurnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel labelContainer = new GlassPanel(500, 100);
        labelContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        labelContainer.add(dateLbl);
        JPanel nextContainer = new GlassPanel(280, 100);
        nextContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        nextContainer.add(nextTurnBtn);
        add(labelContainer, BorderLayout.CENTER);
        add(nextContainer, BorderLayout.EAST);

        //add event
        nextTurnBtn.addMouseListener(new nextBtnOnHover());
    }

    public void addWeekTurnListener(MouseListener l) {
        nextTurnBtn.addMouseListener(l);
    }

    public void setDate(DevDate date) {
        dateLbl.setText("<html>Week <b>" + date.getWeek() + "</b> (of 4) <b>" + date.getMonthString() + "</b> Year <b>" + date.getYear() + "</b></html>");
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        setDate(model.getDate());
    }

    private class nextBtnOnHover extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            nextTurnBtn.setIcon(new ImageIcon(HOVER_ICON_PATH));
            nextTurnBtn.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            nextTurnBtn.setIcon(new ImageIcon(ICON_PATH));
            nextTurnBtn.repaint();
        }
    }
}
