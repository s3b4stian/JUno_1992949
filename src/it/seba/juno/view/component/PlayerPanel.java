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

    public PlayerPanel(Dimension dimension) {
        // setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);
        setVisible(true);
        setEnabled(false);
    }

    @Override
    public Component add(Component comp) {

        super.add(comp);
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
            Point point = new Point(4 + (i++ * space), 6);
            ((PlayerCardButton) comp).setOrigin(point);
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
            g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.3f), getWidth(), 0,
                    new Color(1.0f, 1.0f, 1.0f, 0.5f)));
        }

        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
