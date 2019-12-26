package com.awesome.skylightflights;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class flight_info implements Serializable
{
    private String from;
    private String to;
    private String price;
    private String flight_name;
    private String day;
    private String month;
    private String year;
    private String dep_time;
    private String lan_time;
    private String duration;

    public flight_info() {
    }

    public flight_info(String from, String to, String price, String flight_name, String day, String month, String year, String dep_time, String lan_time, String duration) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.flight_name = flight_name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dep_time = dep_time;
        this.lan_time = lan_time;
        this.duration = duration;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getLan_time() {
        return lan_time;
    }

    public void setLan_time(String lan_time) {
        this.lan_time = lan_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
