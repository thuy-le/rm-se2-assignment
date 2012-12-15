/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import com.sun.media.sound.JARSoundbankReader;
import devfortress.view.components.CustomTable;
import devfortress.enumerations.SkillInfo;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.Skill;
import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class TrainSkillDialog extends JDialog implements Observer {

    private Developer developer;   
    private JTable skillTable;
    private DefaultTableModel skillModel;
    private SkillInfo infos[];
    private CustomButton btnTrain, btnClose;
    private int selectedIndex;
    private JLabel budgetLbl;

    public TrainSkillDialog(Developer developer) {
        this.developer = developer;        
        setSize(800, 600);
        setLayout(new BorderLayout());
        skillModel = new DefaultTableModel(0, 3);
        skillTable = new CustomTable(skillModel);
        infos = SkillInfo.values();
        add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
        SelectionListener selectionListener = new SelectionListener(skillTable);
        skillTable.getSelectionModel().addListSelectionListener(selectionListener);
        skillTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel bottom = new JPanel();
        btnTrain = new CustomButton("Train");
        btnClose = new CustomButton("Close");
        bottom.add(btnTrain);
        bottom.add(btnClose);

        JPanel top = new JPanel();
        budgetLbl = new JLabel("");
        top.setBackground(Colors.DARKBLUE);
        budgetLbl.setForeground(Color.WHITE);
        budgetLbl.setFont(new Font("Century Gothic", Font.PLAIN, 22));
        budgetLbl.setText("Budget: $1000");
        top.add(budgetLbl);        
        add(top, BorderLayout.NORTH);            
        add(bottom, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setTitle(developer.getName() + " - Training");
        update(null, null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public Developer getDeveloper() {
        return developer;
    }

    public SkillInfo getSkillInfo() {
        if (selectedIndex == -1) {
            return null;
        }
        return infos[selectedIndex];
    }

    public void addTrainSkillListener(MouseListener l) {
        btnTrain.addMouseListener(l);
    }

    public void addCloseListener(MouseListener l) {
        btnClose.addMouseListener(l);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
    }

    public void display() {
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {     
        while (skillModel.getRowCount() > 0) {
            skillModel.removeRow(0);
        }
        Object[][] objs = new Object[infos.length][2];
        for (int i = 0; i < objs.length; i++) {
            SkillInfo info = infos[i];
            String name = infos[i].getName();
            Skill skill = developer.getSkills().get(infos[i]);
            if (skill == null) {
                Object[] os = {info.getType(), name, 0};
                skillModel.addRow(os);
            } else {
                Object[] os = {info.getType(), name, skill.getLevel()};
                skillModel.addRow(os);
            }           
        }        
        Object[] ids = {"Type", "Skill Name", "Level"};
        skillModel.setColumnIdentifiers(ids);
        skillTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        skillTable.getColumnModel().getColumn(0).setMaxWidth(200);
        skillTable.getColumnModel().getColumn(0).setMinWidth(200);
        skillTable.getColumnModel().getColumn(2).setMaxWidth(80);           
    }

    private class SelectionListener implements ListSelectionListener {

        private JTable table;

        SelectionListener(JTable table) {
            this.table = table;
            selectedIndex = -1;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int current = table.getSelectedRow();
            if (selectedIndex != current && e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
                selectedIndex = current;
            }
        }
    }
}
