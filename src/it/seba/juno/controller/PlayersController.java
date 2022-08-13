package it.seba.juno.controller;

import java.awt.event.ActionEvent;

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
        listPlayers.addItem(new PlayersProfileModel("Sebastian"));
        listPlayers.addItem(new PlayersProfileModel("Paolo"));

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

    public void goBackAction() {
        mainView.setCurrentView(menuView);
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
