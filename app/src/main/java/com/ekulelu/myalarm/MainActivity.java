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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnDeleteAlarmClicked(View view){

    }

    public void btnAddAlarmClicked(View view){

        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());


        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);    //创建Intent对象
//                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);    //创建PendingIntent
                //alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);        //设置闹钟
//                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);        //设置闹钟，当前时间就唤醒
//        MyToast.showShortText(calendar.get(Calendar.HOUR_OF_DAY) + ": " + calendar.get(Calendar.MINUTE) );
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
    }
}
