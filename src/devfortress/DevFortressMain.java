package devfortress;

import devfortress.controllers.DeveloperController;
import devfortress.controllers.GameViewController;
import devfortress.controllers.ProjectController;
import devfortress.controllers.SystemController;
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
        WelcomeScreen welCm = new WelcomeScreen();
        NavigationPane nav = new NavigationPane();
        InfomationPane inf = new InfomationPane();
        AboutScreen aboutScr = new AboutScreen();
        AvailableDevelopers availableDev = new AvailableDevelopers();
        //Views
        DevFortress devFortress = new DevFortress(welCm, nav, inf);
        TabbedPaneSystem sysTab = new TabbedPaneSystem();
        TabbedPaneDeveloper devTab = new TabbedPaneDeveloper();
        TabbedPaneProject projTab = new TabbedPaneProject();
        TabbedPane tabPne = new TabbedPane(devTab, projTab, sysTab);
        //Controllers
        GameViewController gameController = new GameViewController(devFortress, welCm, nav, inf, tabPne, aboutScr, model);
        DeveloperController devController = new DeveloperController(devTab, model, availableDev, nav, inf, tabPne, devFortress);
        ProjectController projectController = new ProjectController(projTab, model);
        SystemController systemController = new SystemController(sysTab, model);
        //Controllers register views with listeners
        gameController.initialize();
        devController.initilize();
        projectController.initilize();
        systemController.initilize();
        model.addObserver(inf);
        model.addObserver(sysTab);
        model.addObserver(projTab);
        model.addObserver(devTab);
    }
}
