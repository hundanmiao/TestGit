package com.lzy.definedthreadPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefinedThreadPoolTest {

	public static void main(String[] args) {
		// corePoolSize 核心线程数：实际运行线程数
		// maximumPoolSize 最大线程数：创建最大线程数
		// keepAliveTime 空闲线程超时时间（线程不用后，经过多久杀掉）
		// new LinkedBlockingQueue<Runnable>(3) 创建无界阻塞队列（底层是有界的，但是会扩容）
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(3));
		// 任务1 现在创建线程 在执行
		threadPoolExecutor.execute(new TaskThread("任务1"));
		// 任务2 用户线程数大于核心线程数，存放在缓存队列中。与任务1复用同一线程
		//new LinkedBlockingQueue<Runnable>(3) 阻塞缓存队列大小为3
		threadPoolExecutor.execute(new TaskThread("任务2"));
		// 任务3 用户线程数大于核心线程数，存放在缓存队列中。与任务1复用同一线程
		//new LinkedBlockingQueue<Runnable>(3) 阻塞缓存队列大小为3
		threadPoolExecutor.execute(new TaskThread("任务3"));
		// 任务4 用户线程数大于核心线程数，存放在缓存队列中。与任务1复用同一线程
		//new LinkedBlockingQueue<Runnable>(3) 阻塞缓存队列大小为3
		threadPoolExecutor.execute(new TaskThread("任务4"));
		//任务5 缓存队列满了，判断是否大于最大线程数2。小于，所以创建任务5的新线程（任务1+任务5总共为2个线程，最大创建线程数为2）
		threadPoolExecutor.execute(new TaskThread("任务5"));
		//任务6 因为最大创建线程数为2（任务1+任务5），缓存队列大小为3（任务2、3、4）
		//总共为5，现在已经满了，所以再创建任务6新的线程就会报错
		threadPoolExecutor.execute(new TaskThread("任务6"));
	}

}

class TaskThread implements Runnable {
	private String threadName;

	public TaskThread(String threadName) {
		this.threadName = threadName;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + "：" + threadName);
	}

}
