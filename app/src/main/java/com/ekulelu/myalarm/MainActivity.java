package com.ekulelu.myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import Util.MyToast;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnDeleteAlarmClicked(View view){

    }

    public void btnAddAlarmClicked(View view){

        calendar.setTimeInMillis(System.currentTimeMillis());

        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();//获取日期对象
                c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
                c.set(Calendar.HOUR, hourOfDay);        //设置闹钟小时数
                c.set(Calendar.MINUTE, minute);            //设置闹钟的分钟数
                c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
                c.set(Calendar.MILLISECOND, 0);            //设置闹钟的毫秒数
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);    //创建Intent对象
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);    //创建PendingIntent
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);        //设置闹钟
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);        //设置闹钟，当前时间就唤醒
        MyToast.showShortText(hourOfDay + ":" + minute);
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
    }
}
