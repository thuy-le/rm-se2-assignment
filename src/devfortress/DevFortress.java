package devfortress;

import devfortress.utilities.Utilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Team Poseidon
 */
public class DevFortress {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> names = null;
        long t1 = System.currentTimeMillis();
        for (long i = 0; i < 1000000000; i++) {
            names = Utilities.getNameList();
        }
        System.out.println("Done: " + (System.currentTimeMillis() - t1));
        System.out.println(names.size());

    }
}
