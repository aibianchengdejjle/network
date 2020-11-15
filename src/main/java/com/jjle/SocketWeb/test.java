package com.jjle.SocketWeb;


import org.junit.jupiter.api.Test;

public class test {
    @Test
    public  void test(){
        //给线程传入端口号
        new webSever().startServer(8000);
    }
}
