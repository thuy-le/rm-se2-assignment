package devfortress.controllers;

import devfortress.models.Developer;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.view.AddDeveloperToProjectDialog;
import devfortress.view.AddProjectPanel;
import devfortress.view.DevFortressMainFrame;
import devfortress.view.DevFortressTabbedPane;
import devfortress.view.InfomationPanel;
import devfortress.view.NavigationToolBar;
import devfortress.view.ProjectTabPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Michael
 */
public class ProjectTabController {

    private ProjectTabPanel projectTab;
    private DevFortressTabbedPane tabbedPane;
    private AddProjectPanel addProjectPanel;
    private DevFortressMainFrame mainFrame;
    private NavigationToolBar navBar;
    private InfomationPanel infoPanel;
    private GameEngine model;

    public ProjectTabController(ProjectTabPanel projectTab, GameEngine model,
            AddProjectPanel addProjectPanel, DevFortressMainFrame mainFrame,
            DevFortressTabbedPane tabbedPane,
            NavigationToolBar navBar, InfomationPanel infoPanel) {
        this.projectTab = projectTab;
        this.model = model;
        this.addProjectPanel = addProjectPanel;
        this.mainFrame = mainFrame;
        this.tabbedPane = tabbedPane;
        this.navBar = navBar;
        this.infoPanel = infoPanel;
    }

    public void initialize() {
        projectTab.addNewProjectListener(new AddProjectBtnListener());
        projectTab.addDevToProjectListener(new AddDevToProjectListener());
        projectTab.cancelProjectListener(new CancelProjectListener());
        projectTab.removeDevFromProjectListener(new RemoveDevFromProjectListener());

        addProjectPanel.addAcceptProjectEvent(new AcceptProjectListener());
        addProjectPanel.addCloseEvent(new CloseAddProjectPanelListener());
    }

    private class AddProjectBtnListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(tabbedPane);
            navBar.setVisible(false);
            infoPanel.setVisible(false);
            mainFrame.add(addProjectPanel);
            mainFrame.repaint();
        }
    }

    private class CancelProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class AddDevToProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Project project = projectTab.getSelectedProject();
            AddDeveloperToProjectDialog dialog = new AddDeveloperToProjectDialog(model, project);
            dialog.addApplyButtonListener(new ApplyMouseAdapter(dialog, project));
            dialog.setVisible(true);
        }

        private class ApplyMouseAdapter extends MouseAdapter {

            AddDeveloperToProjectDialog dialog;
            Project project;

            public ApplyMouseAdapter(AddDeveloperToProjectDialog dialog, Project project) {
                this.dialog = dialog;
                this.project = project;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Map<Developer, FunctionalArea> selected = dialog.getSelectedDevelopers();

            }
        }
    }

    private class RemoveDevFromProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class AcceptProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Project project = addProjectPanel.getSelectedProject();
            if (project != null) {
                // add project to project list:
                model.acceptProject(project);
                model.notifyObservers();
                JOptionPane.showMessageDialog(null, "New project accepted:\n" + project.getName());
            } else {
                JOptionPane.showMessageDialog(null, "No Project selected");
            }
        }
    }

    private class CloseAddProjectPanelListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.remove(addProjectPanel);
            infoPanel.setVisible(true);
            navBar.setVisible(true);
            mainFrame.add(tabbedPane);
            mainFrame.repaint();
        }
    }
}
