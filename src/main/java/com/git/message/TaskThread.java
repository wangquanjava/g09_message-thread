package com.git.message;

import org.apache.log4j.Logger;

public class TaskThread extends Thread {
	private static Logger logger = Logger.getLogger(TaskThread.class);
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		//每次只有在没有中断的情况下才进行循环
		while (!Thread.interrupted()) {
			try {
				String piId = MessageUtil.blockingQueue.take();
				System.out.println(piId);
			} catch (InterruptedException e) {
				//当手动执行了shutdownNow()的时候，就会出现InterruptedException中断异常，这里进行置位
				Thread.currentThread().interrupt();
				logger.warn(this.currentThread().getName()+"发送短信线程手动终止");
			}
		}
		logger.warn(this.currentThread().getName()+"发送短信线程结束成功");
	}
}
