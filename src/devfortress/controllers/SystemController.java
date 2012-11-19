/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.exceptions.GameNotInitilizedException;
import devfortress.models.GameEngine;
import devfortress.view.TabbedPaneSystem;
import devfortress.view.interfaces.SystemInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Team Poseidon
 */
public final class SystemController implements Initilizable {

    private SystemInterface view;
    private GameEngine model;

    public SystemController() throws GameNotInitilizedException {
        this.view = TabbedPaneSystem.getInstance();
        this.model = GameEngine.getInstance();
        initilize();
    }

    @Override
    public void initilize() {
        view.addBtnAddProjectListener(new ButtonAddProjectMouseListener());
        view.addBtnHireDevListener(new ButtonHireMouseListener());
        view.addDevListListener(new DeveloperListMouseListener());
        view.addProjectListListener(new ProjectListMouseListener());
    }

    private class ButtonAddProjectMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.out.println("Mouse Released");
        }
    }

    private class ButtonHireMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    private class DeveloperListMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    private class ProjectListMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }
}
