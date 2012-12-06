/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.Project;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomListPanel;
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
    private CustomListPanel availDevsListPanel, addedDevsListPanel;
    private List<Developer> availableDevs, addedDevs;
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
        addedDevs = new LinkedList<Developer>();
        for (int i = 0; i < 10; i++) {
            availableDevs.add(new Developer());
            addedDevs.add(new Developer());
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
        availDevsListPanel = new CustomListPanel(avaiList, addedBtns);
        addedDevsListPanel = new CustomListPanel(addedList, removeBtns);
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(availDevsListPanel);
        centerPanel.add(addedDevsListPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new AddDeveloperToProject(new Project());
        frame.setVisible(true);
    }
}
