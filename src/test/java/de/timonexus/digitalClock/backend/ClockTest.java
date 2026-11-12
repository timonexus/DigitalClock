
package de.timonexus.digitalClock.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = new Clock();
    }

    // Constructor tests

    @Test
    void constructorShouldInitializeCorrectly() {
        assertTrue(clock.getRuns());
        assertEquals(1000, clock.getTimeInterval());
        assertEquals(
            "12:30:45",
            clock.getDateTimeFormatter().format(
                LocalDateTime.of(2026, 1, 1, 12, 30, 45)
            )
        );
    }

    // Getter and setter tests

    @Test
    void shouldSetAndGetRuns() {
        clock.setRuns(false);

        assertFalse(clock.getRuns());
    }

    @Test
    void shouldSetAndGetTimeInterval() {
        clock.setTimeInterval(500);

        assertEquals(500, clock.getTimeInterval());
    }

    @Test
    void shouldSetAndGetTime() {
        LocalDateTime expected =
                LocalDateTime.of(2026, 1, 1, 12, 30, 45);

        clock.setTime(expected);

        assertEquals(expected, clock.getTime());
    }

    @Test
    void shouldSetAndGetTimeString() {
        clock.setTimeString("12:30:45");

        assertEquals("12:30:45", clock.getTimeString());
    }

    @Test
    void shouldSetAndGetDateTimeFormatter() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");

        clock.setDateTimeFormatter(formatter);

        assertEquals(formatter, clock.getDateTimeFormatter());
    }

    // Clock tests

    @Test
    void interruptingClockShouldStopClock() {
        clock.interruptingClock();

        assertFalse(clock.getRuns());
    }

    @Test
    void startClockShouldUpdateTime() throws InterruptedException {
        clock.setTimeInterval(50);

        Thread clockThread = new Thread(clock::startClock);
        clockThread.start();

        Thread.sleep(150);

        clock.interruptingClock();
        clockThread.join(1000);

        assertFalse(clockThread.isAlive());
        assertNotNull(clock.getTime());
        assertNotNull(clock.getTimeString());
    }
}
