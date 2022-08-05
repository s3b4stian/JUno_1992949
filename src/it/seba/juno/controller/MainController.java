package it.seba.juno.controller;

import java.io.File;

//import it.seba.juno.sound.AudioManager;
import it.seba.juno.view.MainView;

public class MainController {

    private MainView view;
    //private AudioManager audioManager;
    
    public MainController(MainView mv) {
        view = mv; 
        initView();
    }

    private void initView() {
        // add sound to view
        view.getAudioManager().addToPlayList("click", (new File("")).getAbsolutePath() + "/src/it/seba/juno/sound/click.wav");
        // set the action listener for button exit
        view.getMainMenu().getButtonExit().addActionListener(e -> gameExit());
        
    }
    
    public void gameExit() {
        System.exit(0);
    }
}
