package com.example.mogastyle.Bean;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationList {
    @SerializedName("reservation_list")
    @Expose
    private ArrayList<ReservationBean> reservationList = null;

    public ArrayList<ReservationBean> getReservationList() {
        return reservationList;
    }

    public void setReservationList(ArrayList<ReservationBean> reservationList) {
        this.reservationList = reservationList;
    }

    public String print(){
        String result = "";
        for (int i = 0; i < reservationList.size(); i++){
            result += "[" + i + "]" + reservationList.get(i).ResToString();
            result += "\n";
        }
        return result;
    }

    public String desPrint(){
        String result = "";
        for (int i = 0; i < reservationList.size(); i++){
            result += "[" + i + "]" + reservationList.get(i).desResToString();
            result += "\n";
        }
        return result;
    }
}
