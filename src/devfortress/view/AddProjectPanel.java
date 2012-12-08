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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The view of adding (accepting) new projects function of the game.
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
    private GlassPanel projectPanel;
    private CustomButton acceptProjectBtn, closeBtn;
    private CustomTable functionalTable;
    private JList projectList;
    private JLabel projectNameLbl, mainSkillLbl, budgetLbl, deadlineLbl;
    private DefaultTableModel functionalTableModel;
    private DefaultListModel projectListModel;
    private Project newProject;
    private GameEngine model;

    /**
     * Constructor of the class.
     */
    public AddProjectPanel() {
        projectPanel = new GlassPanel(25, 25, 480, 380, 1f, PANEL_COLOR, 0, 0);
        acceptProjectBtn = new CustomButton("Accept Project");
        closeBtn = new CustomButton("Close");
        projectListModel = new DefaultListModel();
        functionalTableModel = new DefaultTableModel(1, 2);
        projectList = new JList();
        functionalTable = new CustomTable(functionalTableModel);
        projectNameLbl = new JLabel("Project Name");
        mainSkillLbl = new JLabel("Skill ABC");
        budgetLbl = new JLabel("$100000?");
        deadlineLbl = new JLabel("1/1/1");
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());
        // variable for decoration:
        GlassPanel headerPanel = new GlassPanel(800, 70);
        GlassPanel bottomPanel = new GlassPanel(800, 70);
        List<CustomButton> btnList = new LinkedList<CustomButton>();
        btnList.add(acceptProjectBtn);
        CustomListPanel projectListPanel = new CustomListPanel(projectList, btnList);
        JLabel projectPanelTitle = new JLabel("Project details");
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        Font bigFont = new Font("Century Gothic", Font.BOLD, 22);
        Font bigFont2 = new Font("Century Gothic", Font.BOLD, 17);
        Font mediumFont = new Font("Century Gothic", Font.PLAIN, 16);
        Font smallFont = new Font("Century Gothic", Font.PLAIN, 15);
        JLabel title = new CustomLabel("Available Projects");
        // panels inside projectPanel:
        JPanel projectInfoPanel = new JPanel();
        JPanel projectDetailsPanel = new JPanel();
        JPanel projectCenterPanel = new GlassPanel(25, 25, 480, 380, 1f, PANEL_COLOR, 0, 0);
        JPanel projectBottomPanel = new JPanel();

        // Add model for projectList:
        projectList.setModel(projectListModel);
        projectList.setCellRenderer(new CustomListRenderer());

        // UI programming:
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
        title.setForeground(LIST_COLOR);
        projectPanelTitle.setForeground(LIST_COLOR);
        projectPanelTitle.setFont(bigFont);
        projectNameLbl.setFont(bigFont2);
        mainSkillLbl.setFont(bigFont2);
        budgetLbl.setFont(bigFont2);
        deadlineLbl.setFont(bigFont2);
        // Set panels (inside projectPanel):
        projectInfoPanel.setLayout(new GridLayout(1, 2));
        projectInfoPanel.add(imageIcon);
        projectInfoPanel.add(projectDetailsPanel);
        projectDetailsPanel.setLayout(new GridLayout(4, 1));
        projectDetailsPanel.add(projectNameLbl);
        projectDetailsPanel.add(mainSkillLbl);
        projectDetailsPanel.add(budgetLbl);
        projectDetailsPanel.add(deadlineLbl);
//        projectCenterPanel.setLayout(new BorderLayout());
        projectCenterPanel.add(projectInfoPanel, BorderLayout.CENTER);
        projectCenterPanel.add(functionalTable.getTableScroll(), BorderLayout.SOUTH);
        projectInfoPanel.setBackground(PANEL_COLOR);
        projectDetailsPanel.setBackground(PANEL_COLOR);
        projectCenterPanel.setBackground(PANEL_COLOR);
        projectBottomPanel.setBackground(PANEL_COLOR);
        // Add components to projectPanel:
//        projectPanel.setLayout(new BorderLayout());
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);
        projectPanel.add(projectCenterPanel, BorderLayout.CENTER);
        projectPanel.add(projectBottomPanel, BorderLayout.SOUTH);

        headerPanel.add(title);
        bottomPanel.add(closeBtn);

        add(headerPanel, BorderLayout.NORTH);
        add(projectListPanel, BorderLayout.WEST);
        add(projectPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
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
            projectPanel.setVisible(false);
            return;
        }
        // if (project != null)
        DevDate deadline = null;
        try {
            deadline = GameEngine.getInstance().getDate().addMonths(project.getDuration());
        } catch (InvalidDevDateException ex) {
        }

        projectPanel.setVisible(true);
        projectNameLbl.setText(project.getName());
        mainSkillLbl.setText("Skill: " + project.getMainRequirement().getName());
        budgetLbl.setText("Budget: " + project.getBudget());
        deadlineLbl.setText("Deadline: " + deadline.getWeek() + "/" + deadline.getMonth() + "/" + deadline.getYear());
        functionalTableModel.setColumnIdentifiers(new String[]{"Functional Area", "Function Point"});
        while (functionalTableModel.getColumnCount() > 0) {
            functionalTableModel.removeRow(functionalTableModel.getColumnCount() - 1);
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

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        frame.add(new AddProjectPanel());
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
