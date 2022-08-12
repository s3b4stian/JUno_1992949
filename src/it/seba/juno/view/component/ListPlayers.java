package it.seba.juno.view.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import it.seba.juno.manger.FontManager;
import it.seba.juno.model.PlayerProfileModel;

public class ListPlayers<E extends Object> extends JList<E> {

    private static final long serialVersionUID = 1L;

    private int selectedIndex = -1;
    private int overIndex = -1;

    private final DefaultListModel<PlayerProfileModel> model;

    @SuppressWarnings("unchecked")
    public ListPlayers() {
        model = new DefaultListModel<PlayerProfileModel>();
        setModel((ListModel<E>) model);
        setFont(FontManager.getInstance().getCustomFont(22f));
        setPreferredSize(new Dimension(400, 200));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    selectedIndex = locationToIndex(me.getPoint());
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                overIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int index = locationToIndex(me.getPoint());
                if (index != overIndex) {
                    overIndex = index;
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected,
                    boolean focus) {

                ListPlayersItem item = new ListPlayersItem(((PlayerProfileModel) o).getName());
                item.setSelected(selectedIndex == index);
                item.setOver(overIndex == index);

                return item;
            }
        };
    }

    public void addItem(PlayerProfileModel element) {
        model.addElement(element);
    }

    public void removeItem(int index) {
        model.remove(index);

        clearSelection();
        selectedIndex = -1;
        overIndex = -1;
    }
}
