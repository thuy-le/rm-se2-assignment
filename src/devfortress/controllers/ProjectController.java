/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.interfaces.ProjectInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Michael
 */
public class ProjectController {

    private ProjectInterface view;
    private GameEngine model;

    public ProjectController(ProjectInterface view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

    public void initilize() {
        view.addNewProjectListener(new AddProjectListener());
        view.addDevToProjectListener(new AddDevToProjectListener());
        view.cancelProjectListener(new CancelProjectListener());
        view.removeDevFromProjectListener(new RemoveDevFromProjectListener());
    }

    private class AddProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class CancelProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class AddDevToProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class RemoveDevFromProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
}
