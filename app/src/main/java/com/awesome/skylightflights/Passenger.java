package com.awesome.skylightflights;

import java.io.Serializable;

public class Passenger implements Serializable {

    private String price;
    private String from_place;
    private String to_place;
    private String flight;
    private String booking_Id;
    private int date_day;
    private int date_month;
    private int date_year;
    private String dep_time;
    private String lan_time;
    private String name;
    private String flight_class;
    private int seat;
    private int age;
    private String gender;
    private String duration;
    private String email;

    public Passenger() {
    }

    public Passenger(String price, String from_place, String to_place, String flight, String booking_Id, int date_day,
                     int date_month, int date_year, String dep_time, String lan_time, String name, String flight_class,
                     int seat, int age, String gender, String duration, String email)
    {
        this.price = price;
        this.from_place = from_place;
        this.to_place = to_place;
        this.flight = flight;
        this.booking_Id = booking_Id;
        this.date_day = date_day;
        this.date_month = date_month;
        this.date_year = date_year;
        this.dep_time = dep_time;
        this.lan_time = lan_time;
        this.name = name;
        this.flight_class = flight_class;
        this.seat = seat;
        this.age = age;
        this.gender = gender;
        this.duration = duration;
        this.email = email;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFrom_place() {
        return from_place;
    }

    public void setFrom_place(String from_place) {
        this.from_place = from_place;
    }

    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getBooking_Id() {
        return booking_Id;
    }

    public void setBooking_Id(String booking_Id) {
        this.booking_Id = booking_Id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlight_class() {
        return flight_class;
    }

    public void setFlight_class(String flight_class) {
        this.flight_class = flight_class;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public int getDate_day() {
        return date_day;
    }

    public void setDate_day(int date_day) {
        this.date_day = date_day;
    }

    public int getDate_month() {
        return date_month;
    }

    public void setDate_month(int date_month) {
        this.date_month = date_month;
    }

    public int getDate_year() {
        return date_year;
    }

    public void setDate_year(int date_year) {
        this.date_year = date_year;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
