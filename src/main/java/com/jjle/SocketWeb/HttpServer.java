package com.jjle.SocketWeb;


import java.io.*;
import java.net.Socket;

public class HttpServer extends  Thread {
    //存储盘
    public static final String ROOT = "d:/";
    private InputStream input;
    private OutputStream out;
    public HttpServer(Socket socket) {
        try {
            input = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取文件的位置
    private String read() {
        //以流的形式读取路劲
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            // 读取请求头
            String readLine = reader.readLine();
            String[] split = readLine.split(" ");
            //System.out.println(readLine);
            return split[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void response(String filePath) {
        File file = new File(ROOT + filePath);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
                //给浏览器发送请求头
                StringBuffer result = new StringBuffer();
                result.append("HTTP/1.1 200 ok \r\n");
                result.append("Content-Type:text/html \r\n");
                result.append("Content-Length:" + file.length() + "\r\n");
                result.append("\r\n:" + sb.toString());
                //
                out.write(result.toString().getBytes());
                out.flush();
                out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
            //2.资源不存在
                StringBuffer error = new StringBuffer();
                error.append("HTTP/1.1 400 file not found \r\n");
                error.append("Content-Type:text/html \r\n");
                error.append("Content-Length:20 \r\n").append("\r\n");
                error.append("<h1 >File Not Found..</h1>");
                try {
                    out.write(error.toString().getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                       e.printStackTrace();
                    }
            }
    }
    @Override
    public void run() {
        //获得index.html的名字
        String filePath = read();
        response(filePath);
    }

}
