package com.jjle.UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Jerry1 implements Runnable {
    public static void main(String[] args) throws IOException {
    }
    public void run() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(9000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            //接收客户端A的信息
            byte[] recbuf = new byte[1024];
            DatagramPacket recDp = new DatagramPacket(recbuf, recbuf.length);
            try {
                ds.receive(recDp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String recMsg = new String(recbuf, 0, recDp.getLength());
            System.out.println("tom说" + recMsg);
            //发送信息到客户端A
            System.out.println("Jerry请您输入");
            Scanner sc = new Scanner(System.in);
            String msg = sc.next();
            System.out.println("开始发送数据：" + msg);
            InetAddress toIp = null;
            try {
                toIp = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            byte[] buf = msg.getBytes();
            // 构造数据报包，用来将长度为 length 偏移量为 offset 的包发送到指定主机上的指定端口号。
            DatagramPacket dp = new DatagramPacket(buf, buf.length, toIp, 8000);
            //发送数据报
            try {
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}