package com.ekulelu.myalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

import Util.MyToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ekulelu on 16/8/7.
 */
public class AddAlarmListViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private AlarmModel mData;


    public AddAlarmListViewAdapter(Context context, AlarmModel data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.getAlarmTimes().size() *10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.add_alarm_list_cell,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        Calendar c = mData.getAlarmTimes().get(position%2);
        holder.mTvTime.setText("" + (1 + c.get(Calendar.MONTH)) + " - " + c.get(Calendar.DAY_OF_MONTH));

        return convertView;
    }

    /**
     * 组件集合，对应list.xml中的控件
     *
     */
    public static class ViewHolder {

        @BindView(R.id.add_alarm_list_item_Tv_time)  TextView mTvTime;

        @BindView(R.id.add_alarm_list_item_ImaBtn_delete) ImageButton mImgBtnDel;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.add_alarm_list_item_ImaBtn_delete) void delete() {
            MyToast.showShortText("被删除了," + mTvTime.getText());
        }
    }




}
