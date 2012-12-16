package devfortress.view;

import devfortress.models.DevDate;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.exceptions.InvalidDevDateException;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomListPanel;
import devfortress.view.components.CustomListRenderer;
import devfortress.view.components.CustomTable;
import devfortress.view.components.GlassPanel;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * The view of adding (accepting) new projects function of the game.
 *
 * @author Team Poseidon
 */
public class AddProjectPanel extends JPanel implements Observer {

    //static variables
    private static final Color PANEL_COLOR = Colors.LIGHTGREEN;
    private static final Color LIST_COLOR = Colors.DARKBLUE;
    private static final Color color = Colors.MATENGA;
    private static final int X = 5;
    private static final int Y = 5;
    private static final int PANEL_WIDTH = 785;
    private static final int PANEL_HEIGHT = 555;
    private static final int ARCH = 20;
    private static final int ARCW = 20;
    private static final float alpha = .7f;
    //components
    private GlassPanel infoPanel;
    private CustomButton acceptProjectBtn, closeBtn;
    private CustomTable functionalTable;
    private JList projectList;
    private JLabel projectLevelLbl, mainSkillLbl, budgetLbl, deadlineLbl, levelLbl, projectNameLbl;
    private DefaultTableModel functionalTableModel;
    private DefaultListModel projectListModel;
    private Project selectedProject;
    private GameEngine model;

    /**
     * Constructor of the class.
     */
    public AddProjectPanel() {
        infoPanel = new GlassPanel(25, 25, 480, 380, 1f, PANEL_COLOR, 7, 7);
        acceptProjectBtn = new CustomButton("Accept Project");
        closeBtn = new CustomButton("Close");
        projectListModel = new DefaultListModel();
        functionalTableModel = new DefaultTableModel();
        projectList = new JList();
        functionalTable = new CustomTable();
        projectLevelLbl = new JLabel("Project Name");
        levelLbl = new JLabel("1");
        mainSkillLbl = new JLabel("Skill ABC");
        budgetLbl = new JLabel("$100000?");
        deadlineLbl = new JLabel("1/1/1");
        projectNameLbl = new JLabel("Project details");
        init();
    }

