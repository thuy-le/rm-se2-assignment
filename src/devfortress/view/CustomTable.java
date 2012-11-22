/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colour;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
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
        setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(Colour.ORANGE, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(Colour.ORANGE);
        header.setForeground(Color.WHITE);
        tableScroll = new JScrollPane(this);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.setPreferredSize(new Dimension(440, 180));
        tableScroll.setBackground(Colour.ORANGE);
        tableScroll.getViewport().setBackground(Colour.ORANGE);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
