/*

제작 : 윤재필
20210621 테스트용으로 임시로 제작함.

 */
package com.example.mogastyle.Bean;

import java.util.ArrayList;

public class TempDesignerBean {
    int no;
    String name;
    String certificationDate;
    ArrayList<ResDateData> resDates;
    String img;
    ArrayList<Integer> holidays;

    public TempDesignerBean() {
    }

    public TempDesignerBean(int no, String name, String certificationDate, String img, String holiday) {
        this.no = no;
        this.name = name;
        this.certificationDate = certificationDate;
        this.resDates = new ArrayList<>();
        this.img = img;
        this.holidays = new ArrayList<>();
        if(holiday.length() > 2) {
            String[] holidaySplited = holiday.split(",");
            for(String s : holidaySplited){
                holidays.add(Integer.parseInt(s));
            }
        } else {
            holidays.add(Integer.parseInt(holiday));
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(String certificationDate) {
        this.certificationDate = certificationDate;
    }

    public ArrayList<ResDateData> getResDates() {
        return resDates;
    }

    public void setResDates(ArrayList<ResDateData> resDates) {
        this.resDates = resDates;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<Integer> getHolidays() {
        return holidays;
    }

    public void setHolidays(ArrayList<Integer> holidays) {
        this.holidays = holidays;
    }
}
