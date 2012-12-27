package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.enumerations.SkillInfo;
import devfortress.enumerations.SkillTypes;
import devfortress.utilities.ReadOnlyMap;
import devfortress.utilities.Utilities;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Developer is the work force of the game. He provide the function point each
 * week on the project. <p>A
 * <code>Developer</code> has skills which has level. The skill can be leveled
 * up when he work on the project require the skill or be trained with money.
 * His salary is calculated base on the skills.</p> <p>He can be fed every week
 * with pizza, coffee and redbull. He will not work when he hungry or thirsty.
 * The developer can also drink beer, which keep him happy but halve his
 * productivity.</p> <p>An developer can be happy or unhappy. He can be kept
 * happy with beer and party. The developer become unhappy when he is assigned
 * to a project which is not match his skills. An unhappy developer may leave at
 * the end of the month. (20%)</p>
 *
 * @author Team Poseidon
 */
public class Developer implements Serializable {

    private float randomFactor;
    private String name;
    private Map<SkillInfo, Skill> skills;
    private SkillInfo mainSkillInfo;
    private boolean happy, fed, drunk;
    private int salary;
    private int lastWeekFunctionPoints;
    private Project workingProject;
    private AreaName workingArea;

    public Developer() {
        this.name = Utilities.getRandomName();
        this.skills = new EnumMap<SkillInfo, Skill>(SkillInfo.class);
        this.mainSkillInfo = null;
        this.salary = 0;
        this.workingProject = null;
        this.workingArea = null;
        this.happy = Utilities.randInt(2) == 1;
        this.drunk = Utilities.randInt(2) == 1;
        this.fed = Utilities.randInt(2) == 1;
        this.lastWeekFunctionPoints = 0;
        this.randomFactor = Utilities.randFloat() / 2 + 1f;
        randomizeSkills();
        re_calculateDeveloperInfo();
    }

    private void randomizeSkills() {
        int numTechSkills = Utilities.randInt(2) + 1;
        int numConfigSkills = Utilities.randInt(SkillInfo.configSkills().size() + 1);
        int numMetaSkills = Utilities.randInt(SkillInfo.metaSkills().size() + 1);
        int numPersonalSkills = Utilities.randInt(SkillInfo.personalSkills().size() + 1);
        for (int i = 0; i < numTechSkills; i++) {
            Skill skill = Utilities.getRandomSkill(SkillInfo.techSkills(), 4);
            skills.put(skill.getSkillInfo(), skill);
        }
        for (int i = 0; i < numConfigSkills; i++) {
            Skill skill = Utilities.getRandomSkill(SkillInfo.configSkills(), 4);
            skills.put(skill.getSkillInfo(), skill);
        }
        for (int i = 0; i < numMetaSkills; i++) {
            Skill skill = Utilities.getRandomSkill(SkillInfo.metaSkills(), 4);
            skills.put(skill.getSkillInfo(), skill);
        }
        for (int i = 0; i < numPersonalSkills; i++) {
            Skill skill = Utilities.getRandomSkill(SkillInfo.personalSkills(), 6);
            skills.put(skill.getSkillInfo(), skill);
        }

    }

    /*
     * Getters
     */
    public boolean isHappy() {
        return happy;
    }

    public boolean isFed() {
        return fed;
    }

    public boolean isDrunk() {
        return drunk;
    }

    public boolean isAvailable() {
        return workingProject == null && workingArea == null;
    }

    public SkillInfo getMainSkill() {
        return mainSkillInfo;
    }

    public Project getWorkingProject() {
        return workingProject;
    }

