package it.seba.juno.controller;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.model.PlayersModel;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.PlayersView;
import it.seba.juno.view.component.ListPlayers;

public class PlayersController {

    MainView mainView;
    MenuView menuView;
    PlayersView playersView;
    ListPlayers<PlayersProfileModel> listPlayers;

    PlayersModel playersModel;

    public PlayersController(PlayersModel playersModel, MainView mainView, MenuView menuView, PlayersView playersView) {

        this.playersModel = playersModel;

        this.mainView = mainView;
        this.menuView = menuView;
        this.playersView = playersView;
        initView();
    }

    private void initView() {
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
        playersView.getButtonDelete().addActionListener(e -> deleteAction(e));

        // button new
        playersView.getButtonNew().addActionListener(e -> showNewPlayerModalAction(e));
        
        
        // modal buttons
        // button confirm new player
        playersView.getNewPlayerModal().getConfirmButton().addActionListener(e -> confirmNewPlayerModalAction(e));
        // button cancel new player
        playersView.getNewPlayerModal().getCancelButton().addActionListener(e -> cancelNewPlayerModalAction(e));
        
    }

    private void loadListPlayers() {
        Map<String, PlayersProfileModel> players = playersModel.getPlayers();

        // save all profiles
        for (Map.Entry<String, PlayersProfileModel> entry : players.entrySet()) {
            listPlayers.addItem(entry.getValue());
        }
    }

    public void goBackAction() {
        mainView.setCurrentView(menuView);
        playersModel.save();
    }

    public void showNewPlayerModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }

    public void confirmNewPlayerModalAction(ActionEvent e) {
        String playerName = playersView.getNewPlayerModal().getTextField().getText();
        PlayersProfileModel newPlayer = new PlayersProfileModel(playerName);
        playersModel.addPlayer(playerName, newPlayer);
        listPlayers.addItem(newPlayer);
        playersModel.notifyObservers(e);
    }
    
    public void cancelNewPlayerModalAction(ActionEvent e) {
        playersModel.notifyObservers(e);
    }
    
    public void deleteAction(ActionEvent e) {
        if (listPlayers.getSelectedIndex() != -1) {
            playersModel.setCurrentProfile(null);
            playersModel.removePlayer(((PlayersProfileModel) listPlayers.getSelectedValue()).getName());
            playersModel.notifyObservers(e);
        }
    }

    public void loadAction(ListSelectionEvent e) {

        if (listPlayers.getSelectedIndex() != -1) {
            playersModel.setCurrentProfile(((PlayersProfileModel) listPlayers.getSelectedValue()).getName());
            playersModel.notifyObservers(e);
        }
    }
}
