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

public class PlayerPanel extends JLayeredPane {

    private static final long serialVersionUID = 3885628453899889822L;

    private static int layer;
    
    private boolean landscape;

    private boolean currentPlayer;
    
    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public PlayerPanel(Dimension dimension, boolean landscape) {
        this.landscape = landscape;

        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);
        setVisible(true);
        setEnabled(false);
    }

    @Override
    public Component add(Component comp) {

        super.add(comp);

        moveToFront(comp);
        fitCards();

        return comp;
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
        fitCards();
    }

    private void fitCards() {

        int components = getComponentCount();
        int i = 0;
        int space = 40;

        if (components > 9) {
            // 4 + components * x - 387 = 0
            // 4 + components * x = 387
            // components * x = 387 - 4
            // components * x = 383
            // x = 383 / components
            space = Math.floorDiv(383, components);
        }

        for (Component comp : getComponents()) {

            Point point = null;

            if (landscape) {
                point = new Point(6, 4 + (i++ * space));
                comp.setBounds(point.x, point.y, 128, 85);
            } else {
                point = new Point(4 + (i++ * space), 6);
                comp.setBounds(point.x, point.y, 85, 128);
            }
        }
    }

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // white color as paint with alpha channel to 10%
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.02f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.08f)));

        if (isEnabled()) {
            g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                    new Color(1.0f, 1.0f, 1.0f, 0.5f)));
        }

        if (currentPlayer) {
            g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.0f), getWidth(), 0,
                    new Color(1.0f, 0.54f, 0.22f, 1.0f)));
        }
        
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
