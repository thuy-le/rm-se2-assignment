/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the.devfortress;

/**
 *
 * @author PC
 */
public enum SkillName {
    JAVA("Java"),
    C("C"),
    CPLUS("C++"),
    CSHARP("C#"),
    VP("VB"),
    PYTHON("Python"),
    PHP("PHP"),
    RUBY("Ruby"),
    PERL("Perl"),
    LISP("Lisp"),
    HASKELL("Haskell"),
    PROLOG("Prolog"),
    FORTH("Forth"),
    SQL("SQL"),
    PLSQL("PL/SQL"),
    TSQL("T-SQL"),
    VHDL("VHDL"),
    UNIX("Unix"),
    WINDOWS("Windows"),
    ORACLE("Oracle"),
    SQLSERVER("SQL Server"),
    MYSQL("MySQL"),
    NOSQL("NoSQL"),
    UIDEV("UI Development"),
    SCALABILITY("Scalability"),
    DOCUMENTATION("Documentation"),
    PERFORMANCE("Performance"),
    DESIGN("Design"),
    ALGORITHMS("Algorithms"),
    ANALYSIS("Analysis"),
    TEAMPLAYER("Team Player"),
    COMMUNICATION("Communication"),
    CONFIGMANA("Configuration Management");
    
    private String name;

    private SkillName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
