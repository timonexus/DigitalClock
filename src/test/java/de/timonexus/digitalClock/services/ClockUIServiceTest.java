
package de.timonexus.digitalClock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.timonexus.digitalClock.ui.UIType;
import de.timonexus.digitalClock.ui.UserInterfacesClock;

class ClockUIServiceTest {

    private ClockUIService clockUIService;
    private UserInterfacesClock ui;

    @BeforeEach
    void setUp() {
        this.ui = mock(UserInterfacesClock.class);
        this.clockUIService = new ClockUIService(UIType.CUI);
        this.clockUIService.setUi(this.ui);
    }

    @Test
    void testConstructor() {
        ClockUIService service = new ClockUIService(UIType.CUI);

        assertNotNull(service.getUi());
        assertEquals(UIType.CUI, service.getUiType());
    }

    @Test
    void testGetData() {
        when(ui.getTime()).thenReturn("12:30:45");
        when(ui.getDate()).thenReturn("12. November 2026");

        List<String> result = clockUIService.getData();

        assertEquals(
            List.of("12:30:45", "12. November 2026"),
            result
        );

        verify(ui).getTime();
        verify(ui).getDate();
    }

    @Test
    void testSetDataWithTimeAndDate() {
        List<String> data = List.of(
            "12:30:45",
            "12. November 2026"
        );

        clockUIService.setData(data);

        verify(ui).setTime("12:30:45");
        verify(ui).setDate("12. November 2026");
    }

    @Test
    void testSetDataWithTimeOnly() {
        List<String> data = List.of("12:30:45");

        clockUIService.setData(data);

        verify(ui).setTime("12:30:45");
        verify(ui, never()).setDate(anyString());
    }

    @Test
    void testRefresh() {
        clockUIService.refresh();

        verify(ui).refresh();
    }

    @Test
    void testGetUi() {
        assertSame(ui, clockUIService.getUi());
    }

    @Test
    void testGetUiType() {
        assertEquals(UIType.CUI, clockUIService.getUiType());
    }

    @Test
    void testSetUi() {
        UserInterfacesClock newUi = mock(UserInterfacesClock.class);

        clockUIService.setUi(newUi);

        assertSame(newUi, clockUIService.getUi());
    }

    @Test
    void testSetUiType() {
        clockUIService.setUiType(UIType.GUI);

        assertEquals(UIType.GUI, clockUIService.getUiType());
        assertNotNull(clockUIService.getUi());
    }
}
