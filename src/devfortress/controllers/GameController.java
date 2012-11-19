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
import devfortress.view.AboutScreen;
import devfortress.view.DevFortress;
import devfortress.view.InfomationPane;
import devfortress.view.NavigationPane;
import devfortress.view.TabbedPane;
import devfortress.view.TabbedPaneDeveloper;
import devfortress.view.TabbedPaneSystem;
import devfortress.view.WelcomeScreen;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author PC
 */
public class GameController extends MouseAdapter {
    
    DevFortress devFortress = null;

    public GameController() {
        devFortress = DevFortress.getInstance();
    }
    
    /**
     * A temporary method to initialize the game and set up the controllers
     */
    private void addControllers() {
        try {
            GameEngine.initialize("player 1", 100000);
            SystemController systemController = new SystemController(TabbedPaneSystem.getInstance(), GameEngine.getInstance());
            DeveloperTabController developerController = new DeveloperTabController(TabbedPaneDeveloper.getInstance(), GameEngine.getInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //-------Events for CustomButtons
        if (e.getSource() instanceof CustomButton) {
            CustomButton button = (CustomButton) e.getSource();
            if (button.getText().trim().equalsIgnoreCase("submit")) {
                if (!WelcomeScreen.getInstance().getPlayerName().trim().equals("")) {
                    devFortress.remove(WelcomeScreen.getInstance());
                    devFortress.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                    NavigationPane.getInstance().getToolbar().setVisible(true);
                    InfomationPane.getInstance().getInfoPanel().setVisible(true);
                    TabbedPaneSystem.getInstance().setPlayerName(WelcomeScreen.getInstance().getPlayerName());
                    addControllers();
                    devFortress.repaint();
                }
            }
            //Customize Back Button in the About screen
            if (button.getText().trim().equalsIgnoreCase("back")) {
                devFortress.remove(AboutScreen.getInstance());
                devFortress.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                NavigationPane.getInstance().getToolbar().setVisible(true);
                InfomationPane.getInstance().getInfoPanel().setVisible(true);
                devFortress.repaint();
            }
        }
        //-------Events for CustomLabels
        // New Game Command Execute in the Game controller
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("new")) {
                System.out.println("New Game Command");
                devFortress.remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                devFortress.getContentPane().add(WelcomeScreen.getInstance());
                devFortress.getContentPane().repaint();
            }
        }

        //Save game windows appear
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("save")) {
                System.out.println("Saves Game Command");
            }
        }

        //About windows appear
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("about")) {
                System.out.println("About");
                devFortress.remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                devFortress.getContentPane().add(AboutScreen.getInstance());
                devFortress.getContentPane().repaint();
            }
        }


        // The window should close when player hit the "Exit Game" button
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("exit")) {
                System.out.println("EXIT COMMAND");
                System.exit(0);
            }
        }


    }
}