    public AreaName getWorkingArea() {
        return workingArea;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Map<SkillInfo, Skill> getSkills() {
        return new ReadOnlyMap<SkillInfo, Skill>(skills);
    }

    /*
     * Setters
     */
    /*
     * Events can make developers unhappy or happy
     */
    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    /*
     * Everyweek, have to feed developers
     */
    public void feed() {
        this.fed = true;
    }

    /*
     * Give him some beers
     */
    public void drink() {
        this.drunk = true;
        this.happy = true;
    }

    /**
     * This method is called at the end of week
     */
    public void getTired() {
        double rand = Math.random();
        if (rand < 0.15) {
            happy = false;
        }
        fed = false;
        drunk = false;
    }

    /**
     * Used when initialize a new developer or train a new skill
     *
     * @see Skill
     * @see SkillInfo
     */
    public void addSkill(Skill skill) {
        skills.put(skill.getSkillInfo(), skill);
        re_calculateDeveloperInfo();
    }

    /**
     * Select the skill to train
     *
     * @see Skill
     * @see SkillInfo
     */
    public void trainSkill(SkillInfo skillInfo) {
        Skill skill = skills.get(skillInfo);
        if (skill != null) {
            skills.get(skillInfo).levelUp();
        } else {
            Skill s = new Skill(1, skillInfo);
            skills.put(skillInfo, s);
        }
        re_calculateDeveloperInfo();
    }

    /**
     * This function should be only called by
     * <code>{@link Project}</code>.
     */
    public void acceptProject(Project project, AreaName area) throws DeveloperBusyException {
        if (workingProject != null) {
            if (workingProject == project) {
                throw new DeveloperBusyException("Developer has already accepted this project");
            }
            throw new DeveloperBusyException();
        }
        workingProject = project;
        workingArea = area;
    }

    /**
     * This function should be only called by
     * <code>{@link Project}</code>.
     */
    public void leaveProject() {
        workingProject = null;
        workingArea = null;
    }

    public int getLastWeekFunctionPoints() {
        return getCalculateLastWeekFunctionPoints();
    }

    public void setLastWeekFunctionPoints(int lastWeekFunctionPoints) {
        this.lastWeekFunctionPoints = lastWeekFunctionPoints < 1 ? 1 : lastWeekFunctionPoints;
    }

    public int getCalculateLastWeekFunctionPoints() {
        lastWeekFunctionPoints = getProduction(workingProject) * (fed ? 1 : 0);
        if (lastWeekFunctionPoints < 1 && fed) {
            lastWeekFunctionPoints = 1;
        }
        return lastWeekFunctionPoints;
    }

    public int getSkillLevel(SkillInfo info) {
        int result = 0;
        try {
            result = skills.get(info).getLevel();
        } catch (Exception ex) {
        }
        return result;
    }
    /*
     * Private methods
     */
    private void re_calculateDeveloperInfo() {
        determineMainSkill();
        calculateSalary();
    }

    private void determineMainSkill() {
        LinkedList<Skill> skillList = new LinkedList<Skill>();
        for (SkillInfo key : skills.keySet()) {
            if (key.getType() == SkillTypes.TECHNICAL) {
                skillList.add(skills.get(key));
            }
        }
        if (skillList.isEmpty()) {
            mainSkillInfo = null;
            return;
        }
        Collections.sort(skillList);
        // The collection is sorted in ascending order, therefore the highest level skill is the last one
        mainSkillInfo = skillList.getLast().getSkillInfo();
    }

    /**
     * Calculate the salary of the developer.
     */
    private void calculateSalary() {
        float totalPoint = 0;
        for (SkillInfo key : skills.keySet()) {
            if (key == SkillInfo.ALGORITHMS) {
                totalPoint += ((float) skills.get(key).getLevel()) * 3f;
            } else if (key == SkillInfo.ANALYSIS || (mainSkillInfo != null && key == mainSkillInfo)) {
                totalPoint += ((float) skills.get(key).getLevel()) * 2f;
            } else if (key == SkillInfo.TEAM_PLAYER || SkillInfo.configSkills().contains(key) || SkillInfo.specialSkills().contains(key)) {
                totalPoint += ((float) skills.get(key).getLevel()) * 2f;
            } else if (key == SkillInfo.DESIGN) {
                totalPoint += ((float) skills.get(key).getLevel()) * 1.5f;
            } else {
                totalPoint += ((float) skills.get(key).getLevel());
            }
        }
        this.salary = (int) (totalPoint * 20f * randomFactor);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getProduction(Project project) {
        int functionPoints = 0;
        if (project == null) {
            functionPoints = 0;
        } else {
            int tech = getSkillLevel(mainSkillInfo);
            int design = getSkillLevel(SkillInfo.DESIGN);
            int algorithm = getSkillLevel(SkillInfo.ALGORITHMS);
            int analysis = getSkillLevel(SkillInfo.ANALYSIS);
            int teamPlayer = getSkillLevel(SkillInfo.TEAM_PLAYER);
            int config = getSkillLevel(SkillInfo.CONFIG_MANAGEMENT);
            int numMem = project.getDevelopers().size();
            SkillInfo proMainSkill = project.getMainRequirement();
            if (mainSkillInfo != proMainSkill) {
                int lisp = 0;
                int haskell = 0;
                int forth = 0;
                {
                    lisp = getSkillLevel(SkillInfo.LISP);
                    haskell = getSkillLevel(SkillInfo.HASKELL);
                    forth = getSkillLevel(SkillInfo.FORTH);
                }
                if (lisp + haskell + forth != 0) {
                    int temp = lisp > haskell ? lisp : haskell;
                    tech = temp > forth ? temp : forth;
                } else {
                    List<Skill> techSkills = new LinkedList<Skill>(skills.values());
                    for (Iterator<Skill> itr = techSkills.iterator(); itr.hasNext();) {
                        Skill skill = itr.next();
                        if (skill.getSkillInfo().getType() != SkillTypes.TECHNICAL) {
                            itr.remove();
                        }
                    }
                    for (Skill skill : techSkills) {
                        if (tech > skill.getLevel()) {
                            tech = skill.getLevel();
                        }
                    }
                }
            }
            functionPoints = (tech + 2 * design + tech * algorithm + 3 * analysis + ((teamPlayer * numMem) / (12 - config)));

            if (functionPoints < 1) {
                functionPoints = 1;
            }
        }
        return functionPoints;
    }
}
