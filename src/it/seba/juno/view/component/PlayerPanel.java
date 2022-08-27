package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

    private static final long serialVersionUID = 3885628453899889822L;

    public PlayerPanel(Dimension dimension) {
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);
    }

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // white color as paint with alpha channel to 10%
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.2f)));
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
