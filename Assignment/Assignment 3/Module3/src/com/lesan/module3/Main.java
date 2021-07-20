package com.lesan.module3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("             Today's Special Menu   ");
        System.out.println("--------------------------------------------------");
        System.out.println("Special Cakes");
        System.out.println("---------------------------------------------");
        Cake c1 = new Cake();
        c1.setName("Chocolate Brownie");
        c1.setPrice(250.0f);
        Cake c2 = new Cake();
        c2.setName("Chocolate Maple");
        c2.setPrice(300.0f);
        ArrayList<Cake> cakes = new ArrayList<>();
        cakes.add(c1);
        cakes.add(c2);
        for(Cake c: cakes) {
            c.display();
        }



        System.out.println("Special Pastries");
        System.out.println("---------------------------------------------");
        Pastry p1 = new Pastry();
        p1.setName("Black Forest");
        p1.setPrice(35.0f);
        Pastry p2 = new Pastry();
        p2.setName("Choco Truffle");
        p2.setPrice(40.0f);
        ArrayList<Pastry> pastries = new ArrayList<>();
        pastries.add(p1);
        pastries.add(p2);
        for(Pastry p: pastries) {
            p.display();
        }
    }
}






