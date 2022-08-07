package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;

public class OptionsController {

    MainView mainView;
    MenuView menuView;
    OptionsView optionsView;

    public OptionsController(MainView mainView, MenuView menuView, OptionsView optionsView) {
        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;
        initView();
    }
    
    private void initView() {
        optionsView.getButtonBack().addActionListener(e -> buttonBackAction());
    }
    
    public void buttonBackAction() {
        mainView.setCurrentView(menuView);
    }
}
