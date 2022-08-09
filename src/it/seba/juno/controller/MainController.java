package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;

public class MainController {

    private MainView mainView;
    private MenuView menuView;

    public MainController(MainView mainView, MenuView menuView) {
        this.mainView = mainView;
        this.menuView = menuView;
        initView();
    }

    private void initView() {
        mainView.setCurrentView(menuView);
    }
}
