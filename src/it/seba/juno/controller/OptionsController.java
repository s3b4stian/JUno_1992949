package it.seba.juno.controller;

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
        // button back
        optionsView.getButtonBack().addActionListener(e -> goBackAction());

        // button full screen
        optionsView.getButtonFullScreen().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    goFullScreenAction(e);
                } else {
                    goWindowAction(e);
                }
            }
        });

        // button sound
        optionsView.getButtonSound().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    soundOnAction(e);
                } else {
                    soundOffAction(e);
                }
            }
        });
    }

    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }

    public void goFullScreenAction(ItemEvent e) {
        optionsModel.setFullScreen(true);
        optionsModel.notifyObservers(e);
    }

    public void goWindowAction(ItemEvent e) {
        optionsModel.setFullScreen(false);
        optionsModel.notifyObservers(e);
    }

    public void soundOnAction(ItemEvent e) {
        optionsModel.setSound(true);
        optionsModel.notifyObservers(e);
    }

    public void soundOffAction(ItemEvent e) {
        optionsModel.setSound(false);
        optionsModel.notifyObservers(e);
    }
}
