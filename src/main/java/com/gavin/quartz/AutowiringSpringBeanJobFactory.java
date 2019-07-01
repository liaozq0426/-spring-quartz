package com.gavin.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @title AutowiringSpringBeanJobFactory类，这个类用来Quartz中的job自动注入spring容器托管的对象
 * @author gavin
 * @date 2019年5月10日
 */
public final class AutowiringSpringBeanJobFactory 
	extends SpringBeanJobFactory 
	implements ApplicationContextAware{
	
	private transient AutowireCapableBeanFactory beanFactory;
	
	@Override
	public void setApplicationContext(final ApplicationContext context) {
		beanFactory = context.getAutowireCapableBeanFactory();
	}

	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job);
		return job;
	}
	
}
