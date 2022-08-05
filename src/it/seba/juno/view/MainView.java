package it.seba.juno.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import it.seba.juno.sound.AudioManager;
import it.seba.juno.view.component.MainMenu;

public class MainView extends JFrame {

    private AudioManager audioManager;
    private static final long serialVersionUID = 1L;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private MainMenu mainMenu;
    
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /*public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }*/

    public AudioManager getAudioManager() {
        return audioManager;
    }

    /*public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }*/

    public MainView(AudioManager am) {

        super("JUno");

        audioManager = am;
        mainMenu = new MainMenu(am);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // uncomment to set full screen
        device.setFullScreenWindow(this);

        // setSize(1024, 768);
        setMinimumSize(new Dimension(1024, 768));
        setLocationRelativeTo(null);

        add(mainMenu);

        // set the system icon of the program
        setIconImage(
                new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/resources/images/cards/deck_1.png"))
                        .getImage());

        setVisible(true);
    }
}
