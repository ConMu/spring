package netease.acdtest.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yine
 * @createTime 2021/4/20 4:18 下午
 * @description
 */
public class TimeUtils {

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Long getCurrentSeconds() {
        return System.currentTimeMillis()/1000;
    }

    public static Long todayStart() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        Date time = currentDate.getTime();
        return time.getTime();
    }

    public static long getNowMillisec() {
        return System.currentTimeMillis();
    }

    public static String YYYY_MM_DD() {
        Date now=new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        return time.format(now);
    }

    public static String YYYY_MM_DD_HH_MM_SS() {
        Date now=new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(now);
    }

    // 时间转换为时间戳
    public static Long dateToStamp(String s) {
        Long ts = 0L;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            ts = date.getTime();
        } catch (ParseException e) {
            ts = 0L;
        }

        return ts;
    }

    public static long getTodayTime(){
        long currentTimestamps=System.currentTimeMillis()/1000;
        long oneDayTimestamps=60 * 60 * 24;
        return currentTimestamps-(currentTimestamps+60*60*8)%oneDayTimestamps;
    }

    public static long getYesterdayTime(){
        long currentTimestamps=System.currentTimeMillis()/1000;
        long oneDayTimestamps=60 * 60 * 24;
        return currentTimestamps-(currentTimestamps+60*60*8)%oneDayTimestamps-oneDayTimestamps;
    }

    public static boolean isFirstDayOnMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    public static long getPreMonthFirstDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime().getTime()/1000;
    }

    public static long getPreMonthLastDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime().getTime()/1000;
    }

    public static long getNextMonthFirstDay(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND,0);
        return calendar1.getTime().getTime()/1000;
    }

    public static int getYearPreMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonthPreMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Long getFirstTime(int year, int month){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, year);
        calendar1.set(Calendar.MONTH, month -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND,0);
        return calendar1.getTime().getTime()/1000;
    }

    public static Long getLastTime(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime().getTime()/1000;
    }

    public static void main(String... strings) {
        System.out.println(getFirstTime(2019, 9));
    }
}
