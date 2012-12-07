package devfortress.controllers;

import devfortress.models.exceptions.GameOverException;
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
public class MainFrameController {

    private DevFortressMainFrame mainFrame;
    private WelcomePanel welCm;
    private DevFortressTabbedPane tabPne;
    private NavigationToolBar navBar;
    private InfomationPanel infoPnl;
    private AboutScreenPanel aboutPnl;
    private GameEngine model;

    public MainFrameController(DevFortressMainFrame mainFrame, WelcomePanel welCm, NavigationToolBar navPne, InfomationPanel infoPnl, DevFortressTabbedPane tabPne, AboutScreenPanel aboutPnl, GameEngine model) {
        this.mainFrame = mainFrame;
        this.welCm = welCm;
        this.aboutPnl = aboutPnl;
        this.navBar = navPne;
        this.infoPnl = infoPnl;
        this.tabPne = tabPne;
        this.model = model;
    }

    /**
     * A temporary method to initialize the game and set up the controllers
     */
    public void initialize() {
        infoPnl.addWeekTurnListener(new NextWeekListener());
        welCm.addSubmitNameListener(new SubmitNameListener());
        navBar.addAboutGameListener(new AboutGameListener());
        navBar.addExitGameListener(new ExitGameListener());
        navBar.addNewGameListener(new NewGameListener());
        navBar.addSaveGameListener(new SaveGameListener());
        aboutPnl.addBackListener(new AboutBackBtnListener());

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
        }
    }
    //TODO: When submit, the game need to be reset before re-initialized

    private class SubmitNameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!welCm.getPlayerName().trim().equals("")) {
                model.initialize(welCm.getPlayerName());
                mainFrame.remove(welCm.getContainer());
                mainFrame.add(tabPne, BorderLayout.CENTER);
                navBar.setVisible(true);
                infoPnl.setVisible(true);
                tabPne.getSystemTab().setPlayerName(model.getPlayerName());
                tabPne.getSystemTab().setBudget(model.getBudget());
                model.notifyObservers();

            }
        }
    }

    //TODO: Need to save the current game
    private class NewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(tabPne);
            navBar.setVisible(false);
            infoPnl.setVisible(false);
            mainFrame.getContentPane().add(welCm.getContainer());
        }
    }

    //This is finished
    private class AboutGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(tabPne);
            navBar.setVisible(false);
            infoPnl.setVisible(false);
            mainFrame.getContentPane().add(aboutPnl);
            mainFrame.getContentPane().repaint();
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

    private class AboutBackBtnListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(aboutPnl);
            mainFrame.add(tabPne, BorderLayout.CENTER);
            navBar.setVisible(true);
            infoPnl.setVisible(true);
            mainFrame.repaint();
        }
    }
}