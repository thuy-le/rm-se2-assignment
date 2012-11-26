/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.components.CustomTabbedPaneUI;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 *
 * @author PC
 */
public class DevFortressTabbedPane extends JTabbedPane {

    private static final String DEV_TAB_TITLE = "Developer";
    private static final String SYS_TAB_TITLE = "System";
    private static final String PROJ_TAB_TITLE = "Project";
    private DeveloperTabPanel devTab;
    private ProjectTabPanel projTab;
    private SystemTabPanel sysTab;

    public DeveloperTabPanel getDeveveloperTab() {
        return devTab;
    }

    public ProjectTabPanel getProjectTab() {
        return projTab;
    }

    public SystemTabPanel getSystemTab() {
        return sysTab;
    }

    public DevFortressTabbedPane(DeveloperTabPanel devTab, ProjectTabPanel projTab, SystemTabPanel sysTab) {
        this.devTab = devTab;
        this.projTab = projTab;
        this.sysTab = sysTab;
        this.setUI(new CustomTabbedPaneUI());
        this.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        this.setOpaque(false);
        this.add(SYS_TAB_TITLE, sysTab);
        this.add(DEV_TAB_TITLE, devTab);
        this.add(PROJ_TAB_TITLE, projTab);
    }
}
