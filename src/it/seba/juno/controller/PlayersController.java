package it.seba.juno.controller;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.seba.juno.model.PlayersModel;
import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.PlayersView;
import it.seba.juno.view.component.ListPlayers;

/**
 * Controls both the {@link it.seba.juno.view.PlayersView} and the
 * {@link it.seba.juno.model.PlayersModel} of the program, it assigns actions to
 * the {@link it.seba.juno.view.PlayersView} components.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayersController {

    /**
     * The main view reference.
     */
    private MainView mainView;

    /**
     * The menu view reference.
     */
    private MenuView menuView;

    /**
     * The players view reference.
     */
    private PlayersView playersView;

    /**
     * The list of the game players.
     */
    private ListPlayers<PlayersProfileModel> listPlayers;

    /**
     * The player model reference.
     */
    private PlayersModel playersModel;

    /**
     * Class Constructor.
     * 
     * @param playersModel The player model, contains the data about players.
     * @param mainView     The main view, used to host all others view.
     * @param menuView     The menu view, show the main menu.
     * @param playersView  The players view, show players.
     */
    public PlayersController(PlayersModel playersModel, MainView mainView, MenuView menuView, PlayersView playersView) {

        this.playersModel = playersModel;

        this.mainView = mainView;
        this.menuView = menuView;
        this.playersView = playersView;

        initActions();
    }

    /**
     * Action for the cancel button in delete player dialog, hide dialog.
     * 
     * @param e the component triggered the event.
     */
    public void cancelDeleteModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }

    /**
     * Action for the cancel button in new player dialog, hide dialog.
     * 
     * @param e The component triggered the event.
     */
    public void cancelNewPlayerModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }

    /**
     * Action for the confirm button in delete player dialog, delete selected player
     * and hide dialog.
     * 
     * @param e The component triggered the event.
     */
    public void confirmDeleteModalAction(ActionEvent e) {
        if (listPlayers.getSelectedIndex() != -1) {
            playersModel.setCurrentProfile(null);
            playersModel.removePlayer(((PlayersProfileModel) listPlayers.getSelectedValue()).getName());
            playersModel.notifyObservers(e);
        }
    }

    /**
     * Action for the confirm button in new player dialog, create new player and
     * hide dialog.
     * 
     * @param e The component triggered the event.
     */
    public void confirmNewPlayerModalAction(ActionEvent e) {
        String playerName = playersView.getNewPlayerModal().getTextField().getText();
        PlayersProfileModel newPlayer = new PlayersProfileModel(playerName);
        playersModel.addPlayer(playerName, newPlayer);
        listPlayers.addItem(newPlayer);
        playersModel.notifyObservers(e);
    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
        playersModel.save();
    }

    /**
     * Init actions in view.
     */
    private void initActions() {
        // players list
        listPlayers = playersView.getListPlayers();
        loadListPlayers();

        listPlayers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && listPlayers.getSelectedIndex() != -1) {
                    loadAction(e);
                }
            }
        });

        // button back
        playersView.getButtonBack().addActionListener(e -> goBackAction());

        // button delete
        playersView.getButtonDelete().addActionListener(e -> showDeleteModalAction(e));

        // button new
        playersView.getButtonNew().addActionListener(e -> showNewPlayerModalAction(e));

        // modal buttons
        // button confirm new player
        playersView.getNewPlayerModal().getConfirmButton().addActionListener(e -> confirmNewPlayerModalAction(e));
        // button cancel new player
        playersView.getNewPlayerModal().getCancelButton().addActionListener(e -> cancelNewPlayerModalAction(e));

        // button confirm delete player
        playersView.getDeleteModal().getConfirmButton().addActionListener(e -> confirmDeleteModalAction(e));
        // button cancel delete player
        playersView.getDeleteModal().getCancelButton().addActionListener(e -> cancelDeleteModalAction(e));

    }

    /**
     * Action for the players list, load the player profile of the selected player.
     * 
     * @param e The component triggered the event.
     */
    public void loadAction(ListSelectionEvent e) {
        if (listPlayers.getSelectedIndex() != -1) {
            playersModel.setCurrentProfile(((PlayersProfileModel) listPlayers.getSelectedValue()).getName());
            playersModel.notifyObservers(e);
        }
    }

    /**
     * Load players from model to view JList component.
     */
    private void loadListPlayers() {
        Map<String, PlayersProfileModel> players = playersModel.getPlayers();

        // save all profiles
        for (Map.Entry<String, PlayersProfileModel> entry : players.entrySet()) {
            listPlayers.addItem(entry.getValue());
        }
    }

    /**
     * Action for the delete button, show the delete player dialog.
     * 
     * @param e The component triggered the event.
     */
    public void showDeleteModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }

    /**
     * Action for the new button, show the new player dialog.
     * 
     * @param e The component triggered the event.
     */
    public void showNewPlayerModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }
}
