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
import javax.swing.JPanel;

public class ChoseColorPanel extends JPanel {

    private static final long serialVersionUID = -6017090134356797809L;

    private ChoseColorButton buttonRed;

    public ChoseColorButton getButtonRed() {
        return buttonRed;
    }

    public ChoseColorButton getButtonBlue() {
        return buttonBlue;
    }

    public ChoseColorButton getButtonGreen() {
        return buttonGreen;
    }

    public ChoseColorButton getButtonYellow() {
        return buttonYellow;
    }

    private ChoseColorButton buttonBlue;
    private ChoseColorButton buttonGreen;
    private ChoseColorButton buttonYellow;

    public ChoseColorPanel() {

        setOpaque(false);
        setVisible(true);

        buttonRed = new ChoseColorButton(ChoseColorButton.ChoseButtonType.RED);
        buttonBlue = new ChoseColorButton(ChoseColorButton.ChoseButtonType.BLUE);
        buttonGreen = new ChoseColorButton(ChoseColorButton.ChoseButtonType.GREEN);
        buttonYellow = new ChoseColorButton(ChoseColorButton.ChoseButtonType.YELLOW);

        add(buttonRed);
        add(buttonBlue);
        add(buttonGreen);
        add(buttonYellow);
    }

    public void enableButtons() {
        buttonRed.setEnabled(true);
        buttonBlue.setEnabled(true);
        buttonGreen.setEnabled(true);
        buttonYellow.setEnabled(true);
    }

    public void disableButtons() {
        buttonRed.setEnabled(false);
        buttonBlue.setEnabled(false);
        buttonGreen.setEnabled(false);
        buttonYellow.setEnabled(false);
    }

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // white color as paint with alpha channel to 10%
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.5f)));

        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
