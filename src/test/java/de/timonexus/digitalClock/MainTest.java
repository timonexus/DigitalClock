package de.timonexus.digitalClock;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void mainShouldPrintHelloWorld() {
        // Arrange
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            // Act
            Main.main(new String[]{});

            // Assert
            assertEquals("Hello World!", output.toString().trim());

        } finally {
            System.setOut(originalOut);
        }
    }
}