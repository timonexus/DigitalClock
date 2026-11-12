
package de.timonexus.digitalClock.ui;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.BorderLayout;

public class GUITest {

    @Test
    void testGUIInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            GUI gui = new GUI();

            assertEquals("Digital Clock", gui.getTitle());
            assertEquals(Color.BLACK, gui.getContentPane().getBackground());
            assertEquals(BorderLayout.class, gui.getContentPane().getLayout().getClass());
            assertTrue(gui.isVisible());

            gui.dispose();
        });
    }

    @Test
    void testSetTime() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            GUI gui = new GUI();

            gui.setTime("12:30:45");

            assertEquals("12:30:45", gui.getContentPane()
                    .getComponent(0) instanceof javax.swing.JLabel label
                    ? label.getText() : null);

            gui.dispose();
        });
    }

    @Test
    void testRefresh() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            GUI gui = new GUI();

            assertDoesNotThrow(gui::refresh);

            gui.dispose();
        });
    }
}
