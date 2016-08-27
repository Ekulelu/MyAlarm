package com.ekulelu.myalarm;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by aahu on 2016/8/6 0006.
 */
public class AlarmModel {
    //对飞行模式的设置
    public static final int AIRPLANE_MODE_DONOTHING = 0;
    public static final int AIRPLANE_MODE_ON = 1;
    public static final int AIRPLANE_MODE_OFF = 2;

    //闹铃重复模式
    public static final int ONCE = 0;
    public static final int SEVERAL = 1;
    public static final int EVERY_WEEK = 2;
    public static final int EVERY_MONTH = 3;
    public static final int EVERY_YEAR = 4;

    //信息
    private String message;
    //日期,可能有多个
    private ArrayList<Calendar> alarmTimes;
    //重复模式
    private int alarmMode;
    //对飞行模式的设置
    private int airPlaneMode;

    public AlarmModel(String message, ArrayList<Calendar> alarmTimes, int alarmMode, int airPlaneMode){
        this.message = message;
        this.alarmTimes = alarmTimes;
        this.alarmMode = alarmMode;
        this.airPlaneMode = airPlaneMode;
    }

    public AlarmModel(ArrayList<Calendar> alarmTimes, int alarmMode, int airPlaneMode){
        this.message = null;
        this.alarmTimes = alarmTimes;
        this.alarmMode = alarmMode;
        this.airPlaneMode = airPlaneMode;
    }

    public AlarmModel(){

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Calendar> getAlarmTimes() {
        return alarmTimes;
    }

    public void setAlarmTimes(ArrayList<Calendar> alarmTimes) {
        this.alarmTimes = alarmTimes;
    }

    public int getAlarmMode() {
        return alarmMode;
    }

    public void setAlarmMode(int alarmMode) {
        this.alarmMode = alarmMode;
    }

    public int getAirPlaneMode() {
        return airPlaneMode;
    }

    public void setAirPlaneMode(int airPlaneMode) {
        this.airPlaneMode = airPlaneMode;
    }
}
