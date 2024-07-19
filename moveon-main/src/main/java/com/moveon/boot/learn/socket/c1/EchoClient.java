package com.moveon.boot.learn.socket.c1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName EchoClient
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/18 15:27
 * @Version 1.0
 */
public class EchoClient {

    private final Socket mSocket;

    public EchoClient(String host, int port) throws IOException {
        mSocket = new Socket(host, port);
    }

    public void run() throws IOException{
        Thread thread = new Thread(this::readResponse);
        thread.start();
        OutputStream out = mSocket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = System.in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
        }
    }

    private void readResponse() {
        try {
            InputStream in = mSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int n;
            while ((n = in.read(buffer)) > 0) {
                System.out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            EchoClient client = new EchoClient("localhost", 9877);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
