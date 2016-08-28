package com.ekulelu.myalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import Util.MyLog;
import Util.MyToast;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initActionBar();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    }

    @OnClick(R.id.btn_delete_alarm) void btnDeleteAlarmClicked(View view){
        Calendar c=Calendar.getInstance();

        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MyToast.showShortText(year + ":" + monthOfYear + ":" + dayOfMonth);
            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.btn_add_alarm) void btnAddAlarmClicked(View view){
        Intent intent = new Intent(this, AddAlarmActivity.class);
        intent.putExtra(AddAlarmActivity.CREATE_FLAG, AddAlarmActivity.CREATE_FLAG_ADD);
        startActivity(intent);


//        calendar.setTimeInMillis(System.currentTimeMillis());
//
//        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Calendar c=Calendar.getInstance();//获取日期对象
//                c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
////                c.set(Calendar.HOUR, hourOfDay);        //设置闹钟小时数
////                c.set(Calendar.MINUTE, minute);            //设置闹钟的分钟数
////                c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
////                c.set(Calendar.MILLISECOND, 0);            //设置闹钟的毫秒数
//                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);    //创建Intent对象
//                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);    //创建PendingIntent
//                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);        //设置闹钟
////                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);        //设置闹钟，当前时间就唤醒
//
//            }
//        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Subscribe
    public void onEvent(AlarmModel alarmModel) {
        MyLog.e(alarmModel.getHourOfDay() +" :" + alarmModel.getMinute());
    }
}
