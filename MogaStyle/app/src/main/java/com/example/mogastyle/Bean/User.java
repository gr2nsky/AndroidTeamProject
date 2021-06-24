/*
    <<<<<<<<작성 2021.6.15 윤재필
    유저의 모든 속성을 지닌 bean
    >>>>>>>>
 */

package com.example.mogastyle.Bean;

import com.example.mogastyle.Common.LoginedUserInfo;
import com.example.mogastyle.Common.ShareVar;

public class User {

    int no;
    String id;
    String name;
    String gender;
    String birthday;
    String hairType;
    String joinType;
    String userCheck;
    //추가
    String userImage;
    String userPhone;

    public User() {
    }

//    User user = new User(userNo,userId,userName,userImage,userPhone,userJoinType,userCheck);가


    public User(int no, String id, String name, String userImage, String userPhone,String joinType, String userCheck) {
        this.no = no;
        this.id = id;
        this.name = name;
        this.joinType = joinType;
        this.userCheck = userCheck;
        this.userImage = userImage;
        this.userPhone = userPhone;
    }

    public User(int no, String id, String name, String gender, String birthday, String hairType, String joinType, String userCheck) {
        this.no = no;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.hairType = hairType;
        this.joinType = joinType;
        this.userCheck = userCheck;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(String userCheck) {
        this.userCheck = userCheck;
    }

    //추
    public String getUserImage() {
        if(joinType.equals("0")){
            return ShareVar.userImgPath + userImage;
        }
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    //Log용 테스트 메서드
    public String printAll(){
        String str = "no : " + no + ", id : " + id + ", name : " + name +
                ", gender : " + gender + ", birthday : " + birthday + ", hairType : " +
                hairType + ", joinType : " + joinType + ", userCheck : " + userCheck;

        return str;
    }
}
