package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;

/**
 * General button used in game views.
 *
 * @author Sebastian Rapetti
 *
 */
public class MenuButton extends JButton {

    private static final long serialVersionUID = 7781413891725517198L;

    /**
     * Background color.
     */
    private final Color background = new Color(255, 138, 48);

    /**
     * Background color on mouse over.
     */
    private final Color backgroundOver = new Color(198, 86, 0);

    /**
     * Background color on mouse click.
     */
    private final Color backgroundPress = new Color(255, 161, 90);

    /**
     * Text color of the button.
     */
    private final Color foreground = Color.WHITE;

    /**
     * Default background color.
     */
    private Color currentBackground;

    /**
     * Default background color on mouse over.
     */
    private Color currentBackgroundOver;

    /**
     * Class Constructor, default text of the button is void.
     */
    public MenuButton() {
        this("");
    }

    /**
     * Class Constructor.
     *
     * @param text the default text for the button.
     */
    public MenuButton(String text) {
        super(text);

        currentBackground = background;
        currentBackgroundOver = background;

        // set button properties
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 32, 8, 32));
        setForeground(foreground);
        setFocusPainted(false);
        setFont(FontManager.getInstance().getCustomFont(22f));

        // adding behavior for interaction with mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                currentBackgroundOver = backgroundOver;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                currentBackgroundOver = background;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                currentBackground = backgroundPress;
                AudioManager.getInstance().playSoundEffect("click");
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                currentBackground = background;
                repaint();
            }
        });
    }

    /**
     * Draw the button background.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 0;
        int y = 0;
        int width = getWidth();
        int height = getHeight();

        // rounded rectangle
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, 10, 10));
        g2.setColor(currentBackground);

        g2.fill(area);

        // 3px border for mouse over, obtained removing part of the original rectangle
        area.subtract(new Area(new RoundRectangle2D.Double(x, y, width, height - 3, 10, 10)));
        // visible only when mouse is over
        g2.setColor(currentBackgroundOver);

        g2.fill(area);
        g2.dispose();

        super.paintComponent(grphcs);
    }
}
