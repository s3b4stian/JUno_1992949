package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;
import it.seba.juno.view.PlayersView;

public class MenuController {

    MainView mainView;
    MenuView menuView;
    OptionsView optionsView;
    PlayersView playersView;

    public MenuController(MainView mainView, MenuView menuView, OptionsView optionsView, PlayersView playersView) {
        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;
        this.playersView = playersView;
        initView();
    }

    private void initView() {
        // button exit
        menuView.getButtonExit().addActionListener(e -> exitAction());
        
        // button options
        menuView.getButtonOptions().addActionListener(e -> goOptionsAction());
        
        // button players
        menuView.getButtonPlayers().addActionListener(e -> goPlayersAction());
    }

    public void exitAction() {
        System.exit(0);
    }

    public void goOptionsAction() {
        mainView.setCurrentView(optionsView);
    }

    public void goPlayersAction() {
        mainView.setCurrentView(playersView);
    }
}
