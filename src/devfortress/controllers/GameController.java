/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.view.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author PC
 */
public class GameController extends MouseAdapter {

    private DevFortress view;
    private GameEngine model;

    public GameController() {
        view = DevFortress.getInstance();
    }

    public GameController(DevFortress view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

    /**
     * A temporary method to initialize the game and set up the controllers
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //-------Events for CustomButtons
        if (e.getSource() instanceof CustomButton) {
            CustomButton button = (CustomButton) e.getSource();
            if (button.getText().trim().equalsIgnoreCase("submit")) {
                if (!WelcomeScreen.getInstance().getPlayerName().trim().equals("")) {
                    view.remove(WelcomeScreen.getInstance());
                    view.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                    NavigationPane.getInstance().getToolbar().setVisible(true);
                    InfomationPane.getInstance().getInfoPanel().setVisible(true);
                    TabbedPaneSystem.getInstance().setPlayerName(WelcomeScreen.getInstance().getPlayerName());
                    view.repaint();
                }
            }
            //Customize Back Button in the About screen
            if (button.getText().trim().equalsIgnoreCase("back")) {
                view.remove(AboutScreen.getInstance());
                view.add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                NavigationPane.getInstance().getToolbar().setVisible(true);
                InfomationPane.getInstance().getInfoPanel().setVisible(true);
                view.repaint();
            }
        }
        //-------Events for CustomLabels
        // New Game Command Execute in the Game controller
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("new")) {
                System.out.println("New Game Command");
                view.remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                view.getContentPane().add(WelcomeScreen.getInstance());
                view.getContentPane().repaint();
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
                view.remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                view.getContentPane().add(AboutScreen.getInstance());
                view.getContentPane().repaint();
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

    public void initialize() {
    }
}
