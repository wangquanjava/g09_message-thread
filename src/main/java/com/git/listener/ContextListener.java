package com.git.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.git.message.MessageUtil;

public class ContextListener implements ServletContextListener{
	public static final Logger logger = Logger.getLogger(ContextListener.class);
	
	public void contextInitialized(ServletContextEvent sce) {
		MessageUtil.init();
		logger.error("启动服务器");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		MessageUtil.shutdownMessageThread();
		logger.error("关闭服务器");
	}

}
