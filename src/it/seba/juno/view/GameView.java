package it.seba.juno.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.EventObject;

import javax.swing.Box.Filler;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.event.ChangeOrderOfPlayEvent;
import it.seba.juno.event.CurrentPlayerChangeColorEvent;
import it.seba.juno.event.CurrentPlayerDrawFourCardEvent;
import it.seba.juno.event.CurrentPlayerDrawOneCardAndDropEvent;
import it.seba.juno.event.CurrentPlayerDrawOneCardEvent;
import it.seba.juno.event.CurrentPlayerDrawTwoCardEvent;
import it.seba.juno.event.CurrentPlayerDropEvent;
import it.seba.juno.event.CurrentPlayerHaveOneCardEvent;
import it.seba.juno.event.CurrentPlayerMoveEvent;
import it.seba.juno.event.CurrentPlayerSkipEvent;
import it.seba.juno.event.CurrentPlayerWinnerEvent;
import it.seba.juno.event.EnableChoseColorPanelEvent;
import it.seba.juno.event.FirstEnableChoseColorPanelEvent;
import it.seba.juno.event.FirstHumanPlayerChoseColorEvent;
import it.seba.juno.event.FirstLoadEvent;
import it.seba.juno.event.HumanPlayerChoseColorEvent;
import it.seba.juno.event.HumanPlayerDrawOneCardEvent;
import it.seba.juno.event.HumanPlayerDropEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.event.StartGameEvent;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.model.GameModel;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.observer.InterfaceObserver;
import it.seba.juno.observer.Observable;
import it.seba.juno.player.Player;
import it.seba.juno.player.UnoPlayers;
import it.seba.juno.view.component.ChoseColorPanel;
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
import it.seba.juno.view.component.listener.DiscardPileListener;

