
package de.timonexus.digitalClock.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CUITest {

    private CUI cui;

    @BeforeEach
    void setUp() {
        cui = new CUI();
    }

    // Constructor tests

    @Test
    void constructorShouldInitializeCorrectly() {
        assertEquals("", cui.getCurrentTime());
    }

    // Getter and setter tests

    @Test
    void shouldSetAndGetCurrentTime() {
        cui.setCurrentTime("12:30:45");

        assertEquals("12:30:45", cui.getCurrentTime());
    }

    // UI tests

    @Test
    void setTimeShouldUpdateCurrentTime() {
        cui.setTime("12:30:45");

        assertEquals("12:30:45", cui.getCurrentTime());
    }

    @Test
    void setTimeShouldPrintCurrentTime() {
        // Arrange
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            // Act
            cui.setTime("12:30:45");

            // Assert
            assertEquals("12:30:45", output.toString().trim());

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void refreshShouldNotChangeCurrentTime() {
        cui.setCurrentTime("12:30:45");

        cui.refresh();

        assertEquals("12:30:45", cui.getCurrentTime());
    }
}
