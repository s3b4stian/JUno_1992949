package it.seba.juno.controller;

//import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.seba.juno.model.OptionsModel;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;

public class OptionsController {

    MainView mainView;
    MenuView menuView;
    OptionsView optionsView;

    OptionsModel optionsModel;

    public OptionsController(OptionsModel optionsModel, MainView mainView, MenuView menuView, OptionsView optionsView) {
        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;

        this.optionsModel = optionsModel;

        initView();
    }

    private void initView() {
        optionsView.getButtonBack().addActionListener(e -> buttonBackAction());
        optionsView.getButtonFullScreen().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    buttonFullScreenAction(e);
                } else {
                    buttonWindowAction(e);
                }
            }
        });
    }

    public void buttonBackAction() {
        mainView.setCurrentView(menuView);
    }

    /*
     * public void buttonFullScreenAction(ActionEvent e) {
     * optionsModel.setFullScreen(true); optionsModel.notifyObservers(e); }
     * 
     * public void buttonWindowAction(ActionEvent e) {
     * optionsModel.setFullScreen(false); optionsModel.notifyObservers(e); }
     */

    public void buttonFullScreenAction(ItemEvent e) {
        optionsModel.setFullScreen(true);
        optionsModel.notifyObservers(e);
    }

    public void buttonWindowAction(ItemEvent e) {
        optionsModel.setFullScreen(false);
        optionsModel.notifyObservers(e);
    }
}
