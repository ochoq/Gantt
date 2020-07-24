package com.choqnet.gantt.web.screens.dynScreens;

import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ScrollBoxLayout;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

@UiController("gantt_Dynscreen")
@UiDescriptor("dynScreen.xml")
public class Dynscreen extends Screen implements ApplicationListener<CloseEvent> {
    @Inject
    private UiComponents uiComponents;
    @Inject
    private ScrollBoxLayout theBox;
    @Inject
    private Notifications notifications;
    @Inject
    private Fragments fragments;

    private int cpt = 0;

    @Subscribe
    public void onInit(InitEvent event) {

    }

    @Order(10)
    @EventListener
    public void onApplicationEvent(CloseEvent event) {
        // from here, how remove the god damn BasicFragment which fired this event?
    }

    public void onBtnAddClick() {
        Button but01 = uiComponents.create(Button.class);
        theBox.add(but01);
        but01.setId("btn" + cpt +"_dyn");
        cpt++;
        but01.setCaption(but01.getId());
        but01.setAction(new BaseAction("sayHello") {
            @Override
            public void actionPerform(Component component) {
                notifications.create()
                        .withCaption("Hello from " + but01.getId() + "!")
                        .show();
            }
        });
    }

    public void onBtnAddPlusClick() {
        BasicFragment basic = fragments.create(this, BasicFragment.class);
        basic.setIdRoom(cpt);
        cpt++;
        theBox.add(basic.getFragment());
    }
}