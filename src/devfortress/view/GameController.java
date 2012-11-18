/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.GlassPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author PC
 */
public class GameController extends MouseAdapter {

    public GameController() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getSource() instanceof CustomButton) {
            if (!WelcomeScreen.getInstance().getPlayerName().trim().equals("")) {
                DevFortress.getInstance().remove(WelcomeScreen.getInstance());
                DevFortress.getInstance().add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);                
                NavigationPane.getInstance().getToolbar().setVisible(true);
                InfomationPane.getInstance().getInfoPanel().setVisible(true);
                TabbedPaneSystem.getInstance().setPlayerName(WelcomeScreen.getInstance().getPlayerName());
                DevFortress.getInstance().repaint();
            }
        }
        
        //Customize Back Button in the About screen
          if (e.getSource() instanceof CustomButton) {
                DevFortress.getInstance().remove(AboutScreen.getInstance());
                DevFortress.getInstance().add(TabbedPane.getInstance().getContainerTab(), BorderLayout.CENTER);                
                NavigationPane.getInstance().getToolbar().setVisible(true);
                InfomationPane.getInstance().getInfoPanel().setVisible(true);
                DevFortress.getInstance().repaint();
            
        }
        
        // New Game Command Execute in the Game controller
         if(e.getSource() instanceof CustomLabel){
            CustomLabel label = (CustomLabel) e.getSource();
            if(label.getLabelName().equals("new")){
                System.out.println("New Game Command");
                DevFortress.getInstance().remove(TabbedPane.getInstance().getContainerTab());              
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                DevFortress.getInstance().getContentPane().add(WelcomeScreen.getInstance());
                DevFortress.getInstance().getContentPane().repaint();
            }
        }
         
         //Save game windows appear
         if(e.getSource() instanceof CustomLabel){
            CustomLabel label = (CustomLabel) e.getSource();
            if(label.getLabelName().equals("save")){
                System.out.println("Saves Game Command");                
            }
        }
         
          //Save game windows appear
         if(e.getSource() instanceof CustomLabel){
            CustomLabel label = (CustomLabel) e.getSource();
            if(label.getLabelName().equals("about")){
                System.out.println("About");
                DevFortress.getInstance().remove(TabbedPane.getInstance().getContainerTab());              
                NavigationPane.getInstance().getToolbar().setVisible(false);
                InfomationPane.getInstance().getInfoPanel().setVisible(false);
                DevFortress.getInstance().getContentPane().add(AboutScreen.getInstance());
                DevFortress.getInstance().getContentPane().repaint();
            }
        }
         
        
        // The window should close when player hit the "Exit Game" button
        if(e.getSource() instanceof CustomLabel){
            CustomLabel label = (CustomLabel) e.getSource();
            if(label.getLabelName().equals("exit")){
                System.out.println("EXIT COMMAND");
                System.exit(0);
            }
        }
        
       
    }
}
