package devfortress;

import devfortress.utilities.Utilities;

/**
 *
 * @author Team Poseidon
 */
public class DevFortress {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            Utilities.getNameList();
        }
        System.out.println(System.currentTimeMillis() - t1);
    }
}
