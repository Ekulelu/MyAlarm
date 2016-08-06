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

    //闹铃模式
    public static final int ONCE = 0;
    public static final int SEVERAL = 1;
    public static final int EVERY_WEEK = 2;
    public static final int EVERY_MONTH = 3;
    public static final int EVERY_YEAR = 4;


    private String message;
    private ArrayList<Calendar> alarmTimes;
    private int alarmMode;
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

}
