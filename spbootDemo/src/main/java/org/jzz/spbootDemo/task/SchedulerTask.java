package org.jzz.spbootDemo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SchedulerTask {
	
	private static int count = 0;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	/*
	 * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
	 * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
	 * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
	 */
	@Scheduled(fixedRate = 6000)
	private void reportCurrentTime() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
	
	/* 
	 * 第二种定时的设置, cron表达式
	 */
	@Scheduled(cron="*/6 * * * * ?")
	private void count() {
		System.out.println("this is scheduler task runing  "+(count++));
	}
			
}
