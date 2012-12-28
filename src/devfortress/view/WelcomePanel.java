/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colors;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.view.components.GlassPanel;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class WelcomePanel extends JPanel{

    private int X = 20, Y = 25, WIDTH = 755, HEIGHT = 520, ARCH = 20, ARCW = 20;
    private float ALPHA = .7f;
    private Color COLOUR = Colors.YELLOW;
    //private JPanel container;
    private JPanel newGame;
    private JPanel loadGame;
    private JPanel exit;

    public WelcomePanel() {
        setOpaque(false);
        init();
    }

//    public JPanel getContainer() {
//        return container;
//    }

    private void init() {
        //container = new GlassPanel(x, y, width, height, alpha, colour, arcW, arcH);
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 50);
        GlassPanel content = new GlassPanel(500, 400);
        GlassPanel buttonHolder = new GlassPanel(500, 400);
        buttonHolder.setLayout(new GridLayout(4, 1));
        JLabel welcome = new CustomLabel("Welcome to DevFortress");
        JLabel decor = new CustomLabel("***");

        newGame = new CustomButton("New game");
        loadGame = new CustomButton("Load game");
        exit = new CustomButton("Exit");
        //Look and feel
        ((CustomButton) newGame).setButtonSize(130, 0, 250, 70);
        ((CustomButton) newGame).setAlpha(.9f);
        ((CustomButton) newGame).setColour(Colors.DARKGREEN.darker());
        ((CustomButton) newGame).setOnMouseColor(Colors.DARKGREEN);
        ((CustomButton) newGame).setCustomFont(new Font("Century Gothic", Font.BOLD, 28));
        ((CustomButton) newGame).setTextColour(Color.WHITE);
        ((CustomButton) newGame).setArc(15);
        ((CustomButton) loadGame).setButtonSize(130, 0, 250, 70);
        ((CustomButton) loadGame).setAlpha(.9f);
        ((CustomButton) loadGame).setColour(Colors.REDORANGEDARK);
        ((CustomButton) loadGame).setOnMouseColor(Colors.REDORANGEDARK.brighter());
        ((CustomButton) loadGame).setCustomFont(new Font("Century Gothic", Font.BOLD, 28));
        ((CustomButton) loadGame).setTextColour(Color.WHITE);
        ((CustomButton) loadGame).setArc(15);
        ((CustomButton) exit).setButtonSize(130, 0, 250, 70);
        ((CustomButton) exit).setAlpha(.9f);
        ((CustomButton) exit).setCustomFont(new Font("Century Gothic", Font.BOLD, 28));
        ((CustomButton) exit).setTextColour(Color.WHITE);
        ((CustomButton) exit).setArc(15);
        welcome.setForeground(Colors.DARKBLUE);
        decor.setForeground(Colors.DARKBLUE);

        //add components
        content.add(welcome);
        content.add(decor);
        content.add(buttonHolder);
        buttonHolder.add(newGame);
        buttonHolder.add(loadGame);
        buttonHolder.add(exit);
        add(marginTop, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
    }
    
    public void addNewGameListener(MouseListener l){
        newGame.addMouseListener(l);
    }
    
    public void loadGameListener(MouseListener l){
        loadGame.addMouseListener(l);
    }
    
    public void exitGameListener(MouseListener l){
        exit.addMouseListener(l);
    }
    
    //override the paint component method
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));
        if (COLOUR != null) {
            g2d.setColor(COLOUR);
        }
        g2d.drawRoundRect(X, Y, WIDTH, HEIGHT, ARCW, ARCH);
        g2d.fillRoundRect(X, Y, WIDTH, HEIGHT, ARCW, ARCH);
    }

    //override the getPreferredSize method
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

}
