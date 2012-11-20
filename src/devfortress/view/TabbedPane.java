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
public class TabbedPane extends JTabbedPane {

    private TabbedPaneDeveloper devTab;
    private TabbedPaneProject projTab;
    private TabbedPaneSystem sysTab;

    public TabbedPaneDeveloper getDevTab() {
        return devTab;
    }

    public TabbedPaneProject getProjTab() {
        return projTab;
    }

    public TabbedPaneSystem getSysTab() {
        return sysTab;
    }

    public TabbedPane(TabbedPaneDeveloper devTab, TabbedPaneProject projTab, TabbedPaneSystem sysTab) {
        this.devTab = devTab;
        this.projTab = projTab;
        this.sysTab = sysTab;
        this.setUI(new CustomTabbedPaneUI());
        this.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        this.setOpaque(false);
        this.add("System", sysTab);
        this.add("Developer", devTab);
        this.add("Project", projTab);
    }
}
