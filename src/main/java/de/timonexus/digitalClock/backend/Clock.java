package de.timonexus.digitalClock.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import de.timonexus.digitalClock.services.ClockUIService;
import de.timonexus.digitalClock.ui.UIType;

public class Clock {
    
    // attributes
    private boolean runs;
    private int timeInterval;

    private DateTimeFormatter dateTimeFormatter;
    private LocalDateTime time;
    private String timeString;

    private ClockUIService clockUIService;

    public Clock() {
        this(UIType.CUI);
    }

    // constructor
    public Clock(UIType n_UiType){
        this.runs = true;   
        this.timeInterval = 1000;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.clockUIService = new ClockUIService(n_UiType);
    }

    // method for running the clock
    public void startClock(){
        try {
            while(runs){
                this.time = LocalDateTime.now();
                this.timeString = this.time.format(this.dateTimeFormatter);
                this.clockUIService.setData(List.of(this.timeString));
                Thread.sleep(this.timeInterval);
            }
        } catch (Exception e) {
            System.out.println("An error occurred in the clock-loop (class: Clock; method: startClock()): " + e);
        }
    }

    // mehtod for interrupting the clock
    public void interruptingClock(){
        this.runs = false;
    }

    // getters
    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public int getTimeInterval() {
        return timeInterval;
    }
    public String getTimeString() {
        return timeString;
    }
    public boolean getRuns(){
        return this.runs;
    }

    // setter
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    public void setRuns(boolean runs) {
        this.runs = runs;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }
    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }


    
}
