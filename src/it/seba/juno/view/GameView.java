package it.seba.juno.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.EventObject;

import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.JUno;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.model.GameModel;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.DiscardPileColorLabel;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.OptionsRadioPlayers;
import it.seba.juno.view.component.PlayerPanel;

public class GameView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 5048154536202885401L;

    private JButton buttonUno;
    private JLabel dealer;

    private DiscardPileColorLabel discardPileColor;

    private JLabel orderOfPlay;

    private PlayerPanel panelDeck;
    private PlayerPanel panelDiscardPile;
    private PlayerPanel panelEast;
    private PlayerPanel panelNorth;
    private PlayerPanel panelSouth;
    private PlayerPanel panelWest;

    private JLabel playerName;
    private JLabel saidUno;
    // private javax.swing.JPanel jPanel7;

    /**
     * Back button, returns to main menu view.
     */
    private MenuButton buttonBack;

    /**
     * Audio manager, to play sounds.
     */
    private AudioManager audioManager;

    private int numberOfPlayers;

    public GameView() {

        audioManager = AudioManager.getInstance();

        setBorder(new EmptyBorder(0, 0, 0, 0));

        Dimension fillerDimension = new Dimension(0, 0);

        Filler fillerInnerNorth = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerInnerSouth = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerInnerWest = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerInnerEast = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerOuterNorth = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerOtherSouth = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerOuterWest = new Filler(fillerDimension, fillerDimension, fillerDimension);
        Filler fillerOuterEast = new Filler(fillerDimension, fillerDimension, fillerDimension);

        panelWest = new PlayerPanel(new Dimension(140, 480));
        panelNorth = new PlayerPanel(new Dimension(480, 140));
        panelSouth = new PlayerPanel(new Dimension(480, 140));
        panelEast = new PlayerPanel(new Dimension(140, 480));

        panelDeck = new PlayerPanel(new Dimension(140, 200));
        panelDiscardPile = new PlayerPanel(new Dimension(140, 200));

        buttonUno = new JButton();
        discardPileColor = new DiscardPileColorLabel();

        saidUno = new JLabel();
        playerName = new JLabel();
        dealer = new JLabel();
        orderOfPlay = new JLabel();

        buttonBack = new MenuButton("Back");

        setLayout(new java.awt.GridBagLayout());

        fillerInnerNorth.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.7;
        add(fillerInnerNorth, gbc);

        fillerInnerSouth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.7;
        add(fillerInnerSouth, gbc);

        fillerInnerWest.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        add(fillerInnerWest, gbc);

        fillerInnerEast.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        add(fillerInnerEast, gbc);

        fillerOuterNorth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 15;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.4;
        add(fillerOuterNorth, gbc);

        fillerOtherSouth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 15;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.4;
        add(fillerOtherSouth, gbc);

        fillerOuterWest.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 11;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        add(fillerOuterWest, gbc);

        fillerOuterEast.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 14;
        gbc.gridy = 1;
        gbc.gridheight = 11;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        add(fillerOuterEast, gbc);

        // panel west
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelWest, gbc);

        // panel north
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelNorth, gbc);

        // panel south
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelSouth, gbc);

        // panel east
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelEast, gbc);

        // panel deck
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelDeck, gbc);

        // panel discard pile
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 5;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelDiscardPile, gbc);

        // buttons and labels
        buttonUno.setText("jButton1");
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(buttonUno, gbc);

        discardPileColor.setRed();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        add(discardPileColor, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonBack, gbc);

        saidUno.setText("jLabel2");
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 7;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        add(saidUno, gbc);

        playerName.setText("Player");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 9;
        add(playerName, gbc);

        dealer.setText("jLabel8");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 7;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        add(dealer, gbc);

        orderOfPlay.setText("jLabel8");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        add(orderOfPlay, gbc);
    }

    /**
     * Returns a reference to the "back button" of the options, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public MenuButton getButtonBack() {
        return buttonBack;
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

    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        OptionsModel optionModel = null;
        GameModel gameModel = null;

        if (o instanceof OptionsModel) {
            optionModel = (OptionsModel) o;
        } else if (o instanceof GameModel) {
            gameModel = (GameModel) o;
        }

        // update for initial state
        if (t instanceof JUno || t instanceof OptionsRadioPlayers) {
            this.numberOfPlayers = optionModel.getNumberOfPlayer();

            // System.out.println(numberOfPlayers);
        }
    }
}
