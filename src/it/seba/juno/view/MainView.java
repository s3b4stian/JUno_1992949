package it.seba.juno.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.sound.AudioManager;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private JPanel currentPanel;

    public void setCurrentView(JComponent com) {
        currentPanel.removeAll();
        currentPanel.add(com);
        currentPanel.repaint();
        currentPanel.revalidate();
    }
    
    public MainView(AudioManager am) {

        super("JUno");

        // init current panel
        currentPanel = new JPanel();
        currentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        currentPanel.setOpaque(false);
        currentPanel.setLayout(new java.awt.BorderLayout());
        
        // default behavior on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // uncomment to set full screen
        device.setFullScreenWindow(this);

        //setSize(1200, 800);
        //setMinimumSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);

        add(currentPanel);

        // set the system icon of the program
        setIconImage(
                new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/resources/images/cards/deck_1.png"))
                        .getImage());

        setVisible(true);
    }
}
