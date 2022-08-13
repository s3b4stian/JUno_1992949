package it.seba.juno.controller;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.manger.SerializationManager;
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
        
        
        //listPlayers.addItem(new PlayersProfileModel("Sebastian"));
        //listPlayers.addItem(new PlayersProfileModel("Test Profile"));

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

        SerializationManager sm = SerializationManager.getInstance();
        Map<String, PlayersProfileModel> players = playersModel.getPlayers();

        // save all profiles
        for (Map.Entry<String, PlayersProfileModel> entry : players.entrySet()) {
            sm.savePlayers(entry.getValue());
        }

        // save current profile
        // sm.savePlayers(playersModel.getCurrentProfile());
    }

    public void newAction() {

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
