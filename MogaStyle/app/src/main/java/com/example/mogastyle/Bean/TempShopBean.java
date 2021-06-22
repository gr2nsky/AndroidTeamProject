package com.example.mogastyle.Bean;

import java.util.ArrayList;

public class TempShopBean {
    private int no;
    private String name;
    private String tel;
    private String address;
    private String postcode;
    private int image;

    ArrayList<Integer> holidays;

    //임시
    public TempShopBean(int no, String name, String holiday){
        this.no = no;
        this.name = name;
        holidays = new ArrayList<>();
        if(holiday.length() > 2) {
            String[] holidaySplited = holiday.split(",");
            for(String s : holidaySplited){
                holidays.add(Integer.parseInt(s));
            }
        } else {
            holidays.add(Integer.parseInt(holiday));
        }
    }

    public TempShopBean(String name, String tel, String address, String postcode, int image) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.postcode = postcode;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Integer> getHolidays() {
        return holidays;
    }

    public void setHolidays(ArrayList<Integer> holidays) {
        this.holidays = holidays;
    }
}
