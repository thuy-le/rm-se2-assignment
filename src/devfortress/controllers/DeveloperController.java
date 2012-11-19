/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.interfaces.DeveloperInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperController implements Initilizable {

    private DeveloperInterface view;
    private GameEngine model;

    public DeveloperController(DeveloperInterface view, GameEngine model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void initilize() {
        view.addFeedAllDevListener(new FeedAllMouseListener());
        view.addHireDevListener(new HireDeveloperMouseListener());
        view.addPartyListener(new PartyMouseListener());
    }

    private class FeedAllMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    private class HireDeveloperMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    private class PartyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }
}
