package com.example.mogastyle.Bean;

public class ReservationBean {

    int no;
    String reservationDate;
    int reservationTime;
    int totalPrice;
    String cancelDate;

    String reviewPhoto;
    int reviewScore;
    String reviewContent;

    int designer_no;
    int shop_no;
    int user_no;
    int styling_no;

    //등록용
    public ReservationBean(String reservationDate, int reservationTime, int totalPrice,
                           int designer_no, int shop_no, int user_no, int styling_no) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.totalPrice = totalPrice;
        this.designer_no = designer_no;
        this.shop_no = shop_no;
        this.user_no = user_no;
        this.styling_no = styling_no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(int reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getReviewPhoto() {
        return reviewPhoto;
    }

    public void setReviewPhoto(String reviewPhoto) {
        this.reviewPhoto = reviewPhoto;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getDesigner_no() {
        return designer_no;
    }

    public void setDesigner_no(int designer_no) {
        this.designer_no = designer_no;
    }

    public int getShop_no() {
        return shop_no;
    }

    public void setShop_no(int shop_no) {
        this.shop_no = shop_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getStyling_no() {
        return styling_no;
    }

    public void setStyling_no(int styling_no) {
        this.styling_no = styling_no;
    }
}
