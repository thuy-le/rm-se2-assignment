/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.Project;
import devfortress.view.components.CustomCheckBoxJListPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProject extends JFrame {

    private Project project;
    private CustomCheckBoxJListPanel devsJListPanel;
    private JPanel infoPanel;
//    private 
    private List<Developer> availableDevs;
//    private CustomButton addDevBtn, removeDevBtn, applyBtn, closeBtn;

    public AddDeveloperToProject(Project project) {
        this.project = project;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(793, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        devsJListPanel = new CustomCheckBoxJListPanel();
        DefaultListModel devsListModel = devsJListPanel.getListModel();
        availableDevs = new LinkedList<Developer>();
//        addedDevs = project.getDevelopers();

        //mock up data
        for (int i = 0; i < 30; i++) {
            availableDevs.add(new Developer());
        }
        devsListModel.clear();
        for (Developer dev : availableDevs) {
            devsListModel.addElement(dev);
        }
        add(devsJListPanel, BorderLayout.WEST);
        //end mockup
        //        addDevBtn = new CustomButton("Add");
        //        removeDevBtn = new CustomButton("Remove");
        //        applyBtn = new CustomButton("Apply");
        //        closeBtn = new CustomButton("Cancel");

    }

    public static void main(String[] args) {
        JFrame frame = new AddDeveloperToProject(new Project());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