/**
 * The game table, permit to play uno game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class GameView extends JPanel implements InterfaceObserver {

    private static final long serialVersionUID = 5048154536202885401L;

    /**
     * Time for timed events
     */
    private static int time;

    /**
     * Returns the time to use for the next event.
     * 
     * @return Time, not increased.
     */
    public static int getTime() {
        return GameView.time;
    }

    /**
     * Returns the time to use for the next event.
     * 
     * @return Time increased of 50 milliseconds.
     */
    public static int getTimeFast() {
        GameView.time += 50;
        return GameView.time;
    }

    /**
     * Returns the time to use for the next event.
     * 
     * @return Time increased of 100 milliseconds.
     */
    public static int getTimeNormal() {
        GameView.time += 100;
        return GameView.time;
    }

    /**
     * Returns the time to use for the next event.
     * 
     * @return Time increased of 200 milliseconds.
     */
    public static int getTimeSlow() {
        GameView.time += 200;
        return GameView.time;
    }

    /**
     * Returns the time to use for the next event.
     * 
     * @return Time increased of 1500 milliseconds.
     */
    public static int getTimeTurn() {
        GameView.time += 1500;
        return GameView.time;
    }

    /**
     * Reset the timer.
     */
    public static void resetTimer() {
        GameView.time = 0;
    }

    /**
     * The dealer label, show which player is the dealer.
     */
    private DealerLabel dealer;

    /**
     * Show current discard pile color.
     */
    private DiscardPileColorLabel discardPileColor;

    /**
     * Show current discard pile card.
     */
    private DiscardPileLabel discardPile;

    /**
     * Show the order of play, clockwise or anticlockwise.
     */
    private OrderOfPlayLabel orderOfPlay;

    /**
     * The log text area in the left of the game.
     */
    private JTextArea logTextArea;

    /**
     * Scroll pane for the log text area.
     */
    private JScrollPane logTextAreaScrollPane;

    /**
     * The deck of the cards at the center of the table.
     */
    private DeckPanel panelDeck;

    /**
     * The discard pile for cards at the center of the table.
     */
    private DeckPanel panelDiscardPile;

    /**
     * Player panel, ncp.
     */
    private PlayerPanel panelEast;

    /**
     * Player panel, ncp.
     */
    private PlayerPanel panelNorth;

    /**
     * Player panel, human.
     */
    private PlayerPanel panelSouth;

    /**
     * Player panel, ncp.
     */
    private PlayerPanel panelWest;

    /**
     * Panel to chose the color for discard pile, for human player.
     */
    private ChoseColorPanel panelChoseColor;

    /**
     * Player name label displayer above the player panel.
     */
    private PlayerLabel playerName;

    /**
     * Said uno label, show which player said uno.
     */
    private SaidUnoLabel saidUno;

    /**
     * The button in deck panel to draw a card for the human player.
     */
    private DeckButton buttonDeck;

    /**
     * The button uno on the right of the south panel, used by human to say uno.
     */
    private MenuButton buttonUno;

    /**
     * Start the game.
     */
    private MenuButton buttonStart;

    /**
     * Restart the game.
     */
    private MenuButton buttonRestart;

    /**
     * Back button, returns to main menu view.
     */
    private MenuButton buttonBack;

    /**
     * Class Constructor.
     */
    public GameView() {

        // audioManager = AudioManager.getInstance();

        setBorder(new EmptyBorder(0, 0, 0, 0));

        Dimension fillerDimension = new Dimension(0, 0);

        // fillers
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

        // interactive components
        panelWest = new PlayerPanel(panelVertical, true);
        panelNorth = new PlayerPanel(panelHorizontal, false);
        panelSouth = new PlayerPanel(panelHorizontal, false);
        panelEast = new PlayerPanel(panelVertical, true);

        panelDeck = new DeckPanel(new Dimension(136, 171));
        panelDiscardPile = new DeckPanel(new Dimension(136, 171));

        panelChoseColor = new ChoseColorPanel();

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

        logTextAreaScrollPane = new JScrollPane();
        logTextAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        logTextAreaScrollPane.setBorder(null);
        logTextAreaScrollPane.setHorizontalScrollBar(null);
        logTextAreaScrollPane.setOpaque(false);
        logTextAreaScrollPane.setPreferredSize(new Dimension(0, 0));
        logTextAreaScrollPane.setRequestFocusEnabled(false);
        logTextAreaScrollPane.setAutoscrolls(true);
        logTextAreaScrollPane.getViewport().setOpaque(false);

        logTextArea = new JTextArea();
        logTextArea.setColumns(20);
        logTextArea.setRows(20);
        logTextArea.setTabSize(4);
        logTextArea.setBorder(null);
        logTextArea.setFocusable(false);
        logTextArea.setOpaque(false);
        logTextArea.setRequestFocusEnabled(false);
        logTextArea.setForeground(Color.WHITE);
        logTextArea.setLineWrap(true);
        logTextArea.setAutoscrolls(true);
        logTextAreaScrollPane.setViewportView(logTextArea);

        setLayout(new GridBagLayout());

        // inner filler north
        fillerInnerNorth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.9;
        add(fillerInnerNorth, gbc);

        // inner filler south
        fillerInnerSouth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.9;
        add(fillerInnerSouth, gbc);

        // inner filler west
        fillerInnerWest.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        add(fillerInnerWest, gbc);

        // inner filler east
        fillerInnerEast.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        add(fillerInnerEast, gbc);

        // outer filler north
        fillerOuterNorth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 15;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.4;
        add(fillerOuterNorth, gbc);

        // outer filler south
        fillerOtherSouth.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 15;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.4;
        add(fillerOtherSouth, gbc);

        // outer filler west
        fillerOuterWest.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        add(fillerOuterWest, gbc);

        // outer filler east
        fillerOuterEast.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 14;
        gbc.gridy = 1;
        gbc.gridheight = 11;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        add(fillerOuterEast, gbc);

        // scroll pane for the left text area, game log
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(logTextAreaScrollPane, gbc);

        // panel west
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 7;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelWest, gbc);

        // panel north
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelNorth, gbc);

        // panel south
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 9;
        gbc.gridheight = 2;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelSouth, gbc);

        // panel east
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 7;
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

        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panelChoseColor, gbc);

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

    /**
     * Draw cards of the player on deal at the start of the game, human player
     * version.
     * 
     * @param p     The player for which draw cards.
     * @param panel The panel where add cards
     */
    private void dealCards(Player p, PlayerPanel panel) {
        for (UnoCard c : p.getCards()) {
            (new DealCardListener(panel, new PlayerCardButton(c))).startTimer();
        }
    }

    /**
     * Audio manager, to play sounds.
     */
    // private AudioManager audioManager;

    /**
     * Draw cards of the player on deal at the start of the game, npc player
     * version.
     * 
     * @param p     The player for which draw cards.
     * @param panel The panel where add cards
     * @param label The type of card to draw, need to select the card with the
     *              correct orientation.
     */
    private void dealCards(Player p, PlayerPanel panel, PlayerCardLabel.PlayerCardLabelType label) {
        for (UnoCard c : p.getCards()) {
            (new DealCardListener(panel, new PlayerCardLabel(c, label))).startTimer();
        }
    }

    /**
     * Draw cards of the player on deal at the start of the game, generic version.
     *
     * @param players The list of game players.
     * @param topCard The top card to draw for the discard pile.
     */
    private void dealCards(UnoPlayers players, UnoCard topCard) {

        players.forEach(p -> {
            switch (p.getName()) {
            case "Human":
                dealCards(p, panelSouth);
                break;
            case "NPC-East":
                dealCards(p, panelEast, PlayerCardLabel.PlayerCardLabelType.EAST);
                break;
            case "NPC-North":
                dealCards(p, panelNorth, PlayerCardLabel.PlayerCardLabelType.NORTH);
                break;
            case "NPC-West":
                dealCards(p, panelWest, PlayerCardLabel.PlayerCardLabelType.WEST);
                break;
            }
        });

        // make discard pile visible once cards dealt
        (new DiscardPileListener(this, topCard)).startTimer();
    }

    /**
     * Disable all player panels.
     */
    private void disablePlayerPanel() {
        panelSouth.setEnabled(false);
        panelEast.setEnabled(false);
        panelNorth.setEnabled(false);
        panelWest.setEnabled(false);
    }

    /**
     * Enable player panels
     * 
     * @param numberOfPlayers The number of players for the current match.
     */
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
     * Returns a reference to the "deck button" of the options, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public DeckButton getButtonDeck() {
        return buttonDeck;
    }

    /**
     * Returns a reference to the "restart button" of the game, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonRestart() {
        return buttonRestart;
    }

    /**
     * Returns a reference to the "start button" of the game, used mainly to set the
     * action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonStart() {
        return buttonStart;
    }

    /**
     * Returns a reference to the "say UNO button" of the game, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonUno() {
        return buttonUno;
    }

    /**
     * Returns a reference to the "panel" of the colors, used mainly to set the
     * action performed from the buttons inside the panel. The action is assigned to
     * the button at controller level.
     * 
     * @return The panel reference.
     */
    public ChoseColorPanel getPanelChoseColor() {
        return panelChoseColor;
    }

    /**
     * Return the human player panel.
     * 
     * @return Reference to the human player panel.
     */
    public PlayerPanel getPanelSouth() {
        return panelSouth;
    }

    /**
     * Append a text to the left text area.
     * 
     * @param Text the text to append
     */
    private void log(String text) {
        logTextArea.append(text + "\n");
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
     * Manage the label for show when a player said Uno.
     * 
     * @param p The player that said Uno.
     */
    private void playerSaidUno(Player p) {

        switch (p.getName()) {
        case "NPC-East":
            saidUno.setSaidEast();
            AudioManager.getInstance().playSoundEffect("uno-east");
            break;
        case "NPC-North":
            saidUno.setSaidNorth();
            AudioManager.getInstance().playSoundEffect("uno-north");
            break;
        case "NPC-West":
            saidUno.setSaidWest();
            AudioManager.getInstance().playSoundEffect("uno-west");
            break;
        }
    }

    /**
     * Redraw all cards in the player panel, general version, this method use
     * overloading to draw cards for all players.
     * 
     * @param p The player for which redraw cards.
     */
    private void repaintNpcCards(Player p) {

        switch (p.getName()) {
        case "Human":
            repaintNpcCards(p, panelSouth);
            break;
        case "NPC-East":
            repaintNpcCards(p, panelEast, PlayerCardLabel.PlayerCardLabelType.EAST);
            break;
        case "NPC-North":
            repaintNpcCards(p, panelNorth, PlayerCardLabel.PlayerCardLabelType.NORTH);
            break;
        case "NPC-West":
            repaintNpcCards(p, panelWest, PlayerCardLabel.PlayerCardLabelType.WEST);
            break;
        }
    }

    /**
     * Redraw all cards in the player panel, human player version.
     * 
     * @param p     The player for which redraw cards.
     * @param panel The panel where add cards, panel always south panel, this
     *              parameter will be omitted in future.
     */
    private void repaintNpcCards(Player p, PlayerPanel panel) {
        panel.removeAll();
        for (UnoCard c : p.getCards()) {
            panel.add(new PlayerCardButton(c));
        }

    }

    /**
     * Redraw all cards in the player panel, npc player version.
     * 
     * @param p     The player for which redraw cards.
     * @param panel The panel where add cards
     * @param label The type of card to draw, need to select the card with the
     *              correct orientation.
     */
    private void repaintNpcCards(Player p, PlayerPanel panel, PlayerCardLabel.PlayerCardLabelType label) {
        panel.removeAll();
        for (UnoCard c : p.getCards()) {
            panel.add(new PlayerCardLabel(c, label));
        }
    }

    /**
     * Repaint all card panels.
     */
    private void repaintPlayerPanels() {
        panelNorth.repaint();
        panelSouth.repaint();
        panelEast.repaint();
        panelWest.repaint();
    }

    /**
     * Reset the color of the card panel for all players
     */
    private void resetCurrentPlayer() {
        panelSouth.setCurrentPlayer(false);
        panelEast.setCurrentPlayer(false);
        panelNorth.setCurrentPlayer(false);
        panelWest.setCurrentPlayer(false);
    }

    /**
     * Set the color of the card panel for the player that have to move.
     * 
     * @param p The player will move into the turn.
     */
    private void setCurrentPlayer(Player p) {

        resetCurrentPlayer();

        switch (p.getName()) {
        case "Human":
            panelSouth.setCurrentPlayer(true);
            break;
        case "NPC-East":
            panelEast.setCurrentPlayer(true);
            break;
        case "NPC-North":
            panelNorth.setCurrentPlayer(true);
            break;
        case "NPC-West":
            panelWest.setCurrentPlayer(true);
            break;
        }

        repaintPlayerPanels();
    }

    /**
     * Set the dealer label for the game.
     * 
     * @param d The number of the player which will be dealer for the match.
     */
    private void setDealer(int d) {

        switch (d) {
        case 0:
            dealer.setDealerSouth();
            break;
        case 1:
            dealer.setDealerWest();
            break;
        case 2:
            dealer.setDealerNorth();
            break;
        case 3:
            dealer.setDealerEast();
            break;
        }
    }

    /**
     * Draw the current discard pile card.
     * 
     * @param topCard The card to draw to discard pile.
     */
    public void setDiscardPile(UnoCard topCard) {
        if (topCard.hasColor()) {
            setDiscardPileColor(topCard.getColor());
        }

        discardPile.setCard(topCard);
    }

    /**
     * Change the discard pile color.
     * 
     * @param color The new color for the discard pile.
     */
    public void setDiscardPileColor(UnoColor color) {
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

    /**
     * Set the color of the card panel for the player that will move.
     * 
     * @param p The player will move into the next turn.
     */
    private void setNextPlayer(Player p) {

        resetCurrentPlayer();

        switch (p.getName()) {
        case "Human":
            panelSouth.setCurrentPlayer(true);
            break;
        case "NPC-East":
            panelEast.setCurrentPlayer(true);
            break;
        case "NPC-North":
            panelNorth.setCurrentPlayer(true);
            break;
        case "NPC-West":
            panelWest.setCurrentPlayer(true);
            break;
        }

        repaintPlayerPanels();
    }

    /**
     * Update this view when the model had a change, this method in this class is
     * too long, need to find a solution to split the code.
     * 
     * @param o The observable that changed his state.
     * @param e The event object that triggered the change.
     */
    @Override
    public void update(Observable o, EventObject e) {

        Object t = e.getSource();

        OptionsModel optionModel = null;
        GameModel gameModel = null;

        // reset the game for a new match.
        if (e instanceof ResetGameEvent) {
            buttonUno.setVisible(false);
            buttonStart.setVisible(true);
            buttonRestart.setVisible(false);

            buttonDeck.setEnabled(false);

            panelChoseColor.disableButtons();

            discardPileColor.setEnabled(false);
            discardPile.setIcon(null);

            dealer.setDealerDisabled();

            panelNorth.removeAll();
            panelSouth.removeAll();
            panelEast.removeAll();
            panelWest.removeAll();

            logTextArea.setText("");

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
        if (e instanceof StartGameEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log("Deal Cards");

            setDealer(gameModel.getDealer());
            dealCards(gameModel.getPlayers(), gameModel.discardPileTopCard());

            buttonUno.setVisible(true);
            buttonStart.setVisible(false);
            buttonRestart.setVisible(true);
            orderOfPlay.setClockwise();

            setCurrentPlayer(currentPlayer);

            // if first player is human and cannot drop a card, enable the deck
            if (currentPlayer.isHuman() && gameModel.currentPlayerCannotDrop()) {
                buttonDeck.setEnabled(true);
            }

            log(currentPlayer.getName() + " will move first");
        }

        // player winner event
        if (e instanceof CurrentPlayerWinnerEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " is the winner");

            buttonUno.setVisible(false);
            buttonDeck.setEnabled(false);

            repaintPlayerPanels();
        }

        // change color event
        if (e instanceof CurrentPlayerChangeColorEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " changed color to " + gameModel.discardPileColor());
        }

        // draw a card and after drop it
        if (e instanceof CurrentPlayerDrawOneCardAndDropEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();
            UnoCard topCard = gameModel.discardPileTopCard();

            log(currentPlayer.getName() + " drawn 1 card [" + currentPlayer.getCardsNumber() + "] and");
            log(currentPlayer.getName() + " dropped " + topCard);

            setDiscardPile(gameModel.discardPileTopCard());
            setDiscardPileColor(gameModel.discardPileColor());

            repaintNpcCards(currentPlayer);
        }

        // draw one card
        if (e instanceof CurrentPlayerDrawOneCardEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " drawn 1 card [" + currentPlayer.getCardsNumber() + "]");

            repaintNpcCards(currentPlayer);
        }

        // draw two cards
        if (e instanceof CurrentPlayerDrawTwoCardEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " drawn 2 cards [" + currentPlayer.getCardsNumber() + "]");

            AudioManager.getInstance().playSoundEffect("card");

            repaintNpcCards(currentPlayer);
        }

        // draw four cards
        if (e instanceof CurrentPlayerDrawFourCardEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " drawn 4 cards [" + currentPlayer.getCardsNumber() + "]");

            AudioManager.getInstance().playSoundEffect("card");

            repaintNpcCards(currentPlayer);
        }

        // player skip turn
        if (e instanceof CurrentPlayerSkipEvent) {

            Player nextPlayer = gameModel.getNextPlayer();
            Player currentPlayer = gameModel.getCurrentPlayer();

            buttonDeck.setEnabled(false);

            if (!nextPlayer.isNpc()) {
                if (gameModel.nextPlayerCannotDrop()) {
                    buttonDeck.setEnabled(true);
                }
            }

            setNextPlayer(nextPlayer);
            repaintNpcCards(currentPlayer);

            log(currentPlayer.getName() + " skipped turn\n");
            log(nextPlayer.getName() + " will move");
        }

        // player drop a card to pile
        if (e instanceof CurrentPlayerDropEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " dropped " + gameModel.discardPileTopCard() + " ["
                    + currentPlayer.getCardsNumber() + "]");

            setDiscardPile(gameModel.discardPileTopCard());
            setDiscardPileColor(gameModel.discardPileColor());

            repaintNpcCards(currentPlayer);
        }

        // player has one card and say uno
        if (e instanceof CurrentPlayerHaveOneCardEvent) {
            Player currentPlayer = gameModel.getCurrentPlayer();
            playerSaidUno(currentPlayer);
        }

        // player move end actions
        if (e instanceof CurrentPlayerMoveEvent) {

            Player nextPlayer = gameModel.getNextPlayer();
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " moved\n");
            log(nextPlayer.getName() + " will move");

            buttonDeck.setEnabled(false);

            if (!nextPlayer.isNpc()) {
                if (gameModel.nextPlayerCannotDrop()) {
                    buttonDeck.setEnabled(true);
                }
            }

            AudioManager.getInstance().playSoundEffect("card");

            setNextPlayer(nextPlayer);
        }

        // chage the order of play
        if (e instanceof ChangeOrderOfPlayEvent) {

            Player nextPlayer = gameModel.getNextPlayer();

            if (gameModel.getOrderOfPlay()) {
                log("Order of Play Clockwise");
                orderOfPlay.setClockwise();
            } else {
                orderOfPlay.setAnticlockwise();
                log("Order of Play Antilockwise");
            }

            log(nextPlayer.getName() + " will move");
        }

        // human player dropped a card
        if (e instanceof HumanPlayerDropEvent) {

            Player nextPlayer = gameModel.getNextPlayer();
            Player currentPlayer = gameModel.getCurrentPlayer();

            panelSouth.remove(((PlayerCardButton) t));
            panelSouth.repaint();

            setDiscardPile(gameModel.discardPileTopCard());
            setDiscardPileColor(gameModel.discardPileColor());

            log(currentPlayer.getName() + " dropped " + gameModel.discardPileTopCard());
            log(currentPlayer.getName() + " moved\n");
            log(nextPlayer.getName() + " will move");

            AudioManager.getInstance().playSoundEffect("card");

            setNextPlayer(nextPlayer);
        }

        // human player draws one card
        if (e instanceof HumanPlayerDrawOneCardEvent) {
            Player nextPlayer = gameModel.getNextPlayer();
            Player currentPlayer = gameModel.getCurrentPlayer();

            log(currentPlayer.getName() + " drawn 1 card [" + currentPlayer.getCardsNumber() + "]");

            buttonDeck.setEnabled(false);
            repaintNpcCards(currentPlayer);

            AudioManager.getInstance().playSoundEffect("card");

            if (gameModel.currentPlayerCannotDrop()) {

                log(currentPlayer.getName() + " moved\n");
                log(nextPlayer.getName() + " will move");

                setNextPlayer(nextPlayer);
            }
        }

        // enable the color panel, wild as first card in pile, human first to move
        if (e instanceof FirstEnableChoseColorPanelEvent) {
            panelChoseColor.enableButtons();
        }

        // enable the color panel, after wild card drop
        if (e instanceof EnableChoseColorPanelEvent) {
            panelSouth.remove(((PlayerCardButton) t));
            panelSouth.repaint();

            setDiscardPile(gameModel.discardPileTopCard());

            AudioManager.getInstance().playSoundEffect("card");

            panelChoseColor.enableButtons();
        }

        // human player selected new color for discard pile
        // if first card is wild and he is the first to move
        if (e instanceof FirstHumanPlayerChoseColorEvent) {

            setDiscardPileColor(gameModel.discardPileColor());

            log(gameModel.getCurrentPlayer() + " changed color to " + gameModel.discardPileColor());

            panelChoseColor.disableButtons();

        }

        // human player selected new color for discard pile
        if (e instanceof HumanPlayerChoseColorEvent) {

            setDiscardPileColor(gameModel.discardPileColor());

            log(gameModel.getCurrentPlayer().getName() + " changed color to " + gameModel.discardPileColor());

            panelChoseColor.disableButtons();

            setNextPlayer(gameModel.getNextPlayer());
        }

        // update for initial state
        if (e instanceof FirstLoadEvent || t instanceof OptionsRadioPlayers) {
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

            saidUno.setSaidSouth();
            AudioManager.getInstance().playSoundEffect("uno-south");
        }
    }
}
