package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class NewPlayerModal extends JPanel {

    private static final long serialVersionUID = 1L;

    private TextFieldNewPlayerModal textField;
    private DialogButton confirmButton;
    private DialogButton cancelButton;
    
    public NewPlayerModal() {
        super();
        
        textField = new TextFieldNewPlayerModal();
        confirmButton = new DialogButton(DialogButton.DialogButtonType.CONFIRM);
        cancelButton = new DialogButton(DialogButton.DialogButtonType.CANCEL);
        
        setLayout(new FlowLayout());
        add(textField);
        add(confirmButton);
        add(cancelButton);
        setBorder(null);
        setOpaque(false);
        setVisible(false);
    }
    
    public TextFieldNewPlayerModal getTextField() {
        return textField;
    }

    public DialogButton getConfirmButton() {
        return confirmButton;
    }

    public DialogButton getCancelButton() {
        return cancelButton;
    }

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // diagonal gradient as paint
        g2.setPaint(new GradientPaint(0, getHeight(), new Color(1.0f, 1.0f, 1.0f, 0.1f), getWidth(), 0, new Color(1.0f, 1.0f, 1.0f, 0.3f)));
        // fill the panel
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintChildren(grphcs);
    }
}
