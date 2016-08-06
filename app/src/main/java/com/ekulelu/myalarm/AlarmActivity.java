package com.ekulelu.myalarm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Alarm received activity
 */

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        new AlertDialog.Builder(AlarmActivity.this).
                setTitle("闹钟").//设置标题
                setMessage("时间到了！").//设置内容
                setPositiveButton("知道了", new DialogInterface.OnClickListener() {//设置按钮
            public void onClick(DialogInterface dialog, int which) {
                AlarmActivity.this.finish();//关闭Activity
            }
        }).create().show();
    }
}
