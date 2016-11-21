package com.git.message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/*
 * 0.缺陷：
 *     1.这里的ExecutorService本身就可以当队列使用，所以这里的BlockingQueue是多余的
 *     2.这里思路是有问题的，对于Executors.newFixedThreadPool(4);时就创建4个线程路，后面每有一个消息的时候，就应该创建一个线程对象提交到线程池中，就好像是一个车一样
 *     3.详情可以看g20_thread_ftp这个项目
 * 1.在服务器启动的时候条用init()
 * 2.在服务器关闭的时候调用destroy()
 * 3.blockingQueue为阻塞队列，队列为空的时候，blockingQueue.take()会堵塞
 * 4.Executors.newFixedThreadPool(4)会自动创建4个线程路，通过
 */
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
		}
	}
	//回收线程
	public static void destroy(){
		executor.shutdownNow();
	}
}
