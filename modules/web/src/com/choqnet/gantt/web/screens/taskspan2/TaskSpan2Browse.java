package com.choqnet.gantt.web.screens.taskspan2;

import com.choqnet.gantt.entity.Segment2;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.choqnet.gantt.entity.TaskSpan2;

import javax.inject.Inject;

@UiController("gantt_TaskSpan2.browse")
@UiDescriptor("task-span2-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TaskSpan2Browse extends MasterDetailScreen<TaskSpan2> {

    @Inject
    private CollectionLoader<Segment2> segment2sDl;
    @Inject
    private GroupTable<TaskSpan2> table;

    @Subscribe
    public void onInit(InitEvent event) {
        connect();
    }

    @Subscribe("table")
    public void onTableSelection(Table.SelectionEvent<TaskSpan2> event) {
        connect();
    }

    private void connect() {
        segment2sDl.setQuery("select s from gantt_Segment2 s where s.taskSpan2 = :taskSpan");
        segment2sDl.setParameter("taskSpan",table.getSingleSelected());
        segment2sDl.load();
    }
}