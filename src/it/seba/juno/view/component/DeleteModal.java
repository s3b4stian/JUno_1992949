package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * The modal dialog used to confirm a player deletion.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DeleteModal extends JPanel {

    private static final long serialVersionUID = 8547066870254382150L;

    /**
     * The text label with the player name to be deleted.
     */
    private SubSectionLabel textLabel;

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
    public DeleteModal() {
        super();

        // internal components
        textLabel = new SubSectionLabel();
        confirmButton = new DialogButton(DialogButton.DialogButtonType.CONFIRM);
        cancelButton = new DialogButton(DialogButton.DialogButtonType.CANCEL);

        setLayout(new FlowLayout());

        // add components
        add(textLabel);
        add(confirmButton);
        add(cancelButton);

        // set JPanel properties
        setBorder(null);
        setOpaque(false);
        setVisible(false);
    }

    /**
     * Returns a reference to the "text label" of the modal, used to show the player
     * name before deleting.
     * 
     * @return the button reference.
     */
    public SubSectionLabel getTextLabel() {
        return textLabel;
    }

    /**
     * Returns a reference to the "confirm button" of the modal, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
     */
    public DialogButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * Returns a reference to the "cancel button" of the modal, used mainly to set
     * the action performed from the button. The action is assigned to the button at
     * controller level.
     * 
     * @return the button reference.
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
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0,
                new Color(1.0f, 1.0f, 1.0f, 0.3f)));
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
