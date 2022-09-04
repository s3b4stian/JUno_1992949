package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.seba.juno.view.component.MenuButton;

/**
 * The main menu of the game, permit to explore all other game sections.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MenuView extends JPanel {

    private static final long serialVersionUID = 5350734895926530482L;

    /**
     * Play button, go to play a match.
     */
    private MenuButton buttonPlay;

    /**
     * Players button, go to players view.
     */
    private MenuButton buttonPlayers;

    /**
     * Options button, got to options view
     */
    private MenuButton buttonOptions;

    /**
     * Exit button, terminate the game.
     */
    private MenuButton buttonExit;

    /**
     * Class Constructor.
     */
    public MenuView() {

        // interactive components
        buttonPlay = new MenuButton("Play");
        buttonPlayers = new MenuButton("Players");
        buttonOptions = new MenuButton("Options");
        buttonExit = new MenuButton("Exit");

        setBorder(new EmptyBorder(40, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        add(new JLabel("", new ImageIcon(getClass().getResource("/images/cards/logo.png")), 0), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 20, 10);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setOpaque(false);
        buttons.add(buttonPlay, gbc);
        buttons.add(buttonPlayers, gbc);
        buttons.add(buttonOptions, gbc);
        buttons.add(buttonExit, gbc);

        gbc.weighty = 0;
        add(buttons, gbc);
    }

    /**
     * Returns a reference to the "play button" of the main menu, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonPlay() {
        return buttonPlay;
    }

    /**
     * Returns a reference to the "players button" of the main menu, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonPlayers() {
        return buttonPlayers;
    }

    /**
     * Returns a reference to the "options button" of the main menu, used mainly to
     * set the action performed from the button. The action is assigned to the
     * button at controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonOptions() {
        return buttonOptions;
    }

    /**
     * Returns a reference to the "exit button" of the main menu, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public MenuButton getButtonExit() {
        return buttonExit;
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

}