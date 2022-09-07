package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * The modal dialog used to insert a new player to the game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class NewPlayerModal extends JPanel {

    private static final long serialVersionUID = -2682933143203224792L;

    /**
     * Text field where to type the new player name.
     */
    private TextFieldNewPlayerModal textField;

    /**
     * The confirm button to delete the player.
     */
    private DialogButton confirmButton;

    /**
     * The cancel button to cancel the action.
     */
    private DialogButton cancelButton;

    /**
     * Class Constructor.
     */
    public NewPlayerModal() {
        super();

        // internal components
        textField = new TextFieldNewPlayerModal();
        confirmButton = new DialogButton(DialogButton.DialogButtonType.CONFIRM);
        cancelButton = new DialogButton(DialogButton.DialogButtonType.CANCEL);

        setLayout(new FlowLayout());

        // add components
        add(textField);
        add(confirmButton);
        add(cancelButton);

        // set JPanel properties
        setBorder(null);
        setOpaque(false);
        setVisible(false);
    }

    /**
     * Returns a reference to the "text field" of the modal, used to get the name of
     * the new player before adding it to game.
     * 
     * @return The button reference.
     */
    public TextFieldNewPlayerModal getTextField() {
        return textField;
    }

    /**
     * Returns a reference to the "confirm button" of the modal, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public DialogButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * Returns a reference to the "cancel button" of the modal, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return The button reference.
     */
    public DialogButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Draw the panel background as white color with alpha.
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // white color as paint with alpha channel to 10%
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(255, 255, 255, 10), getWidth(), 0,
                new Color(255, 255, 255, 30)));
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
