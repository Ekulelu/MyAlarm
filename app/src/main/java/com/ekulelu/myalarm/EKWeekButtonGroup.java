package com.ekulelu.myalarm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;


import java.util.ArrayList;




/**
 * Created by Ekulelu on 16/8/27.
 */
public class EKWeekButtonGroup extends FrameLayout implements View.OnClickListener{
    private ArrayList<CheckBox> mCheckBoxes;

    /**
     * 用位表示选中的button。
     */
    private int mCheckBtnFlag;

    public int getCheckBtnFlag() {
        return mCheckBtnFlag;
    }

    public void setCheckBtnFlag(int checkBtnFlag) {
        this.mCheckBtnFlag = checkBtnFlag;
        int i = 0;
        while (checkBtnFlag != 0) {
            int flag = checkBtnFlag  & 0x01;
            if (flag == 1) {
                mCheckBoxes.get(i).setChecked(true);
            }
            checkBtnFlag >>= 1;
            i++;
        }
    }

    public EKWeekButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EKWeekButtonGroup(Context context) {
        super(context);
        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_week_button,this);
        mCheckBoxes = new ArrayList<>();
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.week_btn_one);
        checkBox1.setOnClickListener(this);
        mCheckBoxes.add(checkBox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.week_btn_two);
        checkBox2.setOnClickListener(this);
        mCheckBoxes.add(checkBox2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.week_btn_three);
        checkBox3.setOnClickListener(this);
        mCheckBoxes.add(checkBox3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.week_btn_four);
        checkBox4.setOnClickListener(this);
        mCheckBoxes.add(checkBox4);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.week_btn_five);
        checkBox5.setOnClickListener(this);
        mCheckBoxes.add(checkBox5);
        CheckBox checkBox6 = (CheckBox) findViewById(R.id.week_btn_six);
        checkBox6.setOnClickListener(this);
        mCheckBoxes.add(checkBox6);
        CheckBox checkBox7 = (CheckBox) findViewById(R.id.week_btn_seven);
        checkBox7.setOnClickListener(this);
        mCheckBoxes.add(checkBox7);

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.week_btn_one:
                mCheckBtnFlag ^= 0x01;
                break;
            case R.id.week_btn_two:
                mCheckBtnFlag ^= 0x02;
                break;
            case R.id.week_btn_three:
                mCheckBtnFlag ^= 0x04;
                break;
            case R.id.week_btn_four:
                mCheckBtnFlag ^= 0x08;
                break;
            case R.id.week_btn_five:
                mCheckBtnFlag ^= 0x10;
                break;
            case R.id.week_btn_six:
                mCheckBtnFlag ^= 0x20;
                break;
            case R.id.week_btn_seven:
                mCheckBtnFlag ^= 0x40;
                break;
            default:
        }

//        MyLog.e("-----" + mCheckBtnFlag);
    }
}
