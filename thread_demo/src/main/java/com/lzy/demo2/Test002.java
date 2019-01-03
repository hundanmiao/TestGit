package com.lzy.demo2;

//抢票问题，演示线程安全问题
class Thread002 implements Runnable {
	// 多窗口共享100张票
	// 静态变量存放在永久区中，多个线程可以共享，也就是可以共享全局变量
	private static int ticket = 100;
	private static Object obj = new Object();
	public boolean flag = true;

	public void run() {
		if (flag) {
			//t1线程先要拿到obj锁，然后调用sale()非静态同步方法取得this锁
			synchronized (obj) {
				while (ticket > 0) {
					try {
						Thread.sleep(20);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 线程运行调用售票方法
					sale();
				}
			}
		} else {
			//t2线程调用sale()非静态同步方法先取得this锁然后取得obj锁
			while (ticket > 0) {
				// 线程运行调用售票方法
				sale();
			}
		}
	}

	// 同步方法Ï
	/*
	 * private synchronized void sale() { if (ticket > 0) {
	 * System.out.println(Thread.currentThread().getName() + "出售第" + (100 - ticket +
	 * 1) + "票"); ticket--; } }
	 */
	// 非静态同步方法
	private synchronized void sale() {
		synchronized (obj) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "出售第" + (100 - ticket + 1) + "票");
				ticket--;
			}
		}
	}

}

public class Test002 {
	public static void main(String[] args) throws InterruptedException {
		Thread002 thread002 = new Thread002();
		Thread t1 = new Thread(thread002, "窗口1");
		Thread t2 = new Thread(thread002, "窗口2");
		t1.start();
		Thread.sleep(40);
		thread002.flag = false;
		t2.start();
	}
}
