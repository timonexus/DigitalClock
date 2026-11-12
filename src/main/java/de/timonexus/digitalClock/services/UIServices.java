package de.timonexus.digitalClock.services;

import java.util.List;

public interface UIServices {
    
    public List<String> getData();
    public void setData(List<String> nData);
    public void refresh();
}
