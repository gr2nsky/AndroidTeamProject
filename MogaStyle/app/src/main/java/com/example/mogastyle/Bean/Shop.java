package com.example.mogastyle.Bean;

public class Shop {
    private String name;
    private String tel;
    private String address;
    private String postcode;
    private int image;

    public Shop(String name, String tel, String address, String postcode, int image) {
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
}
