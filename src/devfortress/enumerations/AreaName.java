/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.enumerations;

/**
 *
 * @author Team Poseidon
 */
public enum AreaName {

    //Area Name
    CODING("Coding"),
    PROJECT_MANAGER("Project Manager"),
    DATABASE_DESIGN("Database Designing"),
    SYSTEM_DESIGN("System Designing"),
    SQL_DESIGN("SQL Designing"),
    UI_DESIGN("UI Designing"),
    PROJECT_PLANING("Project Planing"),
    TESTING_PLANING("Testing Planing"),
    IMPLEMENTATION("Implementation"),
    TESTING("Testing"),
    UNIT_TESTING("Unit Testing"),
    ITERATION_TESTING("Iteration Testing"),
    SYSTEM_TESTING("System Testing"),
    TEST_MANAGEMENT("Test Management"),
    SYSTEM_ANALYZE("System Analyze"),
    UI_ANALYZE("UI Analyze"),
    UI_DEVELOPMENT("UI Development"),
    DOCUMENTATION("Documentation"),
    DATABASE_DEVELOPMENT("Database Development"),
    NON_FUCNTIONAL_DEVELOPMENT("Non-Functional Development"),
    SQL_DEVELOPMENT("SQL_Development"),
    FUCNTIONAL_DEVELOPMENT("Non-Functional Development"),
    RISK_MANAGEMENT("Risk Management"),
    PROBLEM_SOLVING("Problem Solving"),
    CONFIG_MANAGEMENT("Config Manage"),
    SOURCE_CONTROL("Source Control"),
    BACKLOG_CONTROL("Backlog Control"),
    WEB_DESIGN("Web Design"),
    WEB_DEVELOPMENT("Web Development"),
    SERVER_MANAGEMENT("Server Management"),
    TOOLS_MANAGEMENT("Tools Management");
    private String name;

    private AreaName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
