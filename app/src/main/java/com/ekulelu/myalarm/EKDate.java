package com.ekulelu.myalarm;

import java.util.Calendar;

/**
 * Created by Ekulelu on 16/8/28.
 */
public class EKDate {
    private int mYear;
    private int mMonth;
    private int mDateOfMonth;


    public EKDate() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }


    public int getYear() {
        return mYear;
    }

    public void setYear(int mYear) {
        this.mYear = mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getDateOfMonth() {
        return mDateOfMonth;
    }

    public void setDateOfMonth(int mDateOfMonth) {
        this.mDateOfMonth = mDateOfMonth;
    }

    @Override
    public boolean equals(Object o) {
        EKDate d2 = (EKDate) o;
        return getYear() == d2.getYear()
                && getMonth() == d2.getMonth()
                && getDateOfMonth() == d2.getDateOfMonth();
    }
}
