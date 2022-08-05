package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.sound.AudioManager;
import it.seba.juno.view.component.MainMenuButton;

public class MainMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    private MainMenuButton buttonQuick;
    private MainMenuButton buttonCarrer;
    private MainMenuButton buttonPlayers;
    private MainMenuButton buttonOptions;
    private MainMenuButton buttonExit;
    private JLabel welcomeBanner;

    public MainMenu(AudioManager am) {

        welcomeBanner = new JLabel();
        buttonQuick = new MainMenuButton(am);
        buttonCarrer = new MainMenuButton(am);
        buttonPlayers = new MainMenuButton(am);
        buttonOptions = new MainMenuButton(am);
        buttonExit = new MainMenuButton(am);

        welcomeBanner.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/resources/images/cards/logo.png")));

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        add(welcomeBanner, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 20, 10);

        JPanel buttons = new JPanel(new GridBagLayout());

        buttons.setOpaque(false);

        buttonQuick.setText("Quick Play");
        buttonCarrer.setText("Tournament");
        buttonPlayers.setText("Players");
        buttonOptions.setText("Options");
        buttonExit.setText("Exit");

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        buttons.add(buttonQuick, gbc);
        buttons.add(buttonCarrer, gbc);
        buttons.add(buttonPlayers, gbc);
        buttons.add(buttonOptions, gbc);
        buttons.add(buttonExit, gbc);

        gbc.weighty = 0;
        add(buttons, gbc);

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