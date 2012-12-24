package devfortress.controllers;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InsufficientBudgetException;
import devfortress.view.*;
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

    private DeveloperTabPanel developerTab;
    private GameEngine model;
    private HireDeveloperPanel hireDevPnl;
    private NavigationToolBar navBar;
    private InfomationPanel infoPnl;
    private DevFortressTabbedPane tabPne;
    private DevFortressMainFrame mainFrame;
    private SystemTabPanel tabSystem;

    public DeveloperTabController(DeveloperTabPanel view, GameEngine model, HireDeveloperPanel availableDev, NavigationToolBar navPane, InfomationPanel info, DevFortressTabbedPane tabPne, DevFortressMainFrame mainFrame, SystemTabPanel tabSystem) {
        this.developerTab = view;
        this.model = model;
        this.hireDevPnl = availableDev;
        this.navBar = navPane;
        this.infoPnl = info;
        this.tabPne = tabPne;
        this.mainFrame = mainFrame;
        this.tabSystem = tabSystem;
    }

    public void initialize() {
        developerTab.addFeedAllDevListener(new FeedAllMouseListener());
        developerTab.addHireDevListener(new HireDeveloperMouseListener());
        developerTab.addPartyListener(new PartyMouseListener());
        developerTab.addFeedSelectedDevListener(new FeedDeveloperListener());
        developerTab.addFireDevListener(new FireDeveloperListener());
        developerTab.addGiveBeerListener(new GiveBeerToDevListener());
        hireDevPnl.addCloseHirePaneEvent(new CloseHirePaneListener());
        hireDevPnl.addHireDeveloperEvent(new HireDeveloperListener());
        developerTab.addTrainDeveloperListener(new TrainSkillListener());
    }

    private class FeedAllMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            List<Developer> devs = model.getDevelopers();
            if (devs.isEmpty()) {
            } else {
                try {
                    int cost = model.getFeedingExpense() * devs.size();
                    if (cost > model.getBudget()) {
                        throw new InsufficientBudgetException();
                    }
                    for (Developer dev : devs) {
                        model.feedDeveloper(dev);
                    }
                    model.notifyObservers();
                    JOptionPane.showMessageDialog(null, "All developers are full");
                } catch (InsufficientBudgetException ex) {
                    model.notifyObservers();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    private class HireDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = (Developer) hireDevPnl.getDevToHire();
            if (dev != null) {
                int choosen = JOptionPane.showConfirmDialog(mainFrame, "Do you want to hire this developer?","DevFortress", JOptionPane.YES_NO_OPTION);
                if (choosen == JOptionPane.YES_OPTION) {
                    int numPCs = model.getNumPCs();
                    model.hireDeveloper(dev);
                    developerTab.getDevModel().addElement(dev);
                    hireDevPnl.getDevModel().removeElement(dev);
                    model.notifyObservers();
                    if (model.getNumPCs() > numPCs) {
                        JOptionPane.showMessageDialog(null, dev.getName() + " is hired\n1 PC more is purchased", "DevFortress", JOptionPane.DEFAULT_OPTION);
                        developerTab.btnFeed.enableButton();
                        developerTab.btnParty.enableButton();
                    } else {
                        JOptionPane.showMessageDialog(null, dev.getName() + " is hired");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to choose 1 developer to hire", "DevFortress", JOptionPane.DEFAULT_OPTION);
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
            model.addObserver(dialog);
            dialog.update(model, null);
            trainSkillController.initialize();
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
            if (model.getDate().getWeek() == 1) {
                mainFrame.remove(tabPne);
                infoPnl.setVisible(false);
                navBar.setVisible(false);
                mainFrame.add(hireDevPnl);
            } else {
                JOptionPane.showMessageDialog(null, "You can only hire new developers at the beginning of each month");
            }
        }
    }

    private class CloseHirePaneListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(hireDevPnl);
            infoPnl.setVisible(true);
            navBar.setVisible(true);
            mainFrame.add(tabPne);
            mainFrame.repaint();
        }
    }

    private class PartyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            List<Developer> devs = model.getDevelopers();
            if (devs.isEmpty()) {
            } else {
                try {
                    int cost = model.getBeerExpense() * devs.size();
                    if (cost > model.getBudget()) {
                        throw new InsufficientBudgetException();
                    }
                    for (Developer dev : devs) {
                        model.giveDeveloperBeer(dev);
                    }
                    model.notifyObservers();
                    JOptionPane.showMessageDialog(null, "All developers are now happy! Yey!");
                } catch (InsufficientBudgetException ex) {
                    model.notifyObservers();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
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
            if (model.getDate().getWeek() == 1) {
                Developer dev = developerTab.getSelectedDeveloper();
                List<Developer> devs = model.getDevelopers();
                if (dev != null) {
                    try {
                        model.fireDeveloper(dev);
                        JOptionPane.showMessageDialog(null, dev.getName() + " is fired");
                        model.notifyObservers();
                        if (devs.isEmpty()) {
                            developerTab.btnFeed.disableButton();
                            developerTab.btnParty.disableButton();
                        }
                    } catch (DeveloperBusyException ex) {
                        JOptionPane.showMessageDialog(null, dev.getName() + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have to select a developer");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You can only fire developers at the beginning of each month");
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
