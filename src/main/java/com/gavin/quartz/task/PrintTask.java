package com.gavin.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.gavin.quartz.QuartzCronKey;
import com.gavin.quartz.job.PrintJob;

@Configuration
public class PrintTask {

	@Autowired
	private QuartzCronKey quartzCronKey;
	
	
	@Bean(name="printFactory")
	public JobDetailFactoryBean jobDetailFactoryBean() {
		// 生成jobDetail
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		// 设置对应的Job类型
		factory.setJobClass(PrintJob.class);
		return factory;
	}
	
	
	@Bean("printTrigger")
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
		// 设置jobDetail
		factory.setJobDetail(jobDetailFactoryBean().getObject());
		factory.setStartDelay(1000);
		// 定时任务cron表达式配置
		factory.setCronExpression(quartzCronKey.getPrint());
		return factory;
	}
}
