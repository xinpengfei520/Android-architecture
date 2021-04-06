package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:导出数据库文件
 */
public class ExportDBOperator extends ExportOperate{

	@Override
	public ExportFileApi newFileApi() {
		return new ExportDBFile();
	}

}
