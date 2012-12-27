/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.enumerations.Options;
import devfortress.utilities.Colors;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;

/**
 *
 * @author Team Poseidon
 */
public class SettingDialog extends JDialog {
    // Variables declaration - do not modify

    private static final Color COLOR = Colors.LIGHTRED;
    private JRadioButton allBeerMonthly;
    private JRadioButton allBeerWeekly;
    private JButton applyBtn;
    private JRadioButton keepAllDevFulRadio;
    private JRadioButton keepWorkingFullRadio;
    private JRadioButton manualFeed;
    private JRadioButton manualGiveBeer;
    private JRadioButton unhappyBeerMonthly;
    private JRadioButton unhappyBeerWeekly;
    private ButtonGroup feedGroup, beerGroup;
//    private DefaultButtonModel feedModel, beerModel;

    public SettingDialog() {
        keepAllDevFulRadio = new JRadioButton("Keep all developers full");
        keepWorkingFullRadio = new JRadioButton("Keep only working developers full");
        manualFeed = new JRadioButton("Manually feed developers");
        allBeerWeekly = new JRadioButton("Let all developers have beers every week");
        unhappyBeerWeekly = new JRadioButton("Let only unhappy developers have beers every week");
        allBeerMonthly = new JRadioButton("Let all developers have beers at the end of month");
        unhappyBeerMonthly = new JRadioButton("Let unhappy developers have beers at the end of month");
        manualGiveBeer = new JRadioButton("Manually give beers to developers");
        applyBtn = new JButton("Apply");
        feedGroup = new ButtonGroup();
        beerGroup = new ButtonGroup();
//        feedModel = new DefaultButtonModel();
//        beerModel = new DefaultButtonModel();
//        feedGroup.setSelected(feedModel, false);
//        beerGroup.setSelected(beerModel, false);
        JButton cancelBtn = new JButton("Cancel");
        JSeparator seperatorFeed = new JSeparator();
        JSeparator seperatorBeer = new JSeparator();
        JLabel feedLbl = new JLabel("Feeding options");
        JLabel giveBeerLbl = new JLabel("Giving beers options");
        JPanel innerPanel = new JPanel();
        GroupLayout layout = new GroupLayout(innerPanel);
        feedGroup.add(keepAllDevFulRadio);
        feedGroup.add(keepWorkingFullRadio);
        feedGroup.add(manualFeed);
        beerGroup.add(allBeerWeekly);
        beerGroup.add(unhappyBeerWeekly);
        beerGroup.add(allBeerMonthly);
        beerGroup.add(unhappyBeerMonthly);
        beerGroup.add(manualGiveBeer);
        cancelBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                cancel();
            }
        });
        add(innerPanel);
        innerPanel.setLayout(layout);
        innerPanel.setBackground(COLOR);
        keepAllDevFulRadio.setOpaque(false);
        keepWorkingFullRadio.setOpaque(false);
        manualFeed.setOpaque(false);
        allBeerWeekly.setOpaque(false);
        allBeerMonthly.setOpaque(false);
        unhappyBeerWeekly.setOpaque(false);
        unhappyBeerMonthly.setOpaque(false);
        manualGiveBeer.setOpaque(false);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(seperatorBeer, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(unhappyBeerWeekly, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(seperatorFeed, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(keepAllDevFulRadio, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(manualFeed, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(keepWorkingFullRadio, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(allBeerWeekly, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(applyBtn, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)).addComponent(giveBeerLbl, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(feedLbl, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(allBeerMonthly, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(unhappyBeerMonthly, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addComponent(manualGiveBeer, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)).addContainerGap()));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(feedLbl).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(keepAllDevFulRadio).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(keepWorkingFullRadio).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(manualFeed).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(seperatorFeed, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(giveBeerLbl).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(allBeerWeekly).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(unhappyBeerWeekly).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(allBeerMonthly).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(unhappyBeerMonthly).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(manualGiveBeer).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(seperatorBeer, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(cancelBtn).addComponent(applyBtn)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Setting");
        setResizable(false);
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    public void addApplyMouseListener(MouseListener l) {
        applyBtn.addMouseListener(l);
    }

    public Options getFeedOption() {
        ButtonModel btnModel = feedGroup.getSelection();
        if (btnModel == null) {
            return null;
        }
        if (btnModel == keepAllDevFulRadio.getModel()) {
            return Options.KEEP_ALL_DEVS_FULL;
        } else if (btnModel == keepWorkingFullRadio.getModel()) {
            return Options.KEEP_WORKING_DEVS_FULL;
        } else {
            return Options.MANUALLY_FEED_DEVS;
        }
    }

    public Options getBeerOption() {
        ButtonModel btnModel = beerGroup.getSelection();
        if (btnModel == null) {
            return null;
        }
        if (btnModel == allBeerWeekly.getModel()) {
            return Options.ALL_DEVS_BEER_WEEKLY;
        } else if (btnModel == unhappyBeerWeekly.getModel()) {
            return Options.UNHAPPY_DEVS_BEER_WEEKLY;
        } else if (btnModel == allBeerMonthly.getModel()) {
            return Options.ALL_DEVS_BEER_MONTHLY;
        } else if (btnModel == unhappyBeerMonthly.getModel()) {
            return Options.UNHAPPY_DEVS_BEER_MONTHLY;
        } else {
            return Options.MANUALLY_GIVE_BEER;
        }
    }

    private void cancel() {
        this.dispose();
    }
}
