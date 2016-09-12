package com.git.message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class MessageUtil {
	public static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
	private static ExecutorService executor = Executors.newFixedThreadPool(4);
	public static final Logger logger = Logger.getLogger(MessageUtil.class);
	
	//可以直接调用，一行代码
	public static void sendMessage(String string) {
		try {
			blockingQueue.put(string);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//初始化发送短信线程
	public static void init(){
		for (int i = 0; i < 3; i++) {
			TaskThread taskThread = new TaskThread();
			taskThread.setName("SendMessage"+i);
			executor.submit(taskThread);
			logger.warn(taskThread+"发送短信线程启动");
		}
	}
	//回收线程
	public static void shutdownMessageThread(){
		while (!blockingQueue.isEmpty()) {
			try {
				Thread.sleep(500l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executor.shutdownNow();
	}
}
