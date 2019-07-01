package com.gavin.quartz.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution // 防止当任务执行时间（假如5秒）长于任务设定的间隔时间（假如3秒），本次任务还未执行完毕又开始下一次任务调度。
public class PrintJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date currentTime = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("打印当前时间:" + format.format(currentTime));
	}
}
