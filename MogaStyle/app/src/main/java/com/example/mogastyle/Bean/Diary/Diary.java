package com.example.mogastyle.Bean.Diary;

public class Diary {
    private String image;
    private String title;
    private String hairLength;
    private String infoCount;
    private int no;

    public Diary(String image, String title, String hairLength , int no) {
        this.image = image;
        this.title = title;
        this.hairLength = hairLength;
        this.no = no;
    }

    public Diary(String image, String title, String hairLength, String infoCount) {
        this.image = image;
        this.title = title;
        this.hairLength = hairLength;
        this.infoCount = infoCount;
    }

    public String getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(String infoCount) {
        this.infoCount = infoCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHairLength() {
        return hairLength;
    }

    public void setHairLength(String hairLength) {
        this.hairLength = hairLength;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
