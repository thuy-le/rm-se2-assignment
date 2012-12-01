package devfortress.view;

import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class AddProjectPanel extends JPanel implements Observer {

    //static variables
    //components
    private GlassPanel projectsPanel;
    private CustomButton acceptProjectBtn;
    private JTable functionalTable;
    private JList projectList;
    private JLabel projectNameLbl, mainSkillLbl;
    private CustomButton hireBtn, closeBtn;
    private DefaultTableModel functionalTableModel;
    private DefaultListModel projectListModel;
    private Project newProject;
    private GameEngine model;

    public AddProjectPanel() {
        projectsPanel = new GlassPanel(0, 0, 800, 600, 1f, Colors.GREEN, 7, 7);
        acceptProjectBtn = new CustomButton("Accept Project");
        projectListModel = new DefaultListModel();
        functionalTable = new 
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());

        add(projectsPanel, BorderLayout.CENTER);
        add(new GlassPanel(800, 70), BorderLayout.NORTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
