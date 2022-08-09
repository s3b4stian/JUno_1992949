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
import it.seba.juno.model.OptionsModel;
import it.seba.juno.resources.font.FontManager;
import it.seba.juno.sound.AudioManager;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.MenuButton;

public class OptionsView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 1L;

    private JRadioButton twoPlayersRadio;
    private JRadioButton threePlayersRadio;
    private JRadioButton fourPlayersRadio;

    // private JLabel optionsTitle;
    private MenuButton buttonBack;
    private JToggleButton buttonFullScreen;
    private JToggleButton buttonSound;

    private MainView mainView;

    private AudioManager audioManager;
    private FontManager fontManager;

    public OptionsView(MainView mainView) {

        this.mainView = mainView;

        audioManager = AudioManager.getInstance();
        fontManager = FontManager.getInstance();

        JLabel optionsTitle = new JLabel();
        optionsTitle.setText("Options");
        optionsTitle.setFont(fontManager.getCustomFont(72f));
        optionsTitle.setForeground(new Color(255, 255, 255));

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        add(optionsTitle, gbc);

        Font sectionLabelFont = fontManager.getCustomFont(36f);

        JLabel playersLabel = new JLabel();
        playersLabel.setText("Players");
        playersLabel.setFont(sectionLabelFont);
        playersLabel.setForeground(new Color(255, 255, 255));

        JSeparator playersSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        playersSeparator.setPreferredSize(new Dimension(400,2));
        playersSeparator.setForeground(new Color(255, 255, 255));
        
        
        JLabel screenLabel = new JLabel();
        screenLabel.setText("Screen");
        screenLabel.setFont(sectionLabelFont);
        screenLabel.setForeground(new Color(255, 255, 255));

        JLabel soundLabel = new JLabel();
        soundLabel.setText("Sound");
        soundLabel.setFont(sectionLabelFont);
        soundLabel.setForeground(new Color(255, 255, 255));

        twoPlayersRadio = new JRadioButton("Two Players");
        // twoPlayersRadio.setOpaque(false);
        // twoPlayersRadio.setActionCommand(birdString);
        twoPlayersRadio.setSelected(true);
        twoPlayersRadio.setFocusPainted(false);

        threePlayersRadio = new JRadioButton("Three Players");
        threePlayersRadio.setFocusPainted(false);
        // twoPlayersRadio.setActionCommand(birdString);
        // twoPlayersRadio.setSelected(true)

        fourPlayersRadio = new JRadioButton("Four Players");
        fourPlayersRadio.setFocusPainted(false);
        // twoPlayersRadio.setActionCommand(birdString);
        // twoPlayersRadio.setSelected(true);

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(twoPlayersRadio);
        group.add(threePlayersRadio);
        group.add(fourPlayersRadio);

        buttonBack = new MenuButton();
        buttonBack.setText("Back");
        buttonBack.setFocusPainted(false);

        buttonFullScreen = new JToggleButton();
        buttonFullScreen.setFocusPainted(false);
        buttonFullScreen.setBorderPainted(false);
        buttonFullScreen.setContentAreaFilled(false);
        buttonFullScreen.setIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/window.png")));
        buttonFullScreen.setSelectedIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/full-screen.png")));

        
        buttonSound = new JToggleButton();
        buttonSound.setFocusPainted(false);
        buttonSound.setBorderPainted(false);
        buttonSound.setContentAreaFilled(false);
        buttonSound.setIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/sound-off.png")));
        buttonSound.setSelectedIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/sound-on.png")));

        gbc.insets = new Insets(0, 10, 20, 10);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(optionsTitle, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(playersLabel, gbc);

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

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(screenLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(buttonFullScreen, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(soundLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(buttonSound, gbc);

        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(buttonBack, gbc);

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
                //buttonFullScreen.setText("Set Fullscreen");
            } else {
                buttonFullScreen.setSelected(false);
                //buttonFullScreen.
            }
                

            if (((OptionsModel) o).isSound()) {
                buttonSound.setSelected(true);
            } else {
                buttonSound.setSelected(false);
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
