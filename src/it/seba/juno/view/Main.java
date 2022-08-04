package it.seba.juno.view;

import java.awt.Dimension;
//import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

//import javax.swing.JButton;

import javax.swing.JFrame;
//import javax.swing.JPanel;



public class Main extends JFrame {

    //private JButton button;
    //private JPanel mainMenu;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    //private Option option;
    //private PlayerProfile playerProfile;

    public Main() {
        super("JUno");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // uncomment to set full screen
        //device.setFullScreenWindow(this);

        //setSize(1024, 768);
        setMinimumSize(new Dimension(1024, 768));
        setLocationRelativeTo(null);
        
        add(new MainMenu());

        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/images/cards/deck_1.png")).getImage());
        
        
        setVisible(true);
    }
}
