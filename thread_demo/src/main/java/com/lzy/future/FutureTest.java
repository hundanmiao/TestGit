package com.lzy.future;

public class FutureTest {

	public static void main(String[] args) {
		FutureClient futureClient = new FutureClient();
		Data request = futureClient.request("77777777");
		System.out.println("主线程数据发送成功");
		System.out.println("主线程执行其他任务。。。");
		String requset = request.getRequset();
		System.out.println("主线程获取requset：" + requset);
	}

}
