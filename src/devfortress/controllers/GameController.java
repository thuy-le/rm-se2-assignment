/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.GlassPanel;
import devfortress.view.AboutScreen;
import devfortress.view.DevFortress;
import devfortress.view.InfomationPane;
import devfortress.view.NavigationPane;
import devfortress.view.TabbedPane;
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

    public GameController() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //-------Events for CustomButtons
        if (e.getSource() instanceof CustomButton) {
            CustomButton button = (CustomButton) e.getSource();
            if (button.getText().trim().equalsIgnoreCase("submit")) {
                if (!WelcomeScreen.getInstance().getPlayerName().trim().equals("")) {
                    DevFortress.getInstance().remove(WelcomeScreen.getInstance());
                    DevFortress.getInstance().add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                    NavigationPane.getInstance().getToolbar().setVisible(true);
                    InfomationPane.getInstance().getInfoPanel().setVisible(true);
                    TabbedPaneSystem.getInstance().setPlayerName(WelcomeScreen.getInstance().getPlayerName());
                    DevFortress.getInstance().repaint();
                }
            }
            //Customize Back Button in the About screen
            if (button.getText().trim().equalsIgnoreCase("back")) {
                DevFortress.getInstance().remove(AboutScreen.getInstance());
                DevFortress.getInstance().add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);
                NavigationPane.getInstance().getToolbar().setVisible(true);
                InfomationPane.getInstance().getInfoPanel().setVisible(true);
                DevFortress.getInstance().repaint();
            }
        }
        //-------Events for CustomLabels
        // New Game Command Execute in the Game controller
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("new")) {
                System.out.println("New Game Command");
                DevFortress.getInstance().remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                DevFortress.getInstance().getContentPane().add(WelcomeScreen.getInstance());
                DevFortress.getInstance().getContentPane().repaint();
            }
        }

        //Save game windows appear
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("save")) {
                System.out.println("Saves Game Command");
            }
        }

        //Save game windows appear
        if (e.getSource() instanceof CustomLabel) {
            CustomLabel label = (CustomLabel) e.getSource();
            if (label.getLabelName().equals("about")) {
                System.out.println("About");
                DevFortress.getInstance().remove(TabbedPane.getInstance().getContainerTab());
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                DevFortress.getInstance().getContentPane().add(AboutScreen.getInstance());
                DevFortress.getInstance().getContentPane().repaint();
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
