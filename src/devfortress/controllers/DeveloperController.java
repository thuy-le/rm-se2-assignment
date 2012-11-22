/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.exceptions.DeveloperBusyException;
import devfortress.exceptions.InsufficientBudgetException;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.*;
import devfortress.view.interfaces.DeveloperInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperController {

    private DeveloperInterface view;
    private GameEngine model;
    private AvailableDevelopers availableDev;
    private NavigationPane navPane;
    private InfomationPane info;
    private TabbedPane tabPne;
    private DevFortress devFortress;

    public DeveloperController(DeveloperInterface view, GameEngine model, AvailableDevelopers availableDev, NavigationPane navPane, InfomationPane info, TabbedPane tabPne, DevFortress devFortress) {
        this.view = view;
        this.model = model;
        this.availableDev = availableDev;
        this.navPane = navPane;
        this.info = info;
        this.tabPne = tabPne;
        this.devFortress = devFortress;
    }

    public void initilize() {
        //Feed all developers
        view.addFeedAllDevListener(new FeedAllMouseListener());
        //Open new windown, choose developer to add
        view.addHireDevListener(new HireDeveloperMouseListener());
        //Give all developers beer
        view.addPartyListener(new PartyMouseListener());
        //Feed Selected Developer
        view.addFeedSelectedDevListener(new FeedDeveloperListener());
        //Fire selected Developer
        view.addFireDevListener(new FireDeveloperListener());
        //Give selected Developer beer
        view.addGiveBeerListener(new GiveBeerToDevListener());
        //close hirePane
        availableDev.addCloseHirePaneEvent(new CloseHirePaneListener());
        //train developer
        view.addTrainDeveloperListener(new TrainSkillListener());
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

    private class TrainSkillListener extends MouseAdapter {

        private int index;

        @Override
        public void mouseClicked(MouseEvent e) {
            index = view.getSelectedIndex();
            TrainSkillDialog dialog = new TrainSkillDialog(view.getSelectedDeveloper());
            dialog.addWindowListener(new TrainSkillWindowsListener());
            TrainSkillController trainSkillController = new TrainSkillController(dialog, model);
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
                view.setSelectedDeveloper(index);
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
            info.getInfoPanel().setVisible(false);
            navPane.getToolbar().setVisible(false);
            devFortress.add(availableDev);
//            devFortress.repaint();
        }
    }

    private class CloseHirePaneListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            //JOptionPane.showMessageDialog(null, "Hire new");
            devFortress.remove(availableDev);
            info.getInfoPanel().setVisible(true);
            navPane.getToolbar().setVisible(true);
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
            Developer dev = view.getSelectedDeveloper();
            int index = view.getSelectedIndex();
            if (dev != null) {
                try {
                    model.feedDeveloper(dev);
                    model.notifyObservers();
                    view.setSelectedDeveloper(index);
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
            Developer dev = view.getSelectedDeveloper();
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
            Developer dev = view.getSelectedDeveloper();
            int index = view.getSelectedIndex();
            if (dev != null) {
                try {
                    model.giveDeveloperBeer(dev);
                    JOptionPane.showMessageDialog(null, dev.getName() + " is now very happy");
                    model.notifyObservers();
                    view.setSelectedDeveloper(index);
                } catch (InsufficientBudgetException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have to select a developer");
            }
        }
    }
}
