package com.choqnet.gantt.web.screens.dynScreens;

import com.haulmont.cuba.gui.events.UiEvent;

import org.springframework.context.ApplicationEvent;

public class CloseEvent extends ApplicationEvent implements UiEvent {
    private int idRoom;
    public CloseEvent(Object source, int myidRoomID) {
        super(source);
        this.idRoom = myidRoomID;
        System.out.println(myidRoomID);
    }

    public int getIdRoom() {
        return idRoom;
    }
}
