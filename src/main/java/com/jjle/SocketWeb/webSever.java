package com.jjle.SocketWeb;

import java.net.ServerSocket;
import java.net.Socket;

public class webSever {
    public  void startServer(int port){
        try {
            ServerSocket serverSocket =new ServerSocket(port);
            while(true){
                Socket socket=serverSocket.accept();
                new  HttpServer(socket).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
