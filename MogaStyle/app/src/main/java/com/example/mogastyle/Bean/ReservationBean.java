package com.example.mogastyle.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationBean {

    @SerializedName("no")
    @Expose
    int no;
    @SerializedName("reservationDate")
    @Expose
    String reservationDate;
    @SerializedName("reservationTime")
    @Expose
    int reservationTime;
    @SerializedName("totalPrice")
    @Expose
    int totalPrice;
    @SerializedName("cancelDate")
    @Expose
    String cancelDate;
    @SerializedName("designer_no")
    @Expose
    int designer_no;
    @SerializedName("shop_no")
    @Expose
    int shop_no;
    @SerializedName("user_no")
    @Expose
    int user_no;
    @SerializedName("styling_no")
    @Expose
    int styling_no;

    //-------------------//ㄹㅣ뷰//---------------------------------
    @SerializedName("reviewPhoto")
    @Expose
    String reviewPhoto;
    @SerializedName("reviewScore")
    @Expose
    int reviewScore;
    @SerializedName("reviewContent")
    @Expose
    String reviewContent;
    @SerializedName("userName")
    @Expose
    String userName;

    //-------------------//reservationCheck//---------------------------
    @SerializedName("stylingTitle")
    @Expose
    String stylingTitle;
    @SerializedName("shopName")
    @Expose
    String shopName;
    @SerializedName("designerName")
    @Expose
    String designerName;
    @SerializedName("shopImage")
    @Expose
    String shopImage;
    @SerializedName("designerImage")
    @Expose
    String designerImage;
    @SerializedName("shopAddress")
    @Expose
    String shopAddress;
    @SerializedName("leadTime")
    @Expose
    int leadTime;

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

    public String getStylingTitle() {
        return stylingTitle;
    }

    public void setStylingTitle(String stylingTitle) {
        this.stylingTitle = stylingTitle;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getDesignerImage() {
        return designerImage;
    }

    public void setDesignerImage(String designerImage) {
        this.designerImage = designerImage;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String ResToString(){
        return "no :" + no + " reservationDate : " + reservationDate + " reservationTime : "
                + reservationTime  + " totalPrice : " + totalPrice  + " stylingTitle : "
                + stylingTitle + " shopName : " + shopName + " designerName : " + designerName
                + " shopImage : " + shopImage + "  designerImage : " + designerImage + "  shopAddress : " + shopAddress
                + " cancelDate : " + cancelDate;
    }

    public String desResToString(){
        return "no :" + no + " reservationDate : " + reservationDate + " reservationTime : "
                + reservationTime  + " leadTime : " + leadTime;
    }

    public boolean reviewIsNull(){
        if (reviewContent.equals("null")){
            return true;
        }
        return false;
    }
}
