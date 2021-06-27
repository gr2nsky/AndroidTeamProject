package com.example.mogastyle.Common;

import android.app.Activity;

import java.util.ArrayList;

public class HomeAction {
    private static HomeAction homeAction = null;
    private ArrayList<Activity> stackedActivities = null;

    private HomeAction(){
        stackedActivities = new ArrayList<>();
    };

    public static HomeAction getHomeAction(){
        if (homeAction == null){
            homeAction = new HomeAction();
        }
        return homeAction;
    }

    public void goHome(){
        if (stackedActivities.isEmpty())
            return;
        for(Activity a : stackedActivities) {
            a.finish();
            stackedActivities.remove(a);
        }
    }

    public void addStack(Activity ac){
        stackedActivities.add(ac);
    }

    public void removeStack(Activity ac){
        stackedActivities.remove(ac);
    }

    public void initStack(){
        stackedActivities.clear();
    }
}
