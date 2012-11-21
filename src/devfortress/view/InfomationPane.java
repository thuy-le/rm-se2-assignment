/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.DevDate;
import devfortress.models.GameEngine;
import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.Colour;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author PC
 */
public class InfomationPane implements Observer {

    private GlassPanel infoPanel;
    private JLabel dateLabel, nextTurnBtn;

    public InfomationPane() {
        dateLabel = new JLabel();
        nextTurnBtn = new CustomLabel(new ImageIcon("images/right.png"));
        infoPanel = new GlassPanel(0, 0, 795, 50, .85f, Colour.DARKBLUE, 10, 10);
        infoPanel.setLayout(new FlowLayout());
        dateLabel.setFont(new Font("Century Gothic", Font.BOLD, 27));
        dateLabel.setForeground(Colour.LIGHTBLUE);
        nextTurnBtn.setToolTipText("Go to the next turn");
        nextTurnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoPanel.add(dateLabel);
        infoPanel.add(nextTurnBtn);
    }

    public GlassPanel getInfoPanel() {
        return infoPanel;
    }

    public void addWeekTurnListener(MouseListener l) {
        nextTurnBtn.addMouseListener(l);
    }

    public void setDate(DevDate date) {
        dateLabel.setText("Week " + date.getWeek() + " Month " + date.getMonth() + " Year " + date.getYear());
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        setDate(model.getDate());
    }
}
