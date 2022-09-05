package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLayeredPane;

/**
 * The panel that hosts label or buttons used to show cards for the players.
 * 
 * @author Sebastian Rapetti.
 *
 */
public class PlayerPanel extends JLayeredPane {

    private static final long serialVersionUID = 3885628453899889822L;

    /**
     * Does the card in shown in portrait or landscape mode.
     */
    private boolean landscape;

    /**
     * Does the panel hosts the card of the player that have to do a move?
     */
    private boolean currentPlayer;

    /**
     * Class Constructor.
     * 
     * @param dimension The dimension of the panel.
     * @param landscape Orientation for the cards, true landscape, false portrait.
     */
    public PlayerPanel(Dimension dimension, boolean landscape) {
        this.landscape = landscape;

        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);
        setVisible(true);
        setEnabled(false);
    }

    /**
     * Add a card to the panel.
     */
    @Override
    public Component add(Component comp) {

        super.add(comp);

        moveToFront(comp);
        fitCards();

        return comp;
    }

    /**
     * Set the positions of cards to fit arbitrary number of cards inside the panel.
     */
    private void fitCards() {

        int components = getComponentCount();
        int i = 0;
        int space = 40;

        // start to fit card after 9 cards in panel
        if (components > 9) {
            // 4 + components * x - 387 = 0
            // 4 + components * x = 387
            // components * x = 387 - 4
            // components * x = 383
            // x = 383 / components
            space = Math.floorDiv(383, components);
        }

        // change components boudaries
        for (Component comp : getComponents()) {

            Point point = null;

            // if landscape invert h and w
            if (landscape) {
                point = new Point(6, 4 + (i++ * space));
                comp.setBounds(point.x, point.y, 128, 85);
            } else {
                point = new Point(4 + (i++ * space), 6);
                comp.setBounds(point.x, point.y, 85, 128);
            }
        }
    }

    /**
     * Returns if the panel hosts the current player cards.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Draw the panel background.
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // white color as paint with alpha channel
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.02f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.08f)));

        // white color, less transparent
        if (isEnabled()) {
            g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                    new Color(1.0f, 1.0f, 1.0f, 0.5f)));
        }

        // orange color as paint
        if (currentPlayer) {
            g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.0f), getWidth(), 0,
                    new Color(1.0f, 0.54f, 0.22f, 1.0f)));
        }

        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }

    /**
     * Remove a card from this panel.
     * 
     * @param comp The component to be removed.
     */
    @Override
    public void remove(Component comp) {
        super.remove(comp);
        fitCards();
    }

    /**
     * Set the panel to hosts the current player.
     * 
     * @param currentPlayer True if the panel hosts the current player, false
     *                      otherwise.
     */
    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
