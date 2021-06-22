package com.example.mogastyle.Bean;

public class Designer {

    // Field
    int no;
    String name;
    String gender;
    String userCheck;
    String certificationDate;
    String introduction;
    String holiday;

    // Field_외래키
    int shopNo;
    int stylingNo;

    // 여기에서 이미지는 따로 불러오지 않는다.

    // 생성자(Constructor)

    public Designer(int no, String name, String gender, String userCheck, String certificationDate, String introduction, String holiday, int shopNo, int stylingNo) {
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.userCheck = userCheck;
        this.certificationDate = certificationDate;
        this.introduction = introduction;
        this.holiday = holiday;
        this.shopNo = shopNo;
        this.stylingNo = stylingNo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

} // --------------------------------