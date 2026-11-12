
package de.timonexus.digitalClock.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.timonexus.digitalClock.ui.UIType;

class ClockTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = new Clock(UIType.CUI);
    }

    @Test
    void testConstructor() {
        assertTrue(clock.getRuns());
        assertEquals(1000, clock.getTimeInterval());

        assertEquals(
            "09:43:25",
            LocalDateTime.of(2026, 11, 12, 9, 43, 25)
                .format(clock.getDateTimeFormatter())
        );
    }

    @Test
    void testInterruptingClock() {
        clock.interruptingClock();

        assertFalse(clock.getRuns());
    }

    @Test
    void testSettersAndGetters() {
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime time =
            LocalDateTime.of(2026, 11, 12, 12, 30);

        clock.setDateTimeFormatter(formatter);
        clock.setTime(time);
        clock.setTimeString("12:30");
        clock.setTimeInterval(500);
        clock.setRuns(false);

        assertEquals(formatter, clock.getDateTimeFormatter());
        assertEquals(time, clock.getTime());
        assertEquals("12:30", clock.getTimeString());
        assertEquals(500, clock.getTimeInterval());
        assertFalse(clock.getRuns());
    }

    @Test
    void testStartClock() throws InterruptedException {
        clock.setTimeInterval(100);

        Thread clockThread = new Thread(clock::startClock);
        clockThread.start();

        clockThread.join(250);

        assertNotNull(clock.getTime());
        assertNotNull(clock.getTimeString());

        assertTrue(
            clock.getTimeString().matches("\\d{2}:\\d{2}:\\d{2}")
        );

        clock.interruptingClock();
        clockThread.join(1000);

        assertFalse(clockThread.isAlive());
    }

    @Test
    void testStartClockWithCustomFormatter()
            throws InterruptedException {

        clock.setDateTimeFormatter(
            DateTimeFormatter.ofPattern("HH:mm")
        );

        clock.setTimeInterval(100);

        Thread clockThread = new Thread(clock::startClock);
        clockThread.start();

        clockThread.join(150);

        assertNotNull(clock.getTimeString());
        assertTrue(
            clock.getTimeString().matches("\\d{2}:\\d{2}")
        );

        clock.interruptingClock();
        clockThread.join(1000);

        assertFalse(clockThread.isAlive());
    }

    @Test
    void testClockDoesNotStartWhenInterrupted()
            throws InterruptedException {

        clock.interruptingClock();

        clock.startClock();

        assertNull(clock.getTime());
        assertNull(clock.getTimeString());
    }
}
