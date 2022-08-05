package it.seba.juno.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import it.seba.juno.sound.AudioManager;

public class Main extends JFrame {

    private AudioManager audioManager;
    private static final long serialVersionUID = 1L;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public Main(AudioManager am) {

        super("JUno");

        audioManager = am;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // uncomment to set full screen
        device.setFullScreenWindow(this);

        // setSize(1024, 768);
        setMinimumSize(new Dimension(1024, 768));
        setLocationRelativeTo(null);

        add(new MainMenu(am));

        // set the system icon of the program
        setIconImage(
                new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/resources/images/cards/deck_1.png"))
                        .getImage());

        setVisible(true);
    }
}
