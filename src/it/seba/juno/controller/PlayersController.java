package it.seba.juno.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.seba.juno.model.PlayerProfileModel;
import it.seba.juno.model.PlayersModel;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.PlayersView;
import it.seba.juno.view.component.ListPlayers;

public class PlayersController {

    MainView mainView;
    MenuView menuView;
    PlayersView playersView;
    ListPlayers<PlayerProfileModel> listPlayers;

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
        listPlayers.addItem(new PlayerProfileModel("Sebastian"));
        listPlayers.addItem(new PlayerProfileModel("Paolo"));

        listPlayers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && listPlayers.getSelectedIndex() != -1) {
                    
                    loadAction(e, ((PlayerProfileModel) listPlayers.getSelectedValue()).getName());
                }
            }
        });

        // button back
        playersView.getButtonBack().addActionListener(e -> goBackAction());
        playersView.getButtonDelete().addActionListener(e -> deleteAction(listPlayers.getSelectedIndex()));
    }

    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }

    public void newAction() {

    }

    public void deleteAction(int index) {
        if (index != -1) {
            playersView.getListPlayers().removeItem(index);
            playersView.getListPlayers().updateUI();
        }
        playersModel.setCurrentProfile(null);
    }

    public void loadAction(ListSelectionEvent e, String name) {
        playersModel.setCurrentProfile(name);
        playersModel.notifyObservers(e);
    }
}
