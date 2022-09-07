package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * The panel used to show the deck of Uno cards in game.
 * 
 * @author Sebastian Rapatti.
 *
 */
public class DeckPanel extends JPanel {

    private static final long serialVersionUID = 8947419224558984136L;

    /**
     * Class Constructor.
     * 
     * @param dimension Dimensions of the panel.
     */
    public DeckPanel(Dimension dimension) {
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);
        setLayout(new GridBagLayout());
    }

    /**
     * Draw the panel background.
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // white color as paint with alpha channel to 10%
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(255, 255, 255, 30), getWidth(), 0,
                new Color(255, 255, 255, 50)));
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
