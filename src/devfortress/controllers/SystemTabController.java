package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.interfaces.SystemTabView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Team Poseidon
 */
public final class SystemTabController {

    private SystemTabView view;
    private GameEngine model;

    public SystemTabController(SystemTabView view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

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
