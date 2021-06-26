package com.example.mogastyle.Bean;

import java.util.ArrayList;

public class Shop {
    private int no;
    private String name;
    private String tel;
    private String address;
    private String postcode;
    private String introduction;
    private String holiday;
    private String image;

    public Shop(int no, String name, String tel, String address, String postcode, String introduction, String holiday, String image) {
        this.no = no;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.postcode = postcode;
        this.introduction = introduction;
        this.holiday = holiday;
        this.image = image;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
