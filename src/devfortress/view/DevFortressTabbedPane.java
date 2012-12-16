/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.components.CustomTabbedPaneUI;
import java.awt.event.KeyEvent;
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
    private static final String EVENT_TAB_TITLE = "Event";
    private DeveloperTabPanel devTab;
    private ProjectTabPanel projTab;
    private SystemTabPanel sysTab;
    private EventTabPanel eventTab;

    public DeveloperTabPanel getDeveveloperTab() {
        return devTab;
    }

    public ProjectTabPanel getProjectTab() {
        return projTab;
    }

    public SystemTabPanel getSystemTab() {
        return sysTab;
    }
    
    public EventTabPanel getEventTab() {
        return eventTab;
    }

    public DevFortressTabbedPane(DeveloperTabPanel devTab, ProjectTabPanel projTab, SystemTabPanel sysTab, EventTabPanel eventTab) {
        this.devTab = devTab;
        this.projTab = projTab;
        this.sysTab = sysTab;
        this.eventTab = eventTab;
        this.setUI(new CustomTabbedPaneUI());
        this.setSize(750, 550);
        UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
        this.setOpaque(false);
        this.add(SYS_TAB_TITLE, sysTab);
        this.add(DEV_TAB_TITLE, devTab);
        this.add(PROJ_TAB_TITLE, projTab);
        this.add(EVENT_TAB_TITLE, eventTab);
        setMnemonicAt(0, KeyEvent.VK_1);
        setMnemonicAt(1, KeyEvent.VK_2);
        setMnemonicAt(2, KeyEvent.VK_3);
        setMnemonicAt(3, KeyEvent.VK_4);
    }
}
