package com.lzy.demo2;

class Res {
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		// 初始化threadLocal的值
		protected Integer initialValue() {
			return 0;
		}
	};
	public Integer getNum() {
		int cout = threadLocal.get() + 1;
		threadLocal.set(cout);
		return cout;
	}
}

public class Test003 extends Thread {
	private Res res;

	public Test003(Res res) {
		this.res = res;
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "," + res.getNum());
		}
	}
	public static void main(String[] args) {
		Res res2 = new Res();
		Test003 t1 = new Test003(res2);
		Test003 t2 = new Test003(res2);
		t1.start();
		t2.start();
	}
}
