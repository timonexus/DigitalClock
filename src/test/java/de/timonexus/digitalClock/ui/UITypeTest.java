
package de.timonexus.digitalClock.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UITypeTest {

    @Test
    void testEnumValues() {
        assertEquals(2, UIType.values().length);

        assertNotNull(UIType.GUI);
        assertNotNull(UIType.CUI);
    }

    @Test
    void testGUIInitialization() {
        assertInstanceOf(
            GUI.class,
            UIType.GUI.getUserInterfacesClock()
        );
    }

    @Test
    void testCUIInitialization() {
        assertInstanceOf(
            CUI.class,
            UIType.CUI.getUserInterfacesClock()
        );
    }

    @Test
    void testGetUserInterfacesClock() {
        assertNotNull(UIType.GUI.getUserInterfacesClock());
        assertNotNull(UIType.CUI.getUserInterfacesClock());
    }

    @Test
    void testSetUserInterfacesClock() {
        UserInterfacesClock mockUI =
            org.mockito.Mockito.mock(UserInterfacesClock.class);

        UIType.CUI.setUserInterfacesClock(mockUI);

        assertSame(
            mockUI,
            UIType.CUI.getUserInterfacesClock()
        );
    }

    @Test
    void testValueOf() {
        assertEquals(UIType.GUI, UIType.valueOf("GUI"));
        assertEquals(UIType.CUI, UIType.valueOf("CUI"));
    }
}
