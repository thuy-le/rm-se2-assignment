/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Team Poseidon
 */
public class CustomCheckBoxJListPanel<T> extends JPanel {

    private static final int X = 0;
    private static final int Y = 5;
    private static final int PNL_WIDTH = 272;
    private static final int PNL_HEIGHT = 520;
    private static final int ARCW = 7;
    private static final int ARCH = 7;
    private static final float ALPHA = .9f;
    private Color color;
    private JList jList;
    private DefaultListModel listModel;
    private CheckListManager manager;

    public CustomCheckBoxJListPanel() {
        this.color = Colors.LIGHTBLUE;
        this.listModel = new DefaultListModel();
        this.jList = new JList(listModel);
        this.manager = new CheckListManager(jList);
        init();
    }

    public CustomCheckBoxJListPanel(Color color) {
        this.color = color;
        this.listModel = new DefaultListModel();
        this.jList = new JList(listModel);
        this.manager = new CheckListManager(jList);
        init();
    }

    private void init() {
        removeAll();
        GlassPanel listPanel = new GlassPanel(X, Y, PNL_WIDTH - 20, PNL_HEIGHT - 65, ALPHA, color, ARCW, ARCH);
        JScrollPane scrollPane = new JScrollPane(jList);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        sb.setUI(new MyScrollbarUI());
        //adjust look and feel
        jList.setOpaque(false);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setPreferredSize(new Dimension(PNL_WIDTH - 30, PNL_HEIGHT - 80));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        GlassPanel marginTop = new GlassPanel(0, 0, PNL_WIDTH, 0, 0f, null, 0, 0);
        listPanel.add(marginTop, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        //add components
        add(listPanel);
        scrollPane.getViewport().add(jList);
        setOpaque(false);
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public List<T> getSelectedItems() {
        ListSelectionModel selectionModel = manager.getSelectionModel();
        List<T> objs = new LinkedList<T>();
        for (int i = 0; i < listModel.size(); i++) {
            if (selectionModel.isSelectedIndex(i)) {
                objs.add((T) listModel.get(i));
            }
        }
        return objs;
    }

    public void addJListOnSelectionListener(ListSelectionListener l) {
        jList.addListSelectionListener(l);
    }

    public void addJListKeyListener(KeyListener l) {
        jList.addKeyListener(l);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PNL_WIDTH, PNL_HEIGHT);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
        init();
    }

    private class CheckListManager extends MouseAdapter implements ListSelectionListener, ActionListener {

        private ListSelectionModel selectionModel;
        private JList list;
        private int hotspot;

        public CheckListManager(JList list) {
            this.list = list;
            this.hotspot = new JCheckBox().getPreferredSize().width;
            this.selectionModel = new DefaultListSelectionModel();
            list.setCellRenderer(new CheckListCellRenderer(list.getCellRenderer(), selectionModel));
            list.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), JComponent.WHEN_FOCUSED);
            list.addMouseListener(this);
            selectionModel.addListSelectionListener(this);
        }

        public ListSelectionModel getSelectionModel() {
            return selectionModel;
        }

        private void toggleSelection(int index) {
            if (index < 0) {
                return;
            }

            if (selectionModel.isSelectedIndex(index)) {
                selectionModel.removeSelectionInterval(index, index);
            } else {
                selectionModel.addSelectionInterval(index, index);
            }
        }

        /*------------------------------[ MouseListener ]-------------------------------------*/
        public void mouseClicked(MouseEvent me) {
            int index = list.locationToIndex(me.getPoint());
            if (index < 0) {
                return;
            }
            if (me.getX() > list.getCellBounds(index, index).x + hotspot) {
                return;
            }
            toggleSelection(index);
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            list.repaint(list.getCellBounds(e.getFirstIndex(), e.getLastIndex()));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            toggleSelection(list.getSelectedIndex());
        }

        private class CheckListCellRenderer extends JPanel implements ListCellRenderer {

            private ListCellRenderer delegate;
            private ListSelectionModel selectionModel;
            private JCheckBox checkBox = new JCheckBox();

            public CheckListCellRenderer(ListCellRenderer renderer, ListSelectionModel selectionModel) {
                this.delegate = renderer;
                this.selectionModel = selectionModel;
                setLayout(new BorderLayout());
                setOpaque(false);
                checkBox.setOpaque(false);
            }

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                checkBox.setSelected(selectionModel.isSelectedIndex(index));
                removeAll();
                add(checkBox, BorderLayout.WEST);
                add(renderer, BorderLayout.CENTER);
                return this;
            }
        }
    }
}
