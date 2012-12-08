/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomCheckBoxJListPanel;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProject extends JFrame implements Observer, KeyListener {

    private Project project;
    private CustomCheckBoxJListPanel<Developer> devsJListPanel;
    private CustomButton applyBtn, cancelBtn;
    private JLabel budgetLbl;
//    private CustomButton addDevBtn, removeDevBtn, applyBtn, closeBtn;

    public AddDeveloperToProject(Project project) {
        this.project = project;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(790, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        applyBtn = new CustomButton("Apply");
        cancelBtn = new CustomButton("Cancel");
        budgetLbl = new JLabel("Total Salary: " + 0);
        GlassPanel infoGPanel = new GlassPanel(10, 20, 480, 450, 1f, Colors.LIGHTBLUE2, 7, 7);
        GlassPanel btnPanel = new GlassPanel(15, 0, 745, 40, 1f, Colors.LIGHTBLUE3, 7, 7);
        GlassPanel sysPanel = new GlassPanel(15, 0, 745, 30, 1f, Colors.LIGHTBLUE3, 7, 7);
        devsJListPanel = new CustomCheckBoxJListPanel<Developer>(Colors.LIGHTBLUE2);
        DefaultListModel devsListModel = devsJListPanel.getListModel();
//        addedDevs = project.getDevelopers();

        //mock up data
        devsListModel.clear();
        for (int i = 0; i < 30; i++) {
            devsListModel.addElement(new Developer());
        }

        add(devsJListPanel, BorderLayout.WEST);
        add(infoGPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        add(sysPanel, BorderLayout.NORTH);
        sysPanel.setLayout(new GridLayout(1, 4));
        sysPanel.add(new JLabel());
        sysPanel.add(new JLabel());
        sysPanel.add(new JLabel());
        sysPanel.add(budgetLbl);
        JPanel btn1 = new JPanel();
        btn1.setOpaque(false);
        btn1.add(applyBtn);
        JPanel btn2 = new JPanel();
        btn2.setOpaque(false);
        btn2.add(cancelBtn);
        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        devsJListPanel.addJListKeyListener(this);
        //end mockup
        //        addDevBtn = new CustomButton("Add");
        //        removeDevBtn = new CustomButton("Remove");
        //        applyBtn = new CustomButton("Apply");
        //        closeBtn = new CustomButton("Cancel");

    }

    private void update() {
        int totalSalary = 0;
        List<Developer> devs = devsJListPanel.getSelectedItems();
        for (Developer dev : devs) {
            totalSalary += dev.getSalary();
        }
        budgetLbl.setText("Total Salary: " + totalSalary);
    }

    @Override
    public void update(Observable o, Object arg) {
        update();
    }

    public static void main(String[] args) {
        JFrame frame = new AddDeveloperToProject(new Project());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        update();
    }
}
