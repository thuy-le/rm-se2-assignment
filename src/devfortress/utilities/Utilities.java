/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team Poseidon
 */
public class Utilities {

    private static List<String> names = new ArrayList<String>();

    public static String getRandomName() {
        return "";
    }

    public synchronized static List<String> getNameList() {
        BufferedReader reader = null;
        if (names.isEmpty()) {
            try {
                reader = Files.newBufferedReader(Paths.get("babies.txt"), Charset.forName("UTF-8"));
                String line = reader.readLine();
                if (line != null) {
                    String babynames[] = line.split(",");
                    names.addAll(Arrays.asList(babynames));
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
}
