/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.enumerations.SkillInfo;
import devfortress.models.Developer;
import devfortress.models.Skill;
import devfortress.utilities.CustomButton;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Poseidon
 */
public class TrainSkillDialog extends JFrame implements Observer {

    private Developer developer = new Developer();
    private JTable skillTable;
    private DefaultTableModel skillModel;
    private SkillInfo infos[];

    public TrainSkillDialog() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        developer = new Developer();
        skillModel = new DefaultTableModel(0, 2);
        skillTable = new CustomTable(skillModel);
        infos = SkillInfo.values();
        Object[][] objs = new Object[infos.length][2];
        for (int i = 0; i < objs.length; i++) {
            try {
                SkillInfo info = infos[i];
                String name = infos[i].getName();
                Skill skill = developer.getSkills().get(infos[i]);
                if (skill == null) {
                    Object[] os = {infos[i].getName(), 0, new CustomButton("Train")};
                    skillModel.addRow(os);
                } else {
                    Object[] os = {infos[i].getName(), skill.getLevel(), new CustomButton("Train")};
                    skillModel.addRow(os);
                }


            } catch (Exception ex) {
            }
        }
        Object[] ids = {"Skill Name", "Level"};
        skillModel.setColumnIdentifiers(ids);
        skillTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        skillTable.getColumnModel().getColumn(1).setMaxWidth(80);
        add(((CustomTable) skillTable).getTableScroll(), BorderLayout.CENTER);
        SelectionListener selectionListener = new SelectionListener(skillTable);
        skillTable.getSelectionModel().addListSelectionListener(selectionListener);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public TrainSkillDialog(Developer developer) {
        this.developer = developer;
    }

    public static void main(String[] args) {
        TrainSkillDialog dialog = new TrainSkillDialog();
        dialog.display();
    }

    public void display() {
//        setModal(true);
//        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    private class SelectionListener implements ListSelectionListener {

        JTable table;

        SelectionListener(JTable table) {
            this.table = table;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
                int current = table.getSelectedRow();
                System.out.println(infos[current]);
            } else if (e.getSource() == table.getColumnModel().getSelectionModel() && table.getColumnSelectionAllowed()) {
                int first = e.getFirstIndex();
                int last = e.getLastIndex();
            }
            if (e.getValueIsAdjusting()) {
            }
        }
    }
}
