package com.lzy.demo1;

public class Thread005 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("子线程输出：" + i);
				}
				System.out.println(Thread.currentThread().getName());

			}
		});
		thread.start();
		try {
			thread.join(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("主线程输出：" + i);
		}
		System.out.println("主线程结束。。。。。。");
	}

}
