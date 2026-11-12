package de.timonexus.digitalClock.ui;

public enum UIType {
    
    // enums
    GUI(new GUI()),
    CUI(new CUI());

    // attributes
    private UserInterfacesClock userInterfacesClock;

    // constructor
    UIType(UserInterfacesClock nUserInterfacesClock){
        this.userInterfacesClock = nUserInterfacesClock;
    }

    // getter
    public UserInterfacesClock getUserInterfacesClock() {
        return userInterfacesClock;
    }

    // setter
    public void setUserInterfacesClock(UserInterfacesClock userInterfacesClock) {
        this.userInterfacesClock = userInterfacesClock;
    }

}
