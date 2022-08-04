package it.seba.juno.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {

    private JButton jButton0;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    
    public MainMenu() {
       // java.awt.GridBagConstraints gridBagConstraints;

        //jPanel1 = new javax.swing.JPanel();
        jLabel1 = new JLabel();
        jButton0 = new JButton();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/seba/juno/images/cards/logo.png")));
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        //gbc.insets = new java.awt.Insets(0,10,10,10);
        add(jLabel1, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0,10,20,10);
        
        JPanel buttons = new JPanel(new GridBagLayout());
        
        buttons.setOpaque(false);
        
        jButton0.setText("Quick Play");
        jButton1.setText("Play");
        jButton2.setText("Players");
        jButton3.setText("Options");
        jButton4.setText("Exit");

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        
        buttons.add(jButton0, gbc);
        buttons.add(jButton1, gbc);
        buttons.add(jButton2, gbc);
        buttons.add(jButton3, gbc);
        buttons.add(jButton4, gbc);

        gbc.weighty = 0;
        add(buttons, gbc);    

    }
    
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // diagonal gradient
        GradientPaint g = new GradientPaint(0, getHeight(), Color.decode("#607123"), getWidth(), 0, Color.decode("#95B54C"));
        g2.setPaint(g);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

}