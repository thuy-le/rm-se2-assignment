package devfortress.controllers;

import devfortress.models.Developer;
import devfortress.models.FunctionalArea;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.view.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
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
            Project pro = projectTab.getSelectedProject();
            if (pro != null) {
                try {
                    model.cancelProject(pro);
                    JOptionPane.showMessageDialog(null, pro.getName() + "cancelled");
                    model.notifyObservers();
                } catch (Exception ex) {
                }
            }
        }
    }

    private class AddDevToProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (model.getDevelopers().isEmpty()) {
               JOptionPane.showConfirmDialog(mainFrame, "No developer at the momment!", "DevFortress", -1);
            } else {
                Project project = projectTab.getSelectedProject();
                AddDeveloperToProjectDialog dialog = new AddDeveloperToProjectDialog(model, project);
                dialog.addApplyButtonListener(new ApplyMouseAdapter(dialog, project));
                dialog.setVisible(true);
            }
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
                for (Iterator<Developer> itr = selected.keySet().iterator(); itr.hasNext();) {
                    Developer dev = itr.next();
                    FunctionalArea area = selected.get(dev);
                    try {
                        project.addDeveloper(dev, area.getName());
                    } catch (DeveloperBusyException ex) {
                    } catch (InvalidFunctionalAreaException ex) {
                    }
                }
                dialog.dispose();
            }
        }
    }

    private class RemoveDevFromProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (model.getDevelopers().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No developers at the moments");
            } else {
                Project project = projectTab.getSelectedProject();
                RemoveDeveloperFromProjectDialog dialog = new RemoveDeveloperFromProjectDialog(model, project);
                dialog.addApplyButtonListener(new ApplyMouseAdapter(dialog, project));
                dialog.setVisible(true);
            }
        }

        private class ApplyMouseAdapter extends MouseAdapter {

            RemoveDeveloperFromProjectDialog dialog;
            Project project;

            public ApplyMouseAdapter(RemoveDeveloperFromProjectDialog dialog, Project project) {
                this.dialog = dialog;
                this.project = project;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Map<Developer, FunctionalArea> selected = dialog.getSelectedDevelopers();
                for (Iterator<Developer> itr = selected.keySet().iterator(); itr.hasNext();) {
                    Developer dev = itr.next();
                    project.removeDeveloper(dev);
                    model.notifyObservers();
                }
                dialog.dispose();
            }
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
                if(model.getDevelopers().isEmpty()){
                    projectTab.btnAddDev.disableButton();
                    projectTab.btnRemoveDev.disableButton();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Project Selected","DevFortress",-1);
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
