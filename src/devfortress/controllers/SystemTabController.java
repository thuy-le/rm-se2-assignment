package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.SystemTabPanel;

/**
 *
 * @author Team Poseidon
 */
public final class SystemTabController {

    private SystemTabPanel view;
    private GameEngine model;

    public SystemTabController(SystemTabPanel view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

    public void initialize() {
//        view.addBtnAddProjectListener(new ButtonAddProjectMouseListener());
//        view.addBtnHireDevListener(new ButtonHireMouseListener());
//        view.addDevListListener(new DeveloperListMouseListener());
//        view.addProjectListListener(new ProjectListMouseListener());
    } 
}
