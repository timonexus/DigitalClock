
package de.timonexus.digitalClock.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.timonexus.digitalClock.ui.CUI;
import de.timonexus.digitalClock.ui.UIType;
import de.timonexus.digitalClock.ui.UserInterfacesClock;

class ClockUIServiceTest {

    private ClockUIService service;
    private UserInterfacesClock ui;

    @BeforeEach
    void setUp() {
        service = new ClockUIService(UIType.CUI);

        ui = mock(UserInterfacesClock.class);
        service.setUi(ui);
    }

    @Test
    void testConstructor() {
        ClockUIService newService = new ClockUIService(UIType.CUI);

        assertNotNull(newService.getUi());
        assertEquals(UIType.CUI, newService.getUiType());
    }

    @Test
    void testGetData() {
        when(ui.getTime()).thenReturn("12:30:45");

        String result = service.getData();

        assertEquals("12:30:45", result);
        verify(ui).getTime();
    }

    @Test
    void testSetData() {
        List<String> data = List.of("12:30:45");

        service.setData(data);

        verify(ui).setTime("12:30:45");
    }

    @Test
    void testRefresh() {
        service.refresh();

        verify(ui).refresh();
    }

    @Test
    void testGetUi() {
        assertSame(ui, service.getUi());
    }

    @Test
    void testGetUiType() {
        assertEquals(UIType.CUI, service.getUiType());
    }

    @Test
    void testSetUi() {
        UserInterfacesClock newUi = mock(UserInterfacesClock.class);

        service.setUi(newUi);

        assertSame(newUi, service.getUi());
    }

    @Test
    void testSetUiType() {
        service.setUiType(UIType.CUI);

        assertEquals(UIType.CUI, service.getUiType());
        assertNotNull(service.getUi());
        assertInstanceOf(CUI.class, service.getUi());
    }
}
