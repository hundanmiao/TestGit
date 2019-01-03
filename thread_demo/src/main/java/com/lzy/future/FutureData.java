package com.lzy.future;

//当线程想要realData的时候，程序会被阻塞，等到realData被注入才会使用getReal()方法
public class FutureData extends Data {
	private RealData realData;
	// 读取结果
	private boolean FLAG = false;

	// 读取data数据
	public synchronized void setReadData(RealData realData) {
		// 如果已经获取到结果，直接返回
		if (FLAG) {
			return;
		}
		//如果FLAG为false，表明没有获取到结果，传递realData对象
		this.realData = realData;
		FLAG = true;
		//唤醒等待线程
		notify();
	}

	@Override
	public synchronized String getRequset() {
		//如果FLAG为false，就阻塞等待，执行完毕，获取结果
		while(!FLAG) {
			try {
				//wait与notify必须用在synchronized中
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getRequset();
	}

}
