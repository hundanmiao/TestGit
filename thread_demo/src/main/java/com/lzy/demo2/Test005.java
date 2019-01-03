package com.lzy.demo2;

//生产者与消费者模式
class Rose {
	public String name;
	public String sex;
	//falg控制读写线程
	//flag为ture情况下，允许读，不能写
	//flag为false情况下，允许写，不能读
	public boolean flag = false;
}

//生产者线程
class InputThread extends Thread {
	public Rose rose;

	public InputThread(Rose rose) {
		this.rose = rose;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (rose) {
				try {
					if(rose.flag) {
						rose.wait();//等待并且释放锁资源，处于阻塞的状态
					}
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (count == 0) {
					rose.name = "茜西";
					rose.sex = "女";
				} else {
					rose.name = "杰克";
					rose.sex = "男";
				}
				count = (count + 1) % 2;
				rose.flag = true;//标记当前线程为等待
				rose.notify();//唤醒被等待的线程
			}

		}
	}
}

//消费者线程
class OutputThread extends Thread {
	public Rose rose;

	public OutputThread(Rose rose) {
		this.rose = rose;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (rose) {
				try {
					if(!rose.flag) {
						rose.wait();//等待并且释放锁资源，处于阻塞的状态
					}
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(rose.name + "," + rose.sex);
				rose.flag = false;
				rose.notify();
			}
		}
	}
}

public class Test005 {
	public static void main(String[] args) {
		Rose rose = new Rose();
		InputThread inputThread = new InputThread(rose);
		OutputThread outputThread = new OutputThread(rose);
		inputThread.start();
		outputThread.start();
	}
}
