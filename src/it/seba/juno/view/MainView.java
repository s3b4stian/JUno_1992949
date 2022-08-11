package it.seba.juno.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private JPanel panel;

    public void setCurrentView(JComponent com) {
        panel.removeAll();
        panel.add(com);
        panel.repaint();
        panel.revalidate();
    }

    public MainView(boolean isFullScreen) {

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
        add(panel);
    }

    public void setFullScreen() {
        device.setFullScreenWindow(this);
    }

    public void setWindow() {
        device.setFullScreenWindow(null);
        setSize(1200, 800);
        setMinimumSize(new Dimension(1200, 800));
    }
}
