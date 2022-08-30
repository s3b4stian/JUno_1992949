package it.seba.juno.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.Box.Filler;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import it.seba.juno.JUno;
import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.controller.GameController;
import it.seba.juno.event.NextPlayerEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.model.GameModel;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.NpcPlayer;
import it.seba.juno.player.Player;
import it.seba.juno.player.UnoPlayers;
import it.seba.juno.util.InterfaceObserver;
import it.seba.juno.util.Observable;
import it.seba.juno.view.component.DealerLabel;
import it.seba.juno.view.component.DeckButton;
import it.seba.juno.view.component.DeckPanel;
import it.seba.juno.view.component.DiscardPileColorLabel;
import it.seba.juno.view.component.DiscardPileLabel;
import it.seba.juno.view.component.ListPlayers;
import it.seba.juno.view.component.MenuButton;
import it.seba.juno.view.component.OptionsRadioPlayers;
import it.seba.juno.view.component.OrderOfPlayLabel;
import it.seba.juno.view.component.PlayerCardButton;
import it.seba.juno.view.component.PlayerCardLabel;
import it.seba.juno.view.component.PlayerLabel;
import it.seba.juno.view.component.PlayerPanel;
import it.seba.juno.view.component.SaidUnoLabel;
import it.seba.juno.view.component.listener.DealCardListener;

