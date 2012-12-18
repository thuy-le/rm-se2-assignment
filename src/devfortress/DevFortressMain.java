package devfortress;

import devfortress.controllers.DeveloperTabController;
import devfortress.controllers.MainFrameController;
import devfortress.controllers.ProjectTabController;
import devfortress.controllers.SystemTabController;
import devfortress.models.GameEngine;
import devfortress.view.*;

/**
 *
 * @author Team Poseidon
 */
public class DevFortressMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String agrs[]) {
        //Model
        GameEngine model = GameEngine.getInstance();
        //GUI Components
        NewGameWelcomePanel welCm = new NewGameWelcomePanel();
        NavigationToolBar nav = new NavigationToolBar();
        InfomationPanel inf = new InfomationPanel();
        AboutScreenPanel aboutScr = new AboutScreenPanel();
        HireDeveloperPanel availableDev = new HireDeveloperPanel();
        AddProjectPanel availableProject = new AddProjectPanel();
        WelcomePanel welcome = new WelcomePanel();
        //Views
        DevFortressMainFrame mainFrame = new DevFortressMainFrame(welCm, nav, inf);
        DeveloperTabPanel devTab = new DeveloperTabPanel();
        SystemTabPanel sysTab = new SystemTabPanel(devTab);
        ProjectTabPanel projTab = new ProjectTabPanel();
        EventTabPanel eventTab = new EventTabPanel();
        DevFortressTabbedPane tabPne = new DevFortressTabbedPane(devTab, projTab, sysTab, eventTab);
        //Controllers
        MainFrameController gameController = new MainFrameController(mainFrame, welCm, nav, inf,devTab, tabPne, aboutScr, model, welcome);
        DeveloperTabController devController = new DeveloperTabController(devTab, model, availableDev, nav, inf, tabPne, mainFrame, sysTab);
        ProjectTabController projectController = new ProjectTabController(projTab, model, availableProject, mainFrame, tabPne, nav, inf);
        SystemTabController systemController = new SystemTabController(sysTab, model);
        //Controllers register views with listeners
        gameController.initialize();
        devController.initialize();
        projectController.initialize();
        systemController.initialize();
        model.addObserver(inf);
        model.addObserver(sysTab);
        model.addObserver(projTab);
        model.addObserver(devTab);
        model.addObserver(availableDev);
        model.addObserver(availableProject);
        model.addObserver(eventTab);
        model.addObserver(nav);
    }
}
