package com.ekulelu.myalarm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

import Util.EKRecyclerView;

/**
 * Created by Ekulelu on 16/8/27.
 */
public class AddAlarmRecyclerView extends EKRecyclerView {
    private AlarmModel mAlarmModel;

    public AlarmModel getmAlarmModel() {
        return mAlarmModel;
    }

    public void setmAlarmModel(AlarmModel mAlarmModel) {
        this.mAlarmModel = mAlarmModel;
    }

    public AddAlarmRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutManager receiveLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public int getItemCount() {
        return mAlarmModel.getAlarmTimes().size();
    }

    @Override
    public void bindDataToView(ViewHolder viewHolder, final int position) {
        Calendar calendar = mAlarmModel.getAlarmTimes().get(position);
        int month = calendar.get(Calendar.MONTH) + 1;  //因为从0开始算起
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        final AddAlarmRecyclerViewHolder holder = (AddAlarmRecyclerViewHolder) viewHolder;
        String strDate = month + " - " + date;
        holder.mTvDate.setText(strDate);
        holder.mImgBtnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmModel.getAlarmTimes().remove(holder.getLayoutPosition());
                AddAlarmRecyclerView.this.removeItem(holder.getLayoutPosition());
                if (mAlarmModel.getAlarmTimes().size() == 1) {
                    AddAlarmRecyclerView.this.getAdapter().notifyItemChanged(0,null);
                }
            }
        });
        if (mAlarmModel.getAlarmTimes().size() <= 1) {
            holder.mImgBtnDel.setVisibility(INVISIBLE);
        }
    }

    /**
     * 给外部添加日期后调用刷新
     */
    public void addDate() {
        addItem(mAlarmModel.getAlarmTimes().size() - 1);
        getAdapter().notifyItemChanged(0,null); //确保更新了第一个item
    }


    @Override
    public int getItemViewResourceId() {
        return R.layout.add_alarm_list_item;
    }

    @Override
    public Class receiveViewHolderClass() {
        return AddAlarmRecyclerViewHolder.class;
    }

    /**
     * 组件集合，对应list.xml中的控件
     *
     */
    public static class AddAlarmRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView mTvDate;

        ImageButton mImgBtnDel;

        public AddAlarmRecyclerViewHolder(View view) {
            super(view);
            mTvDate = (TextView) view.findViewById(R.id.add_alarm_list_item_Tv_date);
            mImgBtnDel = (ImageButton) view.findViewById(R.id.add_alarm_list_item_ImaBtn_delete);
        }
    }
}
