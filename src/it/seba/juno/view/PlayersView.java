package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;
import it.seba.juno.player.BadgePlayed;
import it.seba.juno.player.BadgeWon;
import it.seba.juno.view.component.BadgeLabel;
import it.seba.juno.view.component.BadgePlayedLabel;
import it.seba.juno.view.component.MainLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.SectionLabel;
import it.seba.juno.view.component.SubSectionLabel;

//import it.seba.juno.sound.AudioManager;

public class PlayersView extends JPanel {

    private static final long serialVersionUID = 1L;

    private MenuButton buttonBack;

    private AudioManager audioManager;

    //private FontManager fontManager;

    public MenuButton getButtonBack() {
        return buttonBack;
    }

    public PlayersView() {

        audioManager = AudioManager.getInstance();

        //interactive components
        //JPanel playerProfiles = new JPanel(new GridBagLayout());
        //playerProfiles.setOpaque(true);
        String[] data = {"one", "two", "three", "four"};
        JList<String> profiles = new JList<String>(data);
        
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
        add(new SectionLabel("Profile Badges Earned"), gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new SubSectionLabel("Played"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new SubSectionLabel("Victories"), gbc);
        

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(new BadgeLabel(BadgePlayed.GREEN), gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(new BadgeLabel(BadgePlayed.BRONZE), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(new BadgeLabel(BadgePlayed.SILVER), gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(new BadgeLabel(BadgePlayed.GOLD), gbc);
        gbc.gridx = 5;
        gbc.gridy = 2;
        add(new BadgeLabel(BadgePlayed.RED), gbc);
        
        
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(new BadgeLabel(BadgeWon.GREEN), gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(new BadgeLabel(BadgeWon.BRONZE), gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(new BadgeLabel(BadgeWon.SILVER), gbc);
        gbc.gridx = 4;
        gbc.gridy = 3;
        add(new BadgeLabel(BadgeWon.GOLD), gbc);
        gbc.gridx = 5;
        gbc.gridy = 3;
        add(new BadgeLabel(BadgeWon.RED), gbc);
        
        
        
        // add saved profiles title
        /*gbc.gridwidth = 3;
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(new SectionLabel("Saved Profiles"), gbc);*/
        
        
        
        
        
        
        
       /* // add jpanel with saved profiles
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(profiles, gbc);*/
        
       
        // add button to return to main menu
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridwidth = 6;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(buttonBack, gbc);

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
}
