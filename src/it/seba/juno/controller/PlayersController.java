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
        playersView.getButtonNew().addActionListener(e -> newAction());
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

    public void newAction() {
        
        
        playersView.internalFrame.setVisible(true);
        
        //JInternalFrame internalFrame = new ModalDialog();

       /* internalFrame.setLayout(new FlowLayout());
        internalFrame.add(new JLabel("I am label"));
        internalFrame.add(new JButton("Oi button"));    
        internalFrame.setSize(new Dimension(400,200));
        //internalFrame.setOpaque(false);
        internalFrame.setClosable(true);*/

        //internalFrame.pack();
        //internalFrame.setVisible(true);
        //playersView.add(internalFrame);
        
        /*JDialog msg = new JDialog(mainView, true);
        
        MenuButton b = new MenuButton("close");

        b.addActionListener(e -> msg.dispose());
        
        
        msg.add(new JLabel("Test"));
        msg.add(b);
        msg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        msg.setUndecorated(true);
        msg.setLocationRelativeTo(null);
        msg.setSize(new Dimension(400,200));
        //msg.setExtendedState(JFrame.MAXIMIZED_BOTH);
        msg.setVisible(true);
        
        
        /*JOptionPane opti = new JOptionPane();
        JOptionPane.showConfirmDialog(mainView.getFrames()[0], "Do you really want to quit?", "QUIT", JOptionPane.YES_NO_OPTION , 
                JOptionPane.QUESTION_MESSAGE);
        opti.requestFocusInWindow();*/
       
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
