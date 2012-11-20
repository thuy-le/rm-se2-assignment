/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.exceptions.GameAlreadyInitializedException;
import devfortress.models.GameEngine;
import devfortress.view.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        welCm.addSubmitNameListener(new SubmitNameListener());
        navPne.addAboutGameListener(new AboutGameListener());
        navPne.addExitGameListener(new ExitGameListener());
        navPne.addNewGameListener(new NewGameListener());
        navPne.addSaveGameListener(new SaveGameListener());
        aboutScr.addBackListener(new BackAboutListener());

    }

    private class SubmitNameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!welCm.getPlayerName().trim().equals("")) {
//                try {
                model.initialize(welCm.getPlayerName());
//                } catch (GameAlreadyInitializedException ex) {
//                    JOptionPane.showMessageDialog(null, "You're already in a game");
//                }
                devFortress.remove(welCm);
                devFortress.add(tabPne, BorderLayout.CENTER);
                navPne.getToolbar().setVisible(true);
                infoPne.getInfoPanel().setVisible(true);
                tabPne.getSysTab().setPlayerName(model.getPlayerName());
                tabPne.getSysTab().setBudget(model.getBudget());
                model.notifyObservers();
//                devFortress.repaint();

            }
        }
    }

    private class NewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            devFortress.remove(tabPne);
            navPne.getToolbar().setVisible(false);
            infoPne.getInfoPanel().setVisible(false);
            devFortress.getContentPane().add(welCm);
//            devFortress.getContentPane().repaint();
        }
    }

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

    private class ExitGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

    private class SaveGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Save Game Not Implemented");
        }
    }

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
