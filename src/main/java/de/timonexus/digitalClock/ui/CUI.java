package de.timonexus.digitalClock.ui;

public class CUI implements UserInterfacesClock{

    // attribute
    private String currentTime;

    // constructor
    public CUI(){
        this.currentTime = "";
    }

    // method for printing to the console
    private void print(){
        System.out.println(currentTime);
    }

    // method to get the new time
    @Override
    public void setTime(String nTime) {
        this.currentTime = nTime;

        this.print();
    }

    @Override 
    public String getTime(){
        return currentTime;
    }

    // method to hypotecially refresh the ui, but in the console this is not neccessary
    @Override
    public void refresh() {}
    

    // getter
    public String getCurrentTime() {
        return currentTime;
    }

    // setter
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
