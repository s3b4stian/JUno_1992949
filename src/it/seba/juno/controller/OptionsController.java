package it.seba.juno.controller;

import java.awt.event.ActionEvent;
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

    /**
     * The main view reference.
     */
    private MainView mainView;

    /**
     * The menu view reference.
     */
    private MenuView menuView;

    /**
     * The options view reference.
     */
    private OptionsView optionsView;

    /**
     * The options model reference.
     */
    private OptionsModel optionsModel;

    /**
     * Class Constructor.
     * 
     * @param optionsModel The option model, contains the data about options.
     * @param mainView     The main view, used to host all others view.
     * @param menuView     The menu view, show the main menu.
     * @param optionsView  The options view, show options.
     */
    public OptionsController(OptionsModel optionsModel, MainView mainView, MenuView menuView, OptionsView optionsView) {

        this.optionsModel = optionsModel;

        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;

        initActions();
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
     * 
     * @param e The item event that indicate if the button is selected or not.
     */
    public void goFullScreenAction(ItemEvent e) {
        optionsModel.setFullScreen(true);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the screen mode toggle button, set the game to window.
     * 
     * @param e The item event that indicate if the button is selected or not.
     */
    public void goWindowAction(ItemEvent e) {
        optionsModel.setFullScreen(false);
        optionsModel.notifyObservers(e);
    }

    /**
     * Init actions in view.
     */
    private void initActions() {
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
        optionsView.getTwoPlayersRadio().addActionListener(e -> setTwoPlayersAction(e));
        optionsView.getThreePlayersRadio().addActionListener(e -> setThreePlayersAction(e));
        optionsView.getFourPlayersRadio().addActionListener(e -> setFourPlayersAction(e));
    }

    /**
     * Action for the players radio button, set a four players game.
     * 
     * @param e The component triggered the event.
     */
    public void setFourPlayersAction(ActionEvent e) {
        optionsModel.setNumberOfPlayer(4);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the players radio button, set a three players game.
     * 
     * @param e The component triggered the event.
     */
    public void setThreePlayersAction(ActionEvent e) {
        optionsModel.setNumberOfPlayer(3);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the players radio button, set a two players game.
     * 
     * @param e The component triggered the event.
     */
    public void setTwoPlayersAction(ActionEvent e) {
        optionsModel.setNumberOfPlayer(2);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the sound toggle button, disable the sound.
     * 
     * @param e The item event that indicate if the button is selected or not.
     */
    public void soundOffAction(ItemEvent e) {
        optionsModel.setSound(false);
        optionsModel.notifyObservers(e);
    }

    /**
     * Action for the sound toggle button, enable the sound.
     * 
     * @param e The item event that indicate if the button is selected or not.
     */
    public void soundOnAction(ItemEvent e) {
        optionsModel.setSound(true);
        optionsModel.notifyObservers(e);
    }
}
