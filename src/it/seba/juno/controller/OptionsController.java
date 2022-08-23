package it.seba.juno.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.seba.juno.model.OptionsModel;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;

/**
 * Controls both the {@link it.seba.juno.view.OptionsView} and the
 * {@link it.seba.juno.model.OptionsModel} of the program, it assigns actions to
 * the {@link it.seba.juno.view.OptionsView} components.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OptionsController {

    MainView mainView;
    MenuView menuView;
    OptionsView optionsView;

    OptionsModel optionsModel;

    /**
     * Class Constructor.
     * 
     * @param optionsModel the option model, contains the data about options.
     * @param mainView     the main view, used to host all others view.
     * @param menuView     the menu view, show the main menu.
     * @param optionsView  the options view, show options.
     */
    public OptionsController(OptionsModel optionsModel, MainView mainView, MenuView menuView, OptionsView optionsView) {

        this.optionsModel = optionsModel;

        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;

        initView();
    }

    /**
     * Init actions in view.
     */
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

    /**
     * Action for the players radio button, set a two players game.
     */
    public void setTwoPlayersAction() {
        optionsModel.setNumberOfPlayer(2);
    }

    /**
     * Action for the players radio button, set a three players game.
     */
    public void setThreePlayersAction() {
        optionsModel.setNumberOfPlayer(3);
    }

    /**
     * Action for the players radio button, set a four players game.
     */
    public void setFourPlayersAction() {
        optionsModel.setNumberOfPlayer(4);
    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
        optionsModel.save();
    }

    /**
     * Action for the screen mode toggle button, set the game to full-screen.
     */
    public void goFullScreenAction(ItemEvent e) {
        optionsModel.setFullScreen(true);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the screen mode toggle button, set the game to window.
     */
    public void goWindowAction(ItemEvent e) {
        optionsModel.setFullScreen(false);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the sound toggle button, enable the sound.
     */
    public void soundOnAction(ItemEvent e) {
        optionsModel.setSound(true);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the sound toggle button, disable the sound.
     */
    public void soundOffAction(ItemEvent e) {
        optionsModel.setSound(false);
        optionsModel.notifyObservers(e);
    }
}
