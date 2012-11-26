/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.DevDate;
import devfortress.models.GameEngine;
import devfortress.view.components.GlassPanel;
import devfortress.view.components.CustomLabel;
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
public class InfomationPanel extends GlassPanel implements Observer {

    private static final String NEXT_WEEK_TOOLTIP_TEXT = "Jump to next week";
    private static final String ICON_PATH = "images/right.png";
    private static final String FONT = "Century Gothic";
    private JLabel dateLbl, nextTurnBtn;

    public InfomationPanel() {
        super(0, 0, 795, 50, .85f, Colour.DARKBLUE, 10, 10);
        setLayout(new FlowLayout());
        dateLbl = new JLabel();
        nextTurnBtn = new CustomLabel(new ImageIcon(ICON_PATH), NEXT_WEEK_TOOLTIP_TEXT);
        dateLbl.setFont(new Font(FONT, Font.BOLD, 27));
        dateLbl.setForeground(Colour.LIGHTBLUE);
        nextTurnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(dateLbl);
        add(nextTurnBtn);
    }

    public void addWeekTurnListener(MouseListener l) {
        nextTurnBtn.addMouseListener(l);
    }

    public void setDate(DevDate date) {
        dateLbl.setText("Week " + date.getWeek() + " Month " + date.getMonth() + " Year " + date.getYear());
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        setDate(model.getDate());
    }
}
