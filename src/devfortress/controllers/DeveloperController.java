/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.*;
import devfortress.view.interfaces.DeveloperInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperController implements Initilizable {

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

    @Override
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
    }

    private class FeedAllMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Feed all");
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
            devFortress.repaint();
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
            JOptionPane.showMessageDialog(null, "Party");
        }
    }

    private class FeedDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Feed");
        }
    }

    private class FireDeveloperListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = view.getSelectedDeveloper();
            if (dev != null) {
                JOptionPane.showMessageDialog(null, "Fire  " + dev.getName());
            }
        }
    }

    private class GiveBeerToDevListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Drink beer");
        }
    }
}
