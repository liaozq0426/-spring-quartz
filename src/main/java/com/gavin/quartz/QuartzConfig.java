package com.gavin.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

	@Autowired
	@Qualifier("printTrigger")
	private CronTriggerFactoryBean printTrigger;
	
	/**
	 * @title Quartz中的job自动注入spring容器托管的对象
	 * @author gavin
	 * @date 2019年5月10日
	 * @return
	 */
	@Bean
	public AutowiringSpringBeanJobFactory autoWiringSpringBeanJobFactory() {
		return new AutowiringSpringBeanJobFactory();
	}
	
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws Exception{
		SchedulerFactoryBean scheduler  = new SchedulerFactoryBean();
		// 配置Spring注入的Job类 
		scheduler.setJobFactory(autoWiringSpringBeanJobFactory());
		// 配置定时器,参数接收Trigger数组，可接收多个trigger
		scheduler.setTriggers(
			printTrigger.getObject()
		);
		scheduler.setAutoStartup(true);
		return scheduler;
	}
	
}
