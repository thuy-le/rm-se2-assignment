/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomCheckBoxJListPanel;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProject extends JFrame {

    private Project project;
    private CustomCheckBoxJListPanel devsJListPanel;
//    private 
    private List<Developer> availableDevs;
//    private CustomButton addDevBtn, removeDevBtn, applyBtn, closeBtn;

    public AddDeveloperToProject(Project project) {
        this.project = project;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        GlassPanel infoGPanel = new GlassPanel(10, 15, 480, 380, 1f, Colors.DARKBLUE, 7, 7);
        GlassPanel btnPanel = new GlassPanel(15, 0, 745, 40, 1f, Colors.DARKBLUE, 7, 7);
        devsJListPanel = new CustomCheckBoxJListPanel(Colors.DARKBLUE);
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
        add(infoGPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
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
