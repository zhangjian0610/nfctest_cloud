package com.bupt.cardtest.model.pagebean;

import com.bupt.cardtest.model.bean.Record;

public class RecordPage extends Record {


    private Integer last;
    private Integer interval;

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
