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

import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;
import it.seba.juno.model.PlayersProfileModel;

/**
 * The list of players in players view.
 * 
 * @author Sebastian Rapetti
 *
 * @param <E> the type of the data stored in list model
 */
public class ListPlayers<E extends Object> extends JList<E> {

    private static final long serialVersionUID = -5595187148131879403L;

    /**
     * Indicates the element currently selected.
     */
    private int selectedIndex = -1;

    /**
     * Indicate the element that have the mouse over.
     */
    private int overIndex = -1;

    /**
     * The list model.
     */
    private final DefaultListModel<PlayersProfileModel> model;

    /**
     * Class Constructor.
     */
    @SuppressWarnings("unchecked")
    public ListPlayers() {
        // initialize list model
        model = new DefaultListModel<PlayersProfileModel>();
        setModel((ListModel<E>) model);

        // set list properties
        setFont(FontManager.getInstance().getCustomFont(22f));
        setPreferredSize(new Dimension(400, 200));
        setOpaque(false);

        // adding behavior for interaction with mouse button
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                overIndex = -1;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    selectedIndex = locationToIndex(me.getPoint());
                    AudioManager.getInstance().playSoundEffect("click");
                    repaint();
                }
            }
        });

        // adding behavior for interaction with mouse movements
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

    /**
     * Add a player to list.
     * 
     * @param element The player model to be added to the list.
     */
    public void addItem(PlayersProfileModel element) {
        model.addElement(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected,
                    boolean focus) {

                ListPlayersItem item = new ListPlayersItem(((PlayersProfileModel) o).getName());
                item.setSelected(selectedIndex == index);
                item.setOver(overIndex == index);

                return item;
            }
        };
    }

    /**
     * Remove a player from the list.
     * 
     * @param index The list index of the player will be removed.
     */
    public void removeItem(int index) {
        model.remove(index);

        clearSelection();
        selectedIndex = -1;
        overIndex = -1;
    }
}
