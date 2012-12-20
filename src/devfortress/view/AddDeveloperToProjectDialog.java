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
import java.awt.Component;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class AddDeveloperToProjectDialog extends JDialog implements ActionListener, ListSelectionListener {

    static private final String picture = "images/i6.png";
    static private final Color colour = Colors.LIGHTBLUE;
    static private final Font font = new Font("Century Gothic", Font.BOLD, 17);
    private CustomCheckBoxJListPanel<Developer> devsJListPanel;
    private CustomButton applyBtn, cancelBtn;
    private JLabel devName, mainSkill, production;
    private DefaultTableModel skillTblModel;
    private Map<Developer, FunctionalArea> assignMap;
    private DefaultComboBoxModel cmbModel;
    private JPanel infoInnerPanel;
    private Project project;

    public AddDeveloperToProjectDialog(GameEngine model, Project project) {
        this.project = project;
        assignMap = new HashMap<Developer, FunctionalArea>();
        cmbModel = new DefaultComboBoxModel();
        skillTblModel = new DefaultTableModel();
        infoInnerPanel = new JPanel(new BorderLayout());
        devName = new JLabel("Developer name");
        mainSkill = new JLabel("Main skill: ");
        production = new JLabel("Production: ");
        applyBtn = new CustomButton("Apply");
        cancelBtn = new CustomButton("Cancel");
        devsJListPanel = new CustomCheckBoxJListPanel<Developer>(Colors.LIGHTBLUE2);
        init(model);
    }

    private void init(GameEngine model) {
        DefaultListModel devsListModel = devsJListPanel.getListModel();
        CustomTable skillTable = new CustomTable();
        GlassPanel infoGroupPanel = new GlassPanel(10, 10, 500, 450, 1f, Colors.LIGHTBLUE2, 7, 7);
        GlassPanel btnPanel = new GlassPanel(15, 0, 745, 40, 1f, Colors.LIGHTBLUE3, 7, 7);
        JPanel assignedAreaPanel = new JPanel();
        JPanel applyBtnPanel = new JPanel();
        JPanel cancelBtnPanel = new JPanel();
        JPanel backgroundPanel = new JPanel();
        JPanel infoNorthPanel = new JPanel();
        JPanel infoNorthInnerPanel = new JPanel();
        JPanel infoNorthProjectInfoPanel = new JPanel();
        JLabel assignLbl = new JLabel("Assign to: ");
        JComboBox assignAreaCmB = new JComboBox(cmbModel);
        //Data
        {
            FunctionalArea[] areas;
            Collection<FunctionalArea> pAreas = project.getAreas().values();
            areas = new FunctionalArea[pAreas.size()];
            pAreas.toArray(areas);
            for (FunctionalArea area : areas) {
                if (area.isCompleted()) { // don't display completed area
                    continue;
                }
                cmbModel.addElement(area);
            }
            devsListModel.clear();
            List<Developer> devs = model.getDevelopers();
            for (Developer dev : devs) {
                if (dev.isAvailable()) {
                    devsListModel.addElement(dev);
                }
            }
            cmbModel.setSelectedItem(null);
            skillTblModel.setColumnIdentifiers(new String[]{"Skill", "Level"});
            skillTable.init(skillTblModel);
        }
        //Listeners
        {
            cancelBtn.addMouseListener(new CancelButtonMouseAdapter());
            devsJListPanel.addJListOnSelectionListener(this);
            assignAreaCmB.addActionListener(this);
        }
        //Styling
        {
            devName.setForeground(Colors.DARKBLUE);
            devName.setFont(new Font("Century Gothic", Font.BOLD, 22));
            devName.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainSkill.setFont(font);
            mainSkill.setAlignmentX(Component.LEFT_ALIGNMENT);
            production.setFont(font);
            production.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoInnerPanel.setOpaque(false);
            infoInnerPanel.setBounds(15, 10, 490, 440);
            assignLbl.setFont(font);
            assignAreaCmB.setBackground(Colors.LIGHTORANGE);
            assignedAreaPanel.setBackground(Colors.LIGHTORANGE);
            assignedAreaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            backgroundPanel.setBackground(colour);
            applyBtnPanel.setOpaque(false);
            cancelBtnPanel.setOpaque(false);
            infoNorthInnerPanel.setBackground(Colors.LIGHTORANGE);
            infoNorthProjectInfoPanel.setBackground(Colors.LIGHTORANGE);
            infoNorthPanel.setOpaque(false);
        }
        //Layout. Must be in the right order
        {
            //Layout Manager
            backgroundPanel.setLayout(new BorderLayout());
            infoGroupPanel.setLayout(null);
            infoNorthPanel.setLayout(new BoxLayout(infoNorthPanel, BoxLayout.Y_AXIS));
            infoNorthInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            infoNorthProjectInfoPanel.setLayout(new BoxLayout(infoNorthProjectInfoPanel, BoxLayout.Y_AXIS));
            assignedAreaPanel.setLayout(new BoxLayout(assignedAreaPanel, BoxLayout.X_AXIS));
            btnPanel.setLayout(new GridLayout(1, 2));
            //Add components
            add(backgroundPanel);
            backgroundPanel.add(devsJListPanel, BorderLayout.WEST);
            backgroundPanel.add(infoGroupPanel, BorderLayout.CENTER);
            backgroundPanel.add(btnPanel, BorderLayout.SOUTH);
            infoGroupPanel.add(infoInnerPanel);
            infoInnerPanel.add(infoNorthPanel, BorderLayout.NORTH);
            infoInnerPanel.add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
            infoNorthPanel.add(devName);
            infoNorthPanel.add(infoNorthInnerPanel);
            infoNorthInnerPanel.add(new JLabel(new ImageIcon(picture)));
            infoNorthInnerPanel.add(infoNorthProjectInfoPanel);
            infoNorthProjectInfoPanel.add(mainSkill);
            infoNorthProjectInfoPanel.add(production);
            infoNorthProjectInfoPanel.add(assignedAreaPanel);
            assignedAreaPanel.add(assignLbl);
            assignedAreaPanel.add(assignAreaCmB);
            btnPanel.add(applyBtnPanel);
            btnPanel.add(cancelBtnPanel);
            applyBtnPanel.add(applyBtn);
            cancelBtnPanel.add(cancelBtn);
        }

        infoInnerPanel.setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setSize(800, 540);
        setResizable(false);
        setLocationRelativeTo(null);
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
                skillTblModel.addRow(new Object[]{skill.getSkillInfo().getName(), skill.getLevel()});
            }
        }
        infoInnerPanel.setVisible(true);
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
            AddDeveloperToProjectDialog.this.dispose();
        }
    }
}
