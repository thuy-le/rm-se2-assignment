/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.exceptions.GameOverException;
import devfortress.models.GameEngine;
import devfortress.view.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class GameViewController {

    private DevFortress devFortress;
    private WelcomeScreen welCm;
    private TabbedPane tabPne;
    private NavigationPane navPne;
    private InfomationPane infoPne;
//    private TabbedPaneSystem tabPne;
    private AboutScreen aboutScr;
    private GameEngine model;

    public GameViewController(DevFortress devFortress, WelcomeScreen welCm, NavigationPane navPne, InfomationPane infoPne, TabbedPane sysPne, AboutScreen aboutScr, GameEngine model) {
        this.devFortress = devFortress;
        this.welCm = welCm;
        this.aboutScr = aboutScr;
        this.navPne = navPne;
        this.infoPne = infoPne;
        this.tabPne = sysPne;
        this.model = model;
    }

    /**
     * A temporary method to initialize the game and set up the controllers
     */
    public void initialize() {
        infoPne.addWeekTurnListener(new NextWeekListener());
        welCm.addSubmitNameListener(new SubmitNameListener());
        navPne.addAboutGameListener(new AboutGameListener());
        navPne.addExitGameListener(new ExitGameListener());
        navPne.addNewGameListener(new NewGameListener());
        navPne.addSaveGameListener(new SaveGameListener());
        aboutScr.addBackListener(new BackAboutListener());

    }

    private class NextWeekListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                model.nextWeek();
            } catch (GameOverException ex) {
                model.notifyObservers();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            model.notifyObservers();
            System.out.println("Remain: " + model.getBudget());
        }
    }
    //TODO: When submit, the game need to be reset before re-initialized

    private class SubmitNameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!welCm.getPlayerName().trim().equals("")) {
                model.initialize(welCm.getPlayerName());
                devFortress.remove(welCm);
                devFortress.add(tabPne, BorderLayout.CENTER);
                navPne.getToolbar().setVisible(true);
                infoPne.getInfoPanel().setVisible(true);
                tabPne.getSysTab().setPlayerName(model.getPlayerName());
                tabPne.getSysTab().setBudget(model.getBudget());
                model.notifyObservers();

            }
        }
    }

    //TODO: Need to save the current game
    private class NewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            devFortress.remove(tabPne);
            navPne.getToolbar().setVisible(false);
            infoPne.getInfoPanel().setVisible(false);
            devFortress.getContentPane().add(welCm);
        }
    }

    //This is finished
    private class AboutGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            devFortress.remove(tabPne);
            navPne.getToolbar().setVisible(false);
            infoPne.getInfoPanel().setVisible(false);
            devFortress.getContentPane().add(aboutScr);
            devFortress.getContentPane().repaint();
        }
    }

    //TODO: Should save the game first
    private class ExitGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

    //TODO: implement save game
    private class SaveGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Save Game Not Implemented");
        }
    }
    //This is finished

    private class BackAboutListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            devFortress.remove(aboutScr);
            devFortress.add(tabPne, BorderLayout.CENTER);
            navPne.getToolbar().setVisible(true);
            infoPne.getInfoPanel().setVisible(true);
            devFortress.repaint();
        }
    }
}
