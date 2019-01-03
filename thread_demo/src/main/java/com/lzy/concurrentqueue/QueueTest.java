package com.lzy.concurrentqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

//使用并发队列实现生产者与消费者
//生产者 添加队列
class ProducerThread implements Runnable {
	private BlockingQueue<String> blockingQueue;
	private volatile Boolean flag = true;
	// 保证线程安全的计数器
	private AtomicInteger atomicInterger = new AtomicInteger();

	public ProducerThread(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {
		System.out.println("生产者线程已经启动。。。");
		while (flag) {
			String data = atomicInterger.incrementAndGet() + "";// 类似于 i++ 计数作用
			try {
				Boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
				if (!offer) {
					System.out.println("生产者线程存入队列失败，data：" + data);
				} else {
					System.out.println("生产者线程存入队列成功，data：" + data);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("生产者线程已经结束。。。");
	}

	public void stop() {
		this.flag = false;
	}

}

//消费者 取出队列
class ConsumerThread implements Runnable {
	private BlockingQueue<String> blockingQueue;
	private volatile Boolean flag = true;

	public ConsumerThread(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {
		System.out.println("消费者线程已经启动。。。");
		try {
			while (flag) {
				String data = blockingQueue.poll(2, TimeUnit.SECONDS);
				if (StringUtils.isEmpty(data)) {
					System.out.println("超过2秒钟没有取得队列信息。");
					flag = false;
					return;
				}
				System.out.println("消费者取得数据data：" + data);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("消费者线程已经停止");
		}

	}

}

public class QueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new LinkedBlockingQueue(3);
		ProducerThread p1 = new ProducerThread(blockingQueue);
		ConsumerThread c1 = new ConsumerThread(blockingQueue);
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(c1);
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000 * 10);
			// 线程运行10秒钟后停止
			p1.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
