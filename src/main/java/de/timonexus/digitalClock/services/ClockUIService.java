package de.timonexus.digitalClock.services;

import java.util.ArrayList;
import java.util.List;

import de.timonexus.digitalClock.ui.UIType;
import de.timonexus.digitalClock.ui.UserInterfacesClock;

public class ClockUIService implements UIServices{
    
    // attributes
    private UserInterfacesClock ui;
    private UIType uiType;

    // constructor
    public ClockUIService(UIType nUiType){
        this.uiType = nUiType;
        implementUi();
    }

    // method for implementing the ui
    private void implementUi(){
        this.ui = this.uiType.getUserInterfacesClock();
    }

    @Override
    public List<String> getData() {
        List<String> result = new ArrayList<>();
        result.add(this.ui.getTime());
        result.add(this.ui.getDate());
        return result;
    }

    @Override
    public void setData(List<String> nData) {
        this.ui.setTime(nData.get(0));
        if(nData.size() > 1){
            this.ui.setDate(nData.get(1));
        }
    }

    @Override
    public void refresh() {
        this.ui.refresh();
    }

    // getter
    public UserInterfacesClock getUi() {
        return ui;
    }
    public UIType getUiType() {
        return uiType;
    }


    // setter
    public void setUi(UserInterfacesClock ui) {
        this.ui = ui;
    }
    public void setUiType(UIType uiType) {
        this.uiType = uiType;
        implementUi();
    }
}
