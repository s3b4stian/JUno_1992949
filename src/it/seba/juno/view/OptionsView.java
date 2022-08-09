package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import it.seba.juno.JUno;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.sound.AudioManager;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.MenuButton;

public class OptionsView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 1L;

    private JLabel welcomeBanner;
    private MenuButton buttonBack;
    private JToggleButton buttonFullScreen;
    private JToggleButton buttonSound;
    private MainView mainView;

    private AudioManager audioManager;

    public OptionsView(AudioManager am, MainView mainView) {

        this.audioManager = am;

        this.mainView = mainView;

        welcomeBanner = new JLabel();
        welcomeBanner.setText("Options");

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        add(welcomeBanner, gbc);

        buttonBack = new MenuButton(am);
        buttonBack.setText("Back");

        // buttonFullScreen = new MenuButton(am);
        // buttonFullScreen.setText("Full Screen");

        // buttonWindows = new MenuButton(am);
        // buttonWindows.setText("Windows");

        buttonFullScreen = new JToggleButton("Full Screen");
        buttonSound = new JToggleButton("Audio");

        // buttonFullScreen.setSelected(true);

        /*
         * buttonCarrer = new MainMenuButton(am); buttonPlayers = new
         * MainMenuButton(am); buttonOptions = new MainMenuButton(am); buttonExit = new
         * MainMenuButton(am);
         */

        /*
         * welcomeBanner.setIcon( new javax.swing.ImageIcon(getClass().getResource(
         * "/it/seba/juno/resources/images/cards/logo.png")));
         */
        gbc.insets = new java.awt.Insets(0, 10, 20, 10);

        // GridBagConstraints gbc = new GridBagConstraints();

        /*
         * gbc.gridwidth = GridBagConstraints.REMAINDER; gbc.anchor =
         * GridBagConstraints.CENTER;
         */

        add(welcomeBanner, gbc);
        add(buttonBack, gbc);
        add(buttonFullScreen, gbc);
        add(buttonSound, gbc);
        // add(buttonWindows, gbc);
        // add(fullScreenButton, gbc);
        // buttons.add(buttonBack, gbc);

        /*
         * gbc.weighty = 0; add(buttons, gbc);
         */
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

        if (t instanceof JUno) {
            if (((OptionsModel) o).isFullScreen()) {
                buttonFullScreen.setSelected(true);
            }

            if (((OptionsModel) o).isSound()) {
                buttonSound.setSelected(true);
            }
        }

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
