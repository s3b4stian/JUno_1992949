package it.seba.juno.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main extends JFrame {

    private JFrame frame;
    
    public Main() {
        frame = new JFrame("JUno");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
