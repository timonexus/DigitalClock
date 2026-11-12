package de.timonexus.digitalClock.ui;

import java.util.function.Supplier;

public enum UIType {

    GUI(GUI::new),
    CUI(CUI::new);

    private final Supplier<UserInterfacesClock> uiSupplier;

    UIType(Supplier<UserInterfacesClock> uiSupplier) {
        this.uiSupplier = uiSupplier;
    }

    public UserInterfacesClock getUserInterfacesClock() {
        return uiSupplier.get();
    }
}