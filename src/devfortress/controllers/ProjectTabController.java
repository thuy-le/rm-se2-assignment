package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.AddProjectPanel;
import devfortress.view.ProjectTabPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Michael
 */
public class ProjectTabController {

    private ProjectTabPanel view;
    private AddProjectPanel addProjectPanel;
    private GameEngine model;

    public ProjectTabController(ProjectTabPanel view, GameEngine model) {
        this.view = view;
        this.model = model;
        addProjectPanel = null;
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
            System.out.println("Clikced");
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
