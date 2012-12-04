package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomListPanel;
import devfortress.view.components.CustomListRenderer;
import devfortress.view.components.CustomTable;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicListUI.ListSelectionHandler;
import javax.swing.table.DefaultTableModel;

/**
 * The view of adding (accepting) new projects function of the game.
 * @author Team Poseidon
 */
public class AddProjectPanel extends JPanel implements Observer {

    //static variables
    //components
    private GlassPanel projectPanel;
    private CustomButton acceptProjectBtn, closeBtn;
    private JTable functionalTable;
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
        projectPanel = new GlassPanel(25, 25, 480, 380, 1f, new Color(0, 0, 255, 100), 7, 7);
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
        model = GameEngine.getInstance();
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());
        // variable for decoration:
        GlassPanel headerPanel = new GlassPanel(800, 70);
//        GlassPanel bottomPanel = new GlassPanel(800, 70);
        CustomListPanel projectListPanel = new CustomListPanel(projectList, new LinkedList<CustomButton>());
        JLabel projectPanelTitle = new JLabel("Project details");
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        Font bigFont = new Font("Century Gothic", Font.BOLD, 17);
        Font mediumFont = new Font("Century Gothic", Font.PLAIN, 16);
        Font smallFont = new Font("Century Gothic", Font.PLAIN, 15);
        JLabel title = new CustomLabel("Available Projects");
        // panels inside projectPanel:
        JPanel projectDetailsPanel = new JPanel();
        JPanel projectBottomPanel = new JPanel();

        // Add model for projectList:
        projectList.setModel(projectListModel);
        projectList.setCellRenderer(new CustomListRenderer());
//        projectList.addListSelectionListener(new ListSelectionHandler());

        // UI programming:
        // Project list:
        projectList.setSelectionBackground(Colors.LIGHTORANGE);
        projectList.setSelectionForeground(Colors.REDORANGEDARK);
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
        projectNameLbl.setFont(bigFont);
        mainSkillLbl.setFont(bigFont);
        budgetLbl.setFont(bigFont);
        deadlineLbl.setFont(bigFont);
        // Set panels (inside projectPanel):
        projectDetailsPanel.setLayout(new GridLayout(4, 1));
        projectDetailsPanel.add(projectNameLbl);
        projectDetailsPanel.add(mainSkillLbl);
        projectDetailsPanel.add(budgetLbl);
        projectDetailsPanel.add(deadlineLbl);
        projectBottomPanel.add(acceptProjectBtn);
        projectBottomPanel.add(closeBtn);
        // Add components to projectPanel:
//        projectPanel.setLayout(new BorderLayout());
        projectPanel.add(projectPanelTitle, BorderLayout.NORTH);
        projectPanel.add(projectDetailsPanel, BorderLayout.CENTER);
        projectPanel.add(((CustomTable)functionalTable).getTableScroll(), BorderLayout.NORTH);
        projectPanel.add(projectBottomPanel, BorderLayout.SOUTH);


        headerPanel.add(title);
//        bottomPanel.add(acceptProjectBtn);
//        bottomPanel.add(closeBtn);

        add(headerPanel, BorderLayout.NORTH);
        add(projectListPanel, BorderLayout.WEST);
        add(projectPanel, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
