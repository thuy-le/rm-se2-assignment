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
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Team Poseidon
 */
public class CustomTable extends JTable {

    private JScrollPane tableScroll;
    private Color scrollBackground;
    private Color headerBackgrond;
    private Color headerForeground;
    private Color borderColor;
    private int width;
    private int height;

    public CustomTable(TableModel dm) {
        super(dm);
        scrollBackground = Colors.REDORANGE;
        headerBackgrond = Colors.REDORANGE;
        headerForeground = Color.WHITE;
        borderColor = Colors.REDORANGE;
        width = 440;
        height = 180;
        init();
    }
    
    private void init(){
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setDefaultRenderer(getColumnClass(0), new CustomTableRenderer());
        setSelectionBackground(headerForeground);
        setSelectionForeground(headerBackgrond);
        setBorder(BorderFactory.createEmptyBorder());
        setShowGrid(false);
        setRowHeight(25);
        JTableHeader header = getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(headerForeground, 1));
        header.setFont(new Font("Century Gothic", Font.BOLD, 18));
        header.setBackground(headerBackgrond);
        header.setForeground(headerForeground);
        tableScroll = new JScrollPane(this);
        tableScroll.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        tableScroll.setPreferredSize(new Dimension(width, height));
        tableScroll.setBackground(scrollBackground);
        tableScroll.getViewport().setBackground(scrollBackground);
        JScrollBar sb = tableScroll.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }
    
    public void setTableSize(int w, int h){
        this.width = w;
        this.height = h;
        init();
    }
    
    public void setHeaderColors(Color foreground, Color background){
        this.headerForeground = foreground;
        this.headerBackgrond = background;
        init();
    }
    
    public void setScrollBackground(Color c){
        this.scrollBackground = c;
        init();
    }
    
    public void setBorderColor(Color c){
        this.borderColor = c;
        init();
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
