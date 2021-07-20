package com.lesan.javaapp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tax Calculator App");
        System.out.println("-----Welcome-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total person count: ");
        int count = sc.nextInt();
        String[] name = new String[count];
        Long[] income = new Long[count];

        for (int i = 0;i<count;i++){
            System.out.println("Enter name " + (i+1) +" :");
            name[i] = sc.next();
            System.out.println("Enter "+ name[i] + "'s Annual income: ");
            income[i] = sc.nextLong();
        }
        print();
        for (int i =0;i<count;i++){
            calculatorTax(name[i],income[i]);
        }
        sc.close();
    }
    public static void print() {
        System.out.println("Names with liable taxes");
        System.out.println("------------------------");
    }
    public static void calculatorTax(String name, Long income) {

        long tax;
        if (income >= 300000){
            tax  = (long)((income*20)/100);
        }else if (income >= 100000 && income<300000){
            tax = (long)((income*20)/100);
        }else{
            tax =  0L;
        }
        System.out.println(name +" : \u20B9"+tax);
    }
}
