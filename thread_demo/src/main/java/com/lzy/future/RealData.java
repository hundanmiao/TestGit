package com.lzy.future;

//真实realData数据
public class RealData extends Data {
	private String result;

	// 传入requestData参数Ï
	public RealData(String requestData) {
		System.out.println("正在使用requestData进行网络请求，requestData：" + requestData);
		try {
			//模拟调用，等待3秒钟
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("调用结束，返回结果。");
		// 获取返回结果
		this.result = "666";
	}

	@Override
	public String getRequset() {
		return result;
	}

}
