package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.CustomListPanel;
import devfortress.view.components.CustomTable;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The view of adding (accepting) project function of the game.
 * @author Team Poseidon
 */
public class AddProjectPanel extends JPanel implements Observer {

    //static variables
    //components
    private GlassPanel projectsPanel;
    private CustomButton acceptProjectBtn, closeBtn;
    private JTable functionalTable;
    private JList projectList;
    private JLabel projectNameLbl, mainSkillLbl;
    private DefaultTableModel functionalTableModel;
    private DefaultListModel projectListModel;
    private Project newProject;
    private GameEngine model;

    /**
     * Constructor of the class.
     */
    public AddProjectPanel() {
        projectsPanel = new GlassPanel(0, 0, 800, 600, 1f, Colors.GREEN, 7, 7);
        acceptProjectBtn = new CustomButton("Accept Project");
        closeBtn = new CustomButton("Close");
        projectListModel = new DefaultListModel();
        functionalTableModel = new DefaultTableModel(1, 2);
        projectList = new JList();
        functionalTable = new CustomTable(functionalTableModel);
        projectNameLbl = new JLabel("Project Name");
        mainSkillLbl = new JLabel("Skill ABC");
        model = GameEngine.getInstance();
        init();
    }

    private void init() {
        // variable for decoration:
        GlassPanel headerPanel = new GlassPanel(800, 70);
        GlassPanel bottomPanel = new GlassPanel(800, 70);
        CustomListPanel projectListPanel = new CustomListPanel(projectList, new LinkedList<CustomButton>());
        JLabel projectPanelTitle = new JLabel("Project details");
        JLabel imageIcon = new JLabel(new ImageIcon("images/i6.png"));
        Font bigFont = new Font("Century Gothic", Font.BOLD, 17);
        Font smallFont = new Font("Century Gothic", Font.PLAIN, 16);
        JLabel title = new CustomLabel("Available Projects");

        // add controllers:


        // UI programming:
        projectList.setSelectionBackground(Colors.LIGHTORANGE);
        projectList.setSelectionForeground(Colors.REDORANGEDARK);
        acceptProjectBtn.setButtonSize(0, 0, 150, 35);
        acceptProjectBtn.setTextColour(Colors.LIGHTBLUE);
        acceptProjectBtn.setColour(Colors.DARKBLUE);
        acceptProjectBtn.setOnMouseColor(Colors.DARKBLUE2);


        headerPanel.add(title);
        bottomPanel.add(acceptProjectBtn);
        bottomPanel.add(closeBtn);

        setOpaque(false);
        setLayout(new BorderLayout());

        projectsPanel.setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(projectListPanel, BorderLayout.WEST);
        add(projectsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
