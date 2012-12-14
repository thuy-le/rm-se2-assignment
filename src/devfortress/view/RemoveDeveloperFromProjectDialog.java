/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.Skill;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class RemoveDeveloperFromProjectDialog extends JDialog implements ActionListener, ListSelectionListener {

    static private final String picture = "images/i6.png";
    static private final Color colour = Colors.LIGHTBLUE;
    private CustomCheckBoxJListPanel<Developer> devsJListPanel;
    private CustomButton applyBtn, cancelBtn;
    private JLabel devName, mainSkill, production;
    private JTable skillTable;
    private DefaultTableModel skillTblModel;
    private Map<Developer, FunctionalArea> assignMap;
    private DefaultComboBoxModel cmbModel;
    private JPanel infoPanel;
    private Project project;

    public RemoveDeveloperFromProjectDialog(GameEngine model, Project project) {
        FunctionalArea[] areas;
        //Get an array of areas
        {
            Collection<FunctionalArea> pAreas = project.getAreas().values();
            areas = new FunctionalArea[pAreas.size()];
            pAreas.toArray(areas);
        }
        this.project = project;
        assignMap = new HashMap<Developer, FunctionalArea>();
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 540);
        setResizable(false);
        setLocationRelativeTo(null);
        cmbModel = new DefaultComboBoxModel();
        for (FunctionalArea area : areas) {
            cmbModel.addElement(area);
        }
        cmbModel.setSelectedItem(null);
        String[] names = {"A", "B"};
        skillTblModel = new DefaultTableModel(names, 10);
        devName = new JLabel("Developer name");
        mainSkill = new JLabel("Main skill: ");
        production = new JLabel("Production: ");
        JPanel assignedArea = new JPanel();
        assignedArea.setLayout(new BoxLayout(assignedArea, BoxLayout.X_AXIS));
        JLabel assignLbl = new JLabel("Assign: ");
        assignLbl.setFont(font);
        assignedArea.add(assignLbl);
        JComboBox pAreasCmB = new JComboBox(cmbModel);
        pAreasCmB.setEditable(false);
        pAreasCmB.addActionListener(this);
        assignedArea.add(pAreasCmB);
        skillTable = new CustomTable(skillTblModel);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colour);
        applyBtn = new CustomButton("Apply");
        cancelBtn = new CustomButton("Cancel");
        cancelBtn.addMouseListener(new CancelButtonMouseAdapter());
        GlassPanel infoGPanel = new GlassPanel(10, 10, 480, 450, 1f, Colors.LIGHTBLUE2, 7, 7);
        GlassPanel btnPanel = new GlassPanel(15, 0, 745, 40, 1f, Colors.LIGHTBLUE3, 7, 7);
//        GlassPanel sysPanel = new GlassPanel(15, 0, 745, 30, 1f, Colors.LIGHTBLUE3, 7, 7);
        devsJListPanel = new CustomCheckBoxJListPanel<Developer>(Colors.LIGHTBLUE2);
        devsJListPanel.addJListOnSelectionListener(this);
        DefaultListModel devsListModel = devsJListPanel.getListModel();
        {
            devsListModel.clear();
            List<Developer> devs = project.getDevelopers();
            for (Developer dev : devs) {
                devsListModel.addElement(dev);
                assignMap.put(dev, project.getAreas().get(dev.getWorkingArea()));
            }
        }
        devName.setForeground(Colors.DARKBLUE);
        devName.setFont(new Font("Century Gothic", Font.BOLD, 22));
        mainSkill.setFont(font);
        production.setFont(font);
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
        infoTopRightPanel.setPreferredSize(new Dimension(290, 100));
        infoTopRightPanel.add(mainSkill);
        infoTopRightPanel.add(production);
        infoTopRightPanel.add(assignedArea);
        infoTopPanel.add(new JLabel(new ImageIcon(picture)));
        infoTopPanel.add(infoTopRightPanel);

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBounds(15, 10, 470, 440);
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(devName);
        northPanel.add(infoTopPanel);
        infoPanel.add(northPanel, BorderLayout.NORTH);
        infoPanel.add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
        infoGPanel.setLayout(null);
        infoGPanel.add(infoPanel);
        add(panel);
        infoPanel.setVisible(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
    }

    private void assignArea(Developer dev, FunctionalArea area) {
        assignMap.put(dev, area);
    }

    private void showDeveloper(Developer dev) {
        cmbModel.setSelectedItem(assignMap.get(dev));
        while (skillTblModel.getRowCount() != 0) {
            skillTblModel.removeRow(skillTblModel.getRowCount() - 1);
        }
        if (dev != null) {
            devName.setText(dev.getName());
            mainSkill.setText("Main skill: " + dev.getMainSkill().getName());
            production.setText("Production: " + dev.getProduction(project) + " points/week");
            List<Skill> skills = new LinkedList<Skill>(dev.getSkills().values());
            for (Skill skill : skills) {
                Object[] row = {skill.getSkillInfo().getName(), skill.getLevel()};
                skillTblModel.addRow(row);
            }
        }
        infoPanel.setVisible(true);
    }

    public Map<Developer, FunctionalArea> getSelectedDevelopers() {
        Map<Developer, FunctionalArea> map = new HashMap<Developer, FunctionalArea>(assignMap);
        List<Developer> list = devsJListPanel.getSelectedItems();

        for (Iterator<Developer> itr = map.keySet().iterator(); itr.hasNext();) {
            Developer dev = itr.next();
            if (!list.contains(dev)) {
                itr.remove();
            }
        }
        return map;
    }

    public void addApplyButtonListener(MouseListener l) {
        applyBtn.addMouseListener(l);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Developer dev = devsJListPanel.getSelectedItem();
        FunctionalArea area = (FunctionalArea) cmbModel.getSelectedItem();
        assignArea(dev, area);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        showDeveloper(devsJListPanel.getSelectedItem());
    }

    private class CancelButtonMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            RemoveDeveloperFromProjectDialog.this.dispose();
        }
    }
}
