package com.xdtech.platform.util;



public class TimerTaskRegister {
	
	public static void registTask() {
		QuartzManager.addJob("com.xdtech.platform.crawler.task.OneLevelUrlGenerateTask", "com.xdtech.platform.crawler.task.OneLevelUrlGenerateTask", "0 0-59 * * * ?", null);
	}
}
