package com.lzy.threadpooldemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolTest {

	public static void main(String[] args) {
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// 可执行线程，excute方法表示启动线程
			newScheduledThreadPool.schedule(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "," + temp);
				}
			}, 5, TimeUnit.SECONDS);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
