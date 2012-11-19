/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.controllers;

import devfortress.models.GameEngine;
import devfortress.view.interfaces.ProjectInterface;

/**
 *
 * @author Michael
 */
public class ProjectController implements Initilizable{
    
    private ProjectInterface view;
    private GameEngine model;

    public ProjectController(ProjectInterface view, GameEngine model) {
        this.view = view;
        this.model = model;    
    }
    
    

    @Override
    public void initilize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
