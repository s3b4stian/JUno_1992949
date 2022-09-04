package it.seba.juno.controller;

import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.model.GameModel;
import it.seba.juno.view.GameView;
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

    private MainView mainView;
    private MenuView menuView;
    private OptionsView optionsView;
    private PlayersView playersView;
    private GameView gameView;

    private GameModel gameModel;

    /**
     * Class Constructor.
     * 
     * @param gameModel   The game model, used to reset the game view if there is
     *                    any change in options
     * @param mainView    The main view, used to host all others view.
     * @param menuView    The menu view, show the main menu.
     * @param optionsView The options view, show the application options
     * @param playersView The players view, manage players
     */

    public MenuController(GameModel gameModel, MainView mainView, MenuView menuView, OptionsView optionsView,
            PlayersView playersView, GameView gameView) {

        this.gameModel = gameModel;
        this.mainView = mainView;
        this.menuView = menuView;
        this.optionsView = optionsView;
        this.playersView = playersView;
        this.gameView = gameView;

        initActions();
    }

    /**
     * Action for the exit button, terminate the game.
     */
    public void exitAction() {
        System.exit(0);
    }

    /**
     * Action for the play button, switch current view to game view
     */
    public void goGameAction() {
        if (gameModel.needReset()) {
            gameModel.reset();
            gameModel.notifyObservers(new ResetGameEvent(this));
        }

        mainView.setCurrentView(gameView);
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

    /**
     * Init actions in view.
     */
    private void initActions() {

        // button play
        menuView.getButtonPlay().addActionListener(e -> goGameAction());

        // button players
        menuView.getButtonPlayers().addActionListener(e -> goPlayersAction());

        // button options
        menuView.getButtonOptions().addActionListener(e -> goOptionsAction());

        // button exit
        menuView.getButtonExit().addActionListener(e -> exitAction());

    }
}
