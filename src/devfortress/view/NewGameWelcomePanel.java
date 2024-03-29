/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.view.components.GlassPanel;
import devfortress.view.components.CustomButton;
import devfortress.view.components.CustomLabel;
import devfortress.utilities.Colors;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.PlainDocument;

/**
 *
 * @author PC
 */
public class NewGameWelcomePanel extends JPanel{

    private int X = 20, Y = 25, WIDTH = 755, HEIGHT = 520, ARCH = 20, ARCW = 20;
    private float ALPHA = .7f;
    private Color COLOUR = Colors.YELLOW;
    private String playerName;
    private JTextField playerTxt;
    private CustomButton submitName;
    //private JPanel container;

    public NewGameWelcomePanel() {
        setOpaque(false);
        init();
    }

    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

//    public JPanel getContainer() {
//        return container;
//    }

    private void init() {
        //container = new GlassPanel(x, y, width, height, alpha, colour, arcW, arcH);
        //Local Variables
        GlassPanel marginTop = new GlassPanel(800, 100);
        GlassPanel content = new GlassPanel(500, 400);
        GlassPanel textHolder = new GlassPanel(500, 50);
        PlainDocument doc;
        JLabel welcome = new CustomLabel("Welcome to DevFortress");
        JLabel decor = new CustomLabel("***");
        submitName = new CustomButton("SUBMIT");
        TextfieldStateChange validation = new TextfieldStateChange(submitName);

        //Global Variables
        playerTxt = new CustomJTextField("DevFortress", 15);
        playerName = playerTxt.getText();
        doc = (PlainDocument) playerTxt.getDocument();

        //
        doc.setDocumentFilter(new TextLengthDocFilter(15));
        playerTxt.addKeyListener(validation);

        //look and feel
        playerTxt.setOpaque(false);
        playerTxt.setFont(new Font("Century Gothic", Font.BOLD, 32));
        playerTxt.setHorizontalAlignment(JTextField.CENTER);
        playerTxt.setBorder(BorderFactory.createLineBorder(Colors.DARKBLUE, 2));
        welcome.setForeground(Colors.DARKBLUE);
        decor.setForeground(Colors.DARKBLUE);

        //add components
        textHolder.add(playerTxt);
        content.add(welcome);
        content.add(decor);
        content.add(textHolder);
        add(marginTop, BorderLayout.NORTH);
        content.add(submitName);
        add(content, BorderLayout.CENTER);

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

    public void addSubmitNameListener(MouseListener l) {
        submitName.addMouseListener(l);
    }

    private class TextfieldStateChange implements KeyListener {

        private CustomButton button;

        public TextfieldStateChange(CustomButton button) {
            this.button = button;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField playerTxt = (JTextField) e.getSource();
                setPlayerName(playerTxt.getText() + " ");
                if (!playerTxt.getText().trim().equals("")) {
                    button.enableButton();
                } else {
                    button.disableButton();
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField playerTxt = (JTextField) e.getSource();
                setPlayerName(playerTxt.getText() + " ");
                if (!playerTxt.getText().trim().equals("")) {
                    button.enableButton();
                } else {
                    button.disableButton();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField playerTxt = (JTextField) e.getSource();
                setPlayerName(playerTxt.getText() + " ");
                if (!playerTxt.getText().trim().equals("")) {
                    button.enableButton();
                } else {
                    button.disableButton();
                }
            }
        }
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

class CustomJTextField extends JTextField {

    public CustomJTextField(String text, int columns) {
        super(text, columns);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(g);
    }
}
