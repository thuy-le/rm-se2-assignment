package devfortress.enumerations;

import java.util.ArrayList;

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
    private static ArrayList<SkillInfo> techSkills = new ArrayList<>();
    private static ArrayList<SkillInfo> metaSkills = new ArrayList<>();
    private static ArrayList<SkillInfo> personalSkills = new ArrayList<>();
    private static ArrayList<SkillInfo> configSkills = new ArrayList<>();
    private static ArrayList<SkillInfo> specialSkills = new ArrayList<>();

    private SkillInfo(String name, SkillTypes type) {
        this.name = name;
        this.type = type;
    }

    private static void initializeArrayLists() {
        if (techSkills.isEmpty() && metaSkills.isEmpty() && personalSkills.isEmpty() && configSkills.isEmpty() && specialSkills.isEmpty()) {
            for (int i = 0; i < values().length; i++) {
                SkillInfo skill = values()[i];
                switch (values()[i].getType()) {
                    case TECHNICAL:
                        techSkills.add(skill);
                        break;
                    case META:
                        metaSkills.add(skill);
                        break;
                    case PERSONAL:
                        personalSkills.add(skill);
                        break;
                    case CONFIGURATION_MANAGEMENT:
                        configSkills.add(skill);
                        break;
                }
                specialSkills.add(FORTH);
                specialSkills.add(HASKELL);
                specialSkills.add(LISP);
            }
        }
    }

    public static ArrayList<SkillInfo> configSkills() {
        initializeArrayLists();
        return configSkills;
    }

    public static ArrayList<SkillInfo> metaSkills() {
        initializeArrayLists();
        return metaSkills;
    }

    public static ArrayList<SkillInfo> personalSkills() {
        initializeArrayLists();
        return personalSkills;
    }

    public static ArrayList<SkillInfo> specialSkills() {
        initializeArrayLists();
        return specialSkills;
    }

    public static ArrayList<SkillInfo> techSkills() {
        initializeArrayLists();
        return techSkills;
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
