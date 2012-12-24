package devfortress.models;

import devfortress.models.exceptions.InvalidDevDateException;
import java.io.Serializable;

/**
 * Development date is used in the game. It different to the normal date.
 * <p>A DevDate instance contains information of the year, month and week of
 * in the game.</p>
 * <p>Each year has 12 months, started from 1 to 12; each month has 4 weeks 
 * (from 1 to 4).</p>
 * <p>A game is started at year 0, month 1 and week 1.</p>
 * @author Team Poseidon
 */
public class DevDate implements Serializable {

    private int year, month, week;

    public DevDate(int year, int month, int week) throws InvalidDevDateException {
        if (year >= 0 && month >= 1 && month <= 12 && week >= 1 && week <= 4) {
            this.year = year;
            this.month = month;
            this.week = week;
        } else {
            throw new InvalidDevDateException();
        }
    }

    public DevDate() {
        this.year = 0;
        this.month = 1;
        this.week = 1;
    }

    /**
     * Copy Constructor
     */
    public DevDate(DevDate date) {
        this.year = date.year;
        this.month = date.month;
        this.week = date.week;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthString() {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "Febuary";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "Devember";
        }
        return "";
    }

    public int getWeek() {
        return week;
    }

    public int getYear() {
        return year;
    }

    public void nextWeek() {
        if (week < 4) {
            week++;
        } else {
            week = 1;
            nextMonth();
        }
    }

    public void reset() {
        this.year = 0;
        this.month = 1;
        this.week = 1;
    }

    public void nextMonth() {
        if (month < 12) {
            month++;
        } else {
            month = 1;
            nextYear();
        }
    }

    public void nextYear() {
        year++;
    }

    /**
     * Used to calculate the deadline of the project.
     * @param month 
     * @return new <code>DevDate</code> base on the current <code>DevDate</code>
     * object and the additional months
     * @exception InvalidDevDateException if the month is negative
     */
    public DevDate addMonths(int month) throws InvalidDevDateException {
        if (month < 0) {
            throw new InvalidDevDateException();
        }
        int newMonth = this.month + month;
        int newYear = this.year;
        while (newMonth > 12) {
            newYear++;
            newMonth -= 12;
        }
        return new DevDate(newYear, newMonth, this.week);
    }
}
