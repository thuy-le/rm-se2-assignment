/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomCheckBoxJListPanel;
import devfortress.view.components.CustomTable;
import devfortress.view.components.GlassPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProject extends JFrame {

    static private final String picture = "images/i6.png";
    static private final Color colour = Colors.LIGHTBLUE;
    private Project project;
    private CustomCheckBoxJListPanel<Developer> devsJListPanel;
    private CustomButton applyBtn, cancelBtn;
    private JLabel devName, mainSkill, status;
    private JComboBox pAreasCmB;
    private JTable skillTable;
    private DefaultTableModel skillTblModel;

    public AddDeveloperToProject(GameEngine model, Project project) {
        this.project = project;
        FunctionalArea[] areas;
        //Get an array of areas
        {
            Collection<FunctionalArea> pAreas = project.getAreas().values();
            areas = new FunctionalArea[pAreas.size()];
            pAreas.toArray(areas);
        }
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 540);
        setResizable(false);
        setLocationRelativeTo(null);
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(areas);
        String[] names = {"A", "B"};
        skillTblModel = new DefaultTableModel(names, 20);
        devName = new JLabel("Developer name");
        mainSkill = new JLabel("Main skill: ");
        status = new JLabel("Status");
        JPanel assignedArea = new JPanel();
        assignedArea.setLayout(new BoxLayout(assignedArea, BoxLayout.X_AXIS));
        JLabel assignLbl = new JLabel("Assign: ");
        assignLbl.setFont(font);
        assignedArea.add(assignLbl);
        pAreasCmB = new JComboBox(cmbModel);
        assignedArea.add(pAreasCmB);
        skillTable = new CustomTable(skillTblModel);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colour);
        applyBtn = new CustomButton("Apply");
        cancelBtn = new CustomButton("Cancel");
        GlassPanel infoGPanel = new GlassPanel(10, 10, 480, 450, 1f, Colors.LIGHTBLUE2, 7, 7);
        GlassPanel btnPanel = new GlassPanel(15, 0, 745, 40, 1f, Colors.LIGHTBLUE3, 7, 7);
//        GlassPanel sysPanel = new GlassPanel(15, 0, 745, 30, 1f, Colors.LIGHTBLUE3, 7, 7);
        devsJListPanel = new CustomCheckBoxJListPanel<Developer>(Colors.LIGHTBLUE2);
        DefaultListModel devsListModel = devsJListPanel.getListModel();
        //mock up data
        //TODO: replace with free developers

        devsListModel.clear();
        for (int i = 0; i < 30; i++) {
            devsListModel.addElement(new Developer());
        }
        devName.setForeground(Colors.DARKBLUE);
        devName.setFont(new Font("Century Gothic", Font.BOLD, 22));
        mainSkill.setFont(font);
        status.setFont(font);
        panel.add(devsJListPanel, BorderLayout.WEST);
        panel.add(infoGPanel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        JPanel btn1 = new JPanel();
        btn1.setOpaque(false);
        btn1.add(applyBtn);
        JPanel btn2 = new JPanel();
        btn2.setOpaque(false);
        btn2.add(cancelBtn);
        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        //Now do the info panel
        JPanel infoTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel infoTopRightPanel = new JPanel(new GridLayout(3, 1));

        infoTopPanel.setBackground(Colors.LIGHTORANGE);
        infoTopRightPanel.setBackground(Colors.LIGHTORANGE);
        infoTopRightPanel.setPreferredSize(new Dimension(280, 100));
        infoTopRightPanel.add(mainSkill);
        infoTopRightPanel.add(status);
        infoTopRightPanel.add(assignedArea);
        infoTopPanel.add(new JLabel(new ImageIcon(picture)));
        infoTopPanel.add(infoTopRightPanel);

        JPanel innerPnl = new JPanel(new BorderLayout());
        innerPnl.setOpaque(false);
//        innerPnl.setSize(new Dimension(430, 440));
        innerPnl.setBounds(15, 10, 470, 440);
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(devName);
        northPanel.add(infoTopPanel);
        innerPnl.add(northPanel, BorderLayout.NORTH);
        innerPnl.add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
        infoGPanel.setLayout(null);
        infoGPanel.add(innerPnl);
        add(panel);
    }

    public static void main(String[] args) {
        JFrame frame = new AddDeveloperToProject(null, new Project());
        frame.setVisible(true);
    }
}
