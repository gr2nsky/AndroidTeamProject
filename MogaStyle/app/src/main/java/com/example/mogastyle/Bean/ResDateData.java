package com.example.mogastyle.Bean;

import java.util.ArrayList;
import java.util.Calendar;

public class ResDateData {
    int year;
    int month;
    int date;
    int dayOfWeek;
    ArrayList<Integer> time;
    int nowHour;
    //1이면 휴무
    int isHoliday = 0;

    public ResDateData(String dateType, int startTime, int useTime) {
        String[] splitedDate = dateType.split("-");
        year = Integer.parseInt(splitedDate[0]);
        month = Integer.parseInt(splitedDate[1]);
        date = Integer.parseInt(splitedDate[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        time = new ArrayList<>();
        for (int i = startTime; i <= (startTime + useTime); i++){
            time.add(i);
        }
    }

    public ResDateData(int year, int month, int date, int dayOfWeek, int nowHour) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.nowHour = nowHour;
    }

    public ResDateData(int year, int month, int date, int dayOfWeek) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
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

    public String printAll(){
        return "year : " + Integer.toString(year) + ", month : " + Integer.toString(month) + ", date : " + Integer.toString(date) + ", day : " + getDay();
    }

    public ArrayList<Integer> getTime() {
        return time;
    }

    public void setTime(ArrayList<Integer> time) {
        this.time = time;
    }

    public int getNowHour() {
        return nowHour;
    }

    public void setNowHour(int nowHour) {
        this.nowHour = nowHour;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String print(){
        String monthStr = null;
        String dateStr = null;

        if(month < 10){
            monthStr = "0"+month;
        } else {
           monthStr = Integer.toString(month);
        }

        if(date < 10){
            dateStr = "0"+dateStr;
        } else {
            dateStr = Integer.toString(date);
        }

        return year+"-"+monthStr+"-"+dateStr;
    }

    public String getDay(){
        switch (dayOfWeek){
            case 1:
                return "일";
            case 2:
                return "월";
            case 3:
                return "화";
            case 4:
                return "수";
            case 5:
                return "목";
            case 6:
                return "금";
            case 7:
                return "토";
        }
        return "?";
    }
}
