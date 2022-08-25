package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.BadgeLabel;
import it.seba.juno.view.component.BadgePlayed;
import it.seba.juno.view.component.BadgeWon;
import it.seba.juno.view.component.DeleteModal;
import it.seba.juno.view.component.DialogButton;
import it.seba.juno.view.component.ListPlayers;
import it.seba.juno.view.component.MainLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.NewPlayerModal;
import it.seba.juno.view.component.SectionLabel;
import it.seba.juno.view.component.SubSectionLabel;

/**
 * Players of the game, permit to manage players profiles.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayersView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 3697221185201242552L;

    /**
     * The list of players.
     */
    private ListPlayers<PlayersProfileModel> listPlayers;

    /**
     * New button, show modal to create new player.
     */
    private MenuButton buttonNew;

    /**
     * Delete button, show modal to delete new player.
     */
    private MenuButton buttonDelete;

    /**
     * Badge for played matches, green.
     */
    private BadgeLabel badgePlayedGreen;

    /**
     * Badge for played matches, bronze.
     */
    private BadgeLabel badgePlayedBronze;

    /**
     * Badge for played matches, silver.
     */
    private BadgeLabel badgePlayedSilver;

    /**
     * Badge for played matches, gold.
     */
    private BadgeLabel badgePlayedGold;

    /**
     * Badge for played matches, red.
     */
    private BadgeLabel badgePlayedRed;

    /**
     * Badge for victories, green.
     */
    private BadgeLabel badgeWonGreen;

    /**
     * Badge for victories, bronze.
     */
    private BadgeLabel badgeWonBronze;

    /**
     * Badge for victories, silver.
     */
    private BadgeLabel badgeWonSilver;

    /**
     * Badge for victories, gold.
     */
    private BadgeLabel badgeWonGold;

    /**
     * Badge for victories, red.
     */
    private BadgeLabel badgeWonRed;

    /**
     * The new player modal component.
     */
    private NewPlayerModal newPlayerModal;

    /**
     * The delete player modal component.
     */
    private DeleteModal deleteModal;

    /**
     * Back button, returns to main menu view.
     */
    private MenuButton buttonBack;

    /**
     * Class Constructor.
     */
    public PlayersView() {

        // interactive components
        listPlayers = new ListPlayers<>();

        buttonNew = new MenuButton("New");
        buttonDelete = new MenuButton("Delete");

        badgePlayedGreen = new BadgeLabel(BadgePlayed.GREEN);
        badgePlayedBronze = new BadgeLabel(BadgePlayed.BRONZE);
        badgePlayedSilver = new BadgeLabel(BadgePlayed.SILVER);
        badgePlayedGold = new BadgeLabel(BadgePlayed.GOLD);
        badgePlayedRed = new BadgeLabel(BadgePlayed.RED);

        badgeWonGreen = new BadgeLabel(BadgeWon.GREEN);
        badgeWonBronze = new BadgeLabel(BadgeWon.BRONZE);
        badgeWonSilver = new BadgeLabel(BadgeWon.SILVER);
        badgeWonGold = new BadgeLabel(BadgeWon.GOLD);
        badgeWonRed = new BadgeLabel(BadgeWon.RED);

        newPlayerModal = new NewPlayerModal();
        deleteModal = new DeleteModal();

        buttonBack = new MenuButton("Back");

        // layout
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 20, 10);

        // non interactive components
        // created on add call

        // add players title
        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new MainLabel("Players"), gbc);

        // add badges title
        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new SectionLabel("Profiles saved"), gbc);

        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(listPlayers, gbc);

        // add badges title
        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new SectionLabel("Badges Earned"), gbc);

        // matches played
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new SubSectionLabel("Played"), gbc);
        // matches won
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new SubSectionLabel("Victories"), gbc);

        // badges for played
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(badgePlayedGreen, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(badgePlayedBronze, gbc);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(badgePlayedSilver, gbc);
        gbc.gridx = 4;
        gbc.gridy = 5;
        add(badgePlayedGold, gbc);
        gbc.gridx = 5;
        gbc.gridy = 5;
        add(badgePlayedRed, gbc);

        // badges for won
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(badgeWonGreen, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(badgeWonBronze, gbc);
        gbc.gridx = 3;
        gbc.gridy = 6;
        add(badgeWonSilver, gbc);
        gbc.gridx = 4;
        gbc.gridy = 6;
        add(badgeWonGold, gbc);
        gbc.gridx = 5;
        gbc.gridy = 6;
        add(badgeWonRed, gbc);

        // ad modal to set new player name
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(newPlayerModal, gbc);
        add(deleteModal, gbc);

        // add button to return to main menu
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(buttonBack, gbc);

        // new player button
        gbc.gridx = 4;
        gbc.gridy = 7;
        add(buttonNew, gbc);

        // delete player button
        gbc.gridx = 5;
        gbc.gridy = 7;
        add(buttonDelete, gbc);
    }

    /**
     * Returns a reference to the list of the players, used mainly to set the action
     * performed on the elements of the list. The action is assigned to every
     * element at controller level.
     * 
     * @return the list reference.
     */
    public ListPlayers<PlayersProfileModel> getListPlayers() {
        return listPlayers;
    }

    /**
     * Returns a reference to the "new button" of the players, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public MenuButton getButtonNew() {
        return buttonNew;
    }

    /**
     * Returns a reference to the "delete button" of the players, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public MenuButton getButtonDelete() {
        return buttonDelete;
    }

    /**
     * Returns a reference to the "back button" of the players, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public MenuButton getButtonBack() {
        return buttonBack;
    }

    /**
     * Returns a reference to the "new player custom JPanel" of the players, used
     * mainly to set the action performed from the buttons inside this panel. The
     * action is assigned to the buttons at controller level.
     * 
     * @return the custom JPanel reference.
     */
    public NewPlayerModal getNewPlayerModal() {
        return newPlayerModal;
    }

    /**
     * Returns a reference to the "delete player custom JPanel" of the players, used
     * mainly to set the action performed from the buttons inside this panel. The
     * action is assigned to the buttons at controller level.
     * 
     * @return the custom JPanel reference.
     */
    public DeleteModal getDeleteModal() {
        return deleteModal;
    }

    /**
     * Draw the panel background as diagonal gradient.
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // diagonal gradient as paint
        g2.setPaint(new GradientPaint(0, getHeight(), Color.decode("#009FFD"), getWidth(), 0, Color.decode("#2A2A72")));
        // fill the panel
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintChildren(grphcs);
    }

    /**
     * Enable played badges.
     * 
     * @param played the number of played matches.
     */
    private void enbalePlayedBadges(int played) {
        if (played >= 10) {
            badgePlayedGreen.setEnabled(true);
        }
        if (played >= 20) {
            badgePlayedBronze.setEnabled(true);
        }
        if (played >= 40) {
            badgePlayedSilver.setEnabled(true);
        }
        if (played >= 80) {
            badgePlayedGold.setEnabled(true);
        }
        if (played >= 160) {
            badgePlayedRed.setEnabled(true);
        }
    }

    /**
     * Disable all played badges.
     */
    private void disablePlayedBadges() {
        badgePlayedGreen.setEnabled(false);
        badgePlayedBronze.setEnabled(false);
        badgePlayedSilver.setEnabled(false);
        badgePlayedGold.setEnabled(false);
        badgePlayedRed.setEnabled(false);
    }

    /**
     * Enable won badges.
     * 
     * @param won the number of won matches.
     */
    private void enbaleWonBadges(int won) {
        if (won >= 10) {
            badgeWonGreen.setEnabled(true);
        }
        if (won >= 20) {
            badgeWonBronze.setEnabled(true);
        }
        if (won >= 40) {
            badgeWonSilver.setEnabled(true);
        }
        if (won >= 80) {
            badgeWonGold.setEnabled(true);
        }
        if (won >= 160) {
            badgeWonRed.setEnabled(true);
        }
    }

    /**
     * Disable all won badges.
     */
    private void disableWonBadges() {
        badgeWonGreen.setEnabled(false);
        badgeWonBronze.setEnabled(false);
        badgeWonSilver.setEnabled(false);
        badgeWonGold.setEnabled(false);
        badgeWonRed.setEnabled(false);
    }

    /**
     * Delete a player from the JList of players.
     */
    private void deletePlayer() {
        int index = listPlayers.getSelectedIndex();

        if (index != -1) {
            listPlayers.removeItem(index);
            listPlayers.updateUI();

            disablePlayedBadges();
            disableWonBadges();
        }
    }

    /**
     * Update this view when the model had a change.
     * 
     * @param o the observable that changed his state.
     * @param e the event object that triggered the change.
     */
    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        if (t instanceof ListPlayers) {

            // get the current selected player
            PlayersProfileModel current = (PlayersProfileModel) listPlayers.getSelectedValue();

            // reset all badges
            disablePlayedBadges();
            disableWonBadges();

            // enable badges based on player statistics
            enbalePlayedBadges(current.getPlayed());
            enbaleWonBadges(current.getWon());
        }

        if (t instanceof MenuButton) {
            if (t == buttonDelete) {
                // if there is a valid selection in JList
                if (listPlayers.getSelectedIndex() != -1) {
                    // disable new player modal
                    newPlayerModal.setVisible(false);
                    // make the delete modal visible and valid for the selected player
                    deleteModal.getTextLabel()
                            .setText("Delete " + listPlayers.getSelectedValue().getName() + " Profile?");
                    deleteModal.setVisible(true);
                }
            }

            if (t == buttonNew) {
                // disable delete modal
                deleteModal.setVisible(false);
                // enable new player modal
                newPlayerModal.setVisible(true);
            }
        }

        if (t instanceof DialogButton) {
            // reset new player modal, the pressed button doesn't matter
            if (t == newPlayerModal.getConfirmButton() || t == newPlayerModal.getCancelButton()) {
                newPlayerModal.setVisible(false);
                newPlayerModal.getTextField().setText("Player Name");
            }

            // hide delete modal when the cancel button is pressed
            if (t == deleteModal.getCancelButton()) {
                deleteModal.setVisible(false);
            }

            // delete the player from the JList and hide delete modal when confirm button is
            // pressed
            if (t == deleteModal.getConfirmButton()) {
                deletePlayer();
                deleteModal.setVisible(false);
            }
        }
    }
}