    private void init() {
        Font bigFont = new Font("Century Gothic", Font.BOLD, 22);
        Font bigFont2 = new Font("Century Gothic", Font.BOLD, 18);
        Font mediumFont = new Font("Century Gothic", Font.PLAIN, 16);
        Font smallFont = new Font("Century Gothic", Font.PLAIN, 15);
        GlassPanel northPanel = new GlassPanel(800, 70);
        GlassPanel bottomPanel = new GlassPanel(800, 70);
        CustomListPanel projectListPanel = new CustomListPanel(projectList, Arrays.asList(new CustomButton[]{acceptProjectBtn}));
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        JLabel titlePanel = new CustomLabel("Available Projects");
        JPanel projectInfoPanel = new JPanel();
        JPanel projectDetailsPanel = new JPanel();
        JPanel infoNorthPanel = new JPanel();
        GlassPanel infoGroupPanel = new GlassPanel(10, 10, 500, 450, 1f, PANEL_COLOR, 7, 7);
        //Data
        {
            // Add model for projectList:
            projectList.setModel(projectListModel);
            projectList.setCellRenderer(new CustomListRenderer());
            projectList.addListSelectionListener(new ProjectList_ListListener());
            functionalTableModel.setColumnIdentifiers(new String[]{"Functional Area", "Point"});
            functionalTable.init(functionalTableModel);
        }
        //Styling
        {
            setOpaque(false);
            // Project list:
            projectList.setSelectionBackground(Colors.LIGHTORANGE);
            projectList.setSelectionForeground(LIST_COLOR);
            projectList.setFont(mediumFont);
            // Accept Project Button:
            acceptProjectBtn.setButtonSize(0, 0, 150, 35);
            acceptProjectBtn.setTextColour(Colors.LIGHTBLUE);
            acceptProjectBtn.setColour(Colors.DARKBLUE);
            acceptProjectBtn.setOnMouseColor(Colors.DARKBLUE2);
            acceptProjectBtn.setButtonSize(0, 0, 150, 35);
            // Close Button:
            closeBtn.setTextColour(Colors.LIGHTBLUE);
            closeBtn.setColour(Colors.DARKBLUE);
            closeBtn.setOnMouseColor(Colors.DARKBLUE2);
            // Functional Table:
            functionalTable.setFont(smallFont);
            functionalTable.setBorder(BorderFactory.createLineBorder(Colors.ORANGE, 1));
            functionalTable.setRowHeight(25);
            // Set Font for labels:
            titlePanel.setForeground(LIST_COLOR);
            projectNameLbl.setForeground(LIST_COLOR);
            projectNameLbl.setFont(bigFont);
            projectNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            projectLevelLbl.setFont(bigFont2);
            mainSkillLbl.setFont(bigFont2);
            budgetLbl.setFont(bigFont2);
            deadlineLbl.setFont(bigFont2);
            //Panels
            infoPanel.setBounds(15, 10, 490, 410);
            infoNorthPanel.setOpaque(false);
            projectInfoPanel.setBackground(PANEL_COLOR);
            projectDetailsPanel.setBackground(PANEL_COLOR);
        }
        //Layout
        {
            //Layout Managers
            setLayout(new BorderLayout());
            infoPanel.setLayout(new BorderLayout());
            projectInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            projectInfoPanel.add(imageIcon);
            projectInfoPanel.add(projectDetailsPanel);
            projectDetailsPanel.setLayout(new BoxLayout(projectDetailsPanel, BoxLayout.Y_AXIS));
            infoNorthPanel.setLayout(new BoxLayout(infoNorthPanel, BoxLayout.Y_AXIS));
            infoGroupPanel.setLayout(null);
            //Add components in order
            add(northPanel, BorderLayout.NORTH);
            add(projectListPanel, BorderLayout.WEST);
            add(infoGroupPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
            northPanel.add(titlePanel);
            infoGroupPanel.add(infoPanel);
            infoPanel.add(infoNorthPanel, BorderLayout.NORTH);
            infoPanel.add(functionalTable.getTableScroll(), BorderLayout.CENTER);
            infoNorthPanel.add(projectNameLbl);
            infoNorthPanel.add(projectInfoPanel);
            
            projectDetailsPanel.add(projectLevelLbl);
            projectDetailsPanel.add(mainSkillLbl);
            projectDetailsPanel.add(budgetLbl);
            projectDetailsPanel.add(deadlineLbl);
            bottomPanel.add(closeBtn);
        }
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void addAcceptProjectEvent(MouseListener l) {
        acceptProjectBtn.addMouseListener(l);
    }

    public void addCloseEvent(MouseListener l) {
        closeBtn.addMouseListener(l);
    }

    @Override
    public void update(Observable o, Object arg) {
        model = (GameEngine) o;
        projectListModel.clear();
        for (Project project : model.getMarketProjects()) {
            projectListModel.addElement(project);
        }
        showProject((Project) projectList.getSelectedValue());
    }

    public synchronized void showProject(Project project) {
        if (project == null) {
            infoPanel.setVisible(false);
            return;
        }
        // if (project != null)
        DevDate deadline = null;
        try {
            deadline = GameEngine.getInstance().getDate().addMonths(project.getDuration());
        } catch (InvalidDevDateException ex) {
        }

        infoPanel.setVisible(true);
        projectNameLbl.setText(project.getName());
        projectLevelLbl.setText("Level: " + project.getLevel());
        mainSkillLbl.setText("Skill: " + project.getMainRequirement().getName());
        budgetLbl.setText("Budget: $" + project.getBudget());
        deadlineLbl.setText("Deadline: " + deadline.getWeek() + "/" + deadline.getMonth() + "/" + deadline.getYear());
        while (functionalTableModel.getRowCount() > 0) {
            functionalTableModel.removeRow(functionalTableModel.getRowCount() - 1);
        }
        for (FunctionalArea area : project.getAreas().values()) {
            functionalTableModel.addRow(new Object[]{area.getName(), area.getFunctionPoints()});
        }
        functionalTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        functionalTable.getColumnModel().getColumn(1).setMaxWidth(80);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(color);
        g2d.fillRoundRect(X, Y, PANEL_WIDTH, PANEL_HEIGHT, ARCW, ARCH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    private class ProjectList_ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (projectList.getSelectedIndex() != -1) {
                selectedProject = (Project) projectList.getSelectedValue();
                showProject((Project) projectList.getSelectedValue());
            }
        }
    }
}
