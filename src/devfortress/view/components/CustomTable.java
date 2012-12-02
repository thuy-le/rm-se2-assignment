/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author Team Poseidon
 */
public class CustomTable extends JTable {

    private JScrollPane tableScroll;

    public CustomTable(TableModel dm) {
        super(dm);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setBorder(BorderFactory.createLineBorder(Colors.ORANGE, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colors.ORANGE, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colors.ORANGE);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colors.ORANGE);
        tableScroll.getViewport().setBackground(Colors.ORANGE);
        JScrollBar sb = tableScroll.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());

        JTableHeader skillTableHeader = getTableHeader();
        skillTableHeader.setBorder(BorderFactory.createLineBorder(Colors.ORANGE, 1));
        skillTableHeader.setFont(new Font("Century Gothic", Font.BOLD, 18));
        skillTableHeader.setBackground(Colors.ORANGE);
        skillTableHeader.setForeground(Color.WHITE);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(g);
    }
    
    
}
