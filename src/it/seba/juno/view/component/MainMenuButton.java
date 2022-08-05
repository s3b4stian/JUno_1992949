package it.seba.juno.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import it.seba.juno.sound.AudioManager;

public class MainMenuButton extends JButton {

    private static final long serialVersionUID = 1L;

    private AudioManager audioManager;
    private ButtonStyle style = ButtonStyle.DESTRUCTIVE;
    private ButtonColor currentStyle = new ButtonColor(ButtonStyle.DESTRUCTIVE);
    // private int round = 10;

    /*
     * public ButtonStyle getStyle() { return style; }
     */

    /*
     * public void setStyle(ButtonStyle style) { if (this.style != style) {
     * this.style = style; currentStyle.changeStyle(style);
     * setForeground(style.foreground); } }
     */

    /*
     * public int getRound() { return round; }
     */

    /*
     * public void setRound(int round) { this.round = round; repaint(); }
     */

    public MainMenuButton(AudioManager am) {

        audioManager = am;

        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 32, 8, 32));
        setForeground(Color.WHITE);
        setFocusPainted(false);

        try {
            setFont(Font
                    .createFont(Font.TRUETYPE_FONT,
                            getClass().getResourceAsStream("/it/seba/juno/resources/font/agency-fb.ttf"))
                    .deriveFont(22f));
        } catch (FontFormatException | IOException e) {

            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                currentStyle.backgroundHover = style.backgroundHover;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                currentStyle.backgroundHover = style.background;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                currentStyle.background = style.backgroundPress;
                audioManager.playSoundEffect("click");
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                currentStyle.background = style.background;
                repaint();
            }
        });
    }

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
        g2.setColor(currentStyle.background);
        g2.fill(area);

        // rounded rectangle 3px less higher
        area.subtract(new Area(new RoundRectangle2D.Double(x, y, width, height - 3, 10, 10)));
        // visible only when mouse is over
        g2.setColor(currentStyle.backgroundHover);

        g2.fill(area);
        g2.dispose();

        super.paintComponent(grphcs);
    }

    public enum ButtonStyle {
        // PRIMARY(new Color(0, 172, 126), new Color(238, 238, 238), new Color(2, 111,
        // 82), new Color(4, 205, 151)),
        // SECONDARY(new Color(203, 209, 219), new Color(58, 70, 81), new Color(81, 92,
        // 108), new Color(230, 239, 255)),
        DESTRUCTIVE(new Color(255, 138, 48), new Color(238, 238, 238), new Color(198, 86, 0), new Color(255, 161, 90));

        private ButtonStyle(Color background, Color foreground, Color backgroundHover, Color backgroundPress) {
            this.background = background;
            this.foreground = foreground;
            this.backgroundHover = backgroundHover;
            this.backgroundPress = backgroundPress;
        }

        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;
    }

    protected class ButtonColor {

        /*
         * public Color getBackground() { return background; }
         * 
         * public void setBackground(Color background) { this.background = background; }
         * 
         * public Color getForeground() { return foreground; }
         * 
         * public void setForeground(Color foreground) { this.foreground = foreground; }
         * 
         * public Color getBackgroundHover() { return backgroundHover; }
         * 
         * public void setBackgroundHover(Color backgroundHover) { this.backgroundHover
         * = backgroundHover; }
         * 
         * public Color getBackgroundPress() { return backgroundPress; }
         * 
         * public void setBackgroundPress(Color backgroundPress) { this.backgroundPress
         * = backgroundPress; }
         */

        public ButtonColor(ButtonStyle style) {
            changeStyle(style);
        }

        public ButtonColor() {
        }

        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;

        private void changeStyle(ButtonStyle style) {
            this.background = style.background;
            this.foreground = style.foreground;
            this.backgroundHover = style.background;
            this.backgroundPress = style.backgroundPress;
        }
    }
}
