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

public class ListPlayersItem extends JPanel {

    private static final long serialVersionUID = 1L;

    private boolean selected;
    private boolean over;

    private JLabel label;

    public ListPlayersItem(String text) {

        label = new JLabel(text);
        label.setOpaque(false);
        label.setFont(FontManager.getInstance().getCustomFont(24f));
        label.setForeground(new Color(255, 255, 255));
        label.setIcon(new ImageIcon(getClass().getResource("/images/icons/list-item.png")));
        
        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(label, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                );

        setOpaque(false);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (selected) {
                g2.setColor(new Color(255, 255, 255, 60));
                label.setIcon(new ImageIcon(getClass().getResource("/images/icons/list-item-selected.png")));
            } else {
                g2.setColor(new Color(255, 255, 255, 20));
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        super.paintComponent(grphcs);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
}
