package devfortress.controllers;

import devfortress.enumerations.AreaName;
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
            if (model.getDate().getWeek() == 1) {
                mainFrame.remove(tabbedPane);
                navBar.setVisible(false);
                infoPanel.setVisible(false);
                mainFrame.add(addProjectPanel);
                mainFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "You can only add new project at the beginning of each month");
            }
        }
    }

    private class CancelProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (model.getDate().getWeek() == 1) {
                Project pro = projectTab.getSelectedProject();
                if (pro != null) {
                    try {
                        int input = JOptionPane.showConfirmDialog(mainFrame, "Do you want to cancel this project", "DevFortress", JOptionPane.YES_NO_OPTION);
                        if (input == JOptionPane.YES_OPTION) {
                            model.cancelProject(pro);
                            JOptionPane.showMessageDialog(null, pro.getName() + " cancelled");
                            model.notifyObservers();
                        }
                    } catch (Exception ex) {
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Project can only be cancelled at the beginning of each month");
            }
        }
    }

    private class AddDevToProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (model.getDate().getWeek() == 1) {
                if (!model.hasAvalableDevs()) {
                    JOptionPane.showMessageDialog(mainFrame, "No developers at the moment","DevFortress",JOptionPane.OK_OPTION);
                } else {
                    Project project = projectTab.getSelectedProject();
                    AddDeveloperToProjectDialog dialog = new AddDeveloperToProjectDialog(model, project);
                    dialog.addApplyButtonListener(new ApplyMouseAdapter(dialog, project));
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You can only add developer to project at the beginning of each month");
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
                        model.assignDeveloperToProject(project, dev, area.getName());
                    } catch (DeveloperBusyException ex) {
                    } catch (InvalidFunctionalAreaException ex) {
                    }
                }
                model.notifyObservers();
                dialog.dispose();
            }
        }
    }

    private class RemoveDevFromProjectListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (model.getDate().getWeek() == 1) {
                if (model.getDevelopers().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "No developers in project","DevFortress",JOptionPane.OK_OPTION);
                } else {
                    Project project = projectTab.getSelectedProject();
                    RemoveDeveloperFromProjectDialog dialog = new RemoveDeveloperFromProjectDialog(model, project);
                    dialog.addApplyButtonListener(new ApplyMouseAdapter(dialog, project));
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You can only remove developer from project at the beginning of each month");
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
                    model.removeDeveloperFromProject(dev, project);
                }
                model.notifyObservers();
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
                JOptionPane.showMessageDialog(null, "New project accepted:\n" + project.getName(), "DevFortress",JOptionPane.INFORMATION_MESSAGE);                
            } else {
                JOptionPane.showMessageDialog(null, "No Project Selected", "DevFortress", JOptionPane.OK_OPTION);
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
