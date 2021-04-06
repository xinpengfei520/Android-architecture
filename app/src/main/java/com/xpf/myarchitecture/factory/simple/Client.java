package com.xpf.myarchitecture.factory.simple;

public class Client {

	public static void main(String[] args) {
		
		Api obj = Factory.create(2);
		
		obj = Factory.create(3);
		
	}

}
