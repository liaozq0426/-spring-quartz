package com.gavin.quartz;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title quartz cron 表达式配置bean
 * @author gavin
 * @date 2019年5月10日
 */
@Component
@ConfigurationProperties(prefix="quartz.expression")
public class QuartzCronKey {
	
	private String print;

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}
}
