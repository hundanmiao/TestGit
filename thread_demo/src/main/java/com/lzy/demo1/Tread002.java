package com.lzy.demo1;

class ThreadDemo2 implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("子线程输出：" + i);
		}
	}

}

//实现Runnable接口，重写run方法
public class Tread002 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		// 创建线程
		ThreadDemo2 threadDemo2 = new ThreadDemo2();
		Thread thread = new Thread(threadDemo2);
		// 启动线程
		thread.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("主线程输出：" + i);
		}
		System.out.println("主线程结束。。。。。。");
	}

}
