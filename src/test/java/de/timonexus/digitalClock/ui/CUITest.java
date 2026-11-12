
package de.timonexus.digitalClock.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CUITest {

    private CUI cui;

    @BeforeEach
    void setUp() {
        this.cui = new CUI();
    }

    @Test
    void testConstructor() {
        assertEquals("", cui.getTime());
        assertEquals("", cui.getDate());
        assertEquals("", cui.getCurrentTime());
    }

    @Test
    void testSetTime() {
        cui.setTime("12:30:45");

        assertEquals("12:30:45", cui.getTime());
        assertEquals("12:30:45", cui.getCurrentTime());
    }

    @Test
    void testGetTime() {
        cui.setCurrentTime("15:45:30");

        assertEquals("15:45:30", cui.getTime());
    }

    @Test
    void testSetDate() {
        cui.setDate("Thursday, 12. November 2026");

        assertEquals(
            "Thursday, 12. November 2026",
            cui.getDate()
        );
    }

    @Test
    void testPrintTime() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            cui.setTime("12:30:45");

            assertEquals(
                "12:30:45",
                output.toString(StandardCharsets.UTF_8).trim()
            );
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintDate() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            cui.setDate("12. November 2026");

            assertEquals(
                "12. November 2026",
                output.toString(StandardCharsets.UTF_8).trim()
            );
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testRefresh() {
        // Refresh should not throw an exception.
        cui.refresh();
    }
}
