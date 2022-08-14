package it.seba.juno.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;
import it.seba.juno.model.PlayersProfileModel;
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

    private ListPlayers<PlayersProfileModel> listPlayers;

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

    public ListPlayers<PlayersProfileModel> getListPlayers() {
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

        /*t = new JTextField("enter the text", 24);
        //t.setVisible(false);
        
        buttonNew.addActionListener(e -> {t.setVisible(true);t.repaint();});
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(t, gbc);*/
        
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

        
        JTextField textField = new JTextField("Player Name",16);
        textField.setFont(FontManager.getInstance().getCustomFont(22f));
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(new Color(255,255,255));
        
        internalFrame = new JPanel() {
            @Override
            protected void paintChildren(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // diagonal gradient as paint
                g2.setPaint(new GradientPaint(0, getHeight(), Color.decode("#009FFD"), getWidth(), 0, Color.decode("#2A2A72")));
                // fill the panel
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                //g2.fillRect(0, 0, getWidth(), getHeight());

                super.paintChildren(grphcs);
            }
        };
        
        //internalFrame.setSize(new Dimension(200,100));
        internalFrame.repaint();
        internalFrame.setLayout(new FlowLayout());
        internalFrame.add(textField);
        internalFrame.add(new JButton("V"));
        internalFrame.add(new JButton("X"));
        internalFrame.setBorder(null);
        //((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
        //internalFrame.pack();
        internalFrame.setOpaque(false);
        //internalFrame.setBackground(new Color(255,255,255));
        internalFrame.setVisible(false);
        
        
        
        // add button to return to main menu
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(internalFrame, gbc);
        gbc.gridwidth = 1;
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

    public JPanel internalFrame;
    
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

    private void disablePlayedBadges() {
        badgePlayedGreen.setEnabled(false);
        badgePlayedBronze.setEnabled(false);
        badgePlayedSilver.setEnabled(false);
        badgePlayedGold.setEnabled(false);
        badgePlayedRed.setEnabled(false);
    }

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

    private void disableWonBadges() {
        badgeWonGreen.setEnabled(false);
        badgeWonBronze.setEnabled(false);
        badgeWonSilver.setEnabled(false);
        badgeWonGold.setEnabled(false);
        badgeWonRed.setEnabled(false);
    }

    private void deletePlayer() {
        int index = listPlayers.getSelectedIndex();

        if (index != -1) {
            listPlayers.removeItem(index);
            listPlayers.updateUI();

            disablePlayedBadges();
            disableWonBadges();
        }
    }

    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        if (t instanceof ListPlayers) {

            PlayersProfileModel current = (PlayersProfileModel) listPlayers.getSelectedValue();

            enbalePlayedBadges(current.getPlayed());
            enbaleWonBadges(current.getWon());
        }

        if (t instanceof MenuButton) {
            if (t == buttonDelete) {
                deletePlayer();
            }
        }
    }
}
