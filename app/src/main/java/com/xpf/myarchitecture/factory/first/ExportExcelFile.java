package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:文本文件
 */
public class ExportExcelFile implements ExportFileApi {

	@Override
	public void export(String data) {
		System.out.println("Excel表格");
	}

}
