/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.exceptions.GameNotInitilizedException;
import devfortress.models.GameEngine;
import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.GlassPanel;
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
    private NavigationPane navPne;
    private InfomationPane infoPne;
    private TabbedPaneSystem sysPne;
    private AboutScreen aboutScr;
    private GameEngine model;

    public GameViewController(DevFortress devFortress, WelcomeScreen welCm, NavigationPane navPne, InfomationPane infoPne, TabbedPaneSystem sysPne, AboutScreen aboutScr, GameEngine model) {
        this.devFortress = devFortress;
        this.welCm = welCm;
        this.aboutScr = aboutScr;
        this.navPne = navPne;
        this.infoPne = infoPne;
        this.sysPne = sysPne;
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
        System.out.println("Init");

    }

    private class SubmitNameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Submit");
            if (!WelcomeScreen.getInstance().getPlayerName().trim().equals("")) {
                devFortress.remove(welCm);
                devFortress.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                navPne.getToolbar().setVisible(true);
                infoPne.getInfoPanel().setVisible(true);
                sysPne.setPlayerName(welCm.getPlayerName());
                devFortress.repaint();
            }
        }
    }

    private class NewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            devFortress.remove(TabbedPane.getInstance().getContainerTab());
            navPne.getToolbar().setVisible(false);
            infoPne.getInfoPanel().setVisible(false);
            devFortress.getContentPane().add(WelcomeScreen.getInstance());
            devFortress.getContentPane().repaint();
        }
    }

    private class AboutGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("About");
            devFortress.remove(TabbedPane.getInstance().getContainerTab());
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
            System.out.println("Back clicked");
            devFortress.remove(aboutScr);
            devFortress.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
            navPne.getToolbar().setVisible(true);
            infoPne.getInfoPanel().setVisible(true);
            devFortress.repaint();
        }
    }
}
