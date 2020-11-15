package com.jjle.UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Tom1 implements Runnable{
    public static void main(String[] args){
    }
    public void run() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(8000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(true) {
            System.out.println("tom请您输入");
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            //发送信息到客户端B
            System.out.println("tom开始发送数据："+msg);
            byte[] buf = msg.getBytes();

            InetAddress toIp = null;
            try {
                toIp = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            // 构造数据报包，用来将长度为 length 偏移量为 offset 的包发送到指定主机上的指定端口号。
            DatagramPacket dp = new DatagramPacket(buf,buf.length,toIp,9000);
            //用数据报包的套接字,通过8000发送数据报
            try {
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //接收客户端B的信息
            byte[] recBuf = new byte[1024];
            DatagramPacket recDp = new DatagramPacket(recBuf,recBuf.length);
            //信息接收
            try {
                ds.receive(recDp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String recMsg = new String(recBuf,0,recDp.getLength());
            System.out.println("Jerry说:"+recMsg);
        }
    }
}