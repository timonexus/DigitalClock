
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

    // Constants
    private static final Color BACKGROUND_COLOR = new Color(40, 44, 59);
    private static final Color PRIMARY_COLOR = Color.WHITE;
    private static final Color SECONDARY_COLOR = new Color(170, 180, 200);

    private static final Font TIME_FONT = new Font("Segoe UI", Font.PLAIN, 120);
    private static final Font DATE_FONT = new Font("Segoe UI", Font.PLAIN, 24);

    // Attributes
    private final JPanel mainPanel;
    private final JLabel timeLabel;
    private final JLabel dateLabel;

    // Constructor
    public GUI() {
        this.mainPanel = new JPanel();
        this.timeLabel = new JLabel("--:--:--", SwingConstants.CENTER);
        this.dateLabel = new JLabel("", SwingConstants.CENTER);

        initializeFrame();
        initializeComponents();

        this.setVisible(true);
    }

    // Initializes the JFrame
    private void initializeFrame() {
        this.setTitle("Digital Clock");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
    }

    // Initializes the components
    private void initializeComponents() {
        this.mainPanel.setBackground(BACKGROUND_COLOR);
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));

        // Time label
        this.timeLabel.setForeground(PRIMARY_COLOR);
        this.timeLabel.setFont(TIME_FONT);
        this.timeLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Date label
        this.dateLabel.setForeground(SECONDARY_COLOR);
        this.dateLabel.setFont(DATE_FONT);
        this.dateLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Layout
        this.mainPanel.add(Box.createVerticalGlue());
        this.mainPanel.add(this.timeLabel);
        this.mainPanel.add(Box.createVerticalStrut(20));
        this.mainPanel.add(this.dateLabel);
        this.mainPanel.add(Box.createVerticalGlue());

        this.add(this.mainPanel, BorderLayout.CENTER);
    }

    // Updates the displayed time
    @Override
    public void setTime(String nTime) {
        this.timeLabel.setText(nTime);
    }

    // Returns the displayed time
    @Override
    public String getTime() {
        return this.timeLabel.getText();
    }

    // Updates the displayed date
    @Override
    public void setDate(String nDate) {
        this.dateLabel.setText(nDate);
    }

    @Override 
    public String getDate(){
        return this.dateLabel.getText();
    }

    // Refreshes the GUI
    @Override
    public void refresh() {
        this.repaint();
    }
}
