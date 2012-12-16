/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author Michael
 */
public class CustomProjectTable extends JTable {
     private JScrollPane tableScroll;

    public CustomProjectTable(TableModel dm, Color contentColor) {
        super(dm);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setBorder(BorderFactory.createLineBorder(Colors.GREEN, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colors.DARKGREEN, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colors.DARKGREEN);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        JScrollBar sb = tableScroll.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colors.DARKGREEN);
        tableScroll.getViewport().setBackground(contentColor);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
