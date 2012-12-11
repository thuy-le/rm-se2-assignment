package devfortress.controllers;

import devfortress.models.exceptions.GameOverException;
import devfortress.models.GameEngine;
import devfortress.view.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author PC
 */
public class MainFrameController {

    private DevFortressMainFrame mainFrame;
    private NewGameWelcomePanel welCm;
    private DevFortressTabbedPane tabPne;
    private NavigationToolBar navBar;
    private InfomationPanel infoPnl;
    private AboutScreenPanel aboutPnl;
    private GameEngine model;
    private WelcomePanel welcome;

    public MainFrameController(DevFortressMainFrame mainFrame, NewGameWelcomePanel welCm, NavigationToolBar navPne, InfomationPanel infoPnl, DevFortressTabbedPane tabPne, AboutScreenPanel aboutPnl, GameEngine model, WelcomePanel welcome) {
        this.mainFrame = mainFrame;
        this.welCm = welCm;
        this.aboutPnl = aboutPnl;
        this.navBar = navPne;
        this.infoPnl = infoPnl;
        this.tabPne = tabPne;
        this.model = model;
        this.welcome = welcome;
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
        navBar.addLoadGameListener(new LoadGameListener());
        aboutPnl.addBackListener(new AboutBackBtnListener());
        welcome.addNewGameListener(new OpenNewGameListener());

    }

    private class OpenNewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(welcome.getContainer());
            //welCm.getContainer().setVisible(true);
            mainFrame.getContentPane().add(welCm.getContainer(), BorderLayout.CENTER);
            mainFrame.getContentPane().repaint();
        }
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

    /**
     * A class that take care of saving game.
     */
    private class SaveGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            fileChooser.setDialogTitle("Save Game");
            fileChooser.setVisible(true);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Save file", "save"));

            int returnVal = fileChooser.showDialog(null, "Save...");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    model.saveBinary(file.getAbsolutePath());
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                    String error = "Error Occur! Cannot save game.";
                    JOptionPane.showMessageDialog(null, error, null, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class LoadGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.setDialogTitle("Load Game");
            fileChooser.setVisible(true);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Save file", "save"));

            int returnVal = fileChooser.showDialog(null, "Load");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    GameEngine.loadBinary(file.getAbsolutePath());
                    
                } catch (FileNotFoundException ex) {
                    String error = "Error Occur! Cannot find save file.";
                    JOptionPane.showMessageDialog(null, error, null, JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    String error = "Error Occur! Cannot load game.";
                    JOptionPane.showMessageDialog(null, error, null, JOptionPane.ERROR_MESSAGE);
                }
            }
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