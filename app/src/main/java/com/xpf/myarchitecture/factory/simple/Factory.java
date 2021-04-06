package com.xpf.myarchitecture.factory.simple;

/**
 * Created by x-sir on 4/6/21 :)
 * Function:简单工厂
 */
public class Factory {

	public static Api create(int type){
		switch (type) {
		case 1:
			return new ImplA();
		case 2:
			return new ImplB();
		case 3:
			return new ImplC();	
		default:
			return new ImplC();
		}
	}
	
}
