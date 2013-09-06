package com.xdtech.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author gw
 * @version 1.0
 */
public class StaticsUtils {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 改变传入日期为下一小时
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date changeHour(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+1);
		return calendar.getTime();
	}
	/**
	 * 改变传入日期为第二天
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date changeDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+1);
		return calendar.getTime();
	}
	/**
	 * 改变传入日期为当前周的最后一天
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date changeWeek(Date date,boolean flag){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(flag){
			calendar.setTime(changeMonthLastDay(date));
		}else{
			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH)+1);
			calendar.set(Calendar.DAY_OF_WEEK, 1);
		}
		return calendar.getTime();
	}
	/**
	 * 改变日期为下周第一天
	 * @param date
	 * @return
	 */
	public static Date changeNextWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH)+1);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
//		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+1);
		return calendar.getTime();
	}

	/**
	 * 改变传入日期为下月第一天
	 * @param currentdate
	 * @param offset
	 * @return
	 */
	public static Date changeMonth(Date currentdate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	/**
	 * 改变传入日期为下季度第一天
	 * @param currentdate
	 * @param offset
	 * @return
	 */
	public static Date changeQuart(Date currentdate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		for(int i=0;i<3;i++){
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	/**
	 * 改变日期为本月最后一天
	 * @param currentdate
	 * @return
	 */
	public static Date changeMonthLastDay(Date currentdate ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	/**
	 * 改变日期为本月第一天
	 * @param currentdate
	 * @return
	 */
	public static Date changeMonthFirstDay(Date currentdate ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	/**
	 * 得到传入日期的月份
	 * @return
	 */
	public static int getDateMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	/**
	 * 改变日期为本季度最后一天
	 * @param currentdate
	 * @param offset
	 * @return
	 */
	public static Date changeQuartLastDay(Date currentdate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentdate);
		for(int i=0;i<3;i++){
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	/**
	 * 返回当前日期月份的天数
	 * @param date
	 * @return
	 */
	public static int getMonthDay(Date date){
		Calendar calendar = Calendar.getInstance();
		//Date now = calendar.getTime();
		calendar.setTime(date);
		if(calendar.get(Calendar.MONTH)!=Calendar.getInstance().get(Calendar.MONTH)){
			return getMonthDays(calendar);
		}else{
			return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		}
		//		Date dt = new Date();
//		Calendar ct = Calendar.getInstance();
//		ct.setTime(dt);
//		if(calendar.get(Calendar.YEAR)==ct.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==ct.get(Calendar.MONTH)){
//			calendar.setTime(changeMonthFirstDay(date));
//		}
//		return getMonthDays(calendar);
	}
	
	private static int getMonthDays(Calendar c) {
		int m = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		switch (m+1) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				m = 31;
				break;
			case 2:
				m=getMonth(year);
				break;
			default:
				m = 30;
				break;
		}
		return m;
	}
	public static int getDaysBetween(Date start,Date end) {
		if(end.getTime()<start.getTime()){
			return -1;
		}
		Calendar stc = Calendar.getInstance();
		stc.setTime(start);
		Calendar send = Calendar.getInstance();
		send.setTime(end);
		
		int sday = stc.get(Calendar.DAY_OF_MONTH);
		int endday = send.get(Calendar.DAY_OF_MONTH);
		int count=0;
		while(sday!=endday&&count<=31){
			stc.set(Calendar.DAY_OF_MONTH, stc.get(Calendar.DAY_OF_MONTH)+1);
			sday = stc.get(Calendar.DAY_OF_MONTH);
			count++;
		}
		return count+1;
	}
	/***
	 * 得到传入日期月份的周数
	 * @param date
	 * @return
	 */
	public static int getMontWeekNum(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		int count = 0;
		while(calendar.get(Calendar.MONTH)==month){
			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH)+1);
			count++;
		}
		return count;
	}
	/**
	 * 取得近2周的
	 * @param date
	 * @return
	 */
	
	public static Date getTwoWeeks(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-13);
		return calendar.getTime();
	}
	
	/**
	 * 取得近1周的
	 * @param date
	 * @return
	 */
	
	public static Date getOneWeeks(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-6);
		return calendar.getTime();
	}
	/*****
	 * ---------
	 * @param date
	 * @return
	 */
	public static int getQuartWeekNum(Date date){
		int count = 0;
//		for (int i = 0; i < 3; i++) {
//			count+=getMontWeekNum(date);
//		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(changeMonthFirstDay(date));
		
		int month = calendar.get(Calendar.MONTH);
		while(calendar.get(Calendar.MONTH)<month+3){
//			System.out.println(sdf.format(calendar.getTime()));
			calendar.setTime(changeWeek(calendar.getTime(), false));
//			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH)+1);
//			System.out.println(sdf.format(calendar.getTime()));
			count++;
		}
		return count;
	}
	private  static int getMonth(int year) {
		if((year%4 == 0 && year%100 != 0)||(year%400 == 0)){
			return 29;
		}
		return 28;
	}
	
	/**
	 * 改变日期为半年的最后一天
	 * @param td
	 * @return
	 */
	public static Date changeHalfYearLastDay(Date td) {
		for (int i = 0; i < 5; i++) {
			td = changeMonth(td);
		}
		td = changeMonthLastDay(td);
		return td;
	}
	/**
	 * 改变日期为传入年份的最后一天
	 * @param td
	 * @return
	 */
	public static Date changeYearLastDay(Date td) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(td);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	/**
	 * 改变日期为传入年份的第一天
	 * @param td
	 * @return
	 */
	public static Date changeYearFirstDay(Date td) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(td);
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	public static double[][] processDate(double[][]data,List<String> columns){
		List<Integer> indexList = new ArrayList<Integer>();
		List<String> columnss = new ArrayList<String>();
		for(int i=0;i<columns.size();i++){
			for (int j = 0; j < data.length; j++) {
				if(data[j][i]>0){
					indexList.add(i);
					columnss.add(columns.get(i));
					break;
				}
			}
		}
		double[][]dd = new double[data.length][columnss.size()];
		for (int i = 0; i < dd[0].length; i++) {
			for (int j = 0; j < dd.length; j++) {
				dd[j][i] = data[j][indexList.get(i)];
			}
		}
		while(columns.size()>0){
			columns.remove(0);
		}
		columns.addAll(columnss);
		return dd;
	}
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		
		Date fd = changeWeekFirstday(d);
		System.out.println(sdf.format(fd));
		
	}
	public static String getInterva1(String charAt, Date td,boolean flag) {
		int weekNum = getWeekNum(charAt,flag);
		for (int i = 0; i < weekNum; i++) {
			td = changeNextWeek(td);
		}
		String start = sdf.format(td).substring(4);
		td = changeWeek(td);
		String end = sdf.format(td).substring(4);
		return start+"-"+end;
	}
	
	public static Date changeWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH)+1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		return calendar.getTime();
	}
	public static int getWeekNum(String charAt,boolean flag) {
		char c = ' ';
		if(flag){
			c = charAt.charAt(1);
		}else{
			c = charAt.charAt(0);
		}
		switch (c) {
			case '一':
				return 0;
			case '二':
				return 1;
			case '三':
				return 2;
			case '四':
				return 3;
			case '五':
				return 4;
			case '六':
				return 5;
			case '七':
				return 6;
			case '八':
				return 7;
			case '九':
				return 8;
			case '十':
				char key = ' ';
				if(flag){
					key = charAt.charAt(2);
				}else{
					key = charAt.charAt(1);
				}
				switch (key) {
					case '一':
						return 10;
					case '二':
						return 11;
					case '三':
						return 12;
					case '四':
						return 13;
					case '五':
						return 14;
					case '六':
						return 15;
					case '七':
						return 16;
					case '八':
						return 17;
					case '九':
						return 18;
					default:
						return 9;
				}
		}
		return 0;
	}
	/**
	 * 改变日期为传入日期所在周的第一天
	 * @param now
	 * @return
	 */
	public static Date changeWeekFirstday(Date now) {
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.DAY_OF_WEEK,2);
		return c.getTime();
	}
}

