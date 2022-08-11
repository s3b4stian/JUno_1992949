package it.seba.juno.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.EventObject;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.seba.juno.JUno;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.MainLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.OptionsButtonFullScreen;
import it.seba.juno.view.component.OptionsButtonSound;
import it.seba.juno.view.component.OptionsRadioFourPlayers;
import it.seba.juno.view.component.OptionsRadioThreePlayers;
import it.seba.juno.view.component.OptionsRadioTwoPlayers;
import it.seba.juno.view.component.SectionLabel;

public class OptionsView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 1L;

    private JRadioButton twoPlayersRadio;
    private JRadioButton threePlayersRadio;
    private JRadioButton fourPlayersRadio;

    private JToggleButton buttonFullScreen;
    private JToggleButton buttonSound;

    private MenuButton buttonBack;

    private MainView mainView;

    private AudioManager audioManager;

    public OptionsView(MainView mainView) {

        this.mainView = mainView;

        audioManager = AudioManager.getInstance();

        // interactive components
        twoPlayersRadio = new OptionsRadioTwoPlayers();
        threePlayersRadio = new OptionsRadioThreePlayers();
        fourPlayersRadio = new OptionsRadioFourPlayers();

        // group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(twoPlayersRadio);
        group.add(threePlayersRadio);
        group.add(fourPlayersRadio);

        buttonFullScreen = new OptionsButtonFullScreen();
        buttonSound = new OptionsButtonSound();
        buttonBack = new MenuButton("Back");
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 20, 10);

        // non interactive components
        // created on add call
        
        // add option title
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new MainLabel("Options"), gbc);

        // add players label
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new SectionLabel("Players"), gbc);

        // add radio button for players
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(twoPlayersRadio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(threePlayersRadio, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(fourPlayersRadio, gbc);

        // add screen label
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new SectionLabel("Screen"), gbc);

        // add button for fullscreen/window
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(buttonFullScreen, gbc);

        // add sound label
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new SectionLabel("Sound"), gbc);

        // add button to enable/disable sound
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(buttonSound, gbc);

        // add butto to return to main menu
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(buttonBack, gbc);
    }

    public JRadioButton getTwoPlayersRadio() {
        return twoPlayersRadio;
    }

    public JRadioButton getThreePlayersRadio() {
        return threePlayersRadio;
    }
    
    public JRadioButton getFourPlayersRadio() {
        return fourPlayersRadio;
    }

    public MainView getMainView() {
        return mainView;
    }
    
    public MenuButton getButtonBack() {
        return buttonBack;
    }

    public JToggleButton getButtonFullScreen() {
        return buttonFullScreen;
    }

    public JToggleButton getButtonSound() {
        return buttonSound;
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

        Object t = e.getSource();

        // update for initial state
        if (t instanceof JUno) {
            if (((OptionsModel) o).isFullScreen()) {
                buttonFullScreen.setSelected(true);
            }

            if (((OptionsModel) o).isSound()) {
                buttonSound.setSelected(true);
            }

            switch (((OptionsModel) o).getNumberOfPlayer()) {
            case 2:
                twoPlayersRadio.setSelected(true);
                break;
            case 3:
                threePlayersRadio.setSelected(true);
                break;
            case 4:
                fourPlayersRadio.setSelected(true);
                break;
            }
        }

        // update for user clicks
        if (t instanceof JToggleButton) {
            if (t == buttonFullScreen) {
                if (((OptionsModel) o).isFullScreen()) {
                    mainView.setFullScreen();
                } else {
                    mainView.setWindow();
                }
            }

            if (t == buttonSound) {
                if (((OptionsModel) o).isSound()) {
                    audioManager.setSound(true);
                } else {
                    audioManager.setSound(false);
                }
            }
        }
    }
}
