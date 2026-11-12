package de.timonexus.digitalClock;

import de.timonexus.digitalClock.backend.Clock;
import de.timonexus.digitalClock.ui.UIType;

public class Main {

    public static void main(String[] args) {
        new Clock(UIType.GUI).startClock();
    }
}
