package it.seba.juno.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The main view of the application, provide a way to change the current view,
 * to resize and to go full-screen or to window.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Default graphics device, used to switch from window to full-screen mode.
     */
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    /**
     * The main panel, host all other views.
     */
    private JPanel panel;

    /**
     * Class Constructor.
     * 
     * @param isFullScreen Define if the application must start in full-screen mode
     *                     (true) or in windows mode (false).
     */
    public MainView(boolean isFullScreen) {
        // parent constructor
        super("JUno");

        // init main panel
        // it will contains all other game views
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setOpaque(false);
        panel.setLayout(new java.awt.BorderLayout());

        // default behavior on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // full screen or no from options
        if (isFullScreen) {
            setFullScreen();
        } else {
            setWindow();
        }

        // set the system icon of the program
        setIconImage(new ImageIcon(getClass().getResource("/images/cards/deck_1.png")).getImage());

        setLocationRelativeTo(null);
        // current panel is a void panel, every view is loaded inside the private JPanel
        // of this class by the proper controller.
        add(panel);
    }

    /**
     * Change the current view.
     * 
     * @param component the component will be the new view.
     */
    public void setCurrentView(JComponent component) {
        panel.removeAll();
        panel.add(component);
        panel.repaint();
        panel.revalidate();
    }

    /**
     * Set the application in full-screen mode.
     */
    public void setFullScreen() {
        device.setFullScreenWindow(this);
    }

    /**
     * Set the application in window mode.
     */
    public void setWindow() {
        device.setFullScreenWindow(null);
        setSize(1152, 864);
        setMinimumSize(new Dimension(1152, 864));
    }
}
