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

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import it.seba.juno.event.FirstLoadEvent;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.MainLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.OptionsButtonToggle;
import it.seba.juno.view.component.OptionsRadioPlayers;
import it.seba.juno.view.component.SectionLabel;

/**
 * The options of the game, permit to manage audio, screen and players for the
 * game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OptionsView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 1494283430703728423L;

    /**
     * Two players radio button, when selected the human player play against one npc
     * player.
     */
    private OptionsRadioPlayers twoPlayersRadio;

    /**
     * Three players radio button, when selected the human player play against two
     * npc player.
     */
    private OptionsRadioPlayers threePlayersRadio;

    /**
     * Four players radio button, when selected the human player play against three
     * npc player.
     */
    private OptionsRadioPlayers fourPlayersRadio;

    /**
     * Full screen toggle button, when selected the game go full-screen.
     */
    private OptionsButtonToggle buttonFullScreen;

    /**
     * Sound toggle button, when selected the sound is on.
     */
    private OptionsButtonToggle buttonSound;

    /**
     * Back button, returns to main menu view.
     */
    private MenuButton buttonBack;

    /**
     * The main view, to set screen mode.
     */
    private MainView mainView;

    /**
     * Audio manager, to play sounds.
     */
    private AudioManager audioManager;

    /**
     * Class Constructor.
     * 
     * @param mainView The main view, used to manage both full-screen and window
     *                 mode.
     */
    public OptionsView(MainView mainView) {

        this.mainView = mainView;

        audioManager = AudioManager.getInstance();

        // interactive components
        twoPlayersRadio = new OptionsRadioPlayers(OptionsRadioPlayers.OptionRadioType.TWO);
        threePlayersRadio = new OptionsRadioPlayers(OptionsRadioPlayers.OptionRadioType.THREE);
        fourPlayersRadio = new OptionsRadioPlayers(OptionsRadioPlayers.OptionRadioType.FOUR);

        // group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(twoPlayersRadio);
        group.add(threePlayersRadio);
        group.add(fourPlayersRadio);

        buttonFullScreen = new OptionsButtonToggle(OptionsButtonToggle.OptionButtonType.FULLSCREEN);
        buttonSound = new OptionsButtonToggle(OptionsButtonToggle.OptionButtonType.SOUND);
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

        // add button for full-screen/window
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

        // add button to return to main menu
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(buttonBack, gbc);
    }

    /**
     * Returns a reference to the "back button" of the options, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonBack() {
        return buttonBack;
    }

    /**
     * Returns a reference to the "full-screen/windows mode toggle button" of the
     * options, used mainly to set the action performed from the toggle button. The
     * action is assigned to the toggle button at controller level.
     * 
     * @return The toggle button reference.
     */
    public OptionsButtonToggle getButtonFullScreen() {
        return buttonFullScreen;
    }

    /**
     * Returns a reference to the "sound toggle button" of the options, used mainly
     * to set the action performed from the toggle button. The action is assigned to
     * the toggle button at controller level.
     * 
     * @return The toggle button reference.
     */
    public OptionsButtonToggle getButtonSound() {
        return buttonSound;
    }

    /**
     * Returns a reference to the "four players radio button" of the options, used
     * mainly to set the action performed from the radio button. The action is
     * assigned to the radio button at controller level.
     * 
     * @return The radio button reference.
     */
    public OptionsRadioPlayers getFourPlayersRadio() {
        return fourPlayersRadio;
    }

    /**
     * Returns a reference to the "three players radio button" of the options, used
     * mainly to set the action performed from the radio button. The action is
     * assigned to the radio button at controller level.
     * 
     * @return The radio button reference.
     */
    public OptionsRadioPlayers getThreePlayersRadio() {
        return threePlayersRadio;
    }

    /**
     * Returns a reference to the "two players radio button" of the options, used
     * mainly to set the action performed from the radio button. The action is
     * assigned to the radio button at controller level.
     * 
     * @return The radio button reference.
     */
    public OptionsRadioPlayers getTwoPlayersRadio() {
        return twoPlayersRadio;
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
     * Update this view when the model had a change.
     * 
     * @param o The observable that changed his state.
     * @param e The event object that triggered the change.
     */
    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        OptionsModel model = (OptionsModel) o;

        // update for initial state
        if (e instanceof FirstLoadEvent) {
            // set full-screen
            if (model.isFullScreen()) {
                buttonFullScreen.setSelected(true);
            }
            // set sound
            if (model.isSound()) {
                buttonSound.setSelected(true);
            }

            // set the number of players
            switch (model.getNumberOfPlayer()) {
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
                if (model.isFullScreen()) {
                    mainView.setFullScreen();
                } else {
                    mainView.setWindow();
                }
            }

            if (t == buttonSound) {
                if (model.isSound()) {
                    audioManager.setSound(true);
                } else {
                    audioManager.setSound(false);
                }
            }
        }
    }
}
