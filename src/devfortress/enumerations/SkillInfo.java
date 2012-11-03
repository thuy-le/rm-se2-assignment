package devfortress.enumerations;

/**
 *
 * @author Team Poseidon
 */
public enum SkillInfo {

    //Technical
    JAVA("Java", SkillTypes.TECHNICAL),
    C("C", SkillTypes.TECHNICAL),
    C_PLUS_PLUS("C++", SkillTypes.TECHNICAL),
    C_SHARP("C#", SkillTypes.TECHNICAL),
    VB("VB", SkillTypes.TECHNICAL),
    PYTHON("Python", SkillTypes.TECHNICAL),
    PHP("PHP", SkillTypes.TECHNICAL),
    RUBY("Ruby", SkillTypes.TECHNICAL),
    PERL("Perl", SkillTypes.TECHNICAL),
    LISP("Lisp", SkillTypes.TECHNICAL),
    HASKELL("Haskell", SkillTypes.TECHNICAL),
    PROLOG("Prolog", SkillTypes.TECHNICAL),
    FORTH("Forth", SkillTypes.TECHNICAL),
    SQL("SQL", SkillTypes.TECHNICAL),
    PL_SQL("PL/SQL", SkillTypes.TECHNICAL),
    T_SQL("T-SQL", SkillTypes.TECHNICAL),
    VHDL("VHDL", SkillTypes.TECHNICAL),
    UNIX("Unix", SkillTypes.TECHNICAL),
    WINDOWS("Windows", SkillTypes.TECHNICAL),
    ORACLE("Oracle", SkillTypes.TECHNICAL),
    SQL_SERVER("SQL Server", SkillTypes.TECHNICAL),
    MY_SQL("MySQL", SkillTypes.TECHNICAL),
    NO_SQL("NoSQL", SkillTypes.TECHNICAL),
    UI_DEVELOPMENT("UI Development", SkillTypes.TECHNICAL),
    SCALABILITY("Scalability", SkillTypes.TECHNICAL),
    DOCUMENTATION("Documentation", SkillTypes.TECHNICAL),
    PERFORMANCE("Performance", SkillTypes.TECHNICAL),
    //Meta skills
    DESIGN("Design", SkillTypes.META),
    ALGORITHMS("Algorithms", SkillTypes.META),
    ANALYSIS("Analysis", SkillTypes.META),
    //Personal
    TEAM_PLAYER("Team player", SkillTypes.PERSONAL),
    COMMUNICATION("Communication", SkillTypes.PERSONAL),
    //Config
    CONFIG_MANAGEMENT("Config Management", SkillTypes.CONFIGURATION_MANAGEMENT);
    private String name;
    private SkillTypes type;

    private SkillInfo(String name, SkillTypes type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SkillTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
