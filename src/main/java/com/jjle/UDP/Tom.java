package com.jjle.UDP;

public class Tom {
    public static  void main(String []args) {
       new  Thread(new Tom1()).start();
       new  Thread(new Jerry1()).start();
    }
}
