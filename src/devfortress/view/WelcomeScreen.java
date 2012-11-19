/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.controllers.TextfieldStateChange;
import devfortress.controllers.GameController;
import devfortress.utilities.GlassPanel;
import devfortress.utilities.CustomButtonEvent;
import devfortress.utilities.CustomButton;
import devfortress.utilities.CustomLabel;
import devfortress.utilities.Colour;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.*;
import javax.swing.text.DocumentFilter.FilterBypass;

/**
 *
 * @author PC
 */
public class WelcomeScreen extends JPanel {

    private int x, y, width, height, arcH, arcW;
    private float alpha;
    private Color colour;
    private String playerName;
    private JTextField playerTxt;
    private static volatile WelcomeScreen instance = null;

    private WelcomeScreen() {
        this.x = 20;
        this.y = 25;
        this.width = 755;
        this.height = 520;
        this.arcW = 20;
        this.arcH = 20;
        this.alpha = .7f;
        this.colour = Colour.YELLOW;
        setOpaque(false);
        init();
    }

    public static WelcomeScreen getInstance() {
        if (instance == null) {
            synchronized (WelcomeScreen.class) {
                if (instance == null) {
                    instance = new WelcomeScreen();
                }
            }
        }
        return instance;
    }
    
    //Getters and Setters
    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    private void init() {
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 100);
        GlassPanel content = new GlassPanel(500, 400);
        GlassPanel textHolder = new GlassPanel(500, 50);
        PlainDocument doc;
        JLabel welcome = new CustomLabel("Welcome to DevFortress");
        JLabel decor = new CustomLabel("***");
        CustomButton submitName = new CustomButton("SUBMIT");
        TextfieldStateChange validation = new TextfieldStateChange(submitName);
        
        //Global Variables
        playerTxt = new JTextField("DevFortress", 15) {
            @Override
            protected void paintComponent(Graphics g) {
                ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                super.paintComponent(g);
            }
        };
        playerName = playerTxt.getText();
        doc = (PlainDocument) playerTxt.getDocument();
        
        //
        doc.setDocumentFilter(new TextLengthDocFilter(15));
        playerTxt.addKeyListener(validation);
        submitName.addMouseListener(new CustomButtonEvent(Colour.DARKBLUE, Colour.DARKBLUE2));
        submitName.addMouseListener(new GameController());
        
        //look and feel
        playerTxt.setOpaque(false);
        playerTxt.setFont(new Font("Century Gothic", Font.BOLD, 32));
        playerTxt.setHorizontalAlignment(JTextField.CENTER);
        playerTxt.setBorder(BorderFactory.createLineBorder(Colour.DARKBLUE, 2, true));
        welcome.setForeground(Colour.DARKBLUE);
        decor.setForeground(Colour.DARKBLUE);
        
        //add components
        textHolder.add(playerTxt);
        content.add(welcome);
        content.add(decor);
        content.add(textHolder);
        add(marginTop, BorderLayout.NORTH);
        content.add(submitName);
        add(content, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (colour != null) {
            g2d.setColor(colour);
        }
        g2d.fillRoundRect(x, y, width, height, arcW, arcH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}

   class TextLengthDocFilter extends DocumentFilter {
      private int maxTextLength;

      public TextLengthDocFilter(int maxTextLength) {
         this.maxTextLength = maxTextLength;
      }

      private boolean verifyText(String text) {
         return text.length() <= maxTextLength;
      }

      @Override
      public void insertString(FilterBypass fb, int offset, String string,
               AttributeSet attr) throws BadLocationException {

         Document doc = fb.getDocument();
         String oldText = doc.getText(0, doc.getLength());
         StringBuilder sb = new StringBuilder(oldText);
         sb.insert(offset, string);

         if (verifyText(sb.toString())) {
            super.insertString(fb, offset, string, attr);
         }

      }

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
               throws BadLocationException {
         Document doc = fb.getDocument();
         String oldText = doc.getText(0, doc.getLength());
         StringBuilder sb = new StringBuilder(oldText);

         sb.replace(offset, offset + length, text);
         if (verifyText(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
         }
      }

      @Override
      public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
         Document doc = fb.getDocument();
         String oldText = doc.getText(0, doc.getLength());
         StringBuilder sb = new StringBuilder(oldText);

         sb.replace(offset, offset + length, "");

         if (verifyText(sb.toString())) {
            super.remove(fb, offset, length);            
         }
      }
   }