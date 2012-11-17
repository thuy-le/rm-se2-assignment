/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.CustomTabbedPaneUI;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 *
 * @author PC
 */
public class TabbedPane {

    private JTabbedPane containerTab;
    private static volatile TabbedPane instance = null;

    private TabbedPane() {
        init();
    }

    public static TabbedPane getInstance() {
        if (instance == null) {
            synchronized (TabbedPaneDeveloper.class) {
                if (instance == null) {
                    instance = new TabbedPane();
                }
            }
        }
        return instance;
    }
    
    public JTabbedPane getContainerTab(){
        return containerTab;
    }

    private void init() {
        //-------TABS-------
        //initialize JTabbedPane
        containerTab = new JTabbedPane();
        containerTab.setUI(new CustomTabbedPaneUI());
        containerTab.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        containerTab.setOpaque(false);
        containerTab.add("System", TabbedPaneSystem.getInstance());
        containerTab.add("Developer", TabbedPaneDeveloper.getInstance());
        containerTab.add("Project", TabbedPaneProject.getInstance());
    }
}
