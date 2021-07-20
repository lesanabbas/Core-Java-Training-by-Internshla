package com.lesan.module3;

public class Pastry extends Cake {
	@Override
	public void display() {
		System.out.println(name+" : \u20B9"+price+" price per piece");
	}
}
