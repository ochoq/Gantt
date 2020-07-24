package com.choqnet.gantt.web.screens;

import com.choqnet.gantt.entity.Segment2;
import com.choqnet.gantt.entity.TaskSpan2;
import com.haulmont.charts.gui.components.charts.GanttChart;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.SimpleDataItem;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.*;

@UiController("gantt_Ganttchart2")
@UiDescriptor("ganttChart2.xml")
@LoadDataBeforeShow
public class Ganttchart2 extends Screen {
    @Inject
    private DataManager dataManager;
    @Inject
    private GanttChart chart;

    private List<TaskSpan2> taskSpan2s;
    @Inject
    private DateField<Date> dateFrom;
    @Inject
    private DateField<Date> dateTo;

    @Subscribe
    public void onInit(InitEvent event) {
        // sets teh default dates
        Date now = new Date();
        dateFrom.setValue(giveMonthStart(now));
        dateTo.setValue(addTime(giveMonthStart(now),1,"MONTH"));

    }



    public void onBtnGenerateClick() {
        // copy of data under a dynamic format
        taskSpan2s = dataManager.load(TaskSpan2.class)
                .query("select t from gantt_TaskSpan2 t")
                .view("taskSpan2-view")
                .list();
        // prepare the new dataProvider
        ListDataProvider dataProvider = new ListDataProvider();

        // fetch the list of -selected- taskSpan
        for (TaskSpan2 taskSpan2: taskSpan2s) {
            // 1. create the list to hold the dynSegments
            List<DynSegment> dynSegments = new ArrayList<>();

            // 2. get the list of attached segments
            List<Segment2> segment2s = taskSpan2.getSegments();

            // 3. fetch the list of  Segments
            for (Segment2 segment2: segment2s) {
                System.out.println(segment2.getTask() + " " + segment2.getStart().compareTo(Objects.requireNonNull(dateFrom.getValue())));
                // filters on the dates
                if (
                        (segment2.getStart().compareTo(Objects.requireNonNull(dateFrom.getValue())) >= 0)
                                &&
                                (segment2.getEnd().compareTo(Objects.requireNonNull(dateTo.getValue())) <= 0)
                ) {
                    // 3.a create the new dynSegment
                    DynSegment dynSegment = new DynSegment(
                            segment2.getColor(),
                            segment2.getTask(),
                            segment2.getStart(),
                            segment2.getEnd());
                    // 3.b add the new dynSegment to the lis
                    dynSegments.add(dynSegment);
                }
            }

            // 4. create the dynTaskSpan
            DynTaskSpan dynTaskSpan = new DynTaskSpan(
                    taskSpan2.getCategory(),
                    dynSegments);

            // 5. adds the new dynTaskSpan to the dataProvider
            dataProvider.addItem(new SimpleDataItem(dynTaskSpan));
        }

        // sets the dataProvider for the chart
        chart.setDataProvider(dataProvider);
        chart.repaint();
    }

    public static class DynSegment {
        private String color;
        private String task;
        private Date start;
        private Date end;

        DynSegment(String color, String task, Date start, Date end) {
            this.color = color;
            this.task = task;
            this.start = start;
            this.end = end;
        }

        public String getColor() {
            return color;
        }

        public String getTask() {
            return task;
        }

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }

    }

    public static class DynTaskSpan {
        private String category;
        private List<DynSegment> segments;

        DynTaskSpan(String category, List<DynSegment> segments) {
            this.category = category;
            this.segments = segments;
        }

        public String getCategory() {
            return category;
        }

        public List<DynSegment> getSegments() {
            return segments;
        }
    }

    // UILS
    private static Date giveMonthStart(Date ref) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ref);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }
    private static Date addTime(Date ref, int delta, String unit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ref);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        switch (unit) {
            case "DAY":
                cal.add(Calendar.DATE, delta);
                break;
            case "MONTH":
                cal.add(Calendar.MONTH, delta);
                break;
            case "YEAR":
                cal.add(Calendar.YEAR, delta);
                break;
        }
        return cal.getTime();
    }
}