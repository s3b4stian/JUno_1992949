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
        playersView.getButtonBack().addActionListener(e -> mainView.setCurrentView(menuView));
    }

    public void buttonBackAction() {
        mainView.setCurrentView(menuView);
    }
}
