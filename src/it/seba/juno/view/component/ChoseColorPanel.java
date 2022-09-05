package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * The panel that hosts buttons used to chose the new color for the discard
 * pile.
 * 
 * @author Sebastian Rapetti.
 *
 */
public class ChoseColorPanel extends JPanel {

    private static final long serialVersionUID = -6017090134356797809L;

    /**
     * Button to chose color red.
     */
    private ChoseColorButton buttonRed;

    /**
     * Button to chose color blue.
     */
    private ChoseColorButton buttonBlue;

    /**
     * Button to chose color green.
     */
    private ChoseColorButton buttonGreen;

    /**
     * Button to chose color yellow.
     */
    private ChoseColorButton buttonYellow;

    /**
     * Class Constructor.
     */
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

    /**
     * Disable all buttons inside the panel.
     */
    public void disableButtons() {
        buttonRed.setEnabled(false);
        buttonBlue.setEnabled(false);
        buttonGreen.setEnabled(false);
        buttonYellow.setEnabled(false);
    }

    /**
     * Enable all buttons inside the panel.
     */
    public void enableButtons() {
        buttonRed.setEnabled(true);
        buttonBlue.setEnabled(true);
        buttonGreen.setEnabled(true);
        buttonYellow.setEnabled(true);
    }

    /**
     * Returns a reference to the "blue button" of the color panel, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public ChoseColorButton getButtonBlue() {
        return buttonBlue;
    }

    /**
     * Returns a reference to the "green button" of the color panel, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public ChoseColorButton getButtonGreen() {
        return buttonGreen;
    }

    /**
     * Returns a reference to the "red button" of the color panel, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public ChoseColorButton getButtonRed() {
        return buttonRed;
    }

    /**
     * Returns a reference to the "yellow button" of the color panel, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public ChoseColorButton getButtonYellow() {
        return buttonYellow;
    }

    /**
     * Draw the panel background.
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // white color as paint with alpha channel
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.5f)));

        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
