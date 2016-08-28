package com.ekulelu.myalarm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

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

    //时间
    private int mHourOfDay;
    private int mMinute;

    //信息
    private String mMessage;
    //选中的周几,用在每周循环模式
    private int mDaysOfWeek;
    //日期,可能有多个
    private ArrayList<EKDate> mAlarmDates;
    //重复模式
    private int mAlarmMode;
    //对飞行模式的设置
    private int mAirPlaneMode;



    public AlarmModel(String message, ArrayList<EKDate> alarmDates, int alarmMode, int airPlaneMode){
        this(-1, -1, message, alarmDates, -1, alarmMode, airPlaneMode);
    }

    public AlarmModel(ArrayList<EKDate> alarmDates, int alarmMode, int airPlaneMode){
        this(-1, -1 , null, alarmDates, -1, alarmMode, airPlaneMode);
    }

    public AlarmModel(int hourOfDay, int minute, String message, ArrayList<EKDate> alarmDates, int daysOfWeek,int alarmMode, int airPlaneMode){
        if (hourOfDay < 0 || minute <0 ){
            Calendar c = Calendar.getInstance();
            mHourOfDay = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
        } else {
            mHourOfDay = hourOfDay;
            mMinute = minute;
        }

        mMessage = message;
        mAlarmDates = alarmDates;
        mAlarmMode = alarmMode;
        mAirPlaneMode = airPlaneMode;
        if (daysOfWeek < 0) {
            int a = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            int c = a - 1;
            mDaysOfWeek = 0x01 << c;
        } else {
            mDaysOfWeek = daysOfWeek;
        }

    }

    public AlarmModel(){
        this(-1, -1, null, new ArrayList<EKDate>(), -1, ONCE, AIRPLANE_MODE_DONOTHING);
    }


    public String getMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public ArrayList<EKDate> getmAlarmDates() {
        return mAlarmDates;
    }

    public void setAlarmDates(ArrayList<EKDate> mAlarmDates) {
        this.mAlarmDates = mAlarmDates;
    }

    public int getAlarmMode() {
        return mAlarmMode;
    }

    public void setAlarmMode(int alarmMode) {
        this.mAlarmMode = alarmMode;
    }

    public int getAirPlaneMode() {
        return mAirPlaneMode;
    }

    public void setAirPlaneMode(int airPlaneMode) {
        this.mAirPlaneMode = airPlaneMode;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        this.mMinute = minute;
    }

    public int getHourOfDay() {
        return mHourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.mHourOfDay = hourOfDay;
    }

    public int getmDaysOfWeek() {
        return mDaysOfWeek;
    }

    public void setmDaysOfWeek(int mDaysOfWeek) {
        this.mDaysOfWeek = mDaysOfWeek;
    }

//    public boolean containDay(EKDate date){
//        for (EKDate c: mAlarmDates ) {
//            if (isEqualCalendar(c, date)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isEqualCalendar(EKDate d1, EKDate d2) {
//        return d1.getYear() == d2.getYear()
//                && d1.getMonth() == d2.getMonth()
//                && d1.getDateOfMonth() == d2.getDateOfMonth();
//    }

    public void sort() {
        Collections.sort(mAlarmDates, new Comparator<EKDate>() {
            @Override
            public int compare(EKDate d1, EKDate d2) {
                if (d1.getYear() > d2.getYear()) {
                    return 1;
                } else if (d1.getYear() < d2.getYear()){
                    return -1;
                } else {
                    if (d1.getMonth() > d2.getMonth()) {
                        return 1;
                    } else if (d1.getMonth() < d2.getMonth()){
                        return -1;
                    } else {
                        if (d1.getDateOfMonth() > d2.getDateOfMonth()) {
                            return 1;
                        } else if (d1.getDateOfMonth() < d2.getDateOfMonth()){
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });
    }


}
