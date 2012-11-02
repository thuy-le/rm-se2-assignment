/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

/**
 *
 * @author Team Poseidon
 */
public class DevDate {

    private int year, month, week;

    public DevDate(int year, int month, int week) {
        this.year = year;
        this.month = month;
        this.week = week;
    }

    public DevDate() {
        this.year = 0;
        this.month = 1;
        this.week = 1;
    }

    public int getMonth() {
        return month;
    }

    public int getWeek() {
        return week;
    }

    public int getYear() {
        return year;
    }

    public void nextWeek() {
        if (week < 3) {
            week++;
        } else {
            week = 0;
            if (month < 12) {
                month++;
            } else {
                month = 0;
                year++;
            }
        }
    }
}
