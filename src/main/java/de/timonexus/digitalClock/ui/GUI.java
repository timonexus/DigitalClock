
package de.timonexus.digitalClock.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUI extends JFrame implements UserInterfacesClock {

    // attributes
    private final JLabel label;

    // constructor
    public GUI() {
        this.label = new JLabel("", SwingConstants.CENTER);

        initializeFrame();
        initializeComponents();

        this.setVisible(true);
    }

    // initializes the JFrame
    private void initializeFrame() {
        this.setTitle("Digital Clock");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBackground(Color.BLACK);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
    }

    // initializes the components
    private void initializeComponents() {
        this.label.setForeground(Color.WHITE);
        this.label.setFont(new Font("Arial", Font.BOLD, 120));

        this.add(this.label, BorderLayout.CENTER);
    }

    @Override
    public void setTime(String nTime) {
        this.label.setText(nTime);
    }

    @Override 
    public String getTime(){
        return this.label.getText();
    }

    @Override
    public void refresh() {
        this.label.repaint();
    }
}
