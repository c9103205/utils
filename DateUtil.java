package com.cx.framework.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 時間工具類
 */
public class DateUtil {
    /**
     * @Fields DATE_FORMATE : 時間格式
     */
    public static final int TIMESTART_SUFFIX = 1;
    public static final int TIMEEND_SUFFIX = 2;
    public static final int SECSTART_SUFFIX = 3;
    public static final int SECEND_SUFFIX = 4;
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";
    public static final String TIME_START = " 00:00:00";
    public static final String TIME_END = " 23:59:59";
    public static final String SEC_START = ":00";
    public static final String SEC_END = ":59";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMssSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static final String YYYY_MM_DD_HHMM = "yyyy年MM月dd日 HH:mm";
    public static final String HH_MM_SS = "HH:mm:ss";

    public static SimpleDateFormat simpleDateFormat(String format) {
        return simpleDateFormat(format, Locale.getDefault());
    }

    public static SimpleDateFormat simpleDateFormat(String format, Locale locale) {
        return new SimpleDateFormat(format, locale);
    }

    public static SimpleDateFormat simpleDateFormat() {
        return simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 判斷是否為空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 去除空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 返回當前月第一天的日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 返回當前月第一天的日期
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth2(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(calendar.getTime());
        return firstDayOfMonth;
    }

    /**
     * 獲取某月的第一天
     *
     * @param month
     * @return
     */
    public static String getFisrtDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 設置月份
        cal.set(Calendar.MONTH, month - 1);
        // 獲取某月最小天數
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 設置日歷中月份的最小天數
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());

