package com.xpf.myarchitecture.factory.second;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:具体的创建器
 */
public class ConcreteCreator extends Creator {

    @Override
    protected Product factoryMethod() {
        return new ConcreteProduct();
    }

}
