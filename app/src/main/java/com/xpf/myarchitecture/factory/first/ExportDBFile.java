package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:数据库文件
 */
public class ExportDBFile implements ExportFileApi{

	@Override
	public void export(String data) {
		System.out.println("导出数据到数据库文件...");
	}

}
