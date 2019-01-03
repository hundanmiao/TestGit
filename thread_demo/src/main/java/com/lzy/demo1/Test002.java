package com.lzy.demo1;

/**
 * 现… 在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * 
 * @author liuzhiyong
 *
 */
public class Test002 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		final Thread t1 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程t1：" + i);
				}

			}
		});
		final Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程t2：" + i);
				}

			}
		});
		Thread t3 = new Thread(new Runnable() {

			public void run() {
				try {
					t2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程t3：" + i);
				}

			}
		});

		t1.start();
		
		t2.start();
		
		t3.start();
		System.out.println("主线程结束。。。。。。");
	}

}
