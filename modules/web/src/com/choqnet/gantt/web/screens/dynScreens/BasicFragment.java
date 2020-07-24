package com.choqnet.gantt.web.screens.dynScreens;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@UiController("gantt_Basicgragment")
@UiDescriptor("basicGragment.xml")
public class BasicFragment extends ScreenFragment {
    @Inject
    private Notifications notifications;
    @Inject
    private TextField<String> txtIn;String NAME = "cuba_Events";
    @Inject
    private Events events;

    private int idRoom;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int id) {
        this.idRoom = id;
    }

    @Subscribe
    public void onInit(InitEvent event) {
        txtIn.setValue("New @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        txtIn.setStyleName("super");
    }

    public void onBtnShowClick() {
        notifications.create()
                .withCaption("Hello from " + txtIn.getValue() + "!")
                .show();
    }

    public void closeMe() {
        events.publish(new CloseEvent(this, idRoom));

    }
}