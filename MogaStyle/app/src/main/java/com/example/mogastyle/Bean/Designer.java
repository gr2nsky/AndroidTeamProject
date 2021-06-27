package com.example.mogastyle.Bean;

public class Designer {

    // Field
    int no;
    String name;
    String userCheck;
    String certificationDate;
    String introduction;
    String holiday;
    String image;

    // Field_외래키
    int shopNo;
    int stylingNo;

    // 생성자(Constructor)
    // Designer List
    public Designer(int no, String name, String introduction, String image) {
        this.no = no;
        this.name = name;
        this.introduction = introduction;
        this.image = image;
    }

    // Designer Detail page
    public Designer(String name, String certificationDate, String introduction, int shopNo, String image) {
        this.name = name;
        this.certificationDate = certificationDate;
        this.introduction = introduction;
        this.shopNo = shopNo;
        this.image = image;
    }


    // Getter and Setter


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

    public String getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(String userCheck) {
        this.userCheck = userCheck;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(String certificationDate) {
        this.certificationDate = certificationDate;
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

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public int getStylingNo() {
        return stylingNo;
    }

    public void setStylingNo(int stylingNo) {
        this.stylingNo = stylingNo;
    }

    public String print(){
        return " name : " + name  +" certificationDate : " +  certificationDate +" introduction : " + introduction  +" shopNo : " +  shopNo;
    }

}
