/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.models.Event;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.utilities.EffectLevel;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomTable;
import devfortress.view.components.GlassPanel;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class EventTabPanel extends JPanel implements Observer {

    //initialize constant variables
    static private final float ALPHA = 0.8f;
    static private final int X = 0;
    static private final int Y = 5;
    static private final int W = 793;
    static private final int H = 418;
    static private final int ARCW = 10;
    static private final int ARCH = 10;
    static private final Color BACKGROUNDCOLOR = Colors.LIGHTBLUE;
    static private final Color HIGHLIGHTBACKGROUND = Colors.REDORANGEDARK;
    static private final Color HIGHLIGHTFOREGROUND = Color.WHITE;
    static private final Color TABLEDARK = Colors.DARKBLUE;
    static private final Color TABLELIGHT = Colors.LIGHTBLUE;
    static private final Font DESCFONT = new Font("Century Gothic", Font.PLAIN, 17);
    static private final Font EFFECTFONT = new Font("Century Gothic", Font.BOLD, 18);
    //declare private variables
    private JPanel subContainer;
    private JPanel top;
    private int page = 0;
    private int currentPage = 0;
    private JLabel leftArrow;
    private JLabel rightArrow;
    private List<Project> listProject;
    private JLabel projectName;
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private JTextArea eventDescription;
    private JLabel eventEffect;
    private Map<Integer, Event> listCurrentEvents;
    private JPanel tableContainer;

    //constructor
    public EventTabPanel() {
        setOpaque(false);
        init();
    }

    private void showEvent() {
        if (listProject.size() > 0) {
            page = listProject.size();
        } else {
            page = -1;
        }
        if (page >= 0) {
            if (page > 1) {
                leftArrow.setVisible(true);
                rightArrow.setVisible(true);
            } else {
                leftArrow.setVisible(false);
                rightArrow.setVisible(false);
            }
            listCurrentEvents.clear();
            int i = 1;
            Project currentProject = listProject.get(currentPage);
            for (Event event : currentProject.getEvents()) {
                listCurrentEvents.put(i, event);
                i++;
            }
            projectName.setText(currentProject.getName());
            if (listCurrentEvents.size() > 0) {
                ((CustomTable) eventTable).getTableScroll().setVisible(true);
                tableContainer.setVisible(true);
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(tableModel.getRowCount() - 1);
                }
                Iterator iterator = listCurrentEvents.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry) iterator.next();
                    int id = ((Integer) pairs.getKey()).intValue();
                    Event event = (Event) pairs.getValue();
                    if (event.getDescription().length() == 0) {
                        continue;
                    }
                    Object[] rowData = {id, event.getDescription()};
                    tableModel.addRow(rowData);
                }
                repaint();
            } else {
                ((CustomTable) eventTable).getTableScroll().setVisible(false);
                tableContainer.setVisible(false);
                repaint();
            }
        } else {
            leftArrow.setVisible(false);
            rightArrow.setVisible(false);
            ((CustomTable) eventTable).getTableScroll().setVisible(false);
            tableContainer.setVisible(false);
            projectName.setText("");

        }
    }

    private void init() {
        setLayout(new BorderLayout());
        /*
         * ########### initialize variables ##########
         */ //$$$$$-----Global variables
        listCurrentEvents = new HashMap<Integer, Event>();
        eventEffect = new JLabel("");
        tableContainer = new GlassPanel(0, 5, 500, 340, 1f, TABLEDARK, 0, 0);
        eventDescription = new JTextArea();
        subContainer = new GlassPanel(650, 400);
        top = new GlassPanel(550, 55);
        projectName = new JLabel("") {

            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);
            }
        };
        tableModel = new DefaultTableModel(1, 2);
        eventTable = new CustomTable(tableModel);
        leftArrow = new CustomLabel(new ImageIcon("images/arrowLeft.png"));
        rightArrow = new CustomLabel(new ImageIcon("images/arrowRight.png"));
        //$$$$$-----Local variable for UI
        GlassPanel marginTop = new GlassPanel(250, 15);
        GlassPanel highlightPanel = new GlassPanel(15, 15, 250, 390, 1f, HIGHLIGHTBACKGROUND, 15, 15);
        String demoText = "This is the event description. It'll give you "
                + "a more specific statistic about every event happened in "
                + "your project(s)";
        JPanel effectContainer = new GlassPanel(10, 0, 220, 50, 1f, HIGHLIGHTBACKGROUND, 0, 0);
        JPanel nameContainer = new GlassPanel(380, 50);
        /*
         * ########## Set some default values ##########
         */
        eventDescription.setText(demoText);
        Object[] ids = {"ID", "Description"};
        tableModel.setColumnIdentifiers(ids);
        /*
         * ########## Adjust look and feel ##########
         */
        tableContainer.setPreferredSize(new Dimension(500, 320));
        eventEffect.setForeground(HIGHLIGHTFOREGROUND);
        eventDescription.setWrapStyleWord(true);
        eventDescription.setLineWrap(true);
        eventDescription.setEditable(false);
        eventDescription.setBackground(HIGHLIGHTBACKGROUND);
        eventDescription.setPreferredSize(new Dimension(200, 320));
        eventDescription.setFont(DESCFONT);
        eventDescription.setForeground(HIGHLIGHTFOREGROUND);
        eventTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        ((CustomTable) eventTable).setTableSize(500, 340);
        ((CustomTable) eventTable).setHeaderColors(TABLELIGHT, TABLEDARK);
        ((CustomTable) eventTable).setScrollBackground(TABLELIGHT);
        eventTable.getColumnModel().getColumn(0).setMaxWidth(50);
        projectName.setForeground(TABLEDARK);
        projectName.setFont(new Font("Century Gothic", Font.BOLD, 32));
        /*
         * ########## add components together ##########
         */
        effectContainer.add(eventEffect);
        add(subContainer, BorderLayout.CENTER);
        add(highlightPanel, BorderLayout.EAST);
        highlightPanel.add(marginTop, BorderLayout.NORTH);
        highlightPanel.add(eventDescription, BorderLayout.CENTER);
        highlightPanel.add(effectContainer, BorderLayout.SOUTH);
        nameContainer.add(projectName);
        top.add(leftArrow, BorderLayout.WEST);
        top.add(nameContainer, BorderLayout.CENTER);
        top.add(rightArrow, BorderLayout.EAST);
        subContainer.add(top, BorderLayout.NORTH);
        tableContainer.add(((CustomTable) eventTable).getTableScroll());
        subContainer.add(tableContainer, BorderLayout.CENTER);

        //add listeners
        setPreviousArrowListener();
        setNextArrowListener();
        setTableNavigation();
    }

    public void showDescription() {
        int rowID = eventTable.getSelectedRow();
        if (((Integer) eventTable.getModel().getValueAt(rowID, 0)) == null) {
        } else {
            int descID = ((Integer) eventTable.getModel().getValueAt(rowID, 0)).intValue();

            Event selectedEvent = listCurrentEvents.get(descID);
            int effect = selectedEvent.getEffect();
            String eventDesc = selectedEvent.getDescription();
            eventDescription.setText(eventDesc);
            if (effect == EffectLevel.POSITIVE) {
                eventEffect.setIcon(new ImageIcon("images/positive.png"));
                eventEffect.setText("Congrats!");
                eventEffect.setFont(EFFECTFONT);
            } else if (effect == EffectLevel.NEGATIVE) {
                eventEffect.setIcon(new ImageIcon("images/negative.png"));
                eventEffect.setText("Oppps! Too bad!");
                eventEffect.setFont(EFFECTFONT);
            } else if (effect == EffectLevel.NEUTRAL) {
                eventEffect.setIcon(new ImageIcon("images/neutral.png"));
                eventEffect.setText("Not bad!");
                eventEffect.setFont(EFFECTFONT);
            }
            eventEffect.repaint();
            repaint();
        }
    }

    //override the paint component method
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));
        if (BACKGROUNDCOLOR != null) {
            g2d.setColor(BACKGROUNDCOLOR);
        }
        g2d.drawRoundRect(X, Y, W, H, W, H);
        g2d.fillRoundRect(X, Y, W, H, ARCW, ARCH);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPage() {
        return page;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEngine model = (GameEngine) o;
        listProject = model.getProjects();
        showEvent();
    }

    public void setPreviousArrowListener() {
        leftArrow.addMouseListener(new PreviousArrowNavigator());
    }

    public void setNextArrowListener() {
        rightArrow.addMouseListener(new NextArrowNavigator());
    }

    public void setTableNavigation() {
        eventTable.addMouseListener(new EventTableMouseAdapter());
    }

    private class PreviousArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                currentPage--;
                showEvent();
                subContainer.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage > 0) {
                leftArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                leftArrow.setIcon(new ImageIcon("images/arrowLeftOnMouse.png"));
                leftArrow.repaint();
            } else {
                leftArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            leftArrow.setIcon(new ImageIcon("images/arrowLeft.png"));
            leftArrow.repaint();
        }
    }

    private class NextArrowNavigator extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                currentPage++;
                showEvent();
                subContainer.repaint();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (page > 0 && currentPage < page - 1) {
                rightArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
                rightArrow.setIcon(new ImageIcon("images/arrowRightOnMouse.png"));
                rightArrow.repaint();
            } else {
                rightArrow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rightArrow.setIcon(new ImageIcon("images/arrowRight.png"));
            rightArrow.repaint();
        }
    }

    private class EventTableMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            showDescription();
        }
    }
}
