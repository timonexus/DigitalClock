
package de.timonexus.digitalClock.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.timonexus.digitalClock.services.ClockUIService;
import de.timonexus.digitalClock.ui.UIType;

class ClockTest {

    private Clock clock;

    @BeforeEach
    void setUp() {
        this.clock = new Clock(UIType.CUI);
    }

    @Test
    void testConstructor() {
        assertTrue(clock.getRuns());
        assertEquals(1000, clock.getTimeInterval());

        assertNotNull(clock.getDateTimeFormatter());
        assertNotNull(clock.getDateFormatter());
        assertNotNull(clock.getClockUIService());

        assertNull(clock.getTime());
        assertNull(clock.getCurrentDate());
        assertNull(clock.getTimeString());
    }

    @Test
    void testDefaultConstructor() {
        Clock defaultClock = new Clock();

        assertNotNull(defaultClock.getClockUIService());
        assertEquals(
            UIType.CUI,
            defaultClock.getClockUIService().getUiType()
        );
    }

    @Test
    void testDateTimeFormatter() {
        LocalDateTime sampleTime =
            LocalDateTime.of(2026, 11, 12, 14, 30, 45);

        assertEquals(
            "14:30:45",
            sampleTime.format(clock.getDateTimeFormatter())
        );
    }

    @Test
    void testDateFormatter() {
        LocalDate sampleDate = LocalDate.of(2026, 11, 12);

        assertEquals(
            "Donnerstag, 12. November 2026",
            sampleDate.format(clock.getDateFormatter())
        );
    }

    @Test
    void testInterruptingClock() {
        clock.interruptingClock();

        assertFalse(clock.getRuns());
    }

    @Test
    void testSetDateTimeFormatter() {
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm");

        clock.setDateTimeFormatter(formatter);

        assertSame(formatter, clock.getDateTimeFormatter());
    }

    @Test
    void testSetDateFormatter() {
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

        clock.setDateFormatter(formatter);

        assertSame(formatter, clock.getDateFormatter());
    }

    @Test
    void testSetTime() {
        LocalDateTime time =
            LocalDateTime.of(2026, 11, 12, 14, 30);

        clock.setTime(time);

        assertEquals(time, clock.getTime());
    }

    @Test
    void testSetTimeInterval() {
        clock.setTimeInterval(500);

        assertEquals(500, clock.getTimeInterval());
    }

    @Test
    void testSetTimeString() {
        clock.setTimeString("14:30:45");

        assertEquals("14:30:45", clock.getTimeString());
    }

    @Test
    void testSetRuns() {
        clock.setRuns(false);

        assertFalse(clock.getRuns());
    }

    @Test
    void testSetCurrentDate() {
        LocalDate date = LocalDate.of(2026, 11, 12);

        clock.setCurrentDate(date);

        assertEquals(date, clock.getCurrentDate());
    }

    @Test
    void testSetClockUIService() {
        ClockUIService service = mock(ClockUIService.class);

        clock.setClockUIService(service);

        assertSame(service, clock.getClockUIService());
    }

    @Test
    void testStartClockWithNewDate() {
        ClockUIService service = mock(ClockUIService.class);
        clock.setClockUIService(service);
        clock.setTimeInterval(1);

        List<List<String>> receivedData = new ArrayList<>();

        doAnswer(invocation -> {
            receivedData.add(
                new ArrayList<>(invocation.getArgument(0))
            );

            clock.interruptingClock();
            return null;
        }).when(service).setData(anyList());

        clock.startClock();

        assertEquals(1, receivedData.size());
        assertEquals(2, receivedData.get(0).size());

        assertTrue(
            receivedData.get(0).get(0).matches("\\d{2}:\\d{2}:\\d{2}")
        );

        assertEquals(
            clock.getCurrentDate().format(clock.getDateFormatter()),
            receivedData.get(0).get(1)
        );

        assertEquals(
            clock.getTime().format(clock.getDateTimeFormatter()),
            clock.getTimeString()
        );

        assertFalse(clock.getRuns());
    }

    @Test
    void testStartClockWithExistingDate() {
        ClockUIService service = mock(ClockUIService.class);
        clock.setClockUIService(service);
        clock.setTimeInterval(1);

        clock.setCurrentDate(LocalDate.now());

        List<List<String>> receivedData = new ArrayList<>();

        doAnswer(invocation -> {
            receivedData.add(
                new ArrayList<>(invocation.getArgument(0))
            );

            clock.interruptingClock();
            return null;
        }).when(service).setData(anyList());

        clock.startClock();

        assertEquals(1, receivedData.size());
        assertEquals(1, receivedData.get(0).size());

        assertTrue(
            receivedData.get(0).get(0).matches("\\d{2}:\\d{2}:\\d{2}")
        );

        assertEquals(LocalDate.now(), clock.getCurrentDate());
        assertFalse(clock.getRuns());
    }
}
