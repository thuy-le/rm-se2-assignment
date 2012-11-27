/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InsufficientBudgetException;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.*;
import devfortress.view.interfaces.DevTabView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperTabController {

    private DevTabView developerTab;
    private GameEngine model;
    private HireDeveloperPanel hireDevPnl;
    private NavigationToolBar navBar;
    private InfomationPanel infoPnl;
    private DevFortressTabbedPane tabPne;
    private DevFortressMainFrame devFortress;
    private SystemTabPanel tabSystem;

    public DeveloperTabController(DevTabView view, GameEngine model, HireDeveloperPanel availableDev, NavigationToolBar navPane, InfomationPanel info, DevFortressTabbedPane tabPne, DevFortressMainFrame devFortress, SystemTabPanel tabSystem) {
        this.developerTab = view;
        this.model = model;
        this.hireDevPnl = availableDev;
        this.navBar = navPane;
        this.infoPnl = info;
        this.tabPne = tabPne;
        this.devFortress = devFortress;
        this.tabSystem = tabSystem;
    }

    public void initilize() {
        //Feed all developers
        developerTab.addFeedAllDevListener(new FeedAllMouseListener());
        //Open new windown, choose developer to add
        developerTab.addHireDevListener(new HireDeveloperMouseListener());
        //Give all developers beer
        developerTab.addPartyListener(new PartyMouseListener());
        //Feed Selected Developer
        developerTab.addFeedSelectedDevListener(new FeedDeveloperListener());
        //Fire selected Developer
        developerTab.addFireDevListener(new FireDeveloperListener());
        //Give selected Developer beer
        developerTab.addGiveBeerListener(new GiveBeerToDevListener());
        //close hirePane
        hireDevPnl.addCloseHirePaneEvent(new CloseHirePaneListener());
        //hre dev
        hireDevPnl.addHireDeveloperEvent(new HireDeveloperListener());
        //train developer
        developerTab.addTrainDeveloperListener(new TrainSkillListener());
    }

    private class FeedAllMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            List<Developer> devs = model.getDevelopers();
            try {
                for (Developer dev : devs) {
                    model.feedDeveloper(dev);
                }
                model.notifyObservers();
                JOptionPane.showMessageDialog(null, "All developers are full");
            } catch (InsufficientBudgetException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class HireDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = (Developer) hireDevPnl.getDevToHire();
            if (dev != null) {
                int numPCs = model.getNumPCs();
                model.hireDeveloper(dev);
                developerTab.getDevModel().addElement(dev);
                tabSystem.getDevModel().addElement(dev);
                hireDevPnl.getDevModel().removeElement(dev);
                devFortress.remove(hireDevPnl);
                infoPnl.setVisible(true);
                navBar.setVisible(true);
                devFortress.add(tabPne);
                model.notifyObservers();
                if (model.getNumPCs() > numPCs) {
                    JOptionPane.showMessageDialog(null, dev.getName() + " is hired\n1 PC more is purchased");
                } else {
                    JOptionPane.showMessageDialog(null, dev.getName() + " is hired");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to choose 1 developer to hire");
            }
        }
    }

    private class TrainSkillListener extends MouseAdapter {

        private int index;

        @Override
        public void mouseClicked(MouseEvent e) {
            index = developerTab.getSelectedIndex();
            TrainSkillDialog dialog = new TrainSkillDialog(developerTab.getSelectedDeveloper());
            dialog.addWindowListener(new TrainSkillWindowsListener());
            TrainSkillDialogController trainSkillController = new TrainSkillDialogController(dialog, model);
            trainSkillController.initialize();
            model.addObserver(dialog);
            dialog.display();
        }

        private class TrainSkillWindowsListener implements WindowListener {

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                developerTab.setSelectedDeveloper(index);
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }
        }
    }

    private class HireDeveloperMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            //JOptionPane.showMessageDialog(null, "Hire new");
            devFortress.remove(tabPne);
            infoPnl.setVisible(false);
            navBar.setVisible(false);
            devFortress.add(hireDevPnl);
//            devFortress.repaint();
        }
    }

    private class CloseHirePaneListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            //JOptionPane.showMessageDialog(null, "Hire new");
            devFortress.remove(hireDevPnl);
            infoPnl.setVisible(true);
            navBar.setVisible(true);
            devFortress.add(tabPne);
            devFortress.repaint();
        }
    }

    private class PartyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            List<Developer> devs = model.getDevelopers();
            try {
                for (Developer dev : devs) {
                    model.giveDeveloperBeer(dev);
                }
                model.notifyObservers();
                JOptionPane.showMessageDialog(null, "All developers are now happy! Yey!");
            } catch (InsufficientBudgetException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class FeedDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = developerTab.getSelectedDeveloper();
            int index = developerTab.getSelectedIndex();
            if (dev != null) {
                try {
                    model.feedDeveloper(dev);
                    model.notifyObservers();
                    developerTab.setSelectedDeveloper(index);
                    JOptionPane.showMessageDialog(null, dev.getName() + " is now full");
                } catch (InsufficientBudgetException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have to select a developer");
            }
        }
    }

    private class FireDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = developerTab.getSelectedDeveloper();
            if (dev != null) {
                try {
                    model.fireDeveloper(dev);
                    JOptionPane.showMessageDialog(null, dev.getName() + " is fired");
                    model.notifyObservers();
                } catch (DeveloperBusyException ex) {
                    JOptionPane.showMessageDialog(null, dev.getName() + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have to select a developer");
            }
        }
    }

    private class GiveBeerToDevListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = developerTab.getSelectedDeveloper();
            int index = developerTab.getSelectedIndex();
            if (dev != null) {
                try {
                    model.giveDeveloperBeer(dev);
                    model.notifyObservers();
                    developerTab.setSelectedDeveloper(index);
                    JOptionPane.showMessageDialog(null, dev.getName() + " is now very happy");
                } catch (InsufficientBudgetException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have to select a developer");
            }
        }
    }
}
