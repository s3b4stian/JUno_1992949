package it.seba.juno.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.seba.juno.manger.SerializationManager;
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

        this.optionsModel = optionsModel;

        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;

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

        // buttons for players
        optionsView.getTwoPlayersRadio().addActionListener(e -> setTwoPlayersAction());
        optionsView.getThreePlayersRadio().addActionListener(e -> setThreePlayersAction());
        optionsView.getFourPlayersRadio().addActionListener(e -> setFourPlayersAction());
    }

    public void setTwoPlayersAction() {
        optionsModel.setNumberOfPlayer(2);
    }

    public void setThreePlayersAction() {
        optionsModel.setNumberOfPlayer(3);
    }

    public void setFourPlayersAction() {
        optionsModel.setNumberOfPlayer(4);
    }

    public void goBackAction() {
        mainView.setCurrentView(menuView);
        SerializationManager.getInstance().saveOptions(optionsModel);
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
