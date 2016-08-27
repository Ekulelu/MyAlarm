package com.ekulelu.myalarm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.MyLog;
import Util.MyToast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAlarmActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{



    @BindView(R.id.add_alarm_recycler_view)
    AddAlarmRecyclerView mRecyclerView;

    @BindView(R.id.alarmModeGroup)
    RadioGroup mRadioGrpAlarmModes;

    @BindView(R.id.image_button_add_date)
    ImageButton mImgBtnAddDate;

    @BindView(R.id.week_btn_group)
    EKWeekButtonGroup mWeekButtonGroup;

    private AlarmModel mAlarmModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);

        mRadioGrpAlarmModes.setOnCheckedChangeListener(this);

        mRecyclerView.setmAlarmModel(this.getData());

        mWeekButtonGroup.setCheckBtnFlag(0x41);
        mImgBtnAddDate.setVisibility(View.INVISIBLE);
        mWeekButtonGroup.setVisibility(View.GONE);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_button_once:
                mImgBtnAddDate.setVisibility(View.INVISIBLE);
                mWeekButtonGroup.setVisibility(View.GONE);
                break;
            case R.id.radio_button_several:
                mImgBtnAddDate.setVisibility(View.VISIBLE);
                mWeekButtonGroup.setVisibility(View.GONE);
                break;
            case R.id.radio_button_week:
                mImgBtnAddDate.setVisibility(View.INVISIBLE);
                mWeekButtonGroup.setVisibility(View.VISIBLE);
                MyLog.e("-----mode : Week");
                break;
            case R.id.radio_button_month:
                mImgBtnAddDate.setVisibility(View.INVISIBLE);
                mWeekButtonGroup.setVisibility(View.GONE);
                MyLog.e("-----mode : Month");
                break;
            default:
        }
    }

    public AlarmModel getData() {

        ArrayList<Calendar> times = new ArrayList<Calendar>();
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DAY_OF_MONTH,31);
        c1.set(Calendar.MONTH,0);
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.DAY_OF_MONTH,1);
        c2.set(Calendar.MONTH,1);


        Calendar c3 = Calendar.getInstance();
        c3.set(Calendar.DAY_OF_MONTH,1);
        c3.set(Calendar.MONTH,2);


        times.add(c1);
        times.add(c2);
        times.add(c3);
        mAlarmModel = new AlarmModel(times,AlarmModel.SEVERAL,AlarmModel.AIRPLANE_MODE_DONOTHING);
        mAlarmModel.setMessage("第一次设闹铃,好紧张啊");


        return mAlarmModel;
    }








}
