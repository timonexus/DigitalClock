package de.timonexus.digitalClock.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import de.timonexus.digitalClock.services.ClockUIService;
import de.timonexus.digitalClock.ui.UIType;

public class Clock {
    
    // attributes
    private volatile boolean runs;
    private int timeInterval;

    private DateTimeFormatter dateTimeFormatter;
    private DateTimeFormatter dateFormatter;
    private LocalDateTime time;
    private LocalDate currentDate;
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
        this.dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd. MMMM yyyy", Locale.GERMAN);

        this.clockUIService = new ClockUIService(n_UiType);
    }

    // method for running the clock
    public void startClock(){
        try {
            while (runs) {
                this.time = LocalDateTime.now();

                LocalDate newDate = this.time.toLocalDate();
                this.timeString = this.time.format(this.dateTimeFormatter);

                if (this.currentDate == null || !this.currentDate.equals(newDate)) {
                    this.currentDate = newDate;
                    this.clockUIService.setData(List.of(this.timeString, this.currentDate.format(dateFormatter)));
                } else {
                    this.clockUIService.setData(List.of(this.timeString));
                }

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
    public ClockUIService getClockUIService() {
        return clockUIService;
    }
    public LocalDate getCurrentDate() {
        return currentDate;
    }
    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
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
    public void setClockUIService(ClockUIService clockUIService) {
        this.clockUIService = clockUIService;
    }
    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
    public void setDateFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }


    
}
