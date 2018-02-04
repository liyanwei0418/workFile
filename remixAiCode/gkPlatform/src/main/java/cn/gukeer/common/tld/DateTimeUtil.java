package cn.gukeer.common.tld;


import java.util.Calendar;

/**
 * Created by conn on 2016/8/11.
 */
public class DateTimeUtil {

    public static String formatDateInterval(long timestamp) {

        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());

        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH)+1;
        int nowDay = now.get(Calendar.DAY_OF_MONTH);
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int nowMinute = now.get(Calendar.MINUTE);

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timestamp);

        int year = time.get(Calendar.YEAR);
        //int month = time.get(Calendar.MONTH);//读取月份少于当前月份
        int month = time.get(Calendar.MONTH)+1;
        int day = time.get(Calendar.DAY_OF_MONTH);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);

        String rst = null;
        if (now.compareTo(time) < 0) {
           // rst = String.format("%02d年%02d月%02d %02d:%02d", year, month, day, hour, minute);
            rst = String.format("%02d:%02d",hour, minute);
        } else if (year == nowYear && month == nowMonth) {
            if (nowDay - day > 3) {
               // rst = String.format("%02d年%02d月%02d %02d:%02d", year, month, day, hour, minute);
                rst = String.format("%02d:%02d",hour, minute);
            } else if (nowDay - day > 0) {
                rst = String.format("%d天前", nowDay - day);
            } else {
                if (nowHour - hour > 0) {
                    rst = String.format("%d小时前", nowHour - hour);
                } else {
                    if (nowMinute - minute > 5) {
                        rst = String.format("%d分钟前", nowMinute - minute);
                    } else {
                        rst = "刚刚";
                    }
                }
            }
        } else {
            //rst = String.format("%02d年%02d月%02d %02d:%02d", year, month, day, hour, minute);
            rst = String.format("%02d:%02d",hour, minute);
        }

        return rst;
    }

    public static void main(String[] args) {
        System.out.println(formatDateInterval(System.currentTimeMillis()-2111000));
    }
}
