package com.xpf.myarchitecture.factory.first;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:文本文件
 */
public abstract class ExportOperate {

    /**
     * 实例化ExportFileApi
     *
     * @return
     */
    public abstract ExportFileApi newFileApi();

    /**
     * 导出数据
     *
     * @param data
     */
    public void export(String data) {
        ExportFileApi file = newFileApi();
        file.export(data);
    }

}
