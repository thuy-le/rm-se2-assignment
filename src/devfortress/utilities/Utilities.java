package devfortress.utilities;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import devfortress.models.FunctionalArea;
import devfortress.models.Skill;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Team Poseidon
 */
public class Utilities {

    private static List<String> names = new ArrayList<String>();
    private static Random rand = new Random(System.currentTimeMillis());

    private synchronized static List<String> getNameList() {
        BufferedReader reader = null;
        if (names.isEmpty()) {
            try {
                reader = new BufferedReader(new FileReader("babies.txt"));
                if (reader != null) {
                    String line = reader.readLine();
                    if (line != null) {
                        String babynames[] = line.split(",");
                        names.addAll(Arrays.asList(babynames));
                    }
                }
            } catch (IOException ex) {
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                }
            }
        }
        return names;
    }

    public static int randInt() {
        return rand.nextInt();
    }

    public static int randInt(int lim) {
        return rand.nextInt(lim);
    }

    public static String getRandomName() {
        if (getNameList().isEmpty()) {
            int index = rand.nextInt(getNameList().size());
            return getNameList().get(index);
        } else {
            return "";
        }
    }

    public static FunctionalArea getRandomFunctionalArea(List<AreaName> areaNames, int functionPoints, boolean visible) {
        int index = Utilities.randInt(areaNames.size());
        FunctionalArea fA = new FunctionalArea(areaNames.get(index), functionPoints, 0, visible);
        areaNames.remove(areaNames.get(index));
        return fA;
    }

    public static Skill getRandomSkill(ArrayList<SkillInfo> skills, int maxLvl) {
        int level = randInt(maxLvl) + 1;
        int index = randInt(skills.size());
        Skill skill = new Skill(level, skills.get(index));
        return skill;
    }
}