        return firstDayOfMonth;
    }

    /**
     * 返回當前月最後一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /***
     * 獲取下個月的今天
     *
     * @return
     */
    public static String getNextMonthDateStr2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat sdfLong = simpleDateFormat("yyyy-MM");
        String date = sdfLong.format(calendar.getTime());
        return date;
    }

    /***
     * 獲取上個月
     *
     * @return
     */
    public static String getNextMonthDateStr3() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat sdfLong = simpleDateFormat("yyyy-MM");
        String date = sdfLong.format(calendar.getTime());
        return date;
    }

    /**
     * 獲取當前日期的長整型
     */
    public static long getCurLong() {
        SimpleDateFormat sdfLong = simpleDateFormat("yyyyMMddHHmmss");
        String date = sdfLong.format(new Date());
        return Long.parseLong(date);
    }

    /**
     * 獲取當前日期的長整型
     */
    public static long getCurMinuteLong() {
        SimpleDateFormat sdfLong = simpleDateFormat("yyyyMMddHHmm00");
        String date = sdfLong.format(new Date());
        return Long.parseLong(date);
    }

    /**
     * 獲取當前日期的長整型
     */
    public static long getCurrLong() {
        Date date = new Date();
        return date.getTime() / 1000;
    }

    /**
     * 獲得正確的 時間
     *
     * @return yyyyMMddHHmmsss
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat("yyyyMMddHHmmsss");
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;

    }

    /**
     * 字符串轉日期
     *
     * @return yyyyMMddHHmmsss
     * @throws ParseException
     */
    public static String getDateToString(Date date, String format) {
        SimpleDateFormat sdf = simpleDateFormat(format);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;

    }

    /**
     * 字符串轉日期
     *
     * @return yyyyMMddHHmmsss
     * @throws ParseException
     */
    public static String getDateToString(Date date) {
        SimpleDateFormat sdf = simpleDateFormat("yyyy-MM-dd");
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;

    }

    /**
     * 獲得當前的日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNowDate() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 獲得當前的時間
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 根據時區獲得當前的時間
     *
     * @param timeZone 時區
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime(String timeZone) {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 取得系統當前日期的字符串形式
     *
     * @param str
     * @return 指定格式的日期字符串
     */
    public static String getCurrentDate(String str) {
        SimpleDateFormat format = simpleDateFormat(str);
        String date = format.format(Calendar.getInstance().getTime());
        return date;
    }

    /**
     * 解析日期格式yyyy-MM-dd為yyyyMMdd，如轉化日期2008-08-08為20080808
     *
     * @param strDate 格式為yyyy-MM-dd的日期字符串
     * @return 格式為yyyyMMdd的日期字符串
     */
    public static String parseDate(String strDate) {
        strDate = trim(strDate);
        if (strDate.length() == 10) {
            StringBuffer buffer = new StringBuffer("");
            buffer.append(strDate.substring(0, 4));
            buffer.append(strDate.substring(5, 7));
            buffer.append(strDate.substring(8, 10));
            strDate = buffer.toString();
        }

        return strDate;
    }

    /**
     * 轉化日期格式yyyyMMdd為yyyy-MM-dd，如轉化日期20080808為2008-08-08
     *
     * @param strDate 格式為yyyyMMdd的日期字符串
     * @return 格式為yyyy-MM-dd的日期字符串
     */
    public static String parseDateEx(String strDate) {
        strDate = trim(strDate);

        if (strDate.length() == 8) {
            StringBuffer buffer = new StringBuffer("");
            buffer.append(strDate.substring(0, 4));
            buffer.append("-");
            buffer.append(strDate.substring(4, 6));
            buffer.append("-");
            buffer.append(strDate.substring(6, 8));
            strDate = buffer.toString();
        }
        return strDate;
    }

    /**
     * @param year
     * @param mon
     * @return
     * @Description : 統計一個月多少天
     */
    public static synchronized int monthDay(int year, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        return maxDate;
    }

    /**
     * 獲得當前的年份
     *
     * @return yyyy
     */
    public static String getNowYear() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(YYYY);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 獲得當前的月份
     *
     * @return mm
     */
    public static String getNowMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(MM);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 獲得當前的月份第幾天
     *
     * @return mm
     */
    public static String getNowDayByMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = simpleDateFormat(MM);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(date);
        return time;
    }

    /**
     * 獲得指定時間的年月
     *
     * @return yyyyMM
     */
    public static String getNowYearMonth(Date date) {
        SimpleDateFormat sdf = simpleDateFormat(YYYYMM);
        String time = sdf.format(date);
        return time;
    }

    /**
     * 獲取當前日期相差N天後的日期
     *
     * @param diffday
     * @return yyyy-MM-dd
     */
    public static String getDiffDate(Integer diffday) {
        return getStrForDiffDate(new Date(), diffday);
    }

    public static String getStrForDiffDate(Date date, Integer diffday) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, diffday);
        return sdf.format(calendar.getTime());
    }

    /**
     * 獲取傳入日期相差N天後的日期
     *
     * @param datestr
     * @param diffday
     * @return yyyy-MM-dd
     */
    public static String getDiffDate(String datestr, Integer diffday) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD);
        String d = null;
        try {
            Date date = sdf.parse(datestr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, diffday);
            d = sdf.format(calendar.getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    /**
     * 獲取傳入日期相差N天後的日期
     *
     * @param date
     * @param diffday
     * @return
     */
    public static Date getDiffDate(Date date, Integer diffday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, diffday);
        return calendar.getTime();
    }

    public static Date getParseTime(String datestr) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date date = null;
        try {
            date = sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getParseDate(String datestr) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD);
        Date date = null;
        try {
            date = sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getParseTime(String datestr, String format) {
        SimpleDateFormat sdf = simpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 返回與當天相隔n天的日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDay(Date date, int n) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回與當前日期時間相隔n小時的日期時間
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getHour(Date date, int n) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回與當前日期時間相隔n分鐘的日期時間
     *
     * @param date
     * @param m
     * @return
     */
    public static Date getMinute(Date date, int m) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, m);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回與當前日期時間相隔n個月的日期時間
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getMonth(Date date, int n) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 獲得與當前的月份相差n的日期
     *
     * @return yyyy-MM-dd
     */
    public static String getDateLimit(Integer n) {
        try {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, n);
            return simpleDateFormat(YYYY_MM_DD).format(cal.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 獲得傳入日期的月份相差n的日期
     *
     * @return yyyy-MM-dd
     */
    public static String getDateLimit(Integer n, String d) {
        try {
            Date date = simpleDateFormat(YYYY_MM_DD).parse(d);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, n);
            return simpleDateFormat(YYYY_MM_DD).format(cal.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 換名稱
     *
     * @param filename model.xlsx
     * @return 1231312313123.xlsx
     */
    public static String getNewFileName(String filename) {
        String temp = getCurrentTime();
        String newFilename = temp + filename.substring(filename.lastIndexOf("."));
        return newFilename;
    }

    /**
     * 獲得當月第一天的日期
     *
     * @param
     * @return 2012
     */
    public static String getFirstMonth() {
        SimpleDateFormat dateFormat = simpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        Date DateTime = calendar.getTime();
        String sTime = dateFormat.format(DateTime);
        return sTime;
    }

    /**
     * 獲得當月最後一天的日期
     *
     * @param
     * @return 2012
     */
    public static String getEndMonth() {
        SimpleDateFormat dateFormat = simpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        Date endTime = calendar.getTime();
        String eTime = dateFormat.format(endTime);

        return eTime;
    }

    /**
     * 獲得傳入月份的第一天的日期
     *
     * @param
     * @return 2012
     */
    public static String getFirstMonth(String month) {
        SimpleDateFormat dateFormat = simpleDateFormat(YYYY_MM_DD);
        try {
            Date date = dateFormat.parse(month + "-01");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, 1);
            Date DateTime = calendar.getTime();
            String sTime = dateFormat.format(DateTime);
            return sTime;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 獲得傳入月份最後一天的日期
     *
     * @param
     * @return 2012
     */
    public static String getEndMonth(String month) {
        SimpleDateFormat dateFormat = simpleDateFormat(YYYY_MM_DD);
        try {
            Date date = dateFormat.parse(month + "-01");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, 1);
            calendar.roll(Calendar.DATE, -1);
            Date endTime = calendar.getTime();
            String eTime = dateFormat.format(endTime);

            return eTime;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getNowAtteMonth(String d) throws ParseException {
        Date date = simpleDateFormat(YYYY_MM_DD).parse(d);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.DATE) > 20) {
            cal.add(Calendar.MONTH, 1);
        }

        String str = simpleDateFormat(YYYYMM).format(cal.getTime());

        return str;
    }

    /**
     * 獲取倆時間之間的所有日期，返回日期List
     *
     * @param beginDate 開始日期 yyyy-MM-dd
     * @param endDate   結束日期 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static List<String> getDateList(String beginDate, String endDate) {
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD);
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            end.setTime(sdf.parse(endDate));
            for (begin.setTime(sdf.parse(beginDate)); begin.before(end); begin.add(Calendar.DATE, 1)) {
                dateList.add(getDateToString(begin.getTime()));
            }
            dateList.add(getDateToString(end.getTime()));
        } catch (ParseException e) {

        }
        return dateList;
    }

    /**
     * Date 轉 String
     *
     * @param date
     * @return yyyyMMddHHmmss
     * @throws Exception
     */
    public static String formatDateToStr(Date date, String format) {
        SimpleDateFormat df = simpleDateFormat(format);
        return df.format(date);
    }

    public static String getFormatDate(Date timeDate) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(timeDate);
        return time;
    }

    /**
     * 獲得時間 最後日期 date + 23:59:59
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDateLast(Date date, int n) {
        if (date == null) return null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.add(Calendar.DATE, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 獲得時間 最早日期 date + 23:59:59
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDateFirst(Date date, int n) {
        if (date == null) return null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.add(Calendar.DATE, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 獲得時間 日期
     *
     * @param date
     * @param n      天數 0：當天 ， 1：明天 ，-1 昨天
     * @param hour   小時
     * @param minute 分
     * @param second 秒
     * @return
     */
    public static Date getDate(Date date, int n, int hour, int minute, int second) {
        if (date == null) return null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
            cal.add(Calendar.DATE, n);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 兩個時間相差距離多少天多少小時多少分多少秒
     *
     * @param str1 時間參數 1 格式：1990-01-01 12:00:00
     * @param str2 時間參數 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值為：{天, 時, 分, 秒}
     */
    public static long[] getDistanceTimes(Date str1, Date str2) throws Exception {
        // DateFormat df = simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date one;
        // Date two;
        long day = 0; // {天, 時, 分, 秒}
        long hour = 0;
        long min = 0;
        int sec = 0;
        int flag = -1;// 表示超過當前時間
        // try {
        // one = df.parse(str1);
        // two = df.parse(str2);
        long time1 = str1.getTime();
        long time2 = str2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
            flag = 0;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (int) (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        long[] times = {day, hour, min, sec, flag};
        // System.out.println(day+"--"+ hour+"-- "+ min+"--"+ sec+"--"+flag);
        return times;
    }

    /**
     * 字符串轉為時間類型.
     *
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        if (NumberUtils.isDigits(str)) {
            return new Date(Long.parseLong(str));
        }
        DateFormat dateformat = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date tmp = null;
        try {
            tmp = dateformat.parse(str);
        } catch (ParseException e) {
            dateformat = simpleDateFormat(YYYY_MM_DD_HH_MM);
            try {
                tmp = dateformat.parse(str);
            } catch (ParseException e1) {
                dateformat = simpleDateFormat(YYYY_MM_DD);
                try {
                    tmp = dateformat.parse(str);
                } catch (ParseException e2) {
                    tmp = new Date();
                }
            }
        }
        return tmp;
    }

    /**
     * 時間轉月日字符串.格式：MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToMD(Date date) {
        return dateToStr(date, "MM-dd");
    }

    /**
     * 時間轉小時分鐘字符串.格式：HH:mm
     *
     * @param date
     * @return
     */
    public static String dateToHM(Date date) {
        return dateToStr(date, "HH:mm");
    }

    /**
     * 時間轉全數字時分秒字符串.格式：HHmmss
     *
     * @param date
     * @return
     */
    public static String dateToHMSNo(Date date) {
        return dateToStr(date, "HHmmss");
    }

    /**
     * 時間轉年月日字符串.格式：yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToYMD(Date date) {
        return dateToStr(date, YYYY_MM_DD);
    }

    /**
     * 時間轉年月日小時分鐘秒字符串.格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToYMDHMS(Date date) {
        return dateToStr(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 時間轉年月日小時分鐘秒字符串.格式：yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String dateToYMDHMSNo(Date date) {
        return dateToStr(date, "yyyyMMddHHmmss");
    }

    /**
     * 獲得當前時間
     *
     * @param num
     * @return
     */
    public static Date parseDate(int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, num);
        DateFormat dateformat = simpleDateFormat(YYYY_MM_DD);
        try {
            return dateformat.parse(dateformat.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 時間比較
     *
     * @param beginDatetime
     * @param endDatetime
     * @return
     */
    public static boolean dateTimeBijiao(String beginDatetime, String endDatetime) {
        boolean flag = false;
        DateFormat df = simpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(beginDatetime));
            c2.setTime(df.parse(endDatetime));
        } catch (ParseException e) {
            System.err.println("格式不正確");
        }
        int result = c1.compareTo(c2);

        if (result == 0)
            flag = true;
        else if (result < 0)
            flag = false;
        else
            flag = true;
        return flag;
    }

    /**
     * 比較date是否在startDate與endDate區間中,按YMD來比較
     *
     * @param date
     * @param startDate
     * @param endDate
     * @return 存在返回true
     */
    public static boolean dateCompareByYmd(Date date, Date startDate, Date endDate) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(Calendar.HOUR_OF_DAY, 0);
        dateCal.set(Calendar.MINUTE, 0);
        dateCal.set(Calendar.SECOND, 0);
        dateCal.set(Calendar.MILLISECOND, 0);
        long curTime = dateCal.getTimeInMillis();

        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate);
        starCal.set(Calendar.HOUR_OF_DAY, 0);
        starCal.set(Calendar.MINUTE, 0);
        starCal.set(Calendar.SECOND, 0);
        starCal.set(Calendar.MILLISECOND, 0);
        long startTime = starCal.getTimeInMillis();

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        long endTime = endCal.getTimeInMillis();

        if (curTime >= startTime && curTime <= endTime) {
            return true;
        }
        return false;
    }

    /**
     * 比較date是否在startDate與endDate區間中,按YMDHMS來比較
     *
     * @param date
     * @param startDate
     * @param endDate
     * @return 存在返回true
     */
    public static boolean dateCompareByYmdhms(Date date, Date startDate, Date endDate) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(Calendar.MILLISECOND, 0);
        long curTime = dateCal.getTimeInMillis();

        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate);
        starCal.set(Calendar.MILLISECOND, 0);
        long startTime = starCal.getTimeInMillis();

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.set(Calendar.MILLISECOND, 0);
        long endTime = endCal.getTimeInMillis();

        if (curTime >= startTime && curTime <= endTime) {
            return true;
        }
        return false;
    }

    /**
     * 根據時差來獲取時間
     *
     * @param zone_type
     * @return
     * @throws Exception
     */
    public static String getTimeZone(String zone_type) throws Exception {
        Date date = new Date();
        SimpleDateFormat dateformat = simpleDateFormat("HH:mm:ssa E MMM dd,yyyy", Locale.US);
        TimeZone timeZone = TimeZone.getTimeZone(zone_type);
        dateformat.setTimeZone(timeZone);
        return dateformat.format(date);
    }

    /**
     * 根據時差來獲取時間
     *
     * @param zone_type
     * @return
     * @throws Exception
     */
    public static String getTimeZoneJson(String zone_type) throws Exception {
        Date date = new Date();
        DateFormat dateformat = simpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.US);
        TimeZone timeZone = TimeZone.getTimeZone(zone_type);
        dateformat.setTimeZone(timeZone);
        return dateformat.format(date);
    }

    /**
     * 時間轉字符串
     *
     * @param date    時間
     * @param pattern 時間格式
     * @return
     */
    public static String dateToStr(Date date, String pattern, Locale locale) {
        if (date == null) {
            return "";
        }
        DateFormat dateformat = simpleDateFormat(pattern, locale);
        return dateformat.format(date);
    }

    /**
     * 時間轉字符串
     *
     * @param date    時間
     * @param pattern 時間格式
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        return dateToStr(date, pattern, Locale.getDefault());
    }

    /**
     * 字符串轉時間
     *
     * @param string 時間字符串
     * @param format 時間格式
     * @return
     */
    public static Date strToDate(String string, String format) {
        Date tmp = null;
        if (StringUtil.isBlank(string) || StringUtil.isBlank(format)) {
            return tmp;
        } else {
            DateFormat dateformat = simpleDateFormat(format);
            try {
                tmp = dateformat.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return tmp;
        }
    }

    /**
     * 獲取某日報表統計的開始時間
     *
     * @param date
     * @return
     */
    public static Date getStatDateStart(Date date) {
        Date startDate = null;
        if (new DateTime(date).getHourOfDay() < 7) {
            // 7點之前的用前一天的數據
            startDate = new DateTime(date).minusDays(1).withTime(7, 0, 0, 0).toDate();
        } else {
            startDate = new DateTime(date).withTime(7, 0, 0, 0).toDate();
        }
        return startDate;
    }

    /**
     * 獲取某日開始時間
     *
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        String day = dateToStr(date, YYYY_MM_DD);
        return strToDate(day + " 00:00:00", YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 獲取某日結束時間
     *
     * @param end
     * @return
     */
    public static Date getDateEnd(Date end) {
        String day = dateToStr(end, YYYY_MM_DD);
        return strToDate(day + " 23:59:59", YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 獲取日期是星期幾 日為1 六為7
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getMondayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.WEEK_OF_YEAR, -1);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return df.format(cal.getTime());
    }

    /**
     * 計算兩個日期之間相差的天數(bdate-smdate)
     *
     * @param smdate
     * @param bdate
     * @return 相差天數
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        smdate = DateUtil.strToDate(sdf.format(smdate), YYYY_MM_DD);
        bdate = DateUtil.strToDate(sdf.format(bdate), YYYY_MM_DD);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int getUnixTimestamp(Date date) {
        return (int) (date.getTime() / 1000);
    }

    public static Date unixTimestampToDate(int i) {
        return new Date(i * 1000L);
    }

    /**
     * 驗證時間合理性。
     *
     * @param checkStr 待檢查的字符串
     * @param format   默認驗證  yyyy-MM-dd。否則請傳入時間格式
     * @return
     */
    public static boolean isValid(String checkStr, String format) {
        if (StringUtil.isBlank(checkStr))
            return false;
        if (StringUtil.isBlank(format)) {
            format = YYYY_MM_DD; // 默認驗證年月日： yyyy-MM-dd
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (checkStr.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(checkStr.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static Date getBetDate() {
        Calendar rightNow = Calendar.getInstance();
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);
        if (hours < 7) {
            rightNow.add(Calendar.DATE, -1);
        }
        return getDateStart(rightNow.getTime());
    }

    public static Date getStatDate() {
        Calendar rightNow = Calendar.getInstance();
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);
        if (hours < 7) {
            rightNow.add(Calendar.DATE, -1);
        }
        return getDateStart(rightNow.getTime());
    }

    public static Date getStatDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);
        if (hours < 7) {
            rightNow.add(Calendar.DATE, -1);
        }
        return getDateStart(rightNow.getTime());
    }

    /**
     * 往前3天，往後60天（六合彩會提前開盤好幾天，追號的也有往後好幾天）
     * @return
     */
    public static Date[] getUnSettleDate() {
        return new Date[]{getStatDate(new DateTime().minusDays(3).toDate()), getStatDate(new DateTime().plusDays(60).toDate())};
    }

    public static boolean isDateIn(Date start,Date end,Date dateForCheck){
        return !(start.after(dateForCheck) || end.before(dateForCheck));
    }

    public static boolean isYesterday(Date inputDate) {
        if (inputDate == null) {
            return false;
        } else {
            Date yesterday = DateUtils.addDays(new Date(), -1);
            return DateUtils.isSameDay(inputDate, yesterday);
        }
    }

    public static boolean isToday(Date inputDate) {
        return inputDate != null && DateUtils.isSameDay(inputDate, Calendar.getInstance().getTime());
    }


    public static Date parseTime(String datestr) {
        SimpleDateFormat sdf = simpleDateFormat(YYYY_MM_DD_HHMM);
        Date date = null;
        try {
            date = sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String[] args) throws Exception {
//		Long l1 = getCurrLong();
//		System.out.println(l1);
//		Long l2 = getCurrLong();
//		System.out.println(l2);
//		System.out.println(l2 - l1);
//		System.out.println(getWeekDay(new Date()));
//		System.out.println(getMondayDate(new Date()));
//		System.out.println(getDiffDate(1));
//		System.out.println(formatDateToStr(new Date(), "H"));
//		System.out.println(getDateToString(getDateFirst(new Date(), 0), YYYY_MM_DD_HH_MM_SS));
//		System.out.println(getNowDate());
//		System.out.println(dateToStr(new Date(), "MM-dd HH:mm"));
//		System.out.println(isValid("2017-03-30", null));
//		System.out.println(isValid("2017-03-32", null));
//		System.out.println(isValid("2017-02-28 10:27:98", YYYY_MM_DD_HH_MM_SS));
//		System.out.println(isValid("2017-02-29", null));

        System.out.println(strToDate(new Date().getTime()+""));
        System.out.println(strToDate("2020-01-01"));
    }
}
