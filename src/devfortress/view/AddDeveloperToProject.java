/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.Project;
import devfortress.view.components.CustomButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProject extends JFrame {

    private Project project;
    private List<Developer> availableDevs;
    private CustomButton addDevBtn, removeDevBtn, applyBtn, closeBtn;

    public AddDeveloperToProject(Project project) {
        this.project = project;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(793, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        availableDevs = new LinkedList<Developer>();
//        addedDevs = project.getDevelopers();

        //mock up data
        for (int i = 0; i < 10; i++) {
            availableDevs.add(new Developer());
        }
        //end mockup
        addDevBtn = new CustomButton("Add");
        removeDevBtn = new CustomButton("Remove");
        applyBtn = new CustomButton("Apply");
        closeBtn = new CustomButton("Cancel");
        JList addedList = new JList();
        JList avaiList = new JList();
        LinkedList<CustomButton> addedBtns = new LinkedList<CustomButton>();
        addedBtns.add(addDevBtn);
        LinkedList<CustomButton> removeBtns = new LinkedList<CustomButton>();
        removeBtns.add(removeDevBtn);
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new AddDeveloperToProject(new Project());
        frame.setVisible(true);
    }
}
