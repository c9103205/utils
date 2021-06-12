package com.cx.framework.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {

	/**
	 * 獲取當前系統時間到指定時間的毫秒數
	 *
	 * @param d
	 * @param h
	 * @param m
	 * @param s
	 * @return
	 */
	public static long getDiffExpireMilliseconds(int d, int h, int m, int s) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour <= 7) {
			d = 0;
		}

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date);
		calendar2.add(Calendar.DATE, d);
		calendar2.set(Calendar.HOUR_OF_DAY, h);
		calendar2.set(Calendar.MINUTE, m);
		calendar2.set(Calendar.SECOND, s);
		calendar2.set(Calendar.MILLISECOND, 0);

		return (calendar2.getTimeInMillis() - calendar.getTimeInMillis());
	}


	public enum DATE_TYPE {
		STAT_DATE(1, "報表時間"), ADD_DATE(2, "添加時間");
		private int type;
		private String desc;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		private DATE_TYPE(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}

	}

	/**
	 * 獲取統計時間
	 * @return
	 */
	public static Date getStatDate() {
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(Calendar.HOUR_OF_DAY) < 7) {// 淩晨4點都算前一天的數據
			calendar.add(Calendar.DATE, -1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getStatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (calendar.get(Calendar.HOUR_OF_DAY) < 7) {// 淩晨4點都算前一天的數據
			calendar.add(Calendar.DATE, -1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getPeFootboolStatDate(Date statDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(statDate);

		if (calendar.get(Calendar.HOUR_OF_DAY) <= 4) {// 淩晨2點都算前一天的數據
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	/**
	 * 查詢某天的統計時間
	 * @param statDate
	 * @return
	 */
	public static String[] getStatDates(Date statDate) {
		String statDay = DateUtil.dateToYMD(statDate);
		String beginTime = statDay + " 07:00:00";
		String endTime = DateUtil.getDiffDate(statDay, 1)+ " 07:00:00";
		String[] dates=new String[2];
		dates[0]=beginTime;
		dates[1]=endTime;
		return dates;
	}

	public static String getStatYesterDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);

		if (calendar.get(Calendar.HOUR_OF_DAY) < 7) {// 淩晨4點都算前一天的數據
			calendar.add(Calendar.DATE, -1);
		}
		return DateUtil.getDateToString(calendar.getTime());
	}

	public static List<Date> getWeekDates() {
		Calendar cal = Calendar.getInstance();
		// 7點以前從前天開始，7點以後從昨天開始
		if (cal.get(Calendar.HOUR_OF_DAY) < 7) {
			cal.add(Calendar.DATE, -1);
		}
		clearTime(cal);
		List<Date> dates = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			cal.add(Calendar.DATE, -1);
			dates.add(DateUtil.getDateStart(cal.getTime()));
		}
		return dates;
	}

	public static List<Date> getIntegralPointDay(String statDate){
		List<Date> result = new ArrayList<Date>();
		Date date = DateUtil.strToDate(statDate);
		result.add(date);
		Calendar calendar = new Calendar.Builder().setInstant(date).build();
		for(int i=1;i<24;i++){
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			result.add(calendar.getTime());
		}
		return result;
	}
	public static List<Date> getIntervalFewMinuteDay(String statDate,int minute){
		List<Date> result = new ArrayList<Date>();
		Date dateEnd = DateUtil.getDateEnd(DateUtil.strToDate(statDate,DateUtil.YYYY_MM_DD));
		Date date = DateUtil.strToDate(statDate+" 00:00:00");
		result.add(date);
		Calendar calendar = new Calendar.Builder().setInstant(date).build();
		while(calendar.getTime().getTime()<dateEnd.getTime()){
			calendar.add(Calendar.MINUTE, minute);
			result.add(calendar.getTime());
		}
		return result;
	}
	public static List<Date> getIntervalFewMinuteHour(Date statDate,Date endDate,int minute){
		List<Date> result = new ArrayList<Date>();
		result.add(statDate);
		Calendar calendar = new Calendar.Builder().setInstant(statDate).build();
		while(calendar.getTime().getTime()<endDate.getTime()){
			calendar.add(Calendar.MINUTE, minute);
			result.add(calendar.getTime());
		}
		return result;
	}

	public static List<Date> getEachDayMonent(String statDate,String endDate){
		List<Date> result = new ArrayList<Date>();
		Date sdate = DateUtil.strToDate(statDate);
		Date edate = DateUtil.strToDate(endDate);
		result.add(sdate);
		Calendar scalendar = new Calendar.Builder().setInstant(sdate).build();
		Calendar ecalendar = new Calendar.Builder().setInstant(edate).build();
		while(scalendar.get(Calendar.DAY_OF_YEAR)<ecalendar.get(Calendar.DAY_OF_YEAR)){
			scalendar.add(Calendar.DAY_OF_YEAR, 1);
			result.add(scalendar.getTime());

		}
		return result;
	}
	public static List<String> getStatDates(String startDate,String endDate){
		List<String> result = new ArrayList<String>();
		Date sdate = DateUtil.strToDate(startDate);
		Date edate = DateUtil.strToDate(endDate);
		result.add(startDate);
		Calendar scalendar = new Calendar.Builder().setInstant(sdate).build();
		Calendar ecalendar = new Calendar.Builder().setInstant(edate).build();
		while(scalendar.get(Calendar.DAY_OF_YEAR)<ecalendar.get(Calendar.DAY_OF_YEAR)){
			scalendar.add(Calendar.DAY_OF_YEAR, 1);
			result.add(DateUtil.dateToStr(scalendar.getTime(),DateUtil.YYYY_MM_DD));
		}
		return result;
	}

	public static void main(String[] args) {
//		System.out.println(getDiffExpireMilliseconds(1, 4, 0, 0) / 1000 / 60); 1474560000000
//		Date date = getStatDate("2016-09-23 24:00:00");
//		System.out.println(DateUtil.getDateToString(date,DateUtil.YYYY_MM_DD_HH_MM_SS));
		/*getEachDayMonent("2017-09-20 12:05:00", "2017-10-20 12:05:00").forEach(date->{
			System.out.println(DateUtil.dateToStr(date, DateUtil.YYYY_MM_DD_HH_MM_SS));
		});;*/
		Calendar calendar = Calendar.getInstance();
		Date statDate = DateUtil.strToDate("2016-09-22 23:59:59");
	}

	/**
	 * 相隔幾分鐘
	 *
	 * @param diffMinute
	 * @return
	 */
	public static Date getDiffMinute(Date date, Integer diffMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, diffMinute);
		return calendar.getTime();
	}
	/**
	 * 獲取大約的時間，如01:07:55->01:08:00,區間為前後10秒
	 * @param date
	 * @return
	 */
	public static Date getMinute(Date date){
		int second = date.getSeconds();
		if(second>50 && second<60){
			Date result = DateUtil.getMinute(date, 1);
			result.setSeconds(0);
			return result;
		}else if(second>0 && second<10){
			date.setSeconds(0);
		}
		return date;
	}

	public static void clearTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

}
