package com.lzy.demo1;

public class Thread004 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("子线程输出：" + i);
				}
			}
		});
		thread.setDaemon(true);// 将该线程设置为守护线程，和主线程一起销毁
		thread.start();
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("主线程输出：" + i);
		}
		System.out.println("主线程结束。。。。。。");
	}

}
