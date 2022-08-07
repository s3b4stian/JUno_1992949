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
        menuView.getButtonExit().addActionListener(e -> buttonExitAction());
        menuView.getButtonOptions().addActionListener(e -> buttonOptionsAction());
        menuView.getButtonPlayers().addActionListener(e -> buttonPlayersAction());
    }

    public void buttonExitAction() {
        System.exit(0);
    }

    public void buttonOptionsAction() {
        mainView.setCurrentView(optionsView);
    }

    public void buttonPlayersAction() {
        mainView.setCurrentView(playersView);
    }
}
