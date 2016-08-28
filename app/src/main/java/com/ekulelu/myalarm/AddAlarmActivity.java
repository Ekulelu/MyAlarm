package com.ekulelu.myalarm;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import Util.CustomCalendarView;
import Util.EKRecyclerView;
import Util.MyLog;
import Util.MyToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlarmActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        DatePickerDialog.OnDateSetListener, EKRecyclerView.OnItemClickLitener,TimePickerDialog.OnTimeSetListener{
    public static final String ALARM_MODEL = "ALARM_MODEL";
    public static int DATE_DIALOG_MODE_ADD = 0;
    public static int DATE_DIALOG_MODE_MODIFY = 1;

    @BindView(R.id.add_alarm_recycler_view)
    AddAlarmRecyclerView mRecyclerView;

    @BindView(R.id.alarmModeGroup)
    RadioGroup mRadioGrpAlarmModes;

    @BindView(R.id.image_button_add_date)
    ImageButton mImgBtnAddDate;

    @BindView(R.id.week_btn_group)
    EKWeekButtonGroup mWeekButtonGroup;

    @BindView(R.id.textView_time)
    TextView mTvTime;

    @BindView(R.id.text_view_date)
    TextView mTvDate;

    @BindView(R.id.calendar_view)
    CustomCalendarView mCalendarView;

    private AlarmModel mAlarmModel;

    private DatePickerDialog mDatePickerDialog;

    private int mDateDialogMode;
    private int mDateSelectedItemPosition;

    private TimePickerDialog mTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);

        mAlarmModel = (AlarmModel) getIntent().getSerializableExtra(ALARM_MODEL);
        if (mAlarmModel == null) {
            mAlarmModel = getData();
        }

        Calendar c = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, this,c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        mTimePickerDialog = new TimePickerDialog(this, this,c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),true);

        //下面开始更新整个页面数据。
        int hour = mAlarmModel.getHourOfDay();
        int minute = mAlarmModel.getMinute();
        String textTime = "" + hour + " : " + minute;
        if(minute < 10) {
            textTime = "" + hour + " : 0" + minute;
        }
        mTvTime.setText(textTime);  //更新显示的时间

        mRadioGrpAlarmModes.check(getRadioBtnId());  //更新模式

        mRadioGrpAlarmModes.setOnCheckedChangeListener(this);

        mRecyclerView.setmAlarmModel(mAlarmModel); //更新日期列表
        mRecyclerView.setOnItemClickLitener(this); //设置行按下监听

        mWeekButtonGroup.setCheckBtnFlag(mAlarmModel.getmDaysOfWeek()); //更新选中的dayOfWeek

        mImgBtnAddDate.setVisibility(View.INVISIBLE);
        mWeekButtonGroup.setVisibility(View.GONE);

        mCalendarView.setVisibility(View.GONE);
    }

    private int getRadioBtnId() {
        int id = R.id.radio_button_once;
        switch (mAlarmModel.getAlarmMode()) {
            case AlarmModel.ONCE:
                id = R.id.radio_button_once;
                break;
            case AlarmModel.SEVERAL:
                id = R.id.radio_button_several;
                break;
            case AlarmModel.EVERY_WEEK:
                id = R.id.radio_button_week;
                break;
            case AlarmModel.EVERY_MONTH:
                id = R.id.radio_button_month;
        }
        return id;
    }


    //日期控件的回调方法
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        EKDate date = new EKDate();
        date.setYear(year);
        date.setMonth(monthOfYear);
        date.setDateOfMonth(dayOfMonth);

        if (mAlarmModel.getmAlarmDates().contains(date)) {
            MyToast.showShortText("day contain already!!!");
            return;
        }
        if (mDateDialogMode == DATE_DIALOG_MODE_ADD) {
            mAlarmModel.getmAlarmDates().add(date);
            mAlarmModel.sort();
            mRecyclerView.updateAllItems();
        } else {
            mAlarmModel.getmAlarmDates().set(mDateSelectedItemPosition,date);
            mAlarmModel.sort();
            mRecyclerView.updateAllItems();
        }
    }

    //时间控件的回调方法
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mAlarmModel.setHourOfDay(hourOfDay);
        mAlarmModel.setMinute(minute);
        String textTime = "" + hourOfDay + " : " + minute;
        if(minute < 10) {
            textTime = "" + hourOfDay + " : 0" + minute;
        }
        mTvTime.setText(textTime);  //更新显示的时间
    }

    @Override
    public void onItemClick(View view, int position) {
        mDateDialogMode = DATE_DIALOG_MODE_MODIFY;
        mDateSelectedItemPosition = position;
        EKDate date = mAlarmModel.getmAlarmDates().get(position);
        mDatePickerDialog.updateDate(date.getYear(), date.getMonth(), date.getDateOfMonth());
        mDatePickerDialog.show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_button_once:
                mImgBtnAddDate.setVisibility(View.GONE);
                mWeekButtonGroup.setVisibility(View.GONE);
                mTvDate.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAlarmModel.setAlarmMode(AlarmModel.ONCE);
                break;
            case R.id.radio_button_several:
                mImgBtnAddDate.setVisibility(View.VISIBLE);
                mWeekButtonGroup.setVisibility(View.GONE);
                mTvDate.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAlarmModel.setAlarmMode(AlarmModel.SEVERAL);
                break;
            case R.id.radio_button_week:
                mImgBtnAddDate.setVisibility(View.GONE);
                mWeekButtonGroup.setVisibility(View.VISIBLE);
                mTvDate.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                mCalendarView.setVisibility(View.GONE);
                mAlarmModel.setAlarmMode(AlarmModel.EVERY_WEEK);
                break;
            case R.id.radio_button_month:
                mImgBtnAddDate.setVisibility(View.GONE);
                mWeekButtonGroup.setVisibility(View.GONE);
                mTvDate.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mCalendarView.setVisibility(View.VISIBLE);
                mAlarmModel.setAlarmMode(AlarmModel.EVERY_MONTH);
                break;
            default:
        }
    }


    public AlarmModel getData() {

        ArrayList<EKDate> dates = new ArrayList<EKDate>();
        dates.add(new EKDate());
        mAlarmModel = new AlarmModel(dates,AlarmModel.ONCE,AlarmModel.AIRPLANE_MODE_DONOTHING);
        mAlarmModel.setmMessage("第一次设闹铃,好紧张啊");


        return mAlarmModel;
    }


    //添加日期按钮被按下
    @OnClick(R.id.image_button_add_date) void addDate(View view) {
        Calendar calendar = Calendar.getInstance();
        mDateDialogMode = DATE_DIALOG_MODE_ADD;
        mDatePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }


    //时间显示控件被按下
    @OnClick(R.id.textView_time) void tvTimeClicked(View view) {
        mTimePickerDialog.updateTime(mAlarmModel.getHourOfDay(),mAlarmModel.getMinute());
        mTimePickerDialog.show();
    }

    //完成按钮被按下
    @OnClick(R.id.btn_add_alarm) void addAlarm(View view) {
        switch (mAlarmModel.getAlarmMode()) {
            case AlarmModel.ONCE:
                MyLog.e("--ok, once" + mAlarmModel.getmAlarmDates().get(0).getMonth() + "-" +
                mAlarmModel.getmAlarmDates().get(0).getDateOfMonth() + " " + mAlarmModel.getHourOfDay() +
                ":" +mAlarmModel.getMinute());
                break;
            case AlarmModel.SEVERAL:
                MyLog.e("---ok,several");
                break;
            case AlarmModel.EVERY_WEEK:
                MyLog.e("---ok,week");
                break;
            case AlarmModel.EVERY_MONTH:
                MyLog.e("---ok, month");
                break;
            default:
        }
    }
}
