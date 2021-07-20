package com.lesan.module3;

public class Cake {
	public String name;
	public float price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void display() {
		System.out.println(name+" : \u20B9"+price+" price per pound");
	}
}
