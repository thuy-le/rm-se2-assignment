/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.enumerations.SkillInfo;
import devfortress.exceptions.InsufficientBudgetException;
import devfortress.models.Developer;
import devfortress.models.GameEngine;
import devfortress.view.TrainSkillDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

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

        @Override
        public void mouseClicked(MouseEvent e) {
            view.dispose();
        }
    }

    private class TrainSkillListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Developer dev = view.getDeveloper();
            SkillInfo skillInfo = view.getSkillInfo();
            try {
                model.trainDeveloper(dev, skillInfo);
            } catch (InsufficientBudgetException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
            model.notifyObservers();
        }
    }
}
