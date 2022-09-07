package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.seba.juno.manger.FontManager;

/**
 * Item for the list of players.
 * 
 * @author Sebastian Rapetti
 *
 */
public class ListPlayersItem extends JPanel {

    private static final long serialVersionUID = -7248602033791667284L;

    /**
     * Indicates if element is selected, true yes, false no.
     */
    private boolean selected;

    /**
     * Indicates if the element have the mouse over, true yes, false no.
     */
    private boolean over;

    /**
     * The label that show the element name into the list.
     */
    private JLabel label;

    /**
     * Class constructor.
     * 
     * @param text The name of the element into the list.
     */
    public ListPlayersItem(String text) {

        // internal component
        label = new JLabel(text);
        label.setOpaque(false);
        label.setFont(FontManager.getInstance().getCustomFont(24f));
        label.setForeground(Color.WHITE);
        label.setIcon(new ImageIcon(getClass().getResource("/images/icons/list-item.png")));

        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);
        // layout properties
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(label)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label,
                GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE));

        // add component
        add(label);

        // JPanel properties
        setOpaque(false);
    }

    /**
     * Draw the panel background as white color with alpha, the alfa channel is
     * different if the element of the list is selected.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // color for mouse over
            g2.setColor(new Color(255, 255, 255, 20));

            if (selected) {
                // color for a selected element
                g2.setColor(new Color(255, 255, 255, 60));
                // change the icon of the label to more visibility
                label.setIcon(new ImageIcon(getClass().getResource("/images/icons/list-item-selected.png")));
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        super.paintComponent(grphcs);
    }

    /**
     * Warn the element of the list that the mouse is over it.
     * 
     * @param over True if mouse is over, false otherwise.
     */
    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * Set the element of the list selected.
     * 
     * @param selected True if selected, false otherwise.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
