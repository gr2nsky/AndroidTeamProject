package com.example.mogastyle.Bean;

public class ResDateData {
    int year;
    int month;
    int date;
    String day;

    public ResDateData(int year, int month, int date, String day) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String printAll(){
        return "year : " + Integer.toString(year) + ", month : " + Integer.toString(month) + ", date : " + Integer.toString(date) + ", day : " + day;
    }
}
