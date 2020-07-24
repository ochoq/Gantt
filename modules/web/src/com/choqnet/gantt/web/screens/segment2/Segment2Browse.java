package com.choqnet.gantt.web.screens.segment2;

import com.haulmont.cuba.gui.screen.*;
import com.choqnet.gantt.entity.Segment2;

@UiController("gantt_Segment2.browse")
@UiDescriptor("segment2-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class Segment2Browse extends MasterDetailScreen<Segment2> {
}