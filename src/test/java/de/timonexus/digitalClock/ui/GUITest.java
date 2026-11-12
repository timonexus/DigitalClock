
package de.timonexus.digitalClock.ui;

import static org.junit.jupiter.api.Assertions.*;


import javax.swing.SwingUtilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GUITest {

    private GUI gui;

    @BeforeEach
    void setUp() throws Exception {
        assertFalse(
            java.awt.GraphicsEnvironment.isHeadless(),
            "GUI tests require a graphical environment."
        );

        SwingUtilities.invokeAndWait(() -> {
            gui = new GUI();
        });
    }

    @AfterEach
    void tearDown() throws Exception {
        if (gui != null) {
            SwingUtilities.invokeAndWait(() -> {
                gui.dispose();
            });
        }
    }

    @Test
    void testWindowInitialization() {
        assertEquals("Digital Clock", gui.getTitle());
        assertTrue(gui.isVisible());
        assertTrue(gui.isUndecorated());
        assertEquals(
            javax.swing.JFrame.EXIT_ON_CLOSE,
            gui.getDefaultCloseOperation()
        );
    }

    @Test
    void testSetTime() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui.setTime("12:30:45");

            assertEquals("12:30:45", gui.getTime());
        });
    }

    @Test
    void testGetTimeInitially() {
        assertEquals("--:--:--", gui.getTime());
    }

    @Test
    void testSetDate() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            gui.setDate("Thursday, 12. November 2026");

            assertEquals(
                "Thursday, 12. November 2026",
                gui.getDate()
            );
        });
    }

    @Test
    void testGetDateInitially() {
        assertEquals("", gui.getDate());
    }

    @Test
    void testRefresh() throws Exception {
        assertDoesNotThrow(() -> {
            SwingUtilities.invokeAndWait(() -> {
                gui.refresh();
            });
        });
    }
}
