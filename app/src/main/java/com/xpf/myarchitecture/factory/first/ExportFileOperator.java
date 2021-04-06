package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:文本文件
 */
public class ExportFileOperator extends ExportOperate {

    @Override
    public ExportFileApi newFileApi() {
        return new ExportTextFile();
    }

}
