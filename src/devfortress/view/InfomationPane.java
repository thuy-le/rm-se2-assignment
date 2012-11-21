/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.Colour;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author PC
 */
public class InfomationPane {

    private GlassPanel infoPanel;
    private JLabel dateLabel, nextTurnBtn;

    public InfomationPane() {
        dateLabel = new JLabel("16/11/2012");
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
}
