package com.lzy.future;

public class FutureClient{
	public Data request(String requestData) {
		final FutureData futureData = new FutureData();
		new Thread(new Runnable() {
			
			// 阻塞
			public void run() {
				RealData realData = new RealData("888888888");
				futureData.setReadData(realData);
			}
		}).start();
		return futureData;
	}
}
