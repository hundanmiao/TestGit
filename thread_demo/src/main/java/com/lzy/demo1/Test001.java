package com.lzy.demo1;

//继承thread类，重写run方法，run方法中，需要线程执行代码
class threadDemo extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("子线程输出：" + i);
		}
	}
}

//1.什么是线程，线程是一条执行路径，每个线程之间互不影响
//2.什么是多线程，在一个进程中，有多个不同的执行路径，并行执行，目的为了提高程序效率
//3.在一个进程中，一定会有主线程。如果连主线程都没有，程序怎么执行。程序的入口在哪里。
//线程分类	用户线程、守护线程
//主线程、子线程、GC线程
public class Test001 {

	public static void main(String[] args) {
		System.out.println("主线程开始。。。。。。");
		threadDemo threadDemo = new threadDemo();
		threadDemo.start();
		for (int j = 0; j < 10; j++) {
			System.out.println("主线程输出：" + j);
		}
		System.out.println("主线程结束。。。。。。");
	}

}
