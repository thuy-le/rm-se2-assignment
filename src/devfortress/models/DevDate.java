package devfortress.models;

import devfortress.exceptions.InvalidDevDateException;

/**
 * Development date is used in the game. It different to the normal date.
 * <p>A DevDate instance contains information of the year, month and week of
 * in the game.</p>
 * <p>Each year has 12 months, started from 1 to 12; each month has 4 weeks 
 * (from 1 to 4).</p>
 * <p>A game is started at year 0, month 1 and week 1.</p>
 * @author Team Poseidon
 */
public class DevDate {

    private int year, month, week;

    public DevDate(int year, int month, int week) throws InvalidDevDateException {
        if (year >= 0 && month >= 1 && month <= 12 && week >= 1 && week <= 4) {
            this.year = year;
            this.month = month;
            this.week = week;
        } else {
            System.out.println("Year: " + year);
            System.out.println("Month: " + month);
            System.out.println("Week: " + week);

            throw new InvalidDevDateException();
        }
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
        if (week < 4) {
            week++;
        } else {
            week = 1;
            nextMonth();
        }
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
}
