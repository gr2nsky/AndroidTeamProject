package com.example.mogastyle.Bean.Diary;

public class DiaryBookList {

    int no;
    String image;
    String styleDate;
    String hairShop;
    String designerName;
    String contents;

    public DiaryBookList(int no, String image, String styleDate, String hairShop, String designerName, String contents) {
        this.no = no;
        this.image = image;
        this.styleDate = styleDate;
        this.hairShop = hairShop;
        this.designerName = designerName;
        this.contents = contents;
    }

    public DiaryBookList(int no, String image, String styleDate, String hairShop, String designerName) {
        this.no = no;
        this.image = image;
        this.styleDate = styleDate;
        this.hairShop = hairShop;
        this.designerName = designerName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStyleDate() {
        return styleDate;
    }

    public void setStyleDate(String styleDate) {
        this.styleDate = styleDate;
    }

    public String getHairShop() {
        return hairShop;
    }

    public void setHairShop(String hairShop) {
        this.hairShop = hairShop;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
