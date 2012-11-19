package devfortress;

import devfortress.controllers.DeveloperController;
import devfortress.controllers.GameController;
import devfortress.controllers.ProjectController;
import devfortress.controllers.SystemController;
import devfortress.models.GameEngine;
import devfortress.view.DevFortress;
import devfortress.view.InfomationPane;
import devfortress.view.NavigationPane;
import devfortress.view.TabbedPaneDeveloper;
import devfortress.view.TabbedPaneProject;
import devfortress.view.TabbedPaneSystem;
import devfortress.view.WelcomeScreen;

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
        WelcomeScreen welCm = new WelcomeScreen();
        NavigationPane nav = new NavigationPane();
        InfomationPane inf = new InfomationPane();
        //Views
        DevFortress devFortress = new DevFortress(welCm, nav, inf);
        TabbedPaneSystem sysTab = new TabbedPaneSystem();
        TabbedPaneDeveloper devTab = new TabbedPaneDeveloper();
        TabbedPaneProject projTab = new TabbedPaneProject();
        //Controllers
        GameController gameController = new GameController(devFortress, model);
        DeveloperController devController = new DeveloperController(devTab, model);
        ProjectController projectController = new ProjectController(projTab, model);
        SystemController systemController = new SystemController(sysTab, model);
        //Controllers register views with listeners
        gameController.initialize();
        devController.initilize();
        projectController.initilize();
        systemController.initilize();

    }
}
