/*

제작 : 윤재필
20210621 테스트용으로 임시로 제작함.

 */
package com.example.mogastyle.Bean;

public class TempStyleBean {
    int no;
    String title;
    String typeCode;
    int price;

    public TempStyleBean() {

    }

    public TempStyleBean(int no, String title, int price) {
        this.no = no;
        this.title = title;
        this.price = price;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