public class GameView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 5048154536202885401L;

    private DealerLabel dealer;

    private DiscardPileColorLabel discardPileColor;
    private DiscardPileLabel discardPile;

    private OrderOfPlayLabel orderOfPlay;

    private DeckPanel panelDeck;
    private DeckPanel panelDiscardPile;

    private PlayerPanel panelEast;
    private PlayerPanel panelNorth;
    private PlayerPanel panelSouth;

    private PlayerPanel panelWest;

    private PlayerLabel playerName;
    private SaidUnoLabel saidUno;

    private DeckButton buttonDeck;
    private MenuButton buttonUno;
    private MenuButton buttonStart;
    private MenuButton buttonRestart;

    private MenuButton buttonNext;

    public MenuButton getButtonNext() {
        return buttonNext;
    }

    public MenuButton getButtonRestart() {
        return buttonRestart;
    }

    /**
     * Back button, returns to main menu view.
     */
    private MenuButton buttonBack;

    /**
     * Audio manager, to play sounds.
     */
    private AudioManager audioManager;

    // private int numberOfPlayers;

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

        Dimension panelHorizontal = new Dimension(480, 140);
        Dimension panelVertical = new Dimension(140, 480);

        panelWest = new PlayerPanel(panelVertical, true);
        panelNorth = new PlayerPanel(panelHorizontal, false);
        panelSouth = new PlayerPanel(panelHorizontal, false);
        panelEast = new PlayerPanel(panelVertical, true);

        panelDeck = new DeckPanel(new Dimension(136, 171));
        panelDiscardPile = new DeckPanel(new Dimension(136, 171));

        buttonDeck = new DeckButton();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDeck.add(buttonDeck, gbc);

        discardPile = new DiscardPileLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDiscardPile.add(discardPile, gbc);

        buttonUno = new MenuButton("Uno");
        buttonUno.setVisible(false);
        discardPileColor = new DiscardPileColorLabel();

        saidUno = new SaidUnoLabel();
        playerName = new PlayerLabel();
        dealer = new DealerLabel();
        orderOfPlay = new OrderOfPlayLabel();

        buttonBack = new MenuButton("Back");
        buttonStart = new MenuButton("Start");
        buttonRestart = new MenuButton("Restart");
        buttonNext = new MenuButton("Move");

        setLayout(new java.awt.GridBagLayout());

        fillerInnerNorth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.9;
        add(fillerInnerNorth, gbc);

        fillerInnerSouth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.9;
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
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelWest, gbc);

        // panel north
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelNorth, gbc);

        // panel south
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelSouth, gbc);

        // panel east
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 7;
        // gbc.fill = GridBagConstraints.BOTH;
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
        // uno button
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(buttonUno, gbc);

        // discard pile color
        // discardPileColor.setRed();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        add(discardPileColor, gbc);

        // button start
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonStart, gbc);
        add(buttonRestart, gbc);

        // button next
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonNext, gbc);

        // button back
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonBack, gbc);

        // said uno label
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 7;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        gbc.weighty = 0.1;
        add(saidUno, gbc);

        // player name label
        playerName.setText("Player");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 9;
        add(playerName, gbc);

        // dealer icon
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 7;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        gbc.weighty = 0.1;
        add(dealer, gbc);

        // order of play icon
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0.1;
        gbc.weighty = 0.1;
        add(orderOfPlay, gbc);
    }

    public MenuButton getButtonStart() {
        return buttonStart;
    }

    public PlayerPanel getPanelSouth() {
        return panelSouth;
    }

    /**
     * Returns a reference to the "said uno button" of the options, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return the button reference.
     */
    public MenuButton getButtonUno() {
        return buttonUno;
    }

    /**
     * Returns a reference to the "deck button" of the options, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public DeckButton getButtonDeck() {
        return buttonDeck;
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

    private void disablePlayerPanel() {
        panelSouth.setEnabled(false);
        panelEast.setEnabled(false);
        panelNorth.setEnabled(false);
        panelWest.setEnabled(false);
    }

    private void enablePlayerPanel(int numberOfPlayers) {
        panelSouth.setEnabled(true);
        panelWest.setEnabled(true);

        if (numberOfPlayers == 3) {
            this.panelNorth.setEnabled(true);
        }

        if (numberOfPlayers == 4) {
            this.panelNorth.setEnabled(true);
            this.panelEast.setEnabled(true);
        }
    }

    private void setDiscardPile(UnoCard topCard) {
        if (topCard.hasColor()) {
            setDiscardPileColor(topCard.getColor());
        }

        discardPile.setCard(topCard);
    }

    private void setDiscardPileColor(UnoColor color) {
        discardPileColor.setEnabled(true);
        switch (color) {
        case BLUE:
            discardPileColor.setBlue();
            break;
        case RED:
            discardPileColor.setRed();
            break;
        case GREEN:
            discardPileColor.setGreen();
            break;
        case YELLOW:
            discardPileColor.setYellow();
            break;
        }
    }

    private void setDealer(int d) {

        switch (d) {
        case 0:
            dealer.setDealerSouth();
            break;
        case 3:
            dealer.setDealerEast();
            break;
        case 2:
            dealer.setDealerNorth();
            break;
        case 1:
            dealer.setDealerWest();
            break;
        }
    }

    private void repaintNpcCards(Player p) {

        if (p.getName().equals("Human")) {
            panelSouth.removeAll();
            for (UnoCard c : p.getCards()) {
                panelSouth.add(new PlayerCardButton(c));
            }
        }

        if (p.getName().equals("NPC-East")) {
            panelEast.removeAll();
            for (UnoCard c : p.getCards()) {
                panelEast.add(new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.EAST));
            }
        }

        if (p.getName().equals("NPC-North")) {
            panelNorth.removeAll();

            for (UnoCard c : p.getCards()) {
                panelNorth.add(new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.NORTH));
            }
        }

        if (p.getName().equals("NPC-West")) {
            panelWest.removeAll();
            for (UnoCard c : p.getCards()) {
                panelWest.add(new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.WEST));
            }
        }
    }

    private void dealCards(UnoPlayers players) {
        System.out.println("Deal Cards");
        players.forEach(p -> {
            for (UnoCard c : p.getCards()) {

                if (p instanceof HumanPlayer) {
                    (new DealCardListener(panelSouth, new PlayerCardButton(c))).startTimer();
                }

                if (p instanceof NpcPlayer) {
                    if (p.getName().equals("NPC-East")) {
                        (new DealCardListener(panelEast,
                                new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.EAST))).startTimer();
                    }

                    if (p.getName().equals("NPC-North")) {
                        (new DealCardListener(panelNorth,
                                new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.NORTH))).startTimer();
                    }

                    if (p.getName().equals("NPC-West")) {
                        (new DealCardListener(panelWest,
                                new PlayerCardLabel(c, PlayerCardLabel.PlayerCardLabelType.WEST))).startTimer();
                    }
                }
            }
        });

        DealCardListener.resetTimer();
    }

    private void resetCurrentPlayer() {
        panelSouth.setCurrentPlayer(false);
        panelEast.setCurrentPlayer(false);
        panelNorth.setCurrentPlayer(false);
        panelWest.setCurrentPlayer(false);
    }

    private void setCurrentPlayer(Player p) {

        resetCurrentPlayer();

        if (p instanceof HumanPlayer) {
            panelSouth.setCurrentPlayer(true);
        }

        if (p instanceof NpcPlayer) {
            if (p.getName().equals("NPC-East")) {
                panelEast.setCurrentPlayer(true);
            }

            if (p.getName().equals("NPC-North")) {
                panelNorth.setCurrentPlayer(true);
            }

            if (p.getName().equals("NPC-West")) {
                panelWest.setCurrentPlayer(true);
            }
        }

        repaintPlayerPanels();
    }

    private void setNextPlayer(Player p) {

        resetCurrentPlayer();

        if (p instanceof HumanPlayer) {
            panelSouth.setCurrentPlayer(true);
        }

        if (p instanceof NpcPlayer) {
            if (p.getName().equals("NPC-East")) {
                panelEast.setCurrentPlayer(true);
            }

            if (p.getName().equals("NPC-North")) {
                panelNorth.setCurrentPlayer(true);
            }

            if (p.getName().equals("NPC-West")) {
                panelWest.setCurrentPlayer(true);
            }
        }

        repaintPlayerPanels();
    }

    private void repaintPlayerPanels() {
        panelNorth.repaint();
        panelSouth.repaint();
        panelEast.repaint();
        panelWest.repaint();
    }

    private void playerSaidUno(Player p) {
        if (p instanceof NpcPlayer) {
            if (p.getName().equals("NPC-East")) {
                saidUno.setSaidEast();
            }

            if (p.getName().equals("NPC-North")) {
                saidUno.setSaidNorth();
            }

            if (p.getName().equals("NPC-West")) {
                saidUno.setSaidWest();
            }
        }
    }

    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        OptionsModel optionModel = null;
        GameModel gameModel = null;

        if (e instanceof ResetGameEvent) {
            buttonUno.setVisible(false);
            buttonStart.setVisible(true);
            buttonRestart.setVisible(false);

            discardPileColor.setEnabled(false);
            discardPile.setIcon(null);

            dealer.setDealerDisabled();

            panelNorth.removeAll();
            panelSouth.removeAll();
            panelEast.removeAll();
            panelWest.removeAll();

            resetCurrentPlayer();

            repaintPlayerPanels();
        }

        if (o instanceof OptionsModel) {
            optionModel = (OptionsModel) o;
        } else if (o instanceof GameModel) {
            gameModel = (GameModel) o;
        }

        // game start
        // set dealer
        // deal cards to all
        // set discard pile first card
        // ready on current player to start
        if (t instanceof MenuButton && t == buttonStart) {
            setDealer(gameModel.getDealer());
            dealCards(gameModel.getPlayers());
            setDiscardPile(gameModel.discardPileTopCard());

            buttonUno.setVisible(true);
            buttonStart.setVisible(false);
            buttonRestart.setVisible(true);

            Player currentPlayer = gameModel.getCurrentPlayer();
            setCurrentPlayer(currentPlayer);

            /*
             * if (gameModel.getOrderOfPlay()) { orderOfPlay.setClockwise(); } else {
             * orderOfPlay.setAnticlockwise(); }
             */

            // Player nextPlayer = gameModel.getNextPlayer();
            // setNextPlayer(nextPlayer);
        }

        if (e instanceof NextPlayerEvent) {

            Player nextPlayer = gameModel.getNextPlayer();
            Player currentPlayer = gameModel.getCurrentPlayer();

            setNextPlayer(nextPlayer);

            setDiscardPile(gameModel.discardPileTopCard());
            setDiscardPileColor(gameModel.discardPileColor());

            repaintNpcCards(currentPlayer);

            if (gameModel.isOneCard()) {
                playerSaidUno(currentPlayer);
            }

            /*
             * if (gameModel.getOrderOfPlay()) { orderOfPlay.setClockwise(); } else {
             * orderOfPlay.setAnticlockwise(); }
             */

            // dealCards(gameModel.getPlayers());
            // repaintPlayerPanels();
        }

        if (t instanceof PlayerCardButton) {
            Player nextPlayer = gameModel.getNextPlayer();
            setNextPlayer(nextPlayer);

            setDiscardPile(((PlayerCardButton) t).getCard());
            setDiscardPileColor(gameModel.discardPileColor());

            panelSouth.remove(((PlayerCardButton) t));
            panelSouth.repaint();

            /*
             * if (gameModel.getOrderOfPlay()) { orderOfPlay.setClockwise(); } else {
             * orderOfPlay.setAnticlockwise(); }
             */
        }

        if (t instanceof DeckButton) {
            // new PlayerCardButton(
            // panelSouth.add(new PlayerCardButton(gameModel.dealCard()));
            // setDiscardPile(gameModel.discardPileTopCard());
        }

        // update for initial state
        if (t instanceof JUno || t instanceof OptionsRadioPlayers) {
            disablePlayerPanel();
            enablePlayerPanel(optionModel.getNumberOfPlayer());

            buttonRestart.setVisible(false);
        }

        // player name
        if (t instanceof ListPlayers) {
            PlayersProfileModel model = (PlayersProfileModel) ((ListPlayers<?>) t).getSelectedValue();
            playerName.setText(model.getName());
        }

        // player clicked to uno button
        if (t instanceof MenuButton && t == buttonUno) {

            // System.out.println(gameModel.getCurrentPlayer().getName());

            saidUno.setSaidSouth();
        }
    }
}
