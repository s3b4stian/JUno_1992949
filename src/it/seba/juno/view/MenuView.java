package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import it.seba.juno.view.component.MenuButton;

public class MenuView extends JPanel {

    private static final long serialVersionUID = 1L;

    private MenuButton buttonQuick;
    private MenuButton buttonCarrer;
    private MenuButton buttonPlayers;
    private MenuButton buttonOptions;
    private MenuButton buttonExit;
    //private JLabel welcomeBanner;

    public MenuView() {

        // interactive components
        buttonQuick = new MenuButton("Quick Play");
        buttonCarrer = new MenuButton("Tournament");
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
        buttons.add(buttonQuick, gbc);
        buttons.add(buttonCarrer, gbc);
        buttons.add(buttonPlayers, gbc);
        buttons.add(buttonOptions, gbc);
        buttons.add(buttonExit, gbc);

        gbc.weighty = 0;
        add(buttons, gbc);
    }

    public MenuButton getButtonQuick() {
        return buttonQuick;
    }

    public MenuButton getButtonCarrer() {
        return buttonCarrer;
    }

    public MenuButton getButtonPlayers() {
        return buttonPlayers;
    }

    public MenuButton getButtonOptions() {
        return buttonOptions;
    }

    public MenuButton getButtonExit() {
        return buttonExit;
    }

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