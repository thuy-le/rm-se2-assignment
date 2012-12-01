package devfortress;

import devfortress.view.AddProjectPanel;
import devfortress.view.HireDeveloperPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Team Poseidon
 */
public class NewJFrame extends JFrame {
    JPanel testPanel;

    public NewJFrame() {
        setLayout(new BorderLayout());
        setSize(800, 600);
        setTestPanel();
        add(testPanel);
    }

    private void setTestPanel() {
        testPanel = new AddProjectPanel();
    }


    public static void main(String[] args) {
        JFrame frame = new NewJFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
