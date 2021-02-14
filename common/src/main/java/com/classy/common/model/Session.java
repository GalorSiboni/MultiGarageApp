package com.classy.common.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Session {
    @ColumnInfo(name = "start")
    private long start;

    @ColumnInfo(name = "end")
    private long end;

    @ColumnInfo(name = "total")
    private long total;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public Session() {
    }

    public Session(long start, long end, long total, long id) {
        this.start = start;
        this.end = end;
        this.total = total;
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}