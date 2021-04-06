package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:文本文件
 */
public class ExportTextFile implements ExportFileApi {

	@Override
	public void export(String data) {
		System.out.println("导出数据到文本文件...");
	}

}
