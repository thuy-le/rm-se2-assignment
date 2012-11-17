/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.CustomButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class TextfieldStateChange implements KeyListener{
    private CustomButton button;
    public TextfieldStateChange(CustomButton button){
        this.button = button;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() instanceof JTextField){
            JTextField playerTxt = (JTextField) e.getSource();
            WelcomeScreen.getInstance().setPlayerName(playerTxt.getText()+" ");
            if(!playerTxt.getText().trim().equals("")){
                button.enableButton();
            }
            else{
                button.disableButton();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() instanceof JTextField){
            JTextField playerTxt = (JTextField) e.getSource();
            WelcomeScreen.getInstance().setPlayerName(playerTxt.getText()+" ");
            if(!playerTxt.getText().trim().equals("")){
                button.enableButton();
                System.out.println("enteref");
            }
            else{
                button.disableButton();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() instanceof JTextField){
            JTextField playerTxt = (JTextField) e.getSource();
            WelcomeScreen.getInstance().setPlayerName(playerTxt.getText()+" ");
            if(!playerTxt.getText().trim().equals("")){
                button.enableButton();
            }
            else{
                button.disableButton();
            }
        }
    }
}
