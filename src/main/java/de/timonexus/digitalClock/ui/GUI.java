
package de.timonexus.digitalClock.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI extends JFrame implements UserInterfacesClock {

    // attributes
    private final JPanel mainPanel;
    private final JLabel timelabel;
    private final JLabel dateLabel;

    // constructor
    public GUI() {
        this.mainPanel = new JPanel();
        this.dateLabel = new JLabel("Test", SwingConstants.CENTER);
        this.timelabel = new JLabel("", SwingConstants.CENTER);

        initializeFrame();
        initializeComponents();

        this.setVisible(true);
    }

    // initializes the JFrame
    private void initializeFrame() {
        this.setTitle("Digital Clock");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setBackground(new Color(40, 44, 59));
        this.getContentPane().setBackground(new Color(40, 44, 59));
        this.setLayout(new BorderLayout());
    }

    // initializes the components
    private void initializeComponents() {

        this.mainPanel.setBackground(new Color(40, 44, 59));
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.timelabel.setForeground(Color.WHITE);
        Font timeFont = new Font("Segoe UI", Font.PLAIN, 120);
        this.timelabel.setFont(timeFont);

        this.dateLabel.setForeground(Color.WHITE);
        Font dateFont = new Font("Segoe UI", Font.PLAIN, 24);
        this.dateLabel.setFont(dateFont);

        this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.add(Box.createHorizontalGlue());
        this.mainPanel.add(timelabel);
        this.mainPanel.add(Box.createVerticalStrut(20));
        this.mainPanel.add(dateLabel);
        this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.add(Box.createHorizontalGlue());

        this.add(this.mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void setTime(String nTime) {
        this.timelabel.setText(nTime);
    }

    @Override 
    public String getTime(){
        return this.timelabel.getText();
    }

    @Override
    public void refresh() {
        this.timelabel.repaint();
    }
}
