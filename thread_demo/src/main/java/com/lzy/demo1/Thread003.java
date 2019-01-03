package com.lzy.demo1;

//通过匿名内部类方式创建线程
//且主线程停止，不会影响子线程（除非把主线程设置为守护线程）
public class Thread003 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("子线程输出：" + i);
				}

			}
		});
		thread.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("主线程输出：" + i);
		}
		System.out.println("主线程结束。。。。。。");
	}

}
