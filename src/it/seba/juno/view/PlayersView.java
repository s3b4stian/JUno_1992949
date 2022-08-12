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

import it.seba.juno.JUno;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.model.PlayerProfileModel;
import it.seba.juno.player.BadgePlayed;
import it.seba.juno.player.BadgeWon;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.BadgeLabel;
import it.seba.juno.view.component.ListPlayers;
import it.seba.juno.view.component.MainLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.SectionLabel;
import it.seba.juno.view.component.SubSectionLabel;

public class PlayersView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 1L;

    private ListPlayers<PlayerProfileModel> listPlayers;

    private MenuButton buttonNew;
    private MenuButton buttonDelete;

    private BadgeLabel badgePlayedGreen;

    private BadgeLabel badgePlayedBronze;
    private BadgeLabel badgePlayedSilver;
    private BadgeLabel badgePlayedGold;
    private BadgeLabel badgePlayedRed;

    private BadgeLabel badgeWonGreen;
    private BadgeLabel badgeWonBronze;
    private BadgeLabel badgeWonSilver;
    private BadgeLabel badgeWonGold;
    private BadgeLabel badgeWonRed;

    private MenuButton buttonBack;

    public ListPlayers<PlayerProfileModel> getListPlayers() {
        return listPlayers;
    }

    public MenuButton getButtonNew() {
        return buttonNew;
    }

    public MenuButton getButtonDelete() {
        return buttonDelete;
    }
    
    public MenuButton getButtonBack() {
        return buttonBack;
    }

    public BadgeLabel getBadgePlayedGreen() {
        return badgePlayedGreen;
    }

    public BadgeLabel getBadgePlayedBronze() {
        return badgePlayedBronze;
    }

    public BadgeLabel getBadgePlayedSilver() {
        return badgePlayedSilver;
    }

    public BadgeLabel getBadgePlayedGold() {
        return badgePlayedGold;
    }

    public BadgeLabel getBadgePlayedRed() {
        return badgePlayedRed;
    }

    public BadgeLabel getBadgeWonGreen() {
        return badgeWonGreen;
    }

    public BadgeLabel getBadgeWonBronze() {
        return badgeWonBronze;
    }

    public BadgeLabel getBadgeWonSilver() {
        return badgeWonSilver;
    }

    public BadgeLabel getBadgeWonGold() {
        return badgeWonGold;
    }

    public BadgeLabel getBadgeWonRed() {
        return badgeWonRed;
    }
    
    public PlayersView() {

        AudioManager.getInstance();

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
        add(new SectionLabel("Profile Badges Earned"), gbc);

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

        // add button to return to main menu
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(buttonBack, gbc);
        gbc.gridx = 4;
        gbc.gridy = 7;
        add(buttonNew, gbc);
        gbc.gridx = 5;
        gbc.gridy = 7;
        add(buttonDelete, gbc);
    }

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

    @Override
    public void update(Observable o, EventObject e) {
        // TODO Auto-generated method stub
        Object t = e.getSource();

        if (t instanceof ListPlayers) {
           
            PlayerProfileModel current = (PlayerProfileModel) listPlayers.getSelectedValue();
            int played = current.getPlayed();
            int won = current.getWon();
            
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

        // update for initial state
        System.out.println(t);
    }

}
