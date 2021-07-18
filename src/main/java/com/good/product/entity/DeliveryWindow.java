package com.good.product.entity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Objects;

@Entity
public class DeliveryWindow {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private String fromTime;

    @Column(nullable = false)
    private String toTime;

    @Column(nullable = false)
    private boolean active = false;

    public Long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryWindow that = (DeliveryWindow) o;
        return active == that.active && dayOfWeek == that.dayOfWeek && fromTime.equals(that.fromTime) && toTime.equals(that.toTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, fromTime, toTime, active);
    }
}
