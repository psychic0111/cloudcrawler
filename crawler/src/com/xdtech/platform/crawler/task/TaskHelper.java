package com.xdtech.platform.crawler.task;
import java.util.Calendar;
import java.util.Date;

import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.crawler.control.nbean.NTask;

public class TaskHelper {
	/**
	 * 重置任务的初始化时间
	 * @param task
	 */
	public static void setTaskGenerateTime(NTask task){
		long interval = 0;
		if(task.getClickType() == 1){
			interval = task.getClick() * 60 * 60 * 1000;
		}else if(task.getClickType() == 2){
			interval = task.getClick() * 60 * 1000;
		}
		long current = System.currentTimeMillis();
		current = current - interval;
		task.setUrlGenerateDate(new Date(current));
	}
	/**
	 * 重置搜索引擎的初始化时间
	 * @param task
	 */
	public static void setNEngineGenerateTime(NEngine nengine){
		long interval = 0;
		if(nengine.getTimeIntervalType()== 1){
			interval = nengine.getTimeInterval() * 60 * 60 * 1000;
		}else if(nengine.getTimeIntervalType() == 2){
			interval = nengine.getTimeInterval() * 60 * 1000;
		}
		long current = System.currentTimeMillis();
		current = current - interval;
		nengine.setUrlGenerateDate(new Date(current));
	}
	/**
	 * 获取当天是星期几
	 * @return
	 */
	public static String getWeekday(){
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		return weekday+"";
	}
	public static void main(String[] args) {
		System.out.println(getWeekday());
	}
	/**
	 * 判断一个站点是否应该执行地址生成任务
	 * 
	 * @param site
	 * @return
	 */
	public static boolean checked(NTask task) {
		String weekday = getWeekday();
		boolean isContinue = false;
		if(task.getWeekDays().indexOf(weekday) > -1){
			isContinue = true;
		}
		if(isContinue){
			if (task.getUrlGenerateDate() == null) {// 站点还没有执行过地址发生任务
				return true;
			} else {
				if (task.getClick() == 0) {// 不执行更新，只抓取一次
					return false;
				} else {
					long curTimes = System.currentTimeMillis();
					long lasted = task.getUrlGenerateDate().getTime();
					long interval = 0;
					if(task.getClickType() == 1){
						interval = task.getClick() * 60 * 60 * 1000;
					}else if(task.getClickType() == 2){
						interval = task.getClick() * 60 * 1000;
					}
					if (curTimes - lasted >= interval) {// 检查时间间隔
						return true;
					} else {
						return false;
					}
				}
			}
		}else{
			return false;
		}
	}
	/**
	 * 判断一个站点是否应该执行地址生成任务
	 * 
	 * @param site
	 * @return
	 */
	public static boolean checked(NEngine engine) {
		if (engine.getUrlGenerateDate() == null) {// 站点还没有执行过地址发生任务
			return true;
		} else {
			if (engine.getTimeInterval() == 0) {// 不执行更新，只抓取一次
				return false;
			} else {
				long curTimes = System.currentTimeMillis();
				long lasted = engine.getUrlGenerateDate().getTime();
				long interval = 0;
				if(engine.getTimeIntervalType() == 1){
					interval = engine.getTimeIntervalType() * 60 * 60 * 1000;
				}else if(engine.getTimeIntervalType() == 2){
					interval = engine.getTimeInterval() * 60 * 1000;
				}
				long value = curTimes - lasted;
				if (value >= interval) {// 检查时间间隔
					return true;
				} else {
					return false;
				}
			}
		}
	}

}
