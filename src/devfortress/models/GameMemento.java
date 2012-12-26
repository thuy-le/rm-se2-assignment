/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Michael
 */
public class GameMemento implements Serializable {

    int budget;
    DevDate date;
    String playerName;
    int numPCs;
    boolean ended;
    int numHiredDevs;
    List<Developer> developers, marketDevelopers;
    List<Project> projects, marketProjects, pastProjects;

    public GameMemento(int budget, DevDate date, String playerName, int numPCs,
            boolean ended, int numHiredDevs, List<Developer> developers, List<Developer> marketDevelopers,
            List<Project> projects, List<Project> marketProjects, List<Project> pastProjects) {
        this.budget = budget;
        this.date = date;
        this.playerName = playerName;
        this.numPCs = numPCs;
        this.ended = ended;
        this.numHiredDevs = numHiredDevs;
        this.developers = developers;
        this.marketDevelopers = marketDevelopers;
        this.projects = projects;
        this.marketProjects = marketProjects;
        this.pastProjects = pastProjects;
    }
}