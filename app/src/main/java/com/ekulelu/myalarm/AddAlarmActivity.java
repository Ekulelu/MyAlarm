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
import android.widget.ImageView;
import android.widget.ListView;
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

public class AddAlarmActivity extends AppCompatActivity {

    @BindView(R.id.listView_date)
    ListView mListView;

    private AlarmModel mAlarmModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
        AddAlarmListViewAdapter adapter = new AddAlarmListViewAdapter(this,getData());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.e("dianji l :" + i);
                MyToast.showShortText("dfdfdffd");
            }
        });

    }


    public AlarmModel getData() {

        ArrayList<Calendar> times = new ArrayList<Calendar>();
        Calendar c1 = Calendar.getInstance();
//        c1.set(Calendar.DAY_OF_MONTH,31);
//        c1.set(Calendar.MONTH,0);
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.DAY_OF_MONTH,1);
        c2.set(Calendar.MONTH,11);

        times.add(c1);
        times.add(c2);
        mAlarmModel = new AlarmModel(times,AlarmModel.SEVERAL,AlarmModel.AIRPLANE_MODE_DONOTHING);
        mAlarmModel.setMessage("第一次设闹铃,好紧张啊");


        return mAlarmModel;
    }








}
