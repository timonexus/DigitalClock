package de.timonexus.digitalClock.ui;

public class CUI implements UserInterfacesClock{

    // attribute
    private String currentTime;
    private String currentDate;

    // constructor
    public CUI(){
        this.currentTime = "";
        this.currentDate = "";
    }

    // method for printing to the console
    private void printTime(){
        System.out.println(currentTime);
    }
    private void printDate(){
        System.out.println(currentDate);
    }

    // method to get the new time
    @Override
    public void setTime(String nTime) {
        this.currentTime = nTime;

        this.printTime();
    }

    @Override 
    public String getTime(){
        return currentTime;
    }

    // method to get the new date
    @Override 
    public void setDate(String nDate){
        this.currentDate = nDate;

        this.printDate();
    }
    @Override 
    public String getDate(){
        return this.currentDate;
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
