package com.lzy.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolTest {

	public static void main(String[] args) {
		// 可固定长度线程
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			// 可执行线程，excute方法表示启动线程
			newFixedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "," + temp);
				}
			});
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
