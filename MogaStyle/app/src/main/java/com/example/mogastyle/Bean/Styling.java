package com.example.mogastyle.Bean;

import java.sql.Date;

public class Styling {
    private int no;
    private String typeCode;
    private String submitDate;
    private String deleteDate;
    private String title;
    private String content;
    private String gender;
    private int price;
    private int leadTime;

    public Styling(int no, String typeCode, String submitDate, String deleteDate, String title, String content, String gender, int price, String time, int leadTime) {
        this.no = no;
        this.typeCode = typeCode;
        this.submitDate = submitDate;
        this.deleteDate = deleteDate;
        this.title = title;
        this.content = content;
        this.gender = gender;
        this.price = price;
        this.leadTime = leadTime;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }
}
