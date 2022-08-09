package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.PlayersView;

public class PlayersController {

    MainView mainView;
    MenuView menuView;
    PlayersView playersView;

    public PlayersController(MainView mainView, MenuView menuView, PlayersView playersView) {
        this.mainView = mainView;
        this.menuView = menuView;
        this.playersView = playersView;
        initView();
    }

    private void initView() {
        // button back
        playersView.getButtonBack().addActionListener(e -> goBackAction());
    }

    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }
}
