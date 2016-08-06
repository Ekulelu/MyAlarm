package Util;

import android.widget.Toast;

/**Toast used ContextUtil for short call of makeText
 * Created by aahu on 2016/8/4 0004.
 */
public class MyToast {
    private MyToast() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static void showShortText(String string) {
        Toast.makeText(ContextUtil.getInstance(), string,Toast.LENGTH_SHORT).show();
    }

    public static void showLongText(String string) {
        Toast.makeText(ContextUtil.getInstance(), string, Toast.LENGTH_LONG).show();
    }
    /**
     * 自定义显示Toast时间
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration)
    {
        Toast.makeText(ContextUtil.getInstance(), message, duration).show();
    }

}
