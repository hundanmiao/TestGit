package com.lzy.demo2;

class Thread004 extends Thread {
	public volatile boolean flag = true;

	@Override
	public void run() {
		System.out.println("子线程开始执行。。。");
		while (flag) {

		}
		System.out.println("子线程结束执行。。。");
	}

	// 修改flag的值
	public void setFlag(boolean flag) {
		this.flag = false;
	}
}

public class Test004 {
	public static void main(String[] args) throws InterruptedException {
		Thread004 thread004 = new Thread004();
		thread004.start();
		thread004.sleep(3000);
		// 主线程将flag的值改为false，所以正常情况，子线程应该会停止
		thread004.setFlag(false);
		System.out.println("将flag的值设为false");
		thread004.sleep(2000);
		System.out.println("flag:" + " " + thread004.flag);
	}
}
