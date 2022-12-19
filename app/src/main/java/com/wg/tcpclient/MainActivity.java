package com.wg.tcpclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TCPClientThread tcpClientThread = new TCPClientThread();
        new Thread(tcpClientThread).start();
    }

    Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    class TCPClientThread implements Runnable {

        @Override
        public void run() {
            TCPClient();
        }
    }

    private void TCPClient(){
        try {
            //创建客户端Socket，指定服务器的IP地址和端口
            Socket socket = new Socket("192.168.3.50",8888);
            //获取输出流，向服务器发送数据
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("客户端给服务器端发送的数据");
            pw.flush();
            //关闭输出流
            socket.shutdownOutput();

            //获取输入流，接收服务器发来的数据
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            //读取客户端数据
            while((data = br.readLine()) != null){
                System.out.println("客户端接收到服务器回应的数据：" + data);
            }
            //关闭输入流
            socket.shutdownInput();

            //关闭资源
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}