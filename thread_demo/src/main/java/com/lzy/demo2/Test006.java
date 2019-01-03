package com.lzy.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//生产者与消费者模式
class Rose02 {
	public String name;
	public String sex;
	// falg控制读写线程
	// flag为ture情况下，允许读，不能写
	// flag为false情况下，允许写，不能读
	public boolean flag = false;
	// 使用lock锁代替synchronized
	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
}

//生产者线程
class InputThread02 extends Thread {
	public Rose02 rose02;

	public InputThread02(Rose02 rose02) {
		this.rose02 = rose02;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
//			synchronized (rose) {
			try {
				// 上锁
				rose02.lock.lock();
				if (rose02.flag) {
					// 等待并且释放锁资源，处于阻塞的状态
					// rose02.wait();
					// 类似于rose02.wait();
					rose02.condition.await();
				}
				Thread.sleep(500);
				if (count == 0) {
					rose02.name = "茜西";
					rose02.sex = "女";
				} else {
					rose02.name = "杰克";
					rose02.sex = "男";
				}
				count = (count + 1) % 2;
				// 标记当前线程为等待
				rose02.flag = true;
				// 唤醒被等待的线程
//					rose02.notify();
				// res.Condition.Signal() 类似notify
				rose02.condition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放锁
				rose02.lock.unlock();
			}

//			}

		}
	}
}

//消费者线程
class OutputThread02 extends Thread {
	public Rose02 rose02;

	public OutputThread02(Rose02 rose02) {
		this.rose02 = rose02;
	}

	@Override
	public void run() {
		while (true) {
//			synchronized (rose) {
			try {
				rose02.lock.lock();
				if (!rose02.flag) {
					// 等待并且释放锁资源，处于阻塞的状态
//					rose.wait();
					rose02.condition.await();
				}
				Thread.sleep(500);
				System.out.println(rose02.name + "," + rose02.sex);
				rose02.flag = false;
//				rose02.notify();
				rose02.condition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rose02.lock.unlock();
			}

//			}
		}
	}
}

public class Test006 {

	public static void main(String[] args) {
		Rose02 rose02 = new Rose02();
		InputThread02 inputThread02 = new InputThread02(rose02);
		OutputThread02 outputThread02 = new OutputThread02(rose02);
		inputThread02.start();
		outputThread02.start();
	}

}
