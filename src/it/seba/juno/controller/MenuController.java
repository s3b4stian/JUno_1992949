package it.seba.juno.controller;

import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;
import it.seba.juno.view.PlayersView;

/**
 * Controls the {@link it.seba.juno.view.MenuView} of the program and assign
 * actions to the {@link it.seba.juno.view.MenuView} components.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MenuController {

    MainView mainView;
    MenuView menuView;
    OptionsView optionsView;
    PlayersView playersView;

    /**
     * Class Constructor.
     * 
     * @param mainView    the main view, used to host all others view.
     * @param menuView    the menu view, show the main menu.
     * @param optionsView the options view, show the application options
     * @param playersView the players view, manage players
     */
    public MenuController(MainView mainView, MenuView menuView, OptionsView optionsView, PlayersView playersView) {
        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;
        this.playersView = playersView;
        initView();
    }

    /**
     * Init actions in view.
     */
    private void initView() {
        // button exit
        menuView.getButtonExit().addActionListener(e -> exitAction());

        // button options
        menuView.getButtonOptions().addActionListener(e -> goOptionsAction());

        // button players
        menuView.getButtonPlayers().addActionListener(e -> goPlayersAction());
    }

    /**
     * Action for the exit button, terminate the game.
     */
    public void exitAction() {
        System.exit(0);
    }

    /**
     * Action for the options button, switch current view to options view
     */
    public void goOptionsAction() {
        mainView.setCurrentView(optionsView);
    }

    /**
     * Action for the players button, switch current view to players view
     */
    public void goPlayersAction() {
        mainView.setCurrentView(playersView);
    }
}
