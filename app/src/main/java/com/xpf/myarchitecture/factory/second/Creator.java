package com.xpf.myarchitecture.factory.second;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:创建器
 */
public abstract class Creator {

    /**
     * 创建 Product 的工厂方法
     *
     * @return
     */
    protected abstract Product factoryMethod();

    /**
     * 示意，会使用产品的某些功能
     */
    public void someOperation() {
        Product p = factoryMethod();
        //使用产品
        //p.
    }


}
