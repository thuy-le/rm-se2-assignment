/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import java.util.LinkedList;

/**
 *
 * @author btqua_000
 */
public class FunctionalArea {

    private LinkedList<Developer> developers;
    private int functionPoints;
    private int duration;
    private int level;
    private LinkedList<FunctionalArea> areas;
}
