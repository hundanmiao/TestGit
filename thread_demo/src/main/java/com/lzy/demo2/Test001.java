package com.lzy.demo2;

//抢票问题，演示线程安全问题
class Thread001 implements Runnable {
	// 多窗口共享100张票
	//静态变量存放在永久区中，多个线程可以共享，也就是可以共享全局变量
	private volatile static int ticket = 100;
	private volatile static Object obj = new Object();
	public boolean flag = true;

	public void run() {
		if(flag) {
			synchronized (obj) {
				while (ticket > 0) {
					try {
						Thread.sleep(40);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 线程运行调用售票方法
					if (ticket > 0) {
						System.out.println(Thread.currentThread().getName() + "出售第" + (100 - ticket + 1) + "票");
						ticket--;
					}
				}
			}
		}else {
			while(ticket > 0) {
				// 线程运行调用售票方法
				sale();
			}
		}
	}

	//同步方法Ï
	public synchronized void sale() {
		if (ticket > 0) {
			System.out.println(Thread.currentThread().getName() + "出售第" + (100 - ticket + 1) + "票");
			ticket--;
		}
	}
	 
	//同步代码块
	/*
	public void sale() {
		synchronized(obj) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "出售第" + (100 - ticket + 1) + "票");
				ticket--;
			}
		}
	}*/
	
}

public class Test001 {
	public static void main(String[] args) throws InterruptedException {
		Thread001 thread001 = new Thread001();
//		Thread001 thread002 = new Thread001();
		Thread t1 = new Thread(thread001, "窗口1");
		Thread t2 = new Thread(thread001, "窗口2");
		t1.start();
		Thread.sleep(60);
		thread001.flag = false;
		t2.start();
	}
}
