package devfortress.controllers;

import devfortress.enumerations.Options;
import devfortress.models.GameEngine;
import devfortress.models.exceptions.GameOverException;
import devfortress.view.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    private DeveloperTabPanel devTab;

    public MainFrameController(DevFortressMainFrame mainFrame, NewGameWelcomePanel welCm, NavigationToolBar navPne, InfomationPanel infoPnl, DeveloperTabPanel devTab, DevFortressTabbedPane tabPne, AboutScreenPanel aboutPnl, GameEngine model, WelcomePanel welcome) {
        this.mainFrame = mainFrame;
        this.welCm = welCm;
        this.aboutPnl = aboutPnl;
        this.navBar = navPne;
        this.infoPnl = infoPnl;
        this.devTab = devTab;
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
        navBar.addSettingListener(new SettingListener());
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

    private class SettingListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            SettingDialog dialog = new SettingDialog();
            dialog.addApplyMouseListener(new ApplyListener(dialog));
        }

        private class ApplyListener extends MouseAdapter {

            private SettingDialog dialog;

            public ApplyListener(SettingDialog dialog) {
                this.dialog = dialog;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Options feedOption = dialog.getFeedOption();
                Options beerOption = dialog.getBeerOption();
                if (feedOption == null || beerOption == null) {
                    JOptionPane.showMessageDialog(null, "You have to choose both options", "DevFortress", JOptionPane.OK_OPTION);
                } else {
                    model.setOptions(feedOption, beerOption);
                    dialog.dispose();
                    JOptionPane.showMessageDialog(null, "Settings saved","DevFortress", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private class NextWeekListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                model.nextWeek();
            } catch (GameOverException ex) {
                int result = JOptionPane.showConfirmDialog(null, ex.getMessage()
                        + "\nDo you want to save your achievements?",
                        "DevFortress", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    model.endGameReport(new File(model.getPlayerName() + ".txt"));
                }
            }
            model.notifyObservers();
            List<String> notifications = model.getFinishedProjects();
            if (notifications.size() > 0) {
                String message = "Some projects are finished:\n";
                for (String dev : notifications) {
                    message += dev;
                }
                JOptionPane.showMessageDialog(null, message, "DevFortress", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class SubmitNameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!welCm.getPlayerName().trim().equals("")) {
                model.initialize(welCm.getPlayerName());
                mainFrame.remove(welCm.getContainer());
                mainFrame.add(tabPne, BorderLayout.CENTER);
                devTab.btnFeed.disableButton();
                devTab.btnParty.disableButton();
                navBar.setVisible(true);
                infoPnl.setVisible(true);
                tabPne.getSystemTab().setPlayerName(model.getPlayerName());
                model.notifyObservers();

            }
        }
    }

    //TODO: Need to save the current game
    private class NewGameListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int choosen = JOptionPane.showConfirmDialog(mainFrame, "Do you want to have a new game?", "DevFortress", JOptionPane.YES_NO_OPTION);
            if (choosen == JOptionPane.YES_OPTION) {
                mainFrame.remove(tabPne);
                navBar.setVisible(false);
                infoPnl.setVisible(false);
                mainFrame.getContentPane().add(welCm.getContainer());
            }
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
            int choosen = JOptionPane.showConfirmDialog(mainFrame, "Exit Game?", "DevFortress", JOptionPane.YES_NO_OPTION);
            if (choosen == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
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
                    if (!file.getName().endsWith(".save")) {
                        File newFile = new File(file.getAbsolutePath() + ".save");
                        model.saveBinary(newFile);
                    } else {
                        model.saveBinary(file);
                    }                    
                    JOptionPane.showMessageDialog(null, "Game saved.","DevFortress", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                    ex.printStackTrace();
                    String error = "Error Occur! Cannot save game.";
                    JOptionPane.showMessageDialog(null, error, "DevFortress", JOptionPane.ERROR_MESSAGE);
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
                    model.loadBinary(file.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, file.getName() + " loaded.","DevFortress", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    String error = "Error Occur! Cannot find save file.";
                    JOptionPane.showMessageDialog(null, error, "DevFortress", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    String error = "Error Occur! Cannot load game.";
                    JOptionPane.showMessageDialog(null, error, "DevFortress", JOptionPane.ERROR_MESSAGE);
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