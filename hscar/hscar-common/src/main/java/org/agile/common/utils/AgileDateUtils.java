package org.agile.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期处理
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class AgileDateUtils {
	public static final String DATE_TIME_EXPRESSION_GENERAL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_EXPRESSION_NO_SPLIT = "yyyyMMddhhmmss";
	public static final String DATE_EXPRESSION_GENERAL = "yyyy-MM-dd";
	public static final String DATE_EXPRESSION_NO_SPLIT = "yyyyMMdd";
	public static final String DATE_TIME_EXPRESSION_STRIKE = "yyyy-MM-dd HH-mm-ss";
	private static String[] parsePatterns = new String[] { DATE_TIME_EXPRESSION_STRIKE, DATE_EXPRESSION_GENERAL, DATE_TIME_EXPRESSION_NO_SPLIT, DATE_TIME_EXPRESSION_GENERAL,DATE_EXPRESSION_NO_SPLIT };
	public static String[] WEEK_DAYS = new String[] { "日", "一", "二", "三", "四", "五", "六" };
	public static String[] MONTH_NAME_CN = new String[] { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };

	/**
	 * 得到当前日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getNowDate() throws ParseException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_EXPRESSION_STRIKE);
		String date = sdf.format(d);
		return stringToDate(date);
	}

	public static String getNowDateString() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_EXPRESSION_STRIKE);
		String date = sdf.format(d);
		return date;
	}

	public static String getNowDateString(String format) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(d);
		return date;
	}
	
	
	
	/**
	 * 得到时间跨度 day + "天", hou + "小时", min + "分钟", sec + "秒"
	 * 
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static Long[] getTimeSpan(Date dt1, Date dt2) {
		Long span = (dt1.getTime() - dt2.getTime()) / 1000;// 单位秒
		Long day = span / (60 * 60 * 24);// 天
		Long hou = (span % (60 * 60 * 24)) / (60 * 60);// 小时
		Long min = (span % (60 * 60)) / 60;// 分钟
		Long sec = (span % 60);
		// day + "天", hou + "小时", min + "分钟", sec + "秒"
		Long[] arr = new Long[] { day, hou, min, sec };
		return arr;
	}
	
	/**
	 * 获得时间跨度列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getDateSpanList(Date start, Date end) throws ParseException {
		List<Date> result = new ArrayList<Date>();
		if (start.before(end)) {
			Date temp = start;
			while (true) {
				Calendar c = Calendar.getInstance();
				c.setTime(temp);
				result.add(temp);
				c.add(Calendar.DAY_OF_MONTH, 1);
				temp = c.getTime();
				if (end.before(temp)) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 当前时间的上一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date previousMonth(Date date) {
		return DateUtils.addMonths(date, -1);
	}

	/**
	 * 当前时间偏差任意年(整数) + 表示往后 - 表示往前
	 * 
	 * @param date
	 * @return
	 */
	public static Date addYear(Date date, int span) {
		return DateUtils.addYears(date, span);
	}

	/**
	 * 当前时间偏差任意月(整数) + 表示往后 - 表示往前
	 * 
	 * @param date
	 * @return
	 */
	public static Date addMonth(Date date, int span) {
		return DateUtils.addMonths(date, span);
	}

	/**
	 * 当前时间偏差任意天(整数) + 表示往后 - 表示往前
	 * 
	 * @param date
	 * @return
	 */
	public static Date addDay(Date date, int span) {
		return DateUtils.addDays(date, span);
	}

	/**
	 * 当前时间偏差任意小时(整数) + 表示往后 - 表示往前
	 * 
	 * @param date
	 * @return
	 */
	public static Date addHours(Date date, int span) {
		return DateUtils.addHours(date, span);
	}

	/**
	 * 得到当前年+月 yyyy-MM
	 * 
	 * @return
	 */
	public static String getNowYearMonthString() {
		return dateToString(new Date(), "yyyy-MM");
	}
	
	/**
	 * 得到当前年+月+日 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getNowYearMonthDayString() {
		return dateToString(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 得到当前年  yyyy
	 * 
	 * @return
	 */
	public static String getNowYearString() {
		return dateToString(new Date(), "yyyy");
	}

	/**
	 * 得到上一年 yyyy
	 * 
	 * @return
	 */
	public static String getLastYearString() {
		Date last_year = DateUtils.addYears(new Date(), -1);
		return dateToString(last_year, "yyyy");
	}
	
	/**
	 * 得到年 yyyy
	 * 
	 * @return
	 */
	public static String getYearString(Date date) {
		return dateToString(date, "yyyy");
	}

	/**
	 * 得到当前月 MM
	 * 
	 * @return
	 */
	public static String getNowMonthString() {
		return dateToString(new Date(), "MM");
	}

	/**
	 * 得到月 MM
	 * 
	 * @return
	 */
	public static String getMonthString(Date date) {
		return dateToString(date, "MM");
	}

	/**
	 * 得到当前日 dd
	 * 
	 * @return
	 */
	public static String getNowDayString() {
		return dateToString(new Date(), "dd");
	}
	
	/**
	 * 得到日 dd
	 * 
	 * @return
	 */
	public static String getDayString(Date date) {
		return dateToString(date, "dd");
	}
	
	/**
	 * String转化成Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr, StringUtils.EMPTY);
	}

	public static Date stringToDate(String dateStr, String pattern) {
		if (StringUtils.isBlank(pattern)) {
			return stringToDate(dateStr, parsePatterns);
		}
		return stringToDate(dateStr, new String[] { pattern });
	}

	public static Date stringToDate(String dateStr, String[] patterns) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		try {
			return DateUtils.parseDate(dateStr, patterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Date转化成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DATE_EXPRESSION_GENERAL);
	}
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 得到当前时间是本年中的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static Long getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return Long.valueOf(week);
	}

	/**
	 * 得到当前时间是本月中的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static Long getWeekOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		return Long.valueOf(week);
	}

	/**
	 * 获得本周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowWeekStart(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.setFirstDayOfWeek(Calendar.SUNDAY);

		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return (Date) currentDate.getTime().clone();
	}

	/**
	 * 获得本周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowWeekEnd(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.setFirstDayOfWeek(Calendar.SUNDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return (Date) currentDate.getTime().clone();
	}

	public static int dayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 取得当月天数
	 **/
	public static int getNowentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 **/
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 某一天是星期几
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int dayOfWeek(int year, int month, int day) {
		Calendar calendar = createCalender(year, month, day);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	private static Calendar createCalender(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		return calendar;
	}

	public static String mappingMonth(int i) {
		return MONTH_NAME_CN[i - 1];
	}

	/**
	 * 星期几的中文名
	 * 
	 * @param i
	 * @return
	 */
	public static String mappingWeek(int i) {
		return mappingWeek(i, false);
	}

	public static String mappingWeek(int i, boolean complex) {
		if (complex) {
			return "星期" + WEEK_DAYS[i];
		}
		return WEEK_DAYS[i];
	}
	
	
	
	/**
	 * 一天的开始
	 * 
	 * @param date yyyy-MM-dd 00:00:00.000
	 * @return
	 */
	public static Date startOfDay(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return startOfDay(date);
	}
	
	public static Date startOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setStartOfDay(calendar);
		return calendar.getTime();
	}
	
	private static void setStartOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 一天的结束
	 * 
	 * @param date yyyy-MM-dd 23:59:59.999
	 * @return
	 */
	public static Date endOfDay(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return endOfDay(date);
	}
	
	public static Date endOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setEndOfDay(calendar);
		return calendar.getTime();
	}
	
	private static void setEndOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}
	
	
	
	/**
	 * 月的开始
	 * 
	 * @param date yyyy-MM-01 00:00:00.000
	 * @return
	 */
	public static Date startOfMonth(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return startOfMonth(date);
	}
	
	public static Date startOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setStartOfMonth(calendar);
		return calendar.getTime();
	}

	private static void setStartOfMonth(Calendar calendar) {
		calendar.set(Calendar.DATE, 1);
		setStartOfDay(calendar);
	}
	
	
	
	/**
	 * 月的结束
	 * 
	 * @param date yyyy-MM-31 23:59:59.999
	 * @return
	 */
	public static Date endOfMonth(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return endOfMonth(date);
	}
	
	public static Date endOfMonth(Date date) {
		date = startOfMonth(date);
		return setEndOfMonth(date);
	}
	
	private static Date setEndOfMonth(Date date) {
		date = DateUtils.addMonths(date, 1);
		date = DateUtils.addDays(date, -1);
		return endOfDay(date);
	}
	
	
	
	/**
	 * 年的开始
	 * 
	 * @param date yyyy-01-01 00:00:00.000
	 * @return
	 */
	public static Date startOfYear(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return startOfYear(date);
	}
	
	public static Date startOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 1);
		setStartOfMonth(calendar);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 年的结束
	 * 
	 * @param date yyyy-12-31 23:59:59.999
	 * @return
	 */
	public static Date endOfYear(int year, int month, int day) {
		Date date = createCalender(year, month, day).getTime();
		return endOfYear(date);
	}
	
	public static Date endOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DATE, 31);
		setEndOfDay(calendar);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 判断当前时间和目标时间大小：true表示date2比date1时间大，false表示date2比date1时间小
	 */
	public static boolean compareTwoDate(Date date1, Date date2) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		if (d1 > d2) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * （包含）判断当前时间和目标时间大小 true表示比当前时间大，false表示比当前时间小
	 */
	public static boolean isBetweenInTwoDate(Date target_date, Date start_date, Date end_date) {
		long target = target_date.getTime();
		long start = start_date.getTime();
		long end = end_date.getTime();
		if(start<=target && target<=end) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * （不包含）判断当前时间和目标时间大小 true表示比当前时间大，false表示比当前时间小
	 */
	public static boolean isBetweenWithoutTwoDate(Date target_date, Date start_date, Date end_date) {
		long target = target_date.getTime();
		long start = start_date.getTime();
		long end = end_date.getTime();
		if(start<target && target<end) {
			return true;
		} else {
			return false;
		}
	}
}