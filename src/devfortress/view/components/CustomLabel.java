/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

/**
 *
 * @author PC
 */
public class CustomLabel extends JLabel {

    private static final String FONT = "Century Gothic";
    private static final int TOOL_TIP_FONT_SIZE = 16;
    private static final int FONT_SIZE = 40;
    private static final int BORDER = 1;

    public CustomLabel(Icon image) {
        super(image);
    }

    public CustomLabel(Icon image, String toolTipText) {
        super(image);
        setToolTipText(toolTipText);
    }

    public CustomLabel(String text) {
        super(text);
    }

    @Override
    public JToolTip createToolTip() {
        JToolTip tooltip = super.createToolTip();
        tooltip.setFont(new Font(FONT, Font.BOLD, TOOL_TIP_FONT_SIZE));
        tooltip.setForeground(Colors.DARKBLUE);
        tooltip.setBorder(BorderFactory.createLineBorder(Colors.DARKBLUE, BORDER));
        tooltip.setBackground(Colors.LIGHTBLUE);
        return tooltip;
    }

    @Override
    public void setFont(Font font) {
        font = new Font(FONT, Font.BOLD, FONT_SIZE);
        super.setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(g);
    }
}
