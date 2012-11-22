/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.TrainSkillDialog;
import java.awt.event.MouseAdapter;

/**
 *
 * @author Team Poseidon
 */
public class TrainSkillController {

    private TrainSkillDialog view;
    private GameEngine model;

    public TrainSkillController(TrainSkillDialog view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

    public void initialize() {
        view.addCloseListener(new CloseListener());
        view.addTrainSkillListener(new TrainSkillListener());
    }

    private class CloseListener extends MouseAdapter {
    }

    private class TrainSkillListener extends MouseAdapter {
    }
}
